package com.junie.juojbackendcommon.utils;

import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;

/**
 * @Description: 生成签名
 * @Author: juheng
 * @Create: 2026-04-22
 * @Version: 1.0
 */
public class SignUtils {
    /**
     * 生成签名
     * @param body 请求体内容
     * @param secretKey 密钥
     * @return 签名字符串
     */
    public static String genSign(String body, String secretKey) {
        // 使用 HMAC-SHA256 算法，更加安全
        HMac hmac = new HMac(HmacAlgorithm.HmacSHA256, secretKey.getBytes());
        return hmac.digestHex(body);
    }
}
