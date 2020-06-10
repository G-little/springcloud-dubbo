package com.little.g.springcloud.admin.web.vo;

import com.little.g.springcloud.mall.dto.LitemallGoodsAttributeDTO;
import com.little.g.springcloud.mall.dto.LitemallGoodsDTO;
import com.little.g.springcloud.mall.dto.LitemallGoodsProductDTO;
import com.little.g.springcloud.mall.dto.LitemallGoodsSpecificationDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("商品详情")
@Data
public class GoodsDetailVo {
    @ApiModelProperty("产品信息")
    private LitemallGoodsDTO goods;
    @ApiModelProperty("产品规格")
    private List<LitemallGoodsSpecificationDTO> specifications;
    @ApiModelProperty("商品信息")
    private List<LitemallGoodsProductDTO> products;
    @ApiModelProperty("商品属性")
    private List<LitemallGoodsAttributeDTO> attributes;
    @ApiModelProperty("商品分类")
    private Integer[] categoryIds;

}
