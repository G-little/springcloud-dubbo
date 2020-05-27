package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallStorageDTO;

import java.util.List;

public interface LitemallStorageService {
    void deleteByKey(String key);

    void add(LitemallStorageDTO storageInfo);

    LitemallStorageDTO findByKey(String key);

    int update(LitemallStorageDTO storageInfo);

    LitemallStorageDTO findById(Integer id);

    List<LitemallStorageDTO> querySelective(String key, String name, Integer page, Integer limit, String sort, String order);
}
