package com.little.g.springcloud.mall.mapper;

import org.apache.ibatis.annotations.Param;

public interface GoodsProductMapper {
    int addStock(@Param("id") Integer id, @Param("num") Short num);

    int reduceStock(@Param("id") Integer id, @Param("num") Short num);
}
