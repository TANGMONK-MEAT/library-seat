package com.github.tangmonkmeat.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author zwl
 * @date 2020/11/28 17:47
 */
public interface IllegalMapper<T> {

    /**
     * 删除一条信誉记录
     *
     * @param studentNo 学号
     * @return 影响行数
     */
    int deleteOneByStudentNo(@Param("no") String studentNo);
}
