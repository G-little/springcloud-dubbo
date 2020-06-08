package com.little.g.springcloud.mall.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel("订单商品Vo")
@Data
public class OrderGoodsVo {

    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("商品名")
    private String goodsName;
    @ApiModelProperty("购买数量")
    private Short number;
    @ApiModelProperty("商品图片")
    private String picUrl;
    @ApiModelProperty("商品规格")
    private String[] specifications;
    @ApiModelProperty("价格")
    private BigDecimal price;




}
