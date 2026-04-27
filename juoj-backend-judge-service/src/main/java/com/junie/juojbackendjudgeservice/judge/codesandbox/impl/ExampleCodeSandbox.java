package com.junie.juojbackendjudgeservice.judge.codesandbox.impl;

import com.junie.juojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.junie.juojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.junie.juojbackendmodel.model.codesandbox.ExecuteCodeResponse;
import com.junie.juojbackendmodel.model.codesandbox.JudgeInfo;
import com.junie.juojbackendmodel.model.enums.JudgeInfoMessageEnum;
import com.junie.juojbackendmodel.model.enums.QuestionSubmitStatusEnum;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 示例代码沙箱（仅为了跑通业务的代码沙箱）
 * @Author: juheng
 * @Create: 2026-04-16
 * @Version: 1.0
 */
@Component("example")
public class ExampleCodeSandbox implements CodeSandbox {


    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("执行测试成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setTime(1000L);
        judgeInfo.setMemory(1000L);
        executeCodeResponse.setJudgeInfo(judgeInfo);

        return executeCodeResponse;
    }
}
