package com.github.tangmonkmeat.service.impl;

import com.github.tangmonkmeat.common.constant.JwtConstant;
import com.github.tangmonkmeat.common.constant.RedisConstant;
import com.github.tangmonkmeat.common.enums.Lock;
import com.github.tangmonkmeat.entity.User;
import com.github.tangmonkmeat.exception.BusinessException;
import com.github.tangmonkmeat.mapper.UserMapper;
import com.github.tangmonkmeat.result.Result;
import com.github.tangmonkmeat.result.ResultEnum;
import com.github.tangmonkmeat.service.UserService;
import com.github.tangmonkmeat.utils.JwtUtil;
import com.github.tangmonkmeat.utils.Md5Util;
import com.github.tangmonkmeat.utils.RedisUtil;
import com.github.tangmonkmeat.utils.SaltUtil;
import com.github.tangmonkmeat.vo.LoginUserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author zwl
 * @date 2020/12/3 22:16
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper<User> userMapper;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 用户登录业务
     *
     * @param loginUser {@link User}
     * @return {@link Result},
     * <p>登录成功返回token和状态码信息，否则抛出异常</p>
     */
    @Override
    public Result<String> login(LoginUserVO loginUser) {
        if (loginUser.getUsername() == null || loginUser.getPassword() == null){
            throw new BusinessException(ResultEnum.PARAM_NOT_COMPLETE);
        }
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();

        final String key = RedisConstant.USER_LOGIN_COUNT + username;
        // 超过限定时间登录，重新计数
        redisUtil.setnx(key, 1, RedisConstant.USER_IS_LOCK_EXPIRE, TimeUnit.MINUTES);
        int count = (int) redisUtil.get(key);
        final String key1 = RedisConstant.USER_IS_LOCK + username;

        // 超过限制登录次数
        if (count > RedisConstant.USER_LOGIN_MAX_COUNT){
            // 锁定
            redisUtil.setEx(key1, Lock.LOCK.getKey(),RedisConstant.USER_IS_LOCK_EXPIRE,TimeUnit.MINUTES);
        }

        Object isLock =  redisUtil.get(key1);
        if (isLock != null){
            String isLockStr = (String)isLock;
            if (Lock.LOCK.getKey().equals(isLockStr)){
                throw new BusinessException(ResultEnum.USER_PASSWORD_ERROR_GREATER_THAN_MAX_TIMES);
            }
        }

        // 获取盐值
        User user = userMapper.findByAccount(username);
        String salt = user.getSalt();
        if (salt == null || "".equals(salt)){
            salt = SaltUtil.getSalt(JwtConstant.DEFAULT_SALT_SIZE);
        }

        // 生成token
        String secret = Md5Util.md5Encryption(password, salt);
        String token = JwtUtil.sign(username, salt);
        SecurityUtils.getSubject().getPrincipal();


        // 刷新盐值
        salt = SaltUtil.getSalt(8);



        return null;
    }


}
