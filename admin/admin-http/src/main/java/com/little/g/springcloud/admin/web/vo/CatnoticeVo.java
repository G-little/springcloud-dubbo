package com.little.g.springcloud.admin.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@ApiModel("管理员通知Vo")
@Data
public class CatnoticeVo {

	@ApiModelProperty("标题")
	private String title;

	@ApiModelProperty("内容")
	private String content;

	@ApiModelProperty("时间")
	private LocalDateTime time;

	@ApiModelProperty("管理员")
	private String admin;

	@ApiModelProperty("头像")
	private String avatar;

}
