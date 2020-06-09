package com.little.g.springcloud.mall.web.vo;

import com.little.g.springcloud.mall.dto.LitemallGoodsDTO;
import com.little.g.springcloud.mall.dto.LitemallTopicDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("专题")
@Data
public class TopicDetailVo {

	@ApiModelProperty("专题商品列表")
	private List<LitemallGoodsDTO> goods;

	@ApiModelProperty("专题")
	private LitemallTopicDTO topic;

}
