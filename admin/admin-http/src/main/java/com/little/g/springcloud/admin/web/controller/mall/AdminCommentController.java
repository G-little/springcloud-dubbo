package com.little.g.springcloud.admin.web.controller.mall;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissions;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissionsDesc;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallCommentService;
import com.little.g.springcloud.mall.dto.LitemallCommentDTO;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("评论管理")
@RestController
@RequestMapping("/admin/comment")
@Validated
public class AdminCommentController {

	private final Log logger = LogFactory.getLog(AdminCommentController.class);

	@Reference
	private LitemallCommentService commentService;

	@ApiOperation("评论分页查询")
	@RequiresPermissions("admin:comment:list")
	@RequiresPermissionsDesc(menu = { "商品管理", "评论管理" }, button = "查询")
	@GetMapping("/list")
	public ResultJson<Page<LitemallCommentDTO>> list(String userId, String valueId,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		PageInfo<LitemallCommentDTO> pageInfo = commentService.querySelective(userId,
				valueId, page, limit, sort, order);
		return ResponseUtil.okPage(pageInfo);
	}

	@ApiOperation("评论删除")
	@RequiresPermissions("admin:comment:delete")
	@RequiresPermissionsDesc(menu = { "商品管理", "评论管理" }, button = "删除")
	@PostMapping("/delete")
	public ResultJson delete(@RequestBody LitemallCommentDTO comment) {
		Integer id = comment.getId();
		if (id == null) {
			return ResponseUtil.badArgument();
		}
		commentService.deleteById(id);
		return ResponseUtil.ok();
	}

}
