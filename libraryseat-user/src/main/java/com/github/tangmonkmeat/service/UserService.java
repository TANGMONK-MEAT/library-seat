package com.github.tangmonkmeat.service;

import com.github.tangmonkmeat.entity.User;
import com.github.tangmonkmeat.result.Result;
import com.github.tangmonkmeat.vo.LoginUserVO;

/**
 * 用户业务逻辑
 *
 * @author zwl
 * @date 2020/12/3 22:09
 */
public interface UserService {

    /**
     * 用户登录业务
     *
     * @param user {@link User}
     * @return {@link Result},
     *         <p>登录成功返回token和状态码信息，否则抛出异常</p>
     */
    Result<String> login(LoginUserVO user);

}
