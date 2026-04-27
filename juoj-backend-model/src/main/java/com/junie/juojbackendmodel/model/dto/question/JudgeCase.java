package com.junie.juojbackendmodel.model.dto.question;

import lombok.Data;

/**
 * @Description: 判题用例
 * @Author: juheng
 * @Create: 2026-04-13
 * @Version: 1.0
 */
@Data
public class JudgeCase {

    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;
}
