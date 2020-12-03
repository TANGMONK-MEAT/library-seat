package com.github.tangmonkmeat.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zwl
 * @date 2020/11/27 11:56
 */
public interface UserMapper<T> extends BaseMapper<T>{

    /**
     * 通过用户名，查找用户
     *
     * @param account 账户
     * @return 用户详情
     */
    T findByAccount(@Param("account") String account);

    /**
     * 通过用户名，查找用户所有的 资源权限
     *
     * @param id 账户
     * @return list-用户的资源权限
     */
    List<String> findUserPowers(@Param("id") Integer id);

    /**
     * 通过用户名，查找用户所有的 角色
     *
     * @param id 账户
     * @return list-用户角色
     */
    List<String> findUserRoles(@Param("id") Integer id);

}
