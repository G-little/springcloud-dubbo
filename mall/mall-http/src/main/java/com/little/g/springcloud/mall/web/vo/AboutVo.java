package com.little.g.springcloud.mall.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("商城介绍信息")
@Data
public class AboutVo {

    @ApiModelProperty("商城名")
    private String name;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("电话")
    private String phone;
    @ApiModelProperty("qq")
    private String qq;
    @ApiModelProperty("维度")
    private String longitude;
    @ApiModelProperty("经度")
    private String latitude;


}
