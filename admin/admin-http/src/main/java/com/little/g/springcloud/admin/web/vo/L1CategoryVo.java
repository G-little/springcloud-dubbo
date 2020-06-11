package com.little.g.springcloud.admin.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("一级类目Vo")
@Data
public class L1CategoryVo {

	@ApiModelProperty("类目ID")
	private Integer value;

	@ApiModelProperty("类目Label")
	private String label;

}
