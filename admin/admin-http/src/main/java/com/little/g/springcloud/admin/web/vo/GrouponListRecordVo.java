package com.little.g.springcloud.admin.web.vo;

import com.little.g.springcloud.mall.dto.LitemallGoodsDTO;
import com.little.g.springcloud.mall.dto.LitemallGrouponDTO;
import com.little.g.springcloud.mall.dto.LitemallGrouponRulesDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel("团购记录Vo")
@Data
public class GrouponListRecordVo {
    private LitemallGrouponDTO groupon;
    private List<LitemallGrouponDTO> subGroupons;
    private LitemallGrouponRulesDTO rules;
    private LitemallGoodsDTO goods;
}
