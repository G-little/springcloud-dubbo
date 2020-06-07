package com.little.g.springcloud.mall.web.controller;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.common.web.annotation.LoginUser;
import com.little.g.springcloud.mall.api.*;
import com.little.g.springcloud.mall.dto.LitemallBrandDTO;
import com.little.g.springcloud.mall.dto.LitemallCategoryDTO;
import com.little.g.springcloud.mall.dto.LitemallGoodsDTO;
import com.little.g.springcloud.mall.system.SystemConfig;
import com.little.g.springcloud.mall.web.manager.GrouponRuleManager;
import com.little.g.springcloud.mall.web.manager.HomeCacheManager;
import com.little.g.springcloud.mall.web.vo.GrouponRuleVo;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 首页服务
 */
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

	@GetMapping("/cache")
	public Object cache(@NotNull String key) {
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
	@GetMapping("/index")
	public Object index(@LoginUser Integer userId) {
		// 优先从缓存中读取
		if (HomeCacheManager.hasData(HomeCacheManager.INDEX)) {
			return ResponseUtil.ok(
					HomeCacheManager.getCacheData(HomeCacheManager.INDEX, Object.class));
		}
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		Callable<List> bannerListCallable = () -> adService.queryIndex();

		Callable<List> channelListCallable = () -> categoryService.queryChannel();

		Callable<List> couponListCallable;
		if (userId == null) {
			couponListCallable = () -> couponService.queryList(0, 3);
		}
		else {
			couponListCallable = () -> couponService.queryAvailableList(userId, 0, 3);
		}

		Callable<List> newGoodsListCallable = () -> goodsService.queryByNew(0,
				SystemConfig.getNewLimit());

		Callable<List> hotGoodsListCallable = () -> goodsService.queryByHot(0,
				SystemConfig.getHotLimit());

		Callable<PageInfo<LitemallBrandDTO>> brandListCallable = () -> brandService
				.query(0, SystemConfig.getBrandLimit());

		Callable<List> topicListCallable = () -> topicService.queryList(0,
				SystemConfig.getTopicLimit());

		// 团购专区
		Callable<PageInfo<GrouponRuleVo>> grouponListCallable = () -> grouponRuleManager
				.queryList(0, 5);

		Callable<List> floorGoodsListCallable = this::getCategoryList;

		FutureTask<List> bannerTask = new FutureTask<>(bannerListCallable);
		FutureTask<List> channelTask = new FutureTask<>(channelListCallable);
		FutureTask<List> couponListTask = new FutureTask<>(couponListCallable);
		FutureTask<List> newGoodsListTask = new FutureTask<>(newGoodsListCallable);
		FutureTask<List> hotGoodsListTask = new FutureTask<>(hotGoodsListCallable);
		FutureTask<PageInfo<LitemallBrandDTO>> brandListTask = new FutureTask<>(
				brandListCallable);
		FutureTask<List> topicListTask = new FutureTask<>(topicListCallable);
		FutureTask<PageInfo<GrouponRuleVo>> grouponListTask = new FutureTask<>(
				grouponListCallable);
		FutureTask<List> floorGoodsListTask = new FutureTask<>(floorGoodsListCallable);

		executorService.submit(bannerTask);
		executorService.submit(channelTask);
		executorService.submit(couponListTask);
		executorService.submit(newGoodsListTask);
		executorService.submit(hotGoodsListTask);
		executorService.submit(brandListTask);
		executorService.submit(topicListTask);
		executorService.submit(grouponListTask);
		executorService.submit(floorGoodsListTask);

		Map<String, Object> entity = new HashMap<>();
		try {
			entity.put("banner", bannerTask.get());
			entity.put("channel", channelTask.get());
			entity.put("couponList", couponListTask.get());
			entity.put("newGoodsList", newGoodsListTask.get());
			entity.put("hotGoodsList", hotGoodsListTask.get());
			entity.put("brandList", brandListTask.get());
			entity.put("topicList", topicListTask.get());
			entity.put("grouponList", grouponListTask.get());
			entity.put("floorGoodsList", floorGoodsListTask.get());
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

	private List<Map> getCategoryList() {
		List<Map> categoryList = new ArrayList<>();
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

			Map<String, Object> catGoods = new HashMap<>();
			catGoods.put("id", catL1.getId());
			catGoods.put("name", catL1.getName());
			catGoods.put("goodsList", categoryGoods);
			categoryList.add(catGoods);
		}
		return categoryList;
	}

	/**
	 * 商城介绍信息
	 * @return 商城介绍信息
	 */
	@GetMapping("/about")
	public Object about() {
		Map<String, Object> about = new HashMap<>();
		about.put("name", SystemConfig.getMallName());
		about.put("address", SystemConfig.getMallAddress());
		about.put("phone", SystemConfig.getMallPhone());
		about.put("qq", SystemConfig.getMallQQ());
		about.put("longitude", SystemConfig.getMallLongitude());
		about.put("latitude", SystemConfig.getMallLatitude());
		return ResponseUtil.ok(about);
	}

}
