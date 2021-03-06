package com.little.g.springcloud.mall.web.controller;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.common.web.annotation.LoginUser;
import com.little.g.springcloud.mall.api.LitemallKeywordService;
import com.little.g.springcloud.mall.api.LitemallSearchHistoryService;
import com.little.g.springcloud.mall.dto.LitemallKeywordDTO;
import com.little.g.springcloud.mall.dto.LitemallSearchHistoryDTO;
import com.little.g.springcloud.mall.web.vo.SearchIndexVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品搜索服务
 * <p>
 * 注意：目前搜索功能非常简单，只是基于关键字匹配。
 */
@Api("商品搜索服务")
@RestController
@RequestMapping("/search")
@Slf4j
@Validated
public class SearchController {

	@Reference
	private LitemallKeywordService keywordsService;

	@Reference
	private LitemallSearchHistoryService searchHistoryService;

	/**
	 * 搜索页面信息
	 * <p>
	 * 如果用户已登录，则给出用户历史搜索记录； 如果没有登录，则给出空历史搜索记录。
	 * @param userId 用户ID，可选
	 * @return 搜索页面信息
	 */
	@ApiOperation("搜索页面信息")
	@GetMapping("index")
	public ResultJson<SearchIndexVo> index(@LoginUser Integer userId) {
		// 取出输入框默认的关键词
		LitemallKeywordDTO defaultKeyword = keywordsService.queryDefault();
		// 取出热闹关键词
		List<LitemallKeywordDTO> hotKeywordList = keywordsService.queryHots();

		List<LitemallSearchHistoryDTO> historyList = null;
		if (userId != null) {
			// 取出用户历史关键字
			historyList = searchHistoryService.queryByUid(userId);
		}
		else {
			historyList = new ArrayList<>(0);
		}

		SearchIndexVo data = new SearchIndexVo();
		data.setDefaultKeyword(defaultKeyword);
		data.setHistoryKeywordList(historyList);
		data.setHotKeywordList(hotKeywordList);
		return ResponseUtil.ok(data);
	}

	/**
	 * 关键字提醒
	 * <p>
	 * 当用户输入关键字一部分时，可以推荐系统中合适的关键字。
	 * @param keyword 关键字
	 * @return 合适的关键字
	 */
	@ApiOperation(value = "关键字提醒", notes = "当用户输入关键字一部分时，可以推荐系统中合适的关键字。")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "keyword", value = "关键词", required = true),
			@ApiImplicitParam(name = "page", value = "分页", dataType = "int",
					required = false, defaultValue = "1", example = "1"),
			@ApiImplicitParam(name = "limit", value = "单页条数", dataType = "int",
					required = false, defaultValue = "10", example = "10") })
	@GetMapping("helper")
	public ResultJson<String[]> helper(@NotEmpty String keyword,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit) {
		PageInfo<LitemallKeywordDTO> pageInfo = keywordsService.queryByKeyword(keyword,
				page, limit);
		List<LitemallKeywordDTO> keywordsList = pageInfo.getList();
		String[] keys = new String[keywordsList.size()];
		int index = 0;
		for (LitemallKeywordDTO key : keywordsList) {
			keys[index++] = key.getKeyword();
		}
		return ResponseUtil.ok(keys);
	}

	/**
	 * 清除用户搜索历史
	 * @param userId 用户ID
	 * @return 清理是否成功
	 */

	@ApiOperation("清楚用户搜索历史")
	@PostMapping("clearhistory")
	public ResultJson clearhistory(@LoginUser Integer userId) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}

		searchHistoryService.deleteByUid(userId);
		return ResponseUtil.ok();
	}

}
