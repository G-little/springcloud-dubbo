package com.little.g.springcloud.admin.web.vo;

import com.little.g.springcloud.mall.dto.LitemallGoodsDTO;
import com.little.g.springcloud.mall.dto.LitemallTopicDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("专题Vo")
public class TopicDetailVo {

	@ApiModelProperty("专题")
	private LitemallTopicDTO topic;

	@ApiModelProperty("专题产品列表")
	private List<LitemallGoodsDTO> goodsList;

}
