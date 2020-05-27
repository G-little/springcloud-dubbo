package com.little.g.springcloud.mall.api;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.mall.dto.LitemallStorageDTO;

public interface LitemallStorageService {

	void deleteByKey(String key);

	void add(LitemallStorageDTO storageInfo);

	LitemallStorageDTO findByKey(String key);

	int update(LitemallStorageDTO storageInfo);

	LitemallStorageDTO findById(Integer id);

	PageInfo<LitemallStorageDTO> querySelective(String key, String name, Integer page,
                                                Integer limit, String sort, String order);

}
