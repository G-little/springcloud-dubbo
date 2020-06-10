package com.little.g.springcloud.admin.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("品牌Vo")
@Data
public class BrandVo {
    @ApiModelProperty("品牌ID")
    private Integer value;
    @ApiModelProperty("品牌Label")
    private String label;
}
