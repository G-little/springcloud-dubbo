package com.little.g.springcloud.mall.web.vo;

import com.little.g.springcloud.mall.dto.LitemallCategoryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@ApiModel("所有分类数据")
@Data
public class CategoryAllVo {

	@ApiModelProperty("一级分类")
	private List<LitemallCategoryDTO> categoryList;

	@ApiModelProperty("当前分类")
	private LitemallCategoryDTO currentCategory;

	@ApiModelProperty("二级分类")
	private List<LitemallCategoryDTO> currentSubCategory;

	@ApiModelProperty("所有分类")
	private Map<Integer, List<LitemallCategoryDTO>> allList;

}
