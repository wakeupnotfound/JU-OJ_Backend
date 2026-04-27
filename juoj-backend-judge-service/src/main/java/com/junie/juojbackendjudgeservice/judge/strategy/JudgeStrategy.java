package com.junie.juojbackendjudgeservice.judge.strategy;

import com.junie.juojbackendmodel.model.codesandbox.JudgeInfo;

public interface JudgeStrategy {

    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
