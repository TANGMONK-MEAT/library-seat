package com.github.tangmonkmeat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.tangmonkmeat.common.constant.JwtConstant;
import com.github.tangmonkmeat.common.constant.RedisConstant;
import com.github.tangmonkmeat.common.constant.ResultConstant;
import com.github.tangmonkmeat.common.constant.WeChatConstant;
import com.github.tangmonkmeat.common.enums.Lock;
import com.github.tangmonkmeat.entity.Score;
import com.github.tangmonkmeat.entity.User;
import com.github.tangmonkmeat.exception.BusinessException;
import com.github.tangmonkmeat.mapper.ScoreMapper;
import com.github.tangmonkmeat.mapper.UserMapper;
import com.github.tangmonkmeat.result.Result;
import com.github.tangmonkmeat.result.ResultEnum;
import com.github.tangmonkmeat.service.ShiroUserService;
import com.github.tangmonkmeat.service.UserService;
import com.github.tangmonkmeat.utils.JwtUtil;
import com.github.tangmonkmeat.utils.Md5Util;
import com.github.tangmonkmeat.utils.RedisUtil;
import com.github.tangmonkmeat.utils.SaltUtil;
import com.github.tangmonkmeat.vo.IntergrityAndTimeVO;
import com.github.tangmonkmeat.vo.LoginUserVO;
import com.github.tangmonkmeat.vo.WxUserVO;
import com.github.tangmonkmeat.wechat.service.WxMiniApi;
import com.github.tangmonkmeat.wechat.util.WeChatUtil;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zwl
 * @date 2020/12/3 22:16
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserMapper<User> userMapper;
    @Resource
    private ScoreMapper<Score> scoreMapper;

    @Resource
    private ShiroUserService shiroUserService;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private WxMiniApi wxMiniApi;



    /**
     * 用户登录业务
     *
     * @param loginUser {@link User}
     * @param ip ip
     * @return {@link Result},
     * <p>登录成功返回token和状态码信息，否则抛出异常</p>
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public Result<Map<String,Object>> login(LoginUserVO loginUser,String ip) {
        if (loginUser.getUsername() == null || loginUser.getPassword() == null || loginUser.getOpenid() == null) {
            throw new BusinessException(ResultEnum.PARAM_NOT_COMPLETE);
        }
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();
        String openid = loginUser.getOpenid();

        final String keyCount = RedisConstant.USER_LOGIN_COUNT + username;
        // 超过限定时间登录，重新计数
        redisUtil.setnx(keyCount, 1, RedisConstant.USER_IS_LOCK_EXPIRE, TimeUnit.MINUTES);

        int count = (int) redisUtil.get(keyCount);
        final String keyLock = RedisConstant.USER_IS_LOCK + username;
        // 超过限制登录次数
        if (count > RedisConstant.USER_LOGIN_MAX_COUNT) {
            // 锁定
            redisUtil.setEx(keyLock, Lock.LOCK.getKey(), RedisConstant.USER_IS_LOCK_EXPIRE, TimeUnit.MINUTES);
        }

        // 用户是否锁定
        Object isLock = redisUtil.get(keyLock);
        if (isLock != null) {
            String isLockStr = (String) isLock;
            if (Lock.LOCK.getKey().equals(isLockStr)) {
                throw new BusinessException(ResultEnum.USER_PASSWORD_ERROR_GREATER_THAN_MAX_TIMES);
            }
        }

        // 判断用户是否存在
        User user = userMapper.findOneSelective(new User(username,openid));
        if (user == null) {
            throw new BusinessException(ResultEnum.USER_NOT_EXIST);
        }

        // 判断用户是否被禁用
        Byte status = user.getStatus();
        if (status == 0) {
            throw new BusinessException(ResultEnum.USER_ACCOUNT_FORBIDDEN);
        }

        // 判断用户密码是否正确
        String uPassword = user.getuPassword();
        String salt = SaltUtil.getSalt(JwtConstant.DEFAULT_SALT_SIZE);
        String secret = Md5Util.md5Encryption(password, salt);
        //if (!secret.equals(uPassword)) {
        //    throw new BusinessException(ResultEnum.USER_LOGIN_ERROR);
        //}

        // 清空登录计数
        redisUtil.del(keyCount);

        // 刷新盐值和最近登录的时间
        User sysUser = new User();
        sysUser.setSalt(salt);
        Date date = new Date();
        sysUser.setLastLoginTime(date);
        sysUser.setuId(user.getuId());
        try {
            userMapper.updateOneSelective(sysUser);
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.USER_LOGIN_DATABASE_ERROR);
        }

        // 缓存用户的权限信息
        User userWithRoleAndPower = shiroUserService.getUserWithRoleAndPower(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(userWithRoleAndPower.getRoleList());
        authorizationInfo.addStringPermissions(userWithRoleAndPower.getPowerList());
        authorizationInfo.addRole("admin");
        authorizationInfo.addStringPermission("user:*");
        redisUtil.setEx(RedisConstant.SHIRO_AUTHORIZATION_KEY + username,authorizationInfo,RedisConstant.SHIRO_KEY_EXPIRE_TIME);

        // 缓存用户的信息
        user.setSalt(salt);
        user.setLastLoginTime(date);
        cacheUserInfo(user,ip);

        // 生成token
        String token = JwtUtil.sign(username, secret);
        Map<String,Object> map = new HashMap<>(2);
        map.put(JwtConstant.RESPONSE_AUTH_KEY,token);
        return Result.success(map);
    }

    /**
     * 用户微信登录
     *
     * @param loginUser {@link WxUserVO}
     * @return {@link Result}
     * <p>登录成功状态码信息和openid，否则抛出异常</p>
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public Result<Object> wxLogin(WxUserVO loginUser, String ip) {
        // 调用微信登陆授权接口
        JSONObject wxUserInfo = wxMiniApi.authCode2Session(loginUser.getCode());
        if (wxUserInfo == null){
            throw new BusinessException(ResultEnum.INTERFACE_WX_CODE2SESSION_ERROR);
        }

        // 获取微信的 openid 和 sessionKey
        String openid = wxUserInfo.getString(WeChatConstant.OPENID);
        String sessionKey = wxUserInfo.getString(WeChatConstant.SESSION_KEY);
        if (openid == null || "".equals(openid)){
            throw new BusinessException(ResultEnum.INTERFACE_WX_CODE_ERROR);
        }

        // 解密 微信的用户信息
        String realWxUserInfo = WeChatUtil.decryptData(loginUser.getEncryptedData(), sessionKey, loginUser.getIv());
        User sysUser = JSONObject.parseObject(realWxUserInfo, User.class);
        sysUser.setLastLoginTime(new Date());

        // 保存用户的信息
        if (userMapper.findUserByOpenid(openid) == null){
            logger.info("new wechat user access, openid: {}, ip: {}",openid,ip);
            sysUser.setWxOpenid(openid);
            userMapper.addOneSelective(sysUser);
        }else {
            logger.info("old wechat user access, openid: {}, ip: {}",openid,ip);
            userMapper.updateOneSelective(sysUser);
        }

        Map<String,Object> map = new HashMap<>(2);
        map.put(ResultConstant.RESULT_DATA_OPENID_KEY,openid);
        return Result.success(map);
    }

    /**
     * 获取 {@link IntergrityAndTimeVO}
     *
     * <p>user_integrity</p>
     * <p>low_integrity</p>
     * <p>today_time</p>
     * <p>tom_time</p>
     *
     * @return {@link IntergrityAndTimeVO}
     */
    @Override
    public IntergrityAndTimeVO getIntergrityAndTime(String account) {
        Score score = new Score();
        score.setStuName(account);
        score = scoreMapper.findOneSelective(score);
        if(score==null){
            throw new BusinessException(ResultEnum.RESULT_DATA_NONE);
        }
        return new IntergrityAndTimeVO(score.getTotal());
    }

    /**
     * 缓存用户信息 和 ip
     *
     * @param user {@link User}
     * @param ip ip
     */
    private void cacheUserInfo(User user,String ip){
        String username = user.getuAccount();
        redisUtil.setEx(RedisConstant.USER_LOGIN_MESSAGE + username,user,RedisConstant.USER_LOGIN_MESSAGE_EXPIRE,TimeUnit.HOURS);
        redisUtil.setEx(RedisConstant.USER_LOGIN_IP + username,ip,RedisConstant.USER_LOGIN_IP_EXPIRE,TimeUnit.HOURS);
    }
}
