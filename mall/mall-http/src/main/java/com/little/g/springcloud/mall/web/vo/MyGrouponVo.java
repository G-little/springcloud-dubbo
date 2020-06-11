package com.little.g.springcloud.mall.web.vo;

import com.little.g.springcloud.mall.dto.LitemallGrouponDTO;
import com.little.g.springcloud.mall.dto.LitemallGrouponRulesDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel("我的团购")
public class MyGrouponVo {

	@ApiModelProperty("我的团购ID")
	private Integer id;

	@ApiModelProperty("我的团购ID")
	private LitemallGrouponDTO groupon;

	private LitemallGrouponRulesDTO rules;

	private String creator;

	private Boolean isCreator;

	private Integer joinerCount;

	private Integer orderId;

	private String orderSn;

	private BigDecimal actualPrice;

	private String orderStatusText;

	private List<MyGrouponOrderGoodsVo> goodsList;

	@ApiModel("团购订单产品")
	@Data
	public static class MyGrouponOrderGoodsVo {

		@ApiModelProperty("产品ID")
		private Integer id;

		@ApiModelProperty("产品名")
		private String goodsName;

		@ApiModelProperty("产品数量")
		private Short number;

		@ApiModelProperty("产品图片")
		private String picUrl;

	}

}
