package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallBrandDTO;

import java.util.List;

public interface LitemallBrandService {

    List<LitemallBrandDTO> query(Integer page, Integer limit, String sort, String order);

    List<LitemallBrandDTO> query(Integer page, Integer limit);

    LitemallBrandDTO findById(Integer id);

    List<LitemallBrandDTO> querySelective(String id, String name, Integer page,
                                          Integer size, String sort, String order);

    int updateById(LitemallBrandDTO brand);

    void deleteById(Integer id);

    void add(LitemallBrandDTO brand);

    List<LitemallBrandDTO> all();

}
