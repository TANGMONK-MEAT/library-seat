package com.github.tangmonkmeat.common.constant;

/**
 * @author zwl
 * @date 2020/12/1 22:38
 */
public class JwtConstant {

    /**
     * 令牌请求头, 'authorization': token
     *
     */
    public static final String AUTH_HEADER_KEY = "authorization";

    /**
     * 令牌过期时间6小时
     */
    public static final long EXPIRE_TIME = 6*60*60*1000;

    /**
     * token 负载信息key,用户唯一标识，用户名
     *
     */
    public static final String WITH_CLAIM_KEY = "username";

    /**
     * 响应体 携带的令牌的key
     *
     */
    public static final String RESPONSE_AUTH_KEY = "token";

}
