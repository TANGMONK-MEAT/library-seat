package com.github.tangmonkmeat.controller;

import com.github.tangmonkmeat.annotation.ResultResponseBody;
import com.github.tangmonkmeat.common.constant.JwtConstant;
import com.github.tangmonkmeat.config.shiro.JwtToken;
import com.github.tangmonkmeat.config.shiro.UserRealm;
import com.github.tangmonkmeat.entity.User;
import com.github.tangmonkmeat.result.Result;
import com.github.tangmonkmeat.utils.JwtUtil;
import com.github.tangmonkmeat.utils.Md5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zwl
 * @date 2020/12/2 20:00
 */
@ResultResponseBody
@Controller
@RequestMapping("/user")
public class TestController1 {

    @Autowired
    private UserRealm userRealm;

    @PostMapping("/login")
    public Result<Object> login(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();
        String secret = Md5Util.md5Encryption(user.getuPassword(), "aaaa");
        String token = JwtUtil.sign(user.getuAccount(), secret);
        subject.login(new JwtToken(token));

        boolean authenticated = subject.isAuthenticated();
        System.out.println(authenticated);
        // 登录成功 封装用户信息到token
        Map<String, Object> map = new HashMap<>(4);
        map.put("token", token);
        map.put("user", user);
        return Result.success(map);
    }

    @RequiresRoles("admin")
    @RequiresPermissions("user:*")
    @GetMapping("/test")
    public Result<Object> test() {
        String secret = Md5Util.md5Encryption("123", "aaaa");
        String token = JwtUtil.sign("zwl", secret);
        Map<String, Object> map = new HashMap<>(4);
        map.put(JwtConstant.RESPONSE_AUTH_KEY, token);
        map.put("mssage","test");
        return Result.success(map);
    }


    @RequiresRoles("admin")
    @RequiresPermissions("user:*")
    @GetMapping("/clear")
    public Result<Object> clear() {
        String secret = Md5Util.md5Encryption("123", "aaaa");
        String token = JwtUtil.sign("zwl", secret);
        Map<String, Object> map = new HashMap<>(4);
        map.put(JwtConstant.RESPONSE_AUTH_KEY, token);
        map.put("mssage","clear");
        // 清理权限缓存
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        userRealm.clearCache(principals);
        return Result.success(map);
    }
}
