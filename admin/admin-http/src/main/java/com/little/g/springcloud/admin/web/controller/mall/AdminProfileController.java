package com.little.g.springcloud.admin.web.controller.mall;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.admin.api.AdminUserService;
import com.little.g.springcloud.admin.dto.AdminUserDTO;
import com.little.g.springcloud.admin.params.AdminUserParams;
import com.little.g.springcloud.admin.web.utils.SessionUtils;
import com.little.g.springcloud.admin.web.vo.CatnoticeVo;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.encrypt.MD5Utils;
import com.little.g.springcloud.common.utils.JacksonUtil;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallNoticeAdminService;
import com.little.g.springcloud.mall.api.LitemallNoticeService;
import com.little.g.springcloud.mall.dto.LitemallNoticeAdminDTO;
import com.little.g.springcloud.mall.dto.LitemallNoticeDTO;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.little.g.springcloud.admin.AdminErrorCodes.ADMIN_INVALID_ACCOUNT;

@Api("管理员信息")
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

	@ApiOperation("管理员密码修改")
	@PostMapping("/password")
	public ResultJson create(@RequestBody String body) {
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

	@ApiOperation("未读通知计数")
	@GetMapping("/nnotice")
	public ResultJson<Integer> nNotice() {
		int count = noticeAdminService.countUnread(getAdminId());
		return ResponseUtil.ok(count);
	}

	@ApiOperation("通知分页列表")
	@GetMapping("/lsnotice")
	public ResultJson<Page<LitemallNoticeAdminDTO>> lsNotice(String title, String type,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		PageInfo<LitemallNoticeAdminDTO> pageInfo = noticeAdminService
				.querySelective(title, type, getAdminId(), page, limit, sort, order);
		return ResponseUtil.okPage(pageInfo);
	}

	@ApiOperation("获取notice详情")
	@PostMapping("/catnotice")
	public ResultJson<CatnoticeVo> catNotice(@RequestBody String body) {
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
		CatnoticeVo data = new CatnoticeVo();
		LitemallNoticeDTO notice = noticeService.findById(noticeId);
		data.setTitle(notice.getTitle());
		data.setContent(notice.getContent());
		data.setTime(notice.getUpdateTime());
		Integer adminId = notice.getAdminId();
		if (adminId.equals(0)) {
			data.setAdmin("系统");
		}
		else {
			AdminUserDTO admin = adminUserService.get(notice.getAdminId());
			data.setAdmin(admin.getRealName());
			data.setAvatar("");
		}
		return ResponseUtil.ok(data);
	}

	@ApiOperation("批量标记已读")
	@PostMapping("/bcatnotice")
	public ResultJson bcatNotice(@RequestBody String body) {
		List<Integer> ids = JacksonUtil.parseIntegerList(body, "ids");
		noticeAdminService.markReadByIds(ids, getAdminId());
		return ResponseUtil.ok();
	}

	@ApiOperation("删除通知")
	@PostMapping("/rmnotice")
	public ResultJson rmNotice(@RequestBody String body) {
		Integer id = JacksonUtil.parseInteger(body, "id");
		if (id == null) {
			return ResponseUtil.badArgument();
		}
		noticeAdminService.deleteById(id, getAdminId());
		return ResponseUtil.ok();
	}

	@ApiOperation("批量删除通知")
	@PostMapping("/brmnotice")
	public ResultJson brmNotice(@RequestBody String body) {
		List<Integer> ids = JacksonUtil.parseIntegerList(body, "ids");
		noticeAdminService.deleteByIds(ids, getAdminId());
		return ResponseUtil.ok();
	}

}
