package com.github.library.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author zwl
 * @date 2020/11/27 11:38
 */
public interface SeatMapper<T> extends BaseMapper<T> {

    /**
     * 占座
     *
     * @param t
     * @return 影响行数
     */
    int takeSeat(T t) throws Exception;

    /**
     * 取消占座
     *
     * @param id 座位主键
     * @return 影响行数
     */
    int cancelSeat(@Param("id") int id) throws Exception;

    /**
     * 查询空闲座位记录数量
     *
     * @param t
     * @return 空闲座位记录数量
     */
    int findFreeSeat(T t) throws Exception;

}
