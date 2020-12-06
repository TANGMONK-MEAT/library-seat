package com.github.tangmonkmeat.config.shiro;

import com.github.tangmonkmeat.common.constant.RedisConstant;
import com.github.tangmonkmeat.entity.User;
import com.github.tangmonkmeat.exception.BusinessException;
import com.github.tangmonkmeat.result.ResultEnum;
import com.github.tangmonkmeat.service.ShiroUserService;
import com.github.tangmonkmeat.utils.JwtUtil;
import com.github.tangmonkmeat.utils.Md5Util;
import com.github.tangmonkmeat.utils.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息校验 realm
 *
 * @author zwl
 * @date 2020/12/1 22:05
 */
public class UserRealm extends AuthorizingRealm {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private ShiroUserService shiroUserService;


    /**
     * 返回true，才会执行 JwtToken的 身份认证和授权
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        logger.info("authorized to {} started",user);

        List<String> powerList = user.getPowerList();
        if (!ObjectUtils.isEmpty(powerList)){
            authorizationInfo.addStringPermissions(powerList);
        }
        List<String> roleList = user.getRoleList();
        if (!ObjectUtils.isEmpty(roleList)){
            authorizationInfo.addRoles(roleList);
        }
        authorizationInfo.addRole("admin");
        authorizationInfo.addStringPermission("user:*");
        return authorizationInfo;
    }

    /**
     * 身份认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getPrincipal();
        String userName = JwtUtil.getUserName(token);
        if (userName == null) {
            throw new AuthenticationException(new BusinessException(ResultEnum.PARAM_NOT_COMPLETE));
        }

        // 查询 user
        final String key = RedisConstant.USER_KEY + userName;
        Object obj = redisUtil.get(RedisConstant.USER_KEY + userName);
        User user;
        if (obj == null) {
            logger.warn("user not exists redis,redis key: {}", key);
            // 查询数据库
            user = shiroUserService.getUserWithRoleAndPower(userName);
            if (user == null) {
                throw new AuthenticationException(new BusinessException(ResultEnum.USER_NOT_EXIST));
            }
        }else{
            user = (User) obj;
        }

        // 判断 token 是否过期
        if (JwtUtil.isExpire(token)) {
            throw new AuthenticationException(new BusinessException(ResultEnum.PERMISSION_TOKEN_EXPIRED));
        }

        // 校验token
        // 由 密码 + salt + 996散列次数 md5 加密 生成 secret
        //String secret = Md5Util.md5Encryption(user.getuPassword(), user.getSalt());
        //String secret = Md5Util.md5Encryption(user.getuPassword(), "aaaa");
        //if (!JwtUtil.verify(token,userName,secret)){
        //    throw new AuthenticationException(new BusinessException(ResultEnum.PERMISSION_TOKEN_INVALID));
        //}

        return new SimpleAuthenticationInfo(user,token,getName());
    }
}
