package com.little.g.springcloud.mall.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("评论技术vo")
public class CommentCountVo {

	@ApiModelProperty("评论总数")
	private Integer allCount;

	@ApiModelProperty("带图片评论总数")
	private Integer hasPicCount;

}
