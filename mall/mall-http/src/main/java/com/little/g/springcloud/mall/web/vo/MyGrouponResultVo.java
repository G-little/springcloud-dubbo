package com.little.g.springcloud.mall.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("我的团购")
@Data
public class MyGrouponResultVo {

	@ApiModelProperty("计数")
	private Integer total;

	@ApiModelProperty("列表")
	private List<MyGrouponVo> list;

}
