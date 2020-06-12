package com.little.g.springcloud.mall.web.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel("预支付")
@Data
public class PrepayForm {
    /**
     * 订单ID
     */
    @ApiModelProperty("订单ID")
    @NotNull
    private Integer orderId;

    @ApiModelProperty("支付类型")
    @NotEmpty
    private String payType;
    @ApiModelProperty(hidden = true)
    private String openId;
}
