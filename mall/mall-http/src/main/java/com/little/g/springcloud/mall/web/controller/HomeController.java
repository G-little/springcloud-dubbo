package com.little.g.springcloud.mall.web.controller;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.common.web.annotation.LoginUser;
import com.little.g.springcloud.mall.api.*;
import com.little.g.springcloud.mall.dto.*;
import com.little.g.springcloud.mall.system.SystemConfig;
import com.little.g.springcloud.mall.web.manager.GrouponRuleManager;
import com.little.g.springcloud.mall.web.manager.HomeCacheManager;
import com.little.g.springcloud.mall.web.vo.AboutVo;
import com.little.g.springcloud.mall.web.vo.GrouponRuleVo;
import com.little.g.springcloud.mall.web.vo.IndexCategoryVo;
import com.little.g.springcloud.mall.web.vo.IndexDataVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 首页服务
 */

@Api("首页服务")
@RestController
@RequestMapping("/home")
@Validated
public class HomeController {

	private final Log logger = LogFactory.getLog(HomeController.class);

	@Reference
	private LitemallAdService adService;

	@Reference
	private LitemallGoodsService goodsService;

	@Reference
	private LitemallBrandService brandService;

	@Reference
	private LitemallTopicService topicService;

	@Reference
	private LitemallCategoryService categoryService;

	@Autowired
	private GrouponRuleManager grouponRuleManager;

	@Reference
	private LitemallCouponService couponService;

	private final static ArrayBlockingQueue<Runnable> WORK_QUEUE = new ArrayBlockingQueue<>(
			9);

	private final static RejectedExecutionHandler HANDLER = new ThreadPoolExecutor.CallerRunsPolicy();

	private static ThreadPoolExecutor executorService = new ThreadPoolExecutor(9, 9, 1000,
			TimeUnit.MILLISECONDS, WORK_QUEUE, HANDLER);

	@ApiOperation("清除缓存")
	@ApiImplicitParam(name = "key", required = true, value = "缓存key")
	@GetMapping("/cache")
	public ResultJson cache(@NotNull String key) {
		if (!key.equals("litemall_cache")) {
			return ResponseUtil.fail();
		}

		// 清除缓存
		HomeCacheManager.clearAll();
		return ResponseUtil.ok("缓存已清除");
	}

