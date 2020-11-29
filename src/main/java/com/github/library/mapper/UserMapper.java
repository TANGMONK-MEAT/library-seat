package com.github.library.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author zwl
 * @date 2020/11/27 11:56
 */
public interface UserMapper<T> extends BaseMapper<T>{

    /**
     * 判断指定用户是否存在
     *
     * @param username 用户名
     * @return 存在，返回用户信息；否则返回null
     */
    T existsUserWithUserName(@Param("username") String username) throws Exception;

}
