package com.github.tangmonkmeat.common.constant;

/**
 * Redis 常量
 *
 * @author zwl
 * @date 2020/12/2 11:20
 */
public class RedisConstant {

    /**
     * redis缓存的统一的key前缀
     *
     */
    public final static String KEY_PREFIX_VALUE = "library_seat:";

    /**
     * 用户信息的统一的key前缀
     *
     */
    public final static String USER_KEY = KEY_PREFIX_VALUE + ":user:";

    /**
     * shiro 缓存用户信息的key 前缀
     *
     */
    public final static String SHIRO_KEY_PREFIX_VALUE = "uAccount";

    /**
     * shiro 缓存的用户的权限信息的key的过期时间 30 分钟
     *
     */
    public final static int SHIRO_KEY_EXPIRE_TIME = 1800;

    /**
     * shiro 缓存的用户的权限信息的前缀
     *
     */
    public final static String SHIRO_CACHE_KEY_PREFIX = KEY_PREFIX_VALUE + "shiro:cache:";
}
