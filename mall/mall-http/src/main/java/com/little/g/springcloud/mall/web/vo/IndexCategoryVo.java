package com.little.g.springcloud.mall.web.vo;

import com.little.g.springcloud.mall.dto.LitemallGoodsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@ApiModel("首页分类")
@Data
public class IndexCategoryVo {

    @ApiModelProperty("分类ID")
    private Integer id;
    @ApiModelProperty("分类名")
    private String name;
    @ApiModelProperty("产品列表")
    private List<LitemallGoodsDTO> goodsList;

}
