package com.little.g.springcloud.mall.web.vo;

import com.little.g.springcloud.mall.dto.LitemallAddressDTO;
import com.little.g.springcloud.mall.dto.LitemallCartDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@ApiModel("购物车结算")
@Data
public class CartCheckoutVo {

    @ApiModelProperty("地址ID")
    private Integer addressId;
    @ApiModelProperty("优惠券ID")
    private Integer couponId;
    @ApiModelProperty("用户优惠券ID")
    private Integer userCouponId;
    @ApiModelProperty("购物车ID")
    private Integer cartId;
    @ApiModelProperty("团购规则ID")
    private Integer grouponRulesId;
    @ApiModelProperty("团购价格")
    private BigDecimal grouponPrice;
    @ApiModelProperty("确认地址")
    private LitemallAddressDTO checkedAddress;
    @ApiModelProperty("可使用优惠券数量")
    private int availableCouponLength;
    @ApiModelProperty("确认商品价格")
    private BigDecimal goodsTotalPrice;
    @ApiModelProperty("运费")
    private BigDecimal freightPrice;
    @ApiModelProperty("优惠")
    private BigDecimal couponPrice;
    @ApiModelProperty("订单总金额")
    private BigDecimal orderTotalPrice;
    @ApiModelProperty("实付金额")
    private BigDecimal actualPrice;
    @ApiModelProperty("确认商品列表")
    private List<LitemallCartDTO> checkedGoodsList;

}
