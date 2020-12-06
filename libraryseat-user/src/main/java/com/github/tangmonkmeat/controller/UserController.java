package com.github.tangmonkmeat.controller;

import com.github.tangmonkmeat.annotation.JwtIgnore;
import com.github.tangmonkmeat.annotation.ResultResponseBody;
import com.github.tangmonkmeat.common.constant.JwtConstant;
import com.github.tangmonkmeat.common.constant.RedisConstant;
import com.github.tangmonkmeat.common.constant.ResultConstant;
import com.github.tangmonkmeat.entity.User;
import com.github.tangmonkmeat.result.Result;
import com.github.tangmonkmeat.service.UserService;
import com.github.tangmonkmeat.utils.*;
import com.github.tangmonkmeat.vo.IntergrityAndTimeVO;
import com.github.tangmonkmeat.vo.LoginUserVO;
import com.github.tangmonkmeat.vo.WxUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户相关接口
 *
 * @author zwl
 * @date 2020/12/3 22:04
 */
@Controller
@ResultResponseBody
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 用户登录
     *
     * @param user {@link User}
     * @return token
     */
    @PostMapping(value = "/login2")
    @JwtIgnore
    public Result<Map<String,Object>> login(@RequestBody LoginUserVO user, HttpServletRequest request){
        String ip = IpUtil.getIp(request);
        return userService.login(user,ip);
    }

    /**
     * 微信登录
     *
     * @return token
     */
    @PostMapping(value = "/wxlogin")
    @JwtIgnore
    public Result<Object> wxLogin(@RequestBody WxUserVO user, HttpServletRequest request){
        String ip = IpUtil.getIp(request);
        return userService.wxLogin(user,ip);
    }

    /**
     * 获取 诚信度 和 预约时间
     *
     * @return 返回
     *          <p>token</p>
     *          <p>user_integrity</p>
     *          <p>low_integrity</p>
     *          <p>today_time</p>
     *          <p>tom_time</p>
     */
    @GetMapping(value = "/integrity")
    public Result<Map<String,Object>> getIntegrityValues(HttpServletRequest request){
        String token = request.getHeader(JwtConstant.AUTH_HEADER_KEY);
        IntergrityAndTimeVO intergrityAndTimeVO = userService.getIntergrityAndTime();

        // 刷新 token
        String userName = JwtUtil.getUserName(token);
        User user = (User)redisUtil.get(RedisConstant.USER_KEY + userName);
        String password = user.getuPassword();
        String salt = user.getSalt();
        String secret = Md5Util.md5Encryption(password, salt);
        String newToken = JwtUtil.sign(userName, secret);

        // 响应数据
        Map<String,Object> result = new HashMap<>(3);
        result.put(JwtConstant.RESPONSE_AUTH_KEY,newToken);
        result.put(ResultConstant.RESULT_DATA_MESSAGE_KEY,intergrityAndTimeVO);
        return Result.success(result);
    }
}
