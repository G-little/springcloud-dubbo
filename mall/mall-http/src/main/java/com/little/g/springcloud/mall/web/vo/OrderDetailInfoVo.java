package com.little.g.springcloud.mall.web.vo;

import com.little.g.springcloud.mall.util.OrderHandleOption;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApiModel("订单详情Vo")
@Data
public class OrderDetailInfoVo {

	@ApiModelProperty("订单ID")
	private Integer id;

	@ApiModelProperty("订单SN")
	private String orderSn;

	@ApiModelProperty("用户订单留言")
	private String message;

	@ApiModelProperty("创建时间")
	private LocalDateTime addTime;

	@ApiModelProperty("收货人姓名")
	private String consignee;

	@ApiModelProperty("收货人手机号")
	private String mobile;

	@ApiModelProperty("收货地址")
	private String address;

	@ApiModelProperty("商品总价格")
	private BigDecimal goodsPrice;

	@ApiModelProperty("优惠券减免")
	private BigDecimal couponPrice;

	@ApiModelProperty("配送费用")
	private BigDecimal freightPrice;

	@ApiModelProperty("实付费用")
	private BigDecimal actualPrice;

	@ApiModelProperty("订单状态描述")
	private String orderStatusText;

	@ApiModelProperty("处理选项")
	private OrderHandleOption handleOption;

	@ApiModelProperty("售后状态 售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消")
	private Short aftersaleStatus;

	@ApiModelProperty("快递公司Code")
	private String expCode;

	@ApiModelProperty("快递公司名")
	private String expName;

	@ApiModelProperty("快递单号")
	private String expNo;

}
