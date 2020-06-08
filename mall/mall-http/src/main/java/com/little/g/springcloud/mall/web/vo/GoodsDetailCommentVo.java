package com.little.g.springcloud.mall.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@ApiModel("商品详情评论Vo")
@Data
public class GoodsDetailCommentVo {

	@ApiModelProperty("评论计数")
	private Long count;

	@ApiModelProperty("评论数据")
	private List<CommentVo> data;

	@Data
	@ApiModel("评论")
	public static class CommentVo {

		private Integer id;

		private LocalDateTime addTime;

		private String content;

		private String adminContent;

		private String nickname;

		private String avatar;

		private String[] picList;

	}

}
