package com.little.g.springcloud.mall.web.controller;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.common.web.annotation.LoginUser;
import com.little.g.springcloud.mall.api.*;
import com.little.g.springcloud.mall.dto.*;
import com.little.g.springcloud.mall.system.SystemConfig;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import com.little.g.springcloud.mall.web.vo.GoodsDetailCommentVo;
import com.little.g.springcloud.mall.web.vo.GoodsDetailVo;
import com.little.g.springcloud.mall.web.vo.GoodsSearchPageResultVo;
import com.little.g.springcloud.user.api.UserService;
import com.little.g.springcloud.user.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 商品服务
 */
@Api("商品服务")
@RestController
@RequestMapping("/goods")
@Validated
@Slf4j
public class GoodsController {

	@Reference
	private LitemallGoodsService goodsService;

	@Reference
	private LitemallGoodsProductService productService;

	@Reference
	private LitemallIssueService goodsIssueService;

	@Reference
	private LitemallGoodsAttributeService goodsAttributeService;

	@Reference
	private LitemallBrandService brandService;

	@Reference
	private LitemallCommentService commentService;

	@Reference
	private UserService userService;

	@Reference
	private LitemallCollectService collectService;

	@Reference
	private LitemallFootprintService footprintService;

	@Reference
	private LitemallCategoryService categoryService;

	@Reference
	private LitemallSearchHistoryService searchHistoryService;

	@Reference
	private LitemallGoodsSpecificationService goodsSpecificationService;

	@Reference
	private LitemallGrouponRulesService rulesService;

	private final static ArrayBlockingQueue<Runnable> WORK_QUEUE = new ArrayBlockingQueue<>(
			9);

	private final static RejectedExecutionHandler HANDLER = new ThreadPoolExecutor.CallerRunsPolicy();

	private static ThreadPoolExecutor executorService = new ThreadPoolExecutor(16, 16,
			1000, TimeUnit.MILLISECONDS, WORK_QUEUE, HANDLER);

