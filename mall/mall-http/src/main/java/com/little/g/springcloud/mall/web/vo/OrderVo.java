package com.little.g.springcloud.mall.web.vo;

import com.little.g.springcloud.mall.util.OrderHandleOption;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@ApiModel("订单Vo")
@Data
public class OrderVo {
    @ApiModelProperty("订单ID")
    private Integer id;
    @ApiModelProperty("订单SN")
    private String orderSn;
    @ApiModelProperty("实际金额")
    private BigDecimal actualPrice;
    @ApiModelProperty("状态描述")
    private String orderStatusText;
    @ApiModelProperty("状态描述")
    private OrderHandleOption handleOption;
    @ApiModelProperty("售后状态")
    private Short aftersaleStatus;
    @ApiModelProperty("是否集团订购")
    private boolean isGroupin;
    @ApiModelProperty("产品信息")
    private List<OrderGoodsVo> goodsList;

}