	/**
	 * 首页数据
	 * @param userId 当用户已经登录时，非空。为登录状态为null
	 * @return 首页数据
	 */
	@ApiOperation("首页数据")
	@GetMapping("/index")
	public ResultJson<IndexDataVo> index(@LoginUser Integer userId) {
		// 优先从缓存中读取
		if (HomeCacheManager.hasData(HomeCacheManager.INDEX)) {
			return ResponseUtil.ok(HomeCacheManager.getCacheData(HomeCacheManager.INDEX,
					IndexDataVo.class));
		}
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		Callable<List<LitemallAdDTO>> bannerListCallable = () -> adService.queryIndex();

		Callable<List<LitemallCategoryDTO>> channelListCallable = () -> categoryService
				.queryChannel();

		Callable<List<LitemallCouponDTO>> couponListCallable;
		if (userId == null) {
			couponListCallable = () -> couponService.queryList(0, 3);
		}
		else {
			couponListCallable = () -> couponService.queryAvailableList(userId, 0, 3);
		}

		Callable<List<LitemallGoodsDTO>> newGoodsListCallable = () -> goodsService
				.queryByNew(0, SystemConfig.getNewLimit());

		Callable<List<LitemallGoodsDTO>> hotGoodsListCallable = () -> goodsService
				.queryByHot(0, SystemConfig.getHotLimit());

		Callable<PageInfo<LitemallBrandDTO>> brandListCallable = () -> brandService
				.query(0, SystemConfig.getBrandLimit());

		Callable<List<LitemallTopicDTO>> topicListCallable = () -> topicService
				.queryList(0, SystemConfig.getTopicLimit());

		// 团购专区
		Callable<PageInfo<GrouponRuleVo>> grouponListCallable = () -> grouponRuleManager
				.queryList(0, 5);

		Callable<List<IndexCategoryVo>> floorGoodsListCallable = this::getCategoryList;

		FutureTask<List<LitemallAdDTO>> bannerTask = new FutureTask<>(bannerListCallable);
		FutureTask<List<LitemallCategoryDTO>> channelTask = new FutureTask<>(
				channelListCallable);
		FutureTask<List<LitemallCouponDTO>> couponListTask = new FutureTask<>(
				couponListCallable);
		FutureTask<List<LitemallGoodsDTO>> newGoodsListTask = new FutureTask<>(
				newGoodsListCallable);
		FutureTask<List<LitemallGoodsDTO>> hotGoodsListTask = new FutureTask<>(
				hotGoodsListCallable);
		FutureTask<PageInfo<LitemallBrandDTO>> brandListTask = new FutureTask<>(
				brandListCallable);
		FutureTask<List<LitemallTopicDTO>> topicListTask = new FutureTask<>(
				topicListCallable);
		FutureTask<PageInfo<GrouponRuleVo>> grouponListTask = new FutureTask<>(
				grouponListCallable);
		FutureTask<List<IndexCategoryVo>> floorGoodsListTask = new FutureTask<>(
				floorGoodsListCallable);

		executorService.submit(bannerTask);
		executorService.submit(channelTask);
		executorService.submit(couponListTask);
		executorService.submit(newGoodsListTask);
		executorService.submit(hotGoodsListTask);
		executorService.submit(brandListTask);
		executorService.submit(topicListTask);
		executorService.submit(grouponListTask);
		executorService.submit(floorGoodsListTask);

		IndexDataVo entity = new IndexDataVo();
		try {
			entity.setBanner(bannerTask.get());
			entity.setChannel(channelTask.get());
			entity.setCouponList(couponListTask.get());
			entity.setNewGoodsList(newGoodsListTask.get());
			entity.setHotGoodsList(hotGoodsListTask.get());
			PageInfo<LitemallBrandDTO> brandPage = brandListTask.get();
			if (brandPage != null) {
				entity.setBrandList(brandPage.getList());
			}
			entity.setTopicList(topicListTask.get());
			PageInfo<GrouponRuleVo> grouponPage = grouponListTask.get();
			if (grouponPage != null) {
				entity.setGrouponList(grouponPage.getList());
			}
			entity.setFloorGoodsList(floorGoodsListTask.get());
			// 缓存数据
			HomeCacheManager.loadData(HomeCacheManager.INDEX, entity);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			executorService.shutdown();
		}
		return ResponseUtil.ok(entity);
	}

	private List<IndexCategoryVo> getCategoryList() {
		List<IndexCategoryVo> categoryList = new ArrayList<>();
		List<LitemallCategoryDTO> catL1List = categoryService.queryL1WithoutRecommend(0,
				SystemConfig.getCatlogListLimit());
		for (LitemallCategoryDTO catL1 : catL1List) {
			List<LitemallCategoryDTO> catL2List = categoryService
					.queryByPid(catL1.getId());
			List<Integer> l2List = new ArrayList<>();
			for (LitemallCategoryDTO catL2 : catL2List) {
				l2List.add(catL2.getId());
			}

			List<LitemallGoodsDTO> categoryGoods;
			if (l2List.size() == 0) {
				categoryGoods = new ArrayList<>();
			}
			else {
				categoryGoods = goodsService.queryByCategory(l2List, 0,
						SystemConfig.getCatlogMoreLimit());
			}

			IndexCategoryVo catGoods = new IndexCategoryVo();
			catGoods.setId(catL1.getId());
			catGoods.setName(catL1.getName());
			catGoods.setGoodsList(categoryGoods);
			categoryList.add(catGoods);
		}
		return categoryList;
	}

	/**
	 * 商城介绍信息
	 * @return 商城介绍信息
	 */
	@ApiOperation("商城介绍信息")
	@GetMapping("/about")
	public ResultJson<AboutVo> about() {
		AboutVo about = new AboutVo();
		about.setName(SystemConfig.getMallName());
		about.setAddress(SystemConfig.getMallAddress());
		about.setPhone(SystemConfig.getMallPhone());
		about.setQq(SystemConfig.getMallQQ());
		about.setLongitude(SystemConfig.getMallLongitude());
		about.setLatitude(SystemConfig.getMallLatitude());
		return ResponseUtil.ok(about);
	}

}
