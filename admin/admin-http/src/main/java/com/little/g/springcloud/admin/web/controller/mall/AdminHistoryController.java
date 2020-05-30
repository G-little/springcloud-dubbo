package com.little.g.springcloud.admin.web.controller.mall;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissions;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissionsDesc;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallSearchHistoryService;
import com.little.g.springcloud.mall.dto.LitemallSearchHistoryDTO;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/history")
public class AdminHistoryController {

	private final Log logger = LogFactory.getLog(AdminHistoryController.class);

	@Reference
	private LitemallSearchHistoryService searchHistoryService;

	@RequiresPermissions("admin:history:list")
	@RequiresPermissionsDesc(menu = { "用户管理", "搜索历史" }, button = "查询")
	@GetMapping("/list")
	public Object list(String userId, String keyword,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		PageInfo<LitemallSearchHistoryDTO> pageInfo = searchHistoryService
				.querySelective(userId, keyword, page, limit, sort, order);
		return ResponseUtil.okPage(pageInfo);
	}

}
