package com.little.g.springcloud.mall.web.vo;

import com.little.g.springcloud.mall.dto.LitemallCategoryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("当前分类数据")
@Data
public class CategoryCurrentVo {

    @ApiModelProperty("当前分类")
    private LitemallCategoryDTO currentCategory;
    @ApiModelProperty("二级分类")
    private List<LitemallCategoryDTO> currentSubCategory;

}