	/**
	 * 商品详情
	 * <p>
	 * 用户可以不登录。 如果用户登录，则记录用户足迹以及返回用户收藏信息。
	 * @param userId 用户ID
	 * @param id 商品ID
	 * @return 商品详情
	 */
	@ApiOperation(value = "商品详情", notes = "用户可以不登录。 如果用户登录，则记录用户足迹以及返回用户收藏信息。")
	@ApiImplicitParam(name = "id", required = true, value = "商品ID")
	@GetMapping("detail")
	public Object detail(@LoginUser Integer userId, @RequestParam @NotNull Integer id) {
		// 商品信息
		LitemallGoodsDTO info = goodsService.findById(id);

		// 商品属性
		Callable<List<LitemallGoodsAttributeDTO>> goodsAttributeListCallable = () -> goodsAttributeService
				.queryByGid(id);

		// 商品规格 返回的是定制的GoodsSpecificationVo
		Callable<List<GoodsSpecificationListDTO>> objectCallable = () -> goodsSpecificationService
				.getSpecificationVoList(id);

		// 商品规格对应的数量和价格
		Callable<List<LitemallGoodsProductDTO>> productListCallable = () -> productService
				.queryByGid(id);

		// 商品问题，这里是一些通用问题
		Callable<PageInfo<LitemallIssueDTO>> issueCallable = () -> goodsIssueService
				.querySelective("", 1, 4, "", "");

		// 商品品牌商
		Callable<LitemallBrandDTO> brandCallable = () -> {
			Integer brandId = info.getBrandId();
			LitemallBrandDTO brand;
			if (brandId == 0) {
				brand = new LitemallBrandDTO();
			}
			else {
				brand = brandService.findById(info.getBrandId());
			}
			return brand;
		};

		// 评论
		Callable<GoodsDetailCommentVo> commentsCallable = () -> {
			PageInfo<LitemallCommentDTO> comments = commentService.queryGoodsByGid(id, 0,
					2);
			List<GoodsDetailCommentVo.CommentVo> commentsVo = new ArrayList<>(
					comments.getSize());
			long commentCount = comments.getTotal();
			for (LitemallCommentDTO comment : comments.getList()) {
				GoodsDetailCommentVo.CommentVo c = new GoodsDetailCommentVo.CommentVo();
				c.setId(comment.getId());
				c.setAddTime(comment.getAddTime());
				c.setContent(comment.getContent());
				c.setAdminContent(comment.getAdminContent());
				UserDTO user = userService.getUserById(comment.getUserId());
				c.setNickname(user == null ? "" : user.getName());
				c.setAvatar(user == null ? "" : user.getAvatar());
				c.setPicList(comment.getPicUrls());
				commentsVo.add(c);
			}
			GoodsDetailCommentVo commentVo = new GoodsDetailCommentVo();
			commentVo.setCount(commentCount);
			commentVo.setData(commentsVo);
			return commentVo;
		};

		// 团购信息
		Callable<List<LitemallGrouponRulesDTO>> grouponRulesCallable = () -> rulesService
				.queryByGoodsId(id);

		// 用户收藏
		int userHasCollect = 0;
		if (userId != null) {
			userHasCollect = collectService.count(userId, id);
		}

		// 记录用户的足迹 异步处理
		if (userId != null) {
			executorService.execute(() -> {
				LitemallFootprintDTO footprint = new LitemallFootprintDTO();
				footprint.setUserId(userId);
				footprint.setGoodsId(id);
				footprintService.add(footprint);
			});
		}
		FutureTask<List<LitemallGoodsAttributeDTO>> goodsAttributeListTask = new FutureTask<>(
				goodsAttributeListCallable);
		FutureTask<List<GoodsSpecificationListDTO>> objectCallableTask = new FutureTask<>(
				objectCallable);
		FutureTask<List<LitemallGoodsProductDTO>> productListCallableTask = new FutureTask<>(
				productListCallable);
		FutureTask<PageInfo<LitemallIssueDTO>> issueCallableTask = new FutureTask<>(
				issueCallable);
		FutureTask<GoodsDetailCommentVo> commentsCallableTsk = new FutureTask<>(
				commentsCallable);
		FutureTask<LitemallBrandDTO> brandCallableTask = new FutureTask<>(brandCallable);
		FutureTask<List<LitemallGrouponRulesDTO>> grouponRulesCallableTask = new FutureTask<>(
				grouponRulesCallable);

		executorService.submit(goodsAttributeListTask);
		executorService.submit(objectCallableTask);
		executorService.submit(productListCallableTask);
		executorService.submit(issueCallableTask);
		executorService.submit(commentsCallableTsk);
		executorService.submit(brandCallableTask);
		executorService.submit(grouponRulesCallableTask);

		GoodsDetailVo data = new GoodsDetailVo();

		try {
			data.setInfo(info);
			data.setUserHasCollect(userHasCollect);
			data.setIssue(issueCallableTask.get());
			data.setComment(commentsCallableTsk.get());
			data.setSpecificationList(objectCallableTask.get());
			data.setProductList(productListCallableTask.get());
			data.setAttribute(goodsAttributeListTask.get());
			data.setBrand(brandCallableTask.get());
			data.setGroupon(grouponRulesCallableTask.get());
			// SystemConfig.isAutoCreateShareImage()
			data.setShare(SystemConfig.isAutoCreateShareImage());

		}
		catch (Exception e) {
			e.printStackTrace();
		}

		// 商品分享图片地址
		data.setShareImage(info.getShareUrl());
		return ResponseUtil.ok(data);
	}

	/**
	 * 商品分类类目
	 * @param id 分类类目ID
	 * @return 商品分类类目
	 */
	@GetMapping("category")
	public Object category(@NotNull Integer id) {
		LitemallCategoryDTO cur = categoryService.findById(id);
		LitemallCategoryDTO parent = null;
		List<LitemallCategoryDTO> children = null;

		if (cur.getPid() == 0) {
			parent = cur;
			children = categoryService.queryByPid(cur.getId());
			cur = children.size() > 0 ? children.get(0) : cur;
		}
		else {
			parent = categoryService.findById(cur.getPid());
			children = categoryService.queryByPid(cur.getPid());
		}
		Map<String, Object> data = new HashMap<>();
		data.put("currentCategory", cur);
		data.put("parentCategory", parent);
		data.put("brotherCategory", children);
		return ResponseUtil.ok(data);
	}

