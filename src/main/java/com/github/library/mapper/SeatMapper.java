package com.github.library.mapper;

import com.github.library.entity.Seat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zwl
 * @date 2020/11/27 11:38
 */
public interface SeatMapper {

    /**
     * 查询所有符合条件的座位记录
     *
     * @param seat {@link Seat}
     * @return seat 集合
     */
    List<Seat> findSeat(@Param("s") Seat seat);

    /**
     * 查询座位记录数量
     *
     * @param seat {@link Seat}
     * @return 座位数量
     */
    int countSeat(@Param("s") Seat seat);

    /**
     * 修改座位记录
     *
     * @param seat {@link Seat}
     * @return 影响行数
     */
    int updateSeat(@Param("s") Seat seat);

    /**
     * 删除座位记录
     *
     * @param id 座位主键
     * @return 影响行数
     */
    int deleteSeat(@Param("id") int id);

    /**
     * 占座
     *
     * @param seat {@link Seat}
     * @return 影响行数
     */
    int takeSeat(@Param("s") Seat seat);

    /**
     * 取消占座
     *
     * @param id 座位主键
     * @return 影响行数
     */
    int cancelSeat(@Param("id") int id);

    /**
     * 查询空闲座位记录数量
     *
     * @param seat {@link Seat}
     * @return 空闲座位记录数量
     */
    int findFreeSeat(@Param("s") Seat seat);

}
