package com.little.g.springcloud.admin.web.controller.mall;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissions;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissionsDesc;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallFeedbackService;
import com.little.g.springcloud.mall.dto.LitemallFeedbackDTO;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yogeek
 * @date 2018/8/26 1:11
 */
@Api("意见反馈管理")
@RestController
@RequestMapping("/admin/feedback")
@Validated
public class AdminFeedbackController {

	private final Log logger = LogFactory.getLog(AdminFeedbackController.class);

	@Reference
	private LitemallFeedbackService feedbackService;

	@ApiOperation("意见反馈分页查询")
	@RequiresPermissions("admin:feedback:list")
	@RequiresPermissionsDesc(menu = { "用户管理", "意见反馈" }, button = "查询")
	@GetMapping("/list")
	public ResultJson<Page<LitemallFeedbackDTO>> list(Integer userId, String username,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		PageInfo<LitemallFeedbackDTO> pageInfo = feedbackService.querySelective(userId,
				username, page, limit, sort, order);
		return ResponseUtil.okPage(pageInfo);
	}

}
