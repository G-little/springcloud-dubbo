package com.little.g.springcloud.mall.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel("收藏信息列表Vo")
@Data
public class CollectListVo {

	@ApiModelProperty("id")
	private Integer id;

	@ApiModelProperty("收藏类型 如果type=0，则是商品ID；如果type=1，则是专题ID")
	private Byte type;

	@ApiModelProperty("如果type=0，则是商品ID；如果type=1，则是专题ID")
	private Integer valueId;

	@ApiModelProperty("商品名")
	private String name;

	@ApiModelProperty("商品简介")
	private String brief;

	@ApiModelProperty("商品图片")
	private String picUrl;

	@ApiModelProperty("零售价格")
	private BigDecimal retailPrice;

}
