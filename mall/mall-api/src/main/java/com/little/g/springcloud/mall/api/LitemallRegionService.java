package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallRegionDTO;

import java.util.List;

public interface LitemallRegionService {
    List<LitemallRegionDTO> getAll();

    List<LitemallRegionDTO> queryByPid(Integer parentId);

    LitemallRegionDTO findById(Integer id);

    List<LitemallRegionDTO> querySelective(String name, Integer code, Integer page, Integer size, String sort, String order);
}
