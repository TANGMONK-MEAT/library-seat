package com.github.tangmonkmeat.service.impl;

import com.github.tangmonkmeat.entity.User;
import com.github.tangmonkmeat.exception.BusinessException;
import com.github.tangmonkmeat.mapper.UserMapper;
import com.github.tangmonkmeat.result.ResultEnum;
import com.github.tangmonkmeat.service.ShiroUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zwl
 * @date 2020/12/2 16:10
 */
@Service
public class ShiroUserServiceImpl implements ShiroUserService {

    @Resource
    private UserMapper<User> userMapper;

    /**
     * 获取用户的缓存信息
     *
     * @param username 用户名
     * @return {@link User}
     */
    @Transactional(readOnly = true,
            rollbackForClassName = "Exception.class")
    @Override
    public User getUserWithRoleAndPower(String username) {
        User user = userMapper.findByAccount(username);
        if (user == null){
            throw new BusinessException(ResultEnum.USER_NOT_EXIST);
        }
        Integer id = user.getuId();
        List<String> roles = userMapper.findUserRoles(id);
        List<String> powers = userMapper.findUserPowers(id);
        user.setPowerList(powers);
        user.setRoleList(roles);
        return user;
    }
}
