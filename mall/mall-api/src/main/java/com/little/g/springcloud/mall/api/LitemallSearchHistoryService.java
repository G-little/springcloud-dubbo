package com.little.g.springcloud.mall.api;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.mall.dto.LitemallSearchHistoryDTO;

import java.util.List;

public interface LitemallSearchHistoryService {

	void save(LitemallSearchHistoryDTO searchHistory);

	List<LitemallSearchHistoryDTO> queryByUid(int uid);

	void deleteByUid(int uid);

	PageInfo<LitemallSearchHistoryDTO> querySelective(String userId, String keyword,
                                                      Integer page, Integer size, String sort, String order);

}
