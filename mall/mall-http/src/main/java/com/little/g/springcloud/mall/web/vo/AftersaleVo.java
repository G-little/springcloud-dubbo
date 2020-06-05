package com.little.g.springcloud.mall.web.vo;

import com.little.g.springcloud.mall.dto.LitemallAftersaleDTO;
import com.little.g.springcloud.mall.dto.LitemallOrderGoodsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("售后展示")
public class AftersaleVo {

	@ApiModelProperty("售后服务")
	private LitemallAftersaleDTO aftersale;

	@ApiModelProperty("产品订单")
	private List<LitemallOrderGoodsDTO> goodsList;

	public LitemallAftersaleDTO getAftersale() {
		return aftersale;
	}

	public void setAftersale(LitemallAftersaleDTO aftersale) {
		this.aftersale = aftersale;
	}

	public List<LitemallOrderGoodsDTO> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<LitemallOrderGoodsDTO> goodsList) {
		this.goodsList = goodsList;
	}

}
