package com.little.g.springcloud.admin.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("类目，品牌VO")
@Data
public class CategoryBrandVo {

	@ApiModelProperty("分类列表")
	private List<CatVo> categoryList;

	@ApiModelProperty("品牌列表")
	private List<BrandVo> brandList;

}
