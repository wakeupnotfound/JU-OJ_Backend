package com.junie.juojbackendjudgeservice.judge.codesandbox;


import com.junie.juojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.junie.juojbackendmodel.model.codesandbox.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSandbox {

    /**
     * 执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
