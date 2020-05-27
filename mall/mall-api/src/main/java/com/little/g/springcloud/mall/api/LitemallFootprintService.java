package com.little.g.springcloud.mall.api;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.mall.dto.LitemallFootprintDTO;

public interface LitemallFootprintService {

	PageInfo<LitemallFootprintDTO> queryByAddTime(Integer userId, Integer page, Integer size);

	LitemallFootprintDTO findById(Integer id);

	LitemallFootprintDTO findById(Integer userId, Integer id);

	void deleteById(Integer id);

	void add(LitemallFootprintDTO footprint);

	PageInfo<LitemallFootprintDTO> querySelective(String userId, String goodsId, Integer page,
												  Integer size, String sort, String order);

}
