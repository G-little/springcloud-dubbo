package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallFootprintDTO;

import java.util.List;

public interface LitemallFootprintService {

    List<LitemallFootprintDTO> queryByAddTime(Integer userId, Integer page, Integer size);

    LitemallFootprintDTO findById(Integer id);

    LitemallFootprintDTO findById(Integer userId, Integer id);

    void deleteById(Integer id);

    void add(LitemallFootprintDTO footprint);

    List<LitemallFootprintDTO> querySelective(String userId, String goodsId, Integer page,
                                              Integer size, String sort, String order);

}
