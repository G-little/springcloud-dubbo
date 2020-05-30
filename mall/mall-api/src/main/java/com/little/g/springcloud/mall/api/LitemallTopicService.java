package com.little.g.springcloud.mall.api;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.mall.dto.LitemallTopicDTO;

import java.util.List;

public interface LitemallTopicService {

	List<LitemallTopicDTO> queryList(int offset, int limit);

	List<LitemallTopicDTO> queryList(int offset, int limit, String sort, String order);

	int queryTotal();

	LitemallTopicDTO findById(Integer id);

	List<LitemallTopicDTO> queryRelatedList(Integer id, int offset, int limit);

	PageInfo<LitemallTopicDTO> querySelective(String title, String subtitle, Integer page,
			Integer limit, String sort, String order);

	int updateById(LitemallTopicDTO topic);

	void deleteById(Integer id);

	void add(LitemallTopicDTO topic);

	void deleteByIds(List<Integer> ids);

}
