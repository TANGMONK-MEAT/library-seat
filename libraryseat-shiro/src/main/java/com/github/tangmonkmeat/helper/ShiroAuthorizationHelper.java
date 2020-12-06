package com.github.tangmonkmeat.helper;

import com.github.tangmonkmeat.common.constant.RedisConstant;
import com.github.tangmonkmeat.entity.User;
import com.github.tangmonkmeat.utils.ApplicationContextUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.subject.Subject;
import org.crazycake.shiro.RedisCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zwl
 * @date 2020/12/3 14:52
 */
public class ShiroAuthorizationHelper {

    private static final Logger logger = LoggerFactory.getLogger(ShiroAuthorizationHelper.class);

    /**
     * 清除用户的授权信息
     *
     * @param username 用户名
     */
    private static void clearAuthorizationInfo(final String username){
        logger.debug("clear the " + username + " authorizationInfo");
        RedisCacheManager redisCacheManager = ApplicationContextUtil.getBean(RedisCacheManager.class);
        Cache<Object, Object> cache = redisCacheManager.getCache(RedisConstant.SHIRO_AUTHORIZATION_CACHE_CLASS_NAME);
        cache.remove(username);
    }

    /**
     * 清除当前用户的授权信息
     */
    public static void clearAuthorizationInfo(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            User user = (User)subject.getPrincipal();
            clearAuthorizationInfo(user.getuAccount());
        }
    }
}
