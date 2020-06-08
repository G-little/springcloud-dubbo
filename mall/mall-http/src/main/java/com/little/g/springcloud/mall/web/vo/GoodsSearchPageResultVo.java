package com.little.g.springcloud.mall.web.vo;

import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.mall.dto.LitemallCategoryDTO;
import com.little.g.springcloud.mall.dto.LitemallGoodsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("商品搜索结果页")
public class GoodsSearchPageResultVo extends Page<LitemallGoodsDTO> {

	@ApiModelProperty("过滤类目")
	private List<LitemallCategoryDTO> filterCategoryList;

	public List<LitemallCategoryDTO> getFilterCategoryList() {
		return filterCategoryList;
	}

	public void setFilterCategoryList(List<LitemallCategoryDTO> filterCategoryList) {
		this.filterCategoryList = filterCategoryList;
	}

}
