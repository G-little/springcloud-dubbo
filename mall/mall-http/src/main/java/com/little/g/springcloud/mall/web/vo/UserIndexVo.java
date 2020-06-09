package com.little.g.springcloud.mall.web.vo;

import com.little.g.springcloud.mall.dto.UserOrderInfoDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("用户订单信息")
@Data
public class UserIndexVo {

	@ApiModelProperty("订单统计信息")
	private UserOrderInfoDTO order;

}
