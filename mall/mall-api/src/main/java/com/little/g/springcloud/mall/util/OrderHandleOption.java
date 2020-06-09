package com.little.g.springcloud.mall.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("订单操作选项")
@Data
public class OrderHandleOption {

	@ApiModelProperty("取消操作")
	private boolean cancel = false; // 取消操作

	@ApiModelProperty("删除操作")
	private boolean delete = false; // 删除操作

	@ApiModelProperty("支付操作")
	private boolean pay = false; // 支付操作

	@ApiModelProperty("评论操作")
	private boolean comment = false; // 评论操作

	@ApiModelProperty("确认收货操作")
	private boolean confirm = false; // 确认收货操作

	@ApiModelProperty("取消订单并退款操作")
	private boolean refund = false; // 取消订单并退款操作

	@ApiModelProperty("再次购买操作")
	private boolean rebuy = false; // 再次购买

	@ApiModelProperty("售后操作")
	private boolean aftersale = false; // 售后操作

}
