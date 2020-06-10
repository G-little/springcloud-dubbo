package com.little.g.springcloud.admin.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("看板Vo")
@Data
public class DashboardVo {
    @ApiModelProperty("用户数")
    private Integer userTotal;
    @ApiModelProperty("商品数")
    private Integer goodsTotal;
    @ApiModelProperty("货品数")
    private Integer productTotal;
    @ApiModelProperty("订单数")
    private Integer orderTotal;

}
