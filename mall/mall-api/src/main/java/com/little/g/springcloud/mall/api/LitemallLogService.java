package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallLogDTO;

import java.util.List;

public interface LitemallLogService {
    void deleteById(Integer id);

    void add(LitemallLogDTO log);

    List<LitemallLogDTO> querySelective(String name, Integer page, Integer size, String sort, String order);
}
