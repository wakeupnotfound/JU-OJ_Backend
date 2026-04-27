package com.junie.juojbackendmodel.model.dto.question;

import lombok.Data;

/**
 * @Description: 题目配置
 * @Author: juheng
 * @Create: 2026-04-13
 * @Version: 1.0
 */
@Data
public class JudgeConfig {

    /**
     * 时间限制（ms）
     */
    private Long timeLimit;

    /**
     * 内存限制（kb）
     */
    private Long memoryLimit;

    /**
     * 堆栈限制（kb）
     */
    private Long stackLimit;
}
