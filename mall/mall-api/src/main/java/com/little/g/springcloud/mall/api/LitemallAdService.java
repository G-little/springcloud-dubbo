package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallAdDTO;

import java.util.List;

public interface LitemallAdService {
    List<LitemallAdDTO> queryIndex();

    List<LitemallAdDTO> querySelective(String name, String content, Integer page, Integer limit, String sort, String order);

    int updateById(LitemallAdDTO ad);

    void deleteById(Integer id);

    void add(LitemallAdDTO ad);

    LitemallAdDTO findById(Integer id);
}
