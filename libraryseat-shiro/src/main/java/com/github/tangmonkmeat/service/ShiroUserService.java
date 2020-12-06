package com.github.tangmonkmeat.service;

import com.github.tangmonkmeat.entity.User;

/**
 * @author zwl
 * @date 2020/12/2 16:09
 */
public interface ShiroUserService {

    /**
     * 获取用户的缓存信息
     *
     * @param username 用户名
     * @return {@link User}
     */
    User getUserWithRoleAndPower(String username);

}
