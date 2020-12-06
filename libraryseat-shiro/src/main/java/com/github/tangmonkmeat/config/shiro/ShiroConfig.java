package com.github.tangmonkmeat.config.shiro;

import com.github.tangmonkmeat.common.constant.RedisConstant;
import com.github.tangmonkmeat.helper.ShiroAuthorizationHelper;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.serializer.RedisSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * shiro 配置类
 *
 * @author zwl
 * @date 2020/12/1 22:03
 */
@Configuration
public class ShiroConfig {

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.database}")
    private int database;

    @Value("${spring.redis.timeout}")
    private int timeout;

    /**
     * 注册 UserRealm, 用于用户的认证和授权
     *
     */
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    /**
     * 配置shiro redisManager, 使用的是shiro-redis开源插件
     *
     * @return {@link RedisManager}
     */
    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setDatabase(database);
        redisManager.setHost(host + ":" + port);
        redisManager.setPassword(password);
        redisManager.setTimeout(timeout);
        return redisManager;
    }

    /**
     * cacheManager 缓存 redis实现, 使用的是shiro-redis开源插件
     *
     * @return {@link RedisCacheManager}
     */
    @Bean
    public RedisCacheManager redisCacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        /*
         * 必须要设置主键名称，shiro-redis 插件用过这个缓存用户信息
         */
        redisCacheManager.setPrincipalIdFieldName(RedisConstant.SHIRO_KEY_PREFIX_VALUE);
        redisCacheManager.setExpire(RedisConstant.SHIRO_KEY_EXPIRE_TIME);
        redisCacheManager.setKeyPrefix(RedisConstant.SHIRO_CACHE_KEY_PREFIX);
        return redisCacheManager;
    }

    /**
     * 配置 安全管理器
     *
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 设置 自定义的 realm
        securityManager.setRealm(userRealm);
        // 缓存实现 使用 redis
        securityManager.setCacheManager(redisCacheManager());
        /*
         * 关闭shiro自带的 session
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(sessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }


    /**
     * 配置 ShiroFilterFactoryBean
     *
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        // 设置 安全管理器
        filterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //Map<String,String> map = new HashMap<>(4);
        /*
         * anon:所有url都都可以匿名访问，authc:所有url都必须认证通过才可以访问;
         * 过滤链定义，从上向下顺序执行，authc 应放在 anon 下面
         */
        //map.put("/user/login","anon");
        //// 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了, 位置放在 anon、authc下面
        //map.put("/user/logout","logout");
        //map.put("/**", "authc");
        //  配置授权和认证规则
        //filterFactoryBean.setFilterChainDefinitionMap(map);
        return filterFactoryBean;
    }

}
