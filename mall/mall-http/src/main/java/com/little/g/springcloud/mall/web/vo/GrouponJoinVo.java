package com.little.g.springcloud.mall.web.vo;

import com.little.g.springcloud.mall.dto.LitemallGoodsDTO;
import com.little.g.springcloud.mall.dto.LitemallGrouponDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("参加团购结果Vo")
@Data
public class GrouponJoinVo {
    @ApiModelProperty("团购信息")
    private LitemallGrouponDTO groupon;
    @ApiModelProperty("团购产品信息")
    private LitemallGoodsDTO goods;

}
