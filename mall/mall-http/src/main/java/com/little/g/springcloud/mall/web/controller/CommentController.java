package com.little.g.springcloud.mall.web.controller;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.common.web.annotation.LoginUser;
import com.little.g.springcloud.mall.api.LitemallCommentService;
import com.little.g.springcloud.mall.api.LitemallGoodsService;
import com.little.g.springcloud.mall.api.LitemallTopicService;
import com.little.g.springcloud.mall.dto.LitemallCommentDTO;
import com.little.g.springcloud.mall.web.vo.CommentCountVo;
import com.little.g.springcloud.mall.web.vo.CommentListVo;
import com.little.g.springcloud.user.api.UserService;
import com.little.g.springcloud.user.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户评论服务
 */
@Api("用户评论服务")
@RestController
@RequestMapping("comment")
@Slf4j
@Validated
public class CommentController {

	@Reference
	private LitemallCommentService commentService;

	@Reference
	private UserService userService;

	@Reference
	private LitemallGoodsService goodsService;

	@Reference
	private LitemallTopicService topicService;

	private ResultJson validate(LitemallCommentDTO comment) {
		String content = comment.getContent();
		if (StringUtils.isEmpty(content)) {
			return ResponseUtil.badArgument();
		}

		Short star = comment.getStar();
		if (star == null) {
			return ResponseUtil.badArgument();
		}
		if (star < 0 || star > 5) {
			return ResponseUtil.badArgumentValue();
		}

		Byte type = comment.getType();
		Integer valueId = comment.getValueId();
		if (type == null || valueId == null) {
			return ResponseUtil.badArgument();
		}
		if (type == 0) {
			if (goodsService.findById(valueId) == null) {
				return ResponseUtil.badArgumentValue();
			}
		}
		else if (type == 1) {
			if (topicService.findById(valueId) == null) {
				return ResponseUtil.badArgumentValue();
			}
		}
		else {
			return ResponseUtil.badArgumentValue();
		}
		Boolean hasPicture = comment.getHasPicture();
		if (hasPicture == null || !hasPicture) {
			comment.setPicUrls(new String[0]);
		}
		return null;
	}

	/**
	 * 发表评论
	 * @param userId 用户ID
	 * @param comment 评论内容
	 * @return 发表评论操作结果
	 */
	@ApiOperation("发表评论")
	@PostMapping("post")
	public ResultJson<LitemallCommentDTO> post(@LoginUser Integer userId,
			@RequestBody LitemallCommentDTO comment) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		ResultJson error = validate(comment);
		if (error != null) {
			return error;
		}

		comment.setUserId(userId);
		commentService.save(comment);
		return ResponseUtil.ok(comment);
	}

	/**
	 * 评论数量
	 * @param type 类型ID。 如果是0，则查询商品评论；如果是1，则查询专题评论。
	 * @param valueId 商品或专题ID。如果type是0，则是商品ID；如果type是1，则是专题ID。
	 * @return 评论数量
	 */
	@ApiOperation("评论数量")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "类型ID。 如果是0，则查询商品评论；如果是1，则查询专题评论。",
					allowableValues = "0,1"),
			@ApiImplicitParam(value = "商品或专题ID。如果type是0，则是商品ID；如果type是1，则是专题ID。",
					allowableValues = "0,1") })
	@GetMapping("count")
	public ResultJson<CommentCountVo> count(@RequestParam @NotNull Byte type,
			@RequestParam @NotNull Integer valueId) {
		int allCount = commentService.count(type, valueId, 0);
		int hasPicCount = commentService.count(type, valueId, 1);
		CommentCountVo vo = new CommentCountVo();
		vo.setAllCount(allCount);
		vo.setHasPicCount(hasPicCount);
		return ResponseUtil.ok(vo);
	}

	/**
	 * 评论列表
	 * @param type 类型ID。 如果是0，则查询商品评论；如果是1，则查询专题评论。
	 * @param valueId 商品或专题ID。如果type是0，则是商品ID；如果type是1，则是专题ID。
	 * @param showType 显示类型。如果是0，则查询全部；如果是1，则查询有图片的评论。
	 * @param page 分页页数
	 * @param limit 分页大小
	 * @return 评论列表
	 */
	@ApiOperation("评论列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "type", value = "类型ID。 如果是0，则查询商品评论；如果是1，则查询专题评论。",
					allowableValues = "0,1", dataType = "byte", required = true,
					example = "0"),
			@ApiImplicitParam(name = "valueId",
					value = "商品或专题ID。如果type是0，则是商品ID；如果type是1，则是专题ID。", dataType = "int",
					required = true, example = "1"),
			@ApiImplicitParam(name = "showType",
					value = "显示类型。如果是0，则查询全部；如果是1，则查询有图片的评论。", allowableValues = "0,1",
					dataType = "int", required = false, example = "1"),
			@ApiImplicitParam(name = "page", value = "分页", dataType = "int",
					required = false, defaultValue = "1", example = "1"),
			@ApiImplicitParam(name = "limit", value = "单页条数", dataType = "int",
					required = false, defaultValue = "10", example = "10") })
	@GetMapping("list")
	public ResultJson<Page<CommentListVo>> list(@RequestParam @NotNull Byte type,
			@RequestParam @NotNull Integer valueId,
			@RequestParam @NotNull Integer showType,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit) {
		PageInfo<LitemallCommentDTO> commentPage = commentService.query(type, valueId,
				showType, page, limit);
		if (commentPage == null) {
			return ResponseUtil.ok();
		}
		List<LitemallCommentDTO> commentList = commentPage.getList();

		List<CommentListVo> commentVoList = new ArrayList<>(commentList.size());
		commentList.forEach(comment -> {
			CommentListVo commentVo = new CommentListVo();
			commentVo.setAddTime(comment.getAddTime());
			commentVo.setContent(comment.getContent());
			commentVo.setAdminContent(comment.getAdminContent());
			commentVo.setPicList(comment.getPicUrls());
			UserDTO userInfo = userService.getUserById(comment.getUserId());
			commentVo.setUserInfo(userInfo);
			commentVoList.add(commentVo);
		});
		return ResponseUtil.okList(commentVoList, commentPage);
	}

}
