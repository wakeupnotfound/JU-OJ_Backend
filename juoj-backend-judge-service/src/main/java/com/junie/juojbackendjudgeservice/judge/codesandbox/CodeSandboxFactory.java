package com.junie.juojbackendjudgeservice.judge.codesandbox;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description: 代码沙箱工厂（根据字符串参数创建指定的代码沙箱实例）
 * @Author: juheng
 * @Create: 2026-04-16
 * @Version: 1.0
 */
@Component
public class CodeSandboxFactory {

    @Resource
    private ApplicationContext applicationContext;

    public CodeSandbox getInstance(CodeSandboxEnum codeSandboxEnum) {
        // 根据类型直接从 Spring 容器获取已经注入好 @Value 的 Bean
        return applicationContext.getBean(codeSandboxEnum.getValue(), CodeSandbox.class);
    }
}
