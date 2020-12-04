package com.github.tangmonkmeat.controller;

import com.github.tangmonkmeat.annotation.JwtIgnore;
import com.github.tangmonkmeat.annotation.ResultResponseBody;
import com.github.tangmonkmeat.entity.User;
import com.github.tangmonkmeat.result.Result;
import com.github.tangmonkmeat.vo.LoginUserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

    /**
     * 用户登录
     *
     * @param user {@link User}
     * @return token
     */
    @PostMapping
    @JwtIgnore
    public Result<String> login(@RequestBody LoginUserVO user){

        return null;
    }
}
