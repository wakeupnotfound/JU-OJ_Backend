package com.junie.juojbackendjudgeservice.judge.codesandbox.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.junie.juojbackendcommon.common.ErrorCode;
import com.junie.juojbackendcommon.exception.BusinessException;
import com.junie.juojbackendcommon.utils.SignUtils;
import com.junie.juojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.junie.juojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.junie.juojbackendmodel.model.codesandbox.ExecuteCodeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: 远程代码沙箱（实际调用接口的沙箱）
 * @Author: juheng
 * @Create: 2026-04-16
 * @Version: 1.0
 */
@Component("remote")
public class RemoteCodeSandbox implements CodeSandbox {

    @Value("${codesandbox.url:http://localhost:8122/executeCode}")
    private String url;

    @Value("${codesandbox.ak:sandbox_client_001}")
    private String ak;

    @Value("${codesandbox.sk:my_sandbox_secret_888}")
    private String sk;

    /**
     * 定义鉴权请求头和密钥
     */
//    public static final String AUTH_REQUEST_HEADER = "Authorization";
//
//    public static final String AUTH_REQUEST_SECRET = "secretKey";

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        // 1. 准备配置信息（实际建议从配置文件读取）
//        String ak = "sandbox_client_001";
//        String sk = "my_sandbox_secret_888";
//        String url = "http://localhost:8122/executeCode";

        // 2. 生成动态参数
        String ts = String.valueOf(System.currentTimeMillis() / 1000);
        String nonce = RandomUtil.randomNumbers(6); // 建议使用 Hutool 的 RandomUtil 生成随机数
        String json = JSONUtil.toJsonStr(executeCodeRequest);

        // 3. 计算签名
        String sign = SignUtils.genSign(json, sk);

        // 4. 发起请求并携带签名 Header
        String responseStr = HttpUtil.createPost(url)
                .header("accessKey", ak)      // 身份标识
                .header("nonce", nonce)        // 随机数，防重放
                .header("timestamp", ts)      // 时间戳，防过期
                .header("sign", sign)         // 签名
//                .header(AUTH_REQUEST_HEADER, AUTH_REQUEST_SECRET)
                .body(json)
                .execute()
                .body();

        // 5. 结果处理
        if (StrUtil.isBlank(responseStr)) {
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR, "沙箱响应为空");
        }

        return JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);
    }
}
