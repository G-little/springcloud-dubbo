package com.little.g.springcloud.mall.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel("商品规格分组信息")
public class GoodsSpecificationListDTO implements Serializable {

	@ApiModelProperty("商品规格名称")
	private String name;

	@ApiModelProperty("商品规格列表")
	private List<LitemallGoodsSpecificationDTO> valueList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<LitemallGoodsSpecificationDTO> getValueList() {
		return valueList;
	}

	public void setValueList(List<LitemallGoodsSpecificationDTO> valueList) {
		this.valueList = valueList;
	}

}
