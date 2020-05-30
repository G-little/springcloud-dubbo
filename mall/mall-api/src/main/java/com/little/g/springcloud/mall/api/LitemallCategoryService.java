package com.little.g.springcloud.mall.api;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.mall.dto.LitemallCategoryDTO;

import java.util.List;

public interface LitemallCategoryService {

	List<LitemallCategoryDTO> queryL1WithoutRecommend(int offset, int limit);

	List<LitemallCategoryDTO> queryL1(int offset, int limit);

	List<LitemallCategoryDTO> queryL1();

	List<LitemallCategoryDTO> queryByPid(Integer pid);

	List<LitemallCategoryDTO> queryL2ByIds(List<Integer> ids);

	LitemallCategoryDTO findById(Integer id);

	PageInfo<LitemallCategoryDTO> querySelective(String id, String name, Integer page,
			Integer size, String sort, String order);

	int updateById(LitemallCategoryDTO category);

	void deleteById(Integer id);

	void add(LitemallCategoryDTO category);

	List<LitemallCategoryDTO> queryChannel();

}
