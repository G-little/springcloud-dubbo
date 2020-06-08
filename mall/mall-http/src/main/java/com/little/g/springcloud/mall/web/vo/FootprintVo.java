package com.little.g.springcloud.mall.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel("足迹")
public class FootprintVo {

	@ApiModelProperty("足迹ID")
	private Integer id;

	@ApiModelProperty("商品ID")
	private Integer goodsId;

	@ApiModelProperty("添加时间")
	private LocalDateTime addTime;

	@ApiModelProperty("产品名")
	private String name;

	@ApiModelProperty("产品简介")
	private String brief;

	@ApiModelProperty("产品图片")
	private String picUrl;

	@ApiModelProperty("零售价")
	private BigDecimal retailPrice;

}
