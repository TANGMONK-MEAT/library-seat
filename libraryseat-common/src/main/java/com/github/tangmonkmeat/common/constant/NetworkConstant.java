package com.github.tangmonkmeat.common.constant;

/**
 * 网络相关常量
 *
 * @author zwl
 * @date 2020/9/8 21:47
 */
public class NetworkConstant {
    /**
     * 定义GB的计算常量
     */
    public static final int GB = 1024 * 1024 * 1024;
    /**
     * 定义MB的计算常量
     */
    public static final int MB = 1024 * 1024;
    /**
     * 定义KB的计算常量
     */
    public static final int KB = 1024;

    /**
     * 定义Redis缓存默认过期时间,单位秒
     */
    public static final int CACHE_TIMEOUT_SECONDS=1800;

    /**
     * 定义请求登录限制计数的时间段区间，需与REQUEST_LOGIN_LIMIT_COUNT同时使用
     */
    public static final int REQUEST_LOGIN_LIMIT_TIME=60;

    /**
     * 定义请求登录在时间段区间的限制次数，需与REQUEST_LOGIN_LIMIT_TIME同时使用
     */
    public static final int REQUEST_LOGIN_LIMIT_COUNT=5;

    /**
     * 定义unknown字串串的常量
     */
    public  static final String UNKNOWN = "unknown";
}
