package com.little.g.springcloud.mall.web.vo;

import com.little.g.springcloud.mall.dto.ExpressInfoDTO;
import com.little.g.springcloud.mall.dto.LitemallOrderGoodsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("订单详情(订单信息)Vo")
@Data
public class OrderDetailVo {
    @ApiModelProperty("订单信息")
    private OrderDetailInfoVo orderInfo;
    @ApiModelProperty("订单商品")
    private List<LitemallOrderGoodsDTO> orderGoods;
    @ApiModelProperty("快递信息")
    private ExpressInfoDTO expressInfo;
}
