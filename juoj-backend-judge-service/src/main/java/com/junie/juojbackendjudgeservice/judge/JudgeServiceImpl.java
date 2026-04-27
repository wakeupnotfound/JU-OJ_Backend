package com.junie.juojbackendjudgeservice.judge;

import cn.hutool.json.JSONUtil;
import com.junie.juojbackendcommon.common.ErrorCode;
import com.junie.juojbackendcommon.exception.BusinessException;
import com.junie.juojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.junie.juojbackendjudgeservice.judge.codesandbox.CodeSandboxEnum;
import com.junie.juojbackendjudgeservice.judge.codesandbox.CodeSandboxFactory;
import com.junie.juojbackendjudgeservice.judge.codesandbox.CodeSandboxProxy;
import com.junie.juojbackendjudgeservice.judge.strategy.JudgeContext;
import com.junie.juojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.junie.juojbackendmodel.model.codesandbox.ExecuteCodeResponse;
import com.junie.juojbackendmodel.model.codesandbox.JudgeInfo;
import com.junie.juojbackendmodel.model.dto.question.JudgeCase;
import com.junie.juojbackendmodel.model.entity.Question;
import com.junie.juojbackendmodel.model.entity.QuestionSubmit;
import com.junie.juojbackendmodel.model.enums.QuestionSubmitStatusEnum;
import com.junie.juojbackendserviceclient.service.QuestionFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description: 实现判题逻辑
 * @Author: juheng
 * @Create: 2026-04-16
 * @Version: 1.0
 */
@Service
public class JudgeServiceImpl implements JudgeService {

    @Value("${codesandbox.type:example}")
    private String type;

    @Resource
    private QuestionFeignClient questionFeignClient;

    @Resource
    private JudgeManager judgeManager;

    @Resource
    private CodeSandboxFactory codeSandboxFactory;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        // 1.传入题目的提交 id，获取到对应的题目、提交信息（包含代码、编程语言等）
        QuestionSubmit questionSubmit = questionFeignClient.getQuestionSubmitById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交信息不存在");
        }
        Question question = questionFeignClient.getQuestionById(questionSubmit.getQuestionId());
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目信息不存在");
        }

        // 2.如果题目提交状态不为等待中，就不用重复执行了
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在判题中");
        }

        // 3.更改判题（题目提交）的状态为“判题中”，防止重复执行，也能让用户即时看到状态
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新失败");
        }

        // 4.调用沙箱，获取到执行结果
        String code = questionSubmit.getCode();
        String language = questionSubmit.getLanguage();
        // 获取输入用例
        List<JudgeCase> judgeCaseList = JSONUtil.toList(question.getJudgeCase(), JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        CodeSandbox instance = codeSandboxFactory.getInstance(Objects.requireNonNull(CodeSandboxEnum.getEnumByValue(type)));
        instance = new CodeSandboxProxy(instance);
        ExecuteCodeRequest request = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse response = instance.executeCode(request);
        List<String> outputList = response.getOutputList();

        // 5.根据沙箱的执行结果，设置题目的判题状态和信息
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(response.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
        // 修改数据库中的判题结果
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新失败");
        }

        return questionFeignClient.getQuestionSubmitById(questionSubmitId);
    }
}
