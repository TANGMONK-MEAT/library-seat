package com.github.library.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author zwl
 * @date 2020/11/27 11:56
 */
public interface UserMapper<T> extends BaseMapper<T>{

    /**
     * 通过用户名查找用户
     *
     * @param account 账户
     * @return 用户详情
     */
    T findByAccount(@Param("account") String account) throws Exception;


}
