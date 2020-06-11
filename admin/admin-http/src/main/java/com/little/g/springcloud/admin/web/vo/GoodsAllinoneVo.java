package com.little.g.springcloud.admin.web.vo;

import com.little.g.springcloud.mall.dto.LitemallGoodsAttributeDTO;
import com.little.g.springcloud.mall.dto.LitemallGoodsDTO;
import com.little.g.springcloud.mall.dto.LitemallGoodsProductDTO;
import com.little.g.springcloud.mall.dto.LitemallGoodsSpecificationDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GoodsAllinoneVo {

	@ApiModelProperty("产品")
	LitemallGoodsDTO goods;

	@ApiModelProperty("产品规格")
	LitemallGoodsSpecificationDTO[] specifications;

	@ApiModelProperty("产品属性")
	LitemallGoodsAttributeDTO[] attributes;

	@ApiModelProperty("商品")
	LitemallGoodsProductDTO[] products;

	public LitemallGoodsDTO getGoods() {
		return goods;
	}

	public void setGoods(LitemallGoodsDTO goods) {
		this.goods = goods;
	}

	public LitemallGoodsSpecificationDTO[] getSpecifications() {
		return specifications;
	}

	public void setSpecifications(LitemallGoodsSpecificationDTO[] specifications) {
		this.specifications = specifications;
	}

	public LitemallGoodsAttributeDTO[] getAttributes() {
		return attributes;
	}

	public void setAttributes(LitemallGoodsAttributeDTO[] attributes) {
		this.attributes = attributes;
	}

	public LitemallGoodsProductDTO[] getProducts() {
		return products;
	}

	public void setProducts(LitemallGoodsProductDTO[] products) {
		this.products = products;
	}

}
