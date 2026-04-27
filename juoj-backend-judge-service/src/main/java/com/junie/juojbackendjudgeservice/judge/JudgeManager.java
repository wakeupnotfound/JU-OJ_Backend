package com.junie.juojbackendjudgeservice.judge;

import com.junie.juojbackendjudgeservice.judge.strategy.DefaultJudgeStrategyImpl;
import com.junie.juojbackendjudgeservice.judge.strategy.JavaLanguageJudgeStrategyImpl;
import com.junie.juojbackendjudgeservice.judge.strategy.JudgeContext;
import com.junie.juojbackendjudgeservice.judge.strategy.JudgeStrategy;
import com.junie.juojbackendmodel.model.codesandbox.JudgeInfo;
import com.junie.juojbackendmodel.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * @Description: 判题管理，尽量简化对判题功能的调用
 * @Author: juheng
 * @Create: 2026-04-16
 * @Version: 1.0
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategyImpl();
        if (language.equals("java")) {
            judgeStrategy = new JavaLanguageJudgeStrategyImpl();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
