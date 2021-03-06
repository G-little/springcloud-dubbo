package com.little.g.springcloud.mall.web.controller;

import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.utils.RegexUtil;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.common.web.annotation.LoginUser;
import com.little.g.springcloud.mall.api.LitemallFeedbackService;
import com.little.g.springcloud.mall.dto.LitemallFeedbackDTO;
import com.little.g.springcloud.user.api.UserService;
import com.little.g.springcloud.user.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 意见反馈服务
 *
 * @author Yogeek
 * @date 2018/8/25 14:10
 */
@Api("意见反馈服务")
@RestController
@RequestMapping("/feedback")
@Validated
@Slf4j
public class FeedbackController {

	@Reference
	private LitemallFeedbackService feedbackService;

	@Reference
	private UserService userService;

	private ResultJson validate(LitemallFeedbackDTO feedback) {
		String content = feedback.getContent();
		if (StringUtils.isEmpty(content)) {
			return ResponseUtil.badArgument();
		}

		String type = feedback.getFeedType();
		if (StringUtils.isEmpty(type)) {
			return ResponseUtil.badArgument();
		}

		Boolean hasPicture = feedback.getHasPicture();
		if (hasPicture == null || !hasPicture) {
			feedback.setPicUrls(new String[0]);
		}

		// 测试手机号码是否正确
		String mobile = feedback.getMobile();
		if (StringUtils.isEmpty(mobile)) {
			return ResponseUtil.badArgument();
		}
		if (!RegexUtil.isMobileExact(mobile)) {
			return ResponseUtil.badArgument();
		}
		return null;
	}

	/**
	 * 添加意见反馈
	 * @param userId 用户ID
	 * @param feedback 意见反馈
	 * @return 操作结果
	 */
	@ApiOperation("添加意见反馈")
	@PostMapping("submit")
	public Object submit(@LoginUser Integer userId,
			@RequestBody LitemallFeedbackDTO feedback) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		Object error = validate(feedback);
		if (error != null) {
			return error;
		}

		UserDTO user = userService.getUserById(userId);
		String username = user.getName();
		feedback.setId(null);
		feedback.setUserId(userId);
		feedback.setUsername(username);
		// 状态默认是0，1表示状态已发生变化
		feedback.setStatus(1);
		feedbackService.add(feedback);

		return ResponseUtil.ok();
	}

}
