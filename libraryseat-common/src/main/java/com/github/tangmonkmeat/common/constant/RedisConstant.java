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
    public final static String USER_KEY = KEY_PREFIX_VALUE + "user:cache:";


    // ===========================shiro 相关=======================================


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

    /**
     * shiro 缓存的用户的权限的key 的全类名
     *
     */
    public final static String SHIRO_AUTHORIZATION_CACHE_CLASS_NAME = "com.github.tangmonkmeat.config.shiro.UserRealm.authorizationCache";

    /**
     * shiro 缓存用户权限信息的key
     *
     */
    public final static String SHIRO_AUTHORIZATION_KEY = SHIRO_CACHE_KEY_PREFIX + SHIRO_AUTHORIZATION_CACHE_CLASS_NAME + ":";


    // ============================== 用户相关 ==========================================

    /**
     * 用户信息
     *
     */
    public final static String USER_LOGIN_MESSAGE = USER_KEY + "message:";

    /**
     * 用户信息过期时间，单位小时，默认6小时
     *
     */
    public final static int USER_LOGIN_MESSAGE_EXPIRE = 6;

    /**
     * 用户登录的 ip
     *
     */
    public final static String USER_LOGIN_IP = USER_KEY + "login_ip:";

    /**
     * 用户登录ip 的过期时间, 单位小时，默认 6小时
     *
     */
    public final static int USER_LOGIN_IP_EXPIRE = USER_LOGIN_MESSAGE_EXPIRE;

    /**
     * 用户 在限定时间内登陆的次数 的key，value必须是 正整数
     *
     */
    public final static String USER_LOGIN_COUNT = USER_KEY + "login_count:";

    /**
     * {@link RedisConstant#USER_LOGIN_COUNT} 的过期时间，单位分钟，默认 1小时
     *
     */
    public final static int USER_LOGIN_COUNT_EXPIRE = 60;

    /**
     * 用户 是否被锁定，禁止登录的key，value 只能是 lock 或者 unlock
     *
     */
    public final static String USER_IS_LOCK = USER_KEY + "is_lock:";

    /**
     * {@link RedisConstant#USER_IS_LOCK} 的过期时间, 单位分钟，默认 1小时
     *
     */
    public final static int USER_IS_LOCK_EXPIRE = 60;

    /**
     * 限制用户 在规定时间内的 登录最大次数
     *
     */
    public final static int USER_LOGIN_MAX_COUNT = 5;




}
