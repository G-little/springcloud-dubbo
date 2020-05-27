package com.little.g.springcloud.mall.api;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.mall.dto.LitemallLogDTO;

public interface LitemallLogService {

	void deleteById(Integer id);

	void add(LitemallLogDTO log);

	PageInfo<LitemallLogDTO> querySelective(String name, Integer page, Integer size,
                                            String sort, String order);

}
