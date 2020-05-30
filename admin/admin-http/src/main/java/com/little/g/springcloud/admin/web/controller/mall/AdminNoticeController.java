package com.little.g.springcloud.admin.web.controller.mall;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.admin.api.AdminUserService;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissions;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissionsDesc;
import com.little.g.springcloud.admin.web.utils.SessionUtils;
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

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.little.g.springcloud.admin.AdminErrorCodes.NOTICE_UPDATE_NOT_ALLOWED;

@RestController
@RequestMapping("/admin/notice")
@Validated
public class AdminNoticeController {

	private final Log logger = LogFactory.getLog(AdminNoticeController.class);

	@Reference
	private LitemallNoticeService noticeService;

	@Reference
	private LitemallNoticeAdminService noticeAdminService;

	@Resource
	private AdminUserService adminUserService;

	@RequiresPermissions("admin:notice:list")
	@RequiresPermissionsDesc(menu = { "系统管理", "通知管理" }, button = "查询")
	@GetMapping("/list")
	public Object list(String title, String content,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		PageInfo<LitemallNoticeDTO> pageInfo = noticeService.querySelective(title,
				content, page, limit, sort, order);
		return ResponseUtil.okPage(pageInfo);
	}

	private Object validate(LitemallNoticeDTO notice) {
		String title = notice.getTitle();
		if (StringUtils.isEmpty(title)) {
			return ResponseUtil.badArgument();
		}
		return null;
	}

	private Integer getAdminId() {
		return SessionUtils.get().getId();
	}

	@RequiresPermissions("admin:notice:create")
	@RequiresPermissionsDesc(menu = { "推广管理", "通知管理" }, button = "添加")
	@PostMapping("/create")
	public Object create(@RequestBody LitemallNoticeDTO notice) {
		Object error = validate(notice);
		if (error != null) {
			return error;
		}
		// 1. 添加通知记录
		notice.setAdminId(getAdminId());
		noticeService.add(notice);
		// 2. 添加管理员通知记录
		List<LitemallNoticeAdminDTO> adminList = null;
		// adminService.all();
		LitemallNoticeAdminDTO noticeAdmin = new LitemallNoticeAdminDTO();
		noticeAdmin.setNoticeId(notice.getId());
		noticeAdmin.setNoticeTitle(notice.getTitle());
		for (LitemallNoticeAdminDTO admin : adminList) {
			noticeAdmin.setAdminId(admin.getId());
			noticeAdminService.add(noticeAdmin);
		}
		return ResponseUtil.ok(notice);
	}

	@RequiresPermissions("admin:notice:read")
	@RequiresPermissionsDesc(menu = { "推广管理", "通知管理" }, button = "详情")
	@GetMapping("/read")
	public Object read(@NotNull Integer id) {
		LitemallNoticeDTO notice = noticeService.findById(id);
		List<LitemallNoticeAdminDTO> noticeAdminList = noticeAdminService
				.queryByNoticeId(id);
		Map<String, Object> data = new HashMap<>(2);
		data.put("notice", notice);
		data.put("noticeAdminList", noticeAdminList);
		return ResponseUtil.ok(data);
	}

	@RequiresPermissions("admin:notice:update")
	@RequiresPermissionsDesc(menu = { "推广管理", "通知管理" }, button = "编辑")
	@PostMapping("/update")
	public Object update(@RequestBody LitemallNoticeDTO notice) {
		Object error = validate(notice);
		if (error != null) {
			return error;
		}
		LitemallNoticeDTO originalNotice = noticeService.findById(notice.getId());
		if (originalNotice == null) {
			return ResponseUtil.badArgument();
		}
		// 如果通知已经有人阅读过，则不支持编辑
		if (noticeAdminService.countReadByNoticeId(notice.getId()) > 0) {
			return ResponseUtil.fail(NOTICE_UPDATE_NOT_ALLOWED, "通知已被阅读，不能重新编辑");
		}
		// 1. 更新通知记录
		notice.setAdminId(getAdminId());
		noticeService.updateById(notice);
		// 2. 更新管理员通知记录
		if (!originalNotice.getTitle().equals(notice.getTitle())) {
			LitemallNoticeAdminDTO noticeAdmin = new LitemallNoticeAdminDTO();
			noticeAdmin.setNoticeTitle(notice.getTitle());
			noticeAdminService.updateByNoticeId(noticeAdmin, notice.getId());
		}
		return ResponseUtil.ok(notice);
	}

	@RequiresPermissions("admin:notice:delete")
	@RequiresPermissionsDesc(menu = { "推广管理", "通知管理" }, button = "删除")
	@PostMapping("/delete")
	public Object delete(@RequestBody LitemallNoticeDTO notice) {
		// 1. 删除通知管理员记录
		noticeAdminService.deleteByNoticeId(notice.getId());
		// 2. 删除通知记录
		noticeService.deleteById(notice.getId());
		return ResponseUtil.ok();
	}

	@RequiresPermissions("admin:notice:batch-delete")
	@RequiresPermissionsDesc(menu = { "推广管理", "通知管理" }, button = "批量删除")
	@PostMapping("/batch-delete")
	public Object batchDelete(@RequestBody String body) {
		List<Integer> ids = JacksonUtil.parseIntegerList(body, "ids");
		// 1. 删除通知管理员记录
		noticeAdminService.deleteByNoticeIds(ids);
		// 2. 删除通知记录
		noticeService.deleteByIds(ids);
		return ResponseUtil.ok();
	}

}
