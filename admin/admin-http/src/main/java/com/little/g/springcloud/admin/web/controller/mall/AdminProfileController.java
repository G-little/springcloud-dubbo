package com.little.g.springcloud.admin.web.controller.mall;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.admin.api.AdminUserService;
import com.little.g.springcloud.admin.dto.AdminUserDTO;
import com.little.g.springcloud.admin.params.AdminUserParams;
import com.little.g.springcloud.admin.web.utils.SessionUtils;
import com.little.g.springcloud.common.encrypt.MD5Utils;
import com.little.g.springcloud.common.utils.JacksonUtil;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallNoticeAdminService;
import com.little.g.springcloud.mall.api.LitemallNoticeService;
import com.little.g.springcloud.mall.dto.LitemallNoticeAdminDTO;
import com.little.g.springcloud.mall.dto.LitemallNoticeDTO;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.little.g.springcloud.admin.AdminErrorCodes.ADMIN_INVALID_ACCOUNT;

@RestController
@RequestMapping("/admin/profile")
@Validated
public class AdminProfileController {

	private final Log logger = LogFactory.getLog(AdminProfileController.class);

	@Reference
	private AdminUserService adminUserService;

	@Reference
	private LitemallNoticeService noticeService;

	@Reference
	private LitemallNoticeAdminService noticeAdminService;

	@PostMapping("/password")
	public Object create(@RequestBody String body) {
		String oldPassword = JacksonUtil.parseString(body, "oldPassword");
		String newPassword = JacksonUtil.parseString(body, "newPassword");
		if (StringUtils.isEmpty(oldPassword)) {
			return ResponseUtil.badArgument();
		}
		if (StringUtils.isEmpty(newPassword)) {
			return ResponseUtil.badArgument();
		}

		AdminUserDTO adminUserDTO = SessionUtils.get();
		if (!oldPassword.equals(adminUserDTO.getPassword())) {
			return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "账号密码不对");
		}
		AdminUserParams params = new AdminUserParams();
		params.setId(adminUserDTO.getId());
		params.setPassword(MD5Utils.encode(body));
		adminUserService.update(params);

		return ResponseUtil.ok();
	}

	private Integer getAdminId() {
		return SessionUtils.get().getId();
	}

	@GetMapping("/nnotice")
	public Object nNotice() {
		int count = noticeAdminService.countUnread(getAdminId());
		return ResponseUtil.ok(count);
	}

	@GetMapping("/lsnotice")
	public Object lsNotice(String title, String type,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		PageInfo<LitemallNoticeAdminDTO> pageInfo = noticeAdminService
				.querySelective(title, type, getAdminId(), page, limit, sort, order);
		return ResponseUtil.okPage(pageInfo);
	}

	@PostMapping("/catnotice")
	public Object catNotice(@RequestBody String body) {
		Integer noticeId = JacksonUtil.parseInteger(body, "noticeId");
		if (noticeId == null) {
			return ResponseUtil.badArgument();
		}

		LitemallNoticeAdminDTO noticeAdmin = noticeAdminService.find(noticeId,
				getAdminId());
		if (noticeAdmin == null) {
			return ResponseUtil.badArgumentValue();
		}
		// 更新通知记录中的时间
		noticeAdmin.setReadTime(LocalDateTime.now());
		noticeAdminService.update(noticeAdmin);

		// 返回通知的相关信息
		Map<String, Object> data = new HashMap<>();
		LitemallNoticeDTO notice = noticeService.findById(noticeId);
		data.put("title", notice.getTitle());
		data.put("content", notice.getContent());
		data.put("time", notice.getUpdateTime());
		Integer adminId = notice.getAdminId();
		if (adminId.equals(0)) {
			data.put("admin", "系统");
		}
		else {
			AdminUserDTO admin = adminUserService.get(notice.getAdminId());
			data.put("admin", admin.getRealName());
			data.put("avatar", "");
		}
		return ResponseUtil.ok(data);
	}

	@PostMapping("/bcatnotice")
	public Object bcatNotice(@RequestBody String body) {
		List<Integer> ids = JacksonUtil.parseIntegerList(body, "ids");
		noticeAdminService.markReadByIds(ids, getAdminId());
		return ResponseUtil.ok();
	}

	@PostMapping("/rmnotice")
	public Object rmNotice(@RequestBody String body) {
		Integer id = JacksonUtil.parseInteger(body, "id");
		if (id == null) {
			return ResponseUtil.badArgument();
		}
		noticeAdminService.deleteById(id, getAdminId());
		return ResponseUtil.ok();
	}

	@PostMapping("/brmnotice")
	public Object brmNotice(@RequestBody String body) {
		List<Integer> ids = JacksonUtil.parseIntegerList(body, "ids");
		noticeAdminService.deleteByIds(ids, getAdminId());
		return ResponseUtil.ok();
	}

}
