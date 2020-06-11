package com.little.g.springcloud.admin.web.controller.mall;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.admin.api.AdminUserService;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissions;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissionsDesc;
import com.little.g.springcloud.admin.web.utils.SessionUtils;
import com.little.g.springcloud.admin.web.vo.NoticeDetailVo;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.JacksonUtil;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallNoticeAdminService;
import com.little.g.springcloud.mall.api.LitemallNoticeService;
import com.little.g.springcloud.mall.dto.LitemallNoticeAdminDTO;
import com.little.g.springcloud.mall.dto.LitemallNoticeDTO;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.little.g.springcloud.admin.AdminErrorCodes.NOTICE_UPDATE_NOT_ALLOWED;

@Api("通知管理")
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

	@ApiOperation("通知分页查询")
	@RequiresPermissions("admin:notice:list")
	@RequiresPermissionsDesc(menu = { "系统管理", "通知管理" }, button = "查询")
	@GetMapping("/list")
	public ResultJson<Page<LitemallNoticeDTO>> list(String title, String content,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		PageInfo<LitemallNoticeDTO> pageInfo = noticeService.querySelective(title,
				content, page, limit, sort, order);
		return ResponseUtil.okPage(pageInfo);
	}

	private ResultJson validate(LitemallNoticeDTO notice) {
		String title = notice.getTitle();
		if (StringUtils.isEmpty(title)) {
			return ResponseUtil.badArgument();
		}
		return null;
	}

	private Integer getAdminId() {
		return SessionUtils.get().getId();
	}

	@ApiOperation("创建通知")
	@RequiresPermissions("admin:notice:create")
	@RequiresPermissionsDesc(menu = { "推广管理", "通知管理" }, button = "添加")
	@PostMapping("/create")
	public ResultJson<LitemallNoticeDTO> create(@RequestBody LitemallNoticeDTO notice) {
		ResultJson error = validate(notice);
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

	@ApiModelProperty("通知详情")
	@RequiresPermissions("admin:notice:read")
	@RequiresPermissionsDesc(menu = { "推广管理", "通知管理" }, button = "详情")
	@GetMapping("/read")
	public ResultJson<NoticeDetailVo> read(@NotNull Integer id) {
		LitemallNoticeDTO notice = noticeService.findById(id);
		List<LitemallNoticeAdminDTO> noticeAdminList = noticeAdminService
				.queryByNoticeId(id);
		NoticeDetailVo data = new NoticeDetailVo();
		data.setNotice(notice);
		data.setNoticeAdminList(noticeAdminList);
		return ResponseUtil.ok(data);
	}

	@ApiModelProperty("更新通知")
	@RequiresPermissions("admin:notice:update")
	@RequiresPermissionsDesc(menu = { "推广管理", "通知管理" }, button = "编辑")
	@PostMapping("/update")
	public ResultJson<LitemallNoticeDTO> update(@RequestBody LitemallNoticeDTO notice) {
		ResultJson error = validate(notice);
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

	@ApiModelProperty("删除通知")
	@RequiresPermissions("admin:notice:delete")
	@RequiresPermissionsDesc(menu = { "推广管理", "通知管理" }, button = "删除")
	@PostMapping("/delete")
	public ResultJson delete(@RequestBody LitemallNoticeDTO notice) {
		// 1. 删除通知管理员记录
		noticeAdminService.deleteByNoticeId(notice.getId());
		// 2. 删除通知记录
		noticeService.deleteById(notice.getId());
		return ResponseUtil.ok();
	}

	@ApiModelProperty("批量删除通知")
	@RequiresPermissions("admin:notice:batch-delete")
	@RequiresPermissionsDesc(menu = { "推广管理", "通知管理" }, button = "批量删除")
	@PostMapping("/batch-delete")
	public ResultJson batchDelete(@RequestBody String body) {
		List<Integer> ids = JacksonUtil.parseIntegerList(body, "ids");
		// 1. 删除通知管理员记录
		noticeAdminService.deleteByNoticeIds(ids);
		// 2. 删除通知记录
		noticeService.deleteByIds(ids);
		return ResponseUtil.ok();
	}

}