	/**
	 * 根据条件搜素商品
	 * <p>
	 * 1. 这里的前五个参数都是可选的，甚至都是空 2. 用户是可选登录，如果登录，则记录用户的搜索关键字
	 * @param categoryId 分类类目ID，可选
	 * @param brandId 品牌商ID，可选
	 * @param keyword 关键字，可选
	 * @param isNew 是否新品，可选
	 * @param isHot 是否热买，可选
	 * @param userId 用户ID
	 * @param page 分页页数
	 * @param limit 分页大小
	 * @param sort 排序方式，支持"add_time", "retail_price"或"name"
	 * @param order 排序类型，顺序或者降序
	 * @return 根据条件搜素的商品详情
	 */
	@ApiOperation(value = "根据条件搜素商品",
			notes = "这里的前五个参数都是可选的，甚至都是空 2. 用户是可选登录，如果登录，则记录用户的搜索关键字")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "brandId", value = "品牌商ID", dataType = "int",
					required = false, example = "1"),
			@ApiImplicitParam(name = "categoryId", value = "分类类目ID，可选", dataType = "int",
					required = false, example = "1"),
			@ApiImplicitParam(name = "brandId", value = "品牌商I", dataType = "int",
					required = false, example = "1"),
			@ApiImplicitParam(name = "keyword", value = "关键字", dataType = "String",
					required = false),
			@ApiImplicitParam(name = "isNew", value = "是否新品", dataType = "boolean",
					required = false),
			@ApiImplicitParam(name = "isHot", value = "是否热买", dataType = "boolean",
					required = false),
			@ApiImplicitParam(name = "page", value = "分页", dataType = "int",
					required = false, defaultValue = "1", example = "1"),
			@ApiImplicitParam(name = "limit", value = "单页条数", dataType = "int",
					required = false, defaultValue = "10", example = "10") })

	@GetMapping("list")
	public ResultJson<GoodsSearchPageResultVo> list(
			@RequestParam(required = false) Integer categoryId,
			@RequestParam(required = false) Integer brandId,
			@RequestParam(required = false) String keyword,
			@RequestParam(required = false) Boolean isNew,
			@RequestParam(required = false) Boolean isHot, @LoginUser Integer userId,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort(accepts = { "add_time", "retail_price", "name" }) @RequestParam(
					defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {

		// 添加到搜索历史
		if (userId != null && !StringUtils.isEmpty(keyword)) {
			LitemallSearchHistoryDTO searchHistoryVo = new LitemallSearchHistoryDTO();
			searchHistoryVo.setKeyword(keyword);
			searchHistoryVo.setUserId(userId);
			searchHistoryVo.setFrom("wx");
			searchHistoryService.save(searchHistoryVo);
		}

		// 查询列表数据
		PageInfo<LitemallGoodsDTO> goodsList = goodsService.querySelective(categoryId,
				brandId, keyword, isHot, isNew, page, limit, sort, order);

		// 查询商品所属类目列表。
		List<Integer> goodsCatIds = goodsService.getCatIds(brandId, keyword, isHot,
				isNew);
		List<LitemallCategoryDTO> categoryList = null;
		if (goodsCatIds.size() != 0) {
			categoryList = categoryService.queryL2ByIds(goodsCatIds);
		}
		else {
			categoryList = new ArrayList<>(0);
		}

		GoodsSearchPageResultVo vo = Page.newInstance(goodsList,
				GoodsSearchPageResultVo.class);
		vo.setFilterCategoryList(categoryList);

		// 因为这里需要返回额外的filterCategoryList参数，因此不能方便使用ResponseUtil.okList
		return ResponseUtil.ok(vo);
	}

	/**
	 * 商品详情页面“大家都在看”推荐商品
	 * @param id, 商品ID
	 * @return 商品详情页面推荐商品
	 */
	@ApiOperation("商品详情页面“大家都在看”推荐商品")
	@ApiImplicitParam(name = "id", value = "商品ID")
	@GetMapping("related")
	public ResultJson<Page<LitemallGoodsDTO>> related(@RequestParam @NotNull Integer id) {
		LitemallGoodsDTO goods = goodsService.findById(id);
		if (goods == null) {
			return ResponseUtil.badArgumentValue();
		}

		// 目前的商品推荐算法仅仅是推荐同类目的其他商品
		int cid = goods.getCategoryId();

		// 查找六个相关商品
		int related = 6;
		List<LitemallGoodsDTO> goodsList = goodsService.queryByCategory(cid, 0, related);
		return ResponseUtil.okList(goodsList);
	}

	/**
	 * 在售的商品总数
	 * @return 在售的商品总数
	 */
	@ApiOperation("在售的商品总数")
	@GetMapping("count")
	public Object count() {
		Integer goodsCount = goodsService.queryOnSale();
		return ResponseUtil.ok(goodsCount);
	}

}
