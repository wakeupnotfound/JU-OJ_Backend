package com.junie.juojbackendjudgeservice.judge.strategy;

import com.junie.juojbackendmodel.model.codesandbox.JudgeInfo;
import com.junie.juojbackendmodel.model.dto.question.JudgeCase;
import com.junie.juojbackendmodel.model.entity.Question;
import com.junie.juojbackendmodel.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * @Description: 上下文（用于定义在策略中传递的参数）
 * @Author: juheng
 * @Create: 2026-04-16
 * @Version: 1.0
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;
}
