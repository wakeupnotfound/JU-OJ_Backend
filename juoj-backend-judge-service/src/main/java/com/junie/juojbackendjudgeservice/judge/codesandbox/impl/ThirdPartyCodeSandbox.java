package com.junie.juojbackendjudgeservice.judge.codesandbox.impl;

import com.junie.juojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.junie.juojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.junie.juojbackendmodel.model.codesandbox.ExecuteCodeResponse;
import org.springframework.stereotype.Component;

/**
 * @Description: 第三方代码沙箱（调用网上现成的代码沙箱）
 * @Author: juheng
 * @Create: 2026-04-16
 * @Version: 1.0
 */
@Component("thirdParty")
public class ThirdPartyCodeSandbox implements CodeSandbox {


    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        return null;
    }
}
