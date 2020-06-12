package com.little.g.springcloud.mall.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("订单提交结果Vo")
@Data
public class OrderSubmitVo {

	@ApiModelProperty("订单ID")
	private Integer orderId;

	@ApiModelProperty("团购ID")
	private Integer grouponLinkId;

}
