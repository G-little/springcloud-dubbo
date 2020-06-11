package com.little.g.springcloud.admin.web.vo;

import com.little.g.springcloud.mall.dto.LitemallNoticeAdminDTO;
import com.little.g.springcloud.mall.dto.LitemallNoticeDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("通知详情")
@Data
public class NoticeDetailVo {

	@ApiModelProperty("通知")
	private LitemallNoticeDTO notice;

	@ApiModelProperty("管理员列表")
	private List<LitemallNoticeAdminDTO> noticeAdminList;

}
