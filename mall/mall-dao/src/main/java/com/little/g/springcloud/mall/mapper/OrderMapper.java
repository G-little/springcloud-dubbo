package com.little.g.springcloud.mall.mapper;

import com.little.g.springcloud.mall.model.LitemallOrder;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

public interface OrderMapper {

	int updateWithOptimisticLocker(@Param("lastUpdateTime") LocalDateTime lastUpdateTime,
			@Param("order") LitemallOrder order);

}
