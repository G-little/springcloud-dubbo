package com.little.g.springcloud.mall.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("用户订单统计信息")
@Data
public class UserOrderInfoDTO implements Serializable {

	@ApiModelProperty("未支付订单")
	private int unpaid;

	@ApiModelProperty("未发货")
	private int unship;

	@ApiModelProperty("已收货")
	private int unrecv;

	@ApiModelProperty("未评价")
	private int uncomment;

}
