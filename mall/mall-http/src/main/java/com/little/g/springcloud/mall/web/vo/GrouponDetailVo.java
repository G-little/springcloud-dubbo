package com.little.g.springcloud.mall.web.vo;

import com.little.g.springcloud.mall.dto.LitemallGrouponDTO;
import com.little.g.springcloud.mall.dto.LitemallGrouponRulesDTO;
import com.little.g.springcloud.user.vo.UserVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("团购规则详情Vo")
@Data
public class GrouponDetailVo extends OrderDetailVo {

	@ApiModelProperty("关联团购规则ID")
	private Integer linkGrouponId;

	@ApiModelProperty("创建人")
	private UserVo creator;

	@ApiModelProperty("参与人")
	private List<UserVo> joiners;

	@ApiModelProperty("团购信息")
	private LitemallGrouponDTO groupon;

	@ApiModelProperty("团购规则")
	private LitemallGrouponRulesDTO rules;

}
