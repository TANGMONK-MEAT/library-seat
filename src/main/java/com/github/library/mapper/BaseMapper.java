package com.github.library.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zwl
 * @date 2020/11/28 17:16
 */
public interface BaseMapper<T> {

    /**
     * 查询所有符合条件的记录
     *
     * @param t
     * @return t T 集合
     */
    List<T> findOfList(T t) throws Exception;

    /**
     * 查询符合条件的记录数量
     *
     * @param t
     * @return 座位数量
     */
    int count(T t) throws Exception;

    /**
     * 查询一条记录
     *
     * @param id
     * @return 记录
     */
    T findOne(@Param("id") int id);

    /**
     * 有选择的查询一条记录
     *
     * @param t
     * @return 记录
     */
    T findOneSelective(T t);

    /**
     * 添加一条记录
     *
     * @param t
     * @return 影响行数
     */
    int addOne(T t) throws Exception;

    /**
     * 有选择的添加一条记录
     *
     * @param t
     * @return 影响行数
     */
    int addOneSelective(T t) throws Exception;

    /**
     * 修改一条记录
     *
     * @param t
     * @return 影响行数
     */
    int updateOne(T t) throws Exception;

    /**
     * 有选择的修改一条记录
     *
     * @param t
     * @return 影响行数
     */
    int updateOneSelective(T t) throws Exception;

    /**
     * 删除一条记录
     *
     * @param id 唯一标示
     * @return 影响行数
     */
    int deleteOne(@Param("id") int id) throws Exception;
}
