package com.little.g.springcloud.mall.api;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.mall.dto.LitemallIssueDTO;

public interface LitemallIssueService {

	void deleteById(Integer id);

	void add(LitemallIssueDTO issue);

    PageInfo<LitemallIssueDTO> querySelective(String question, Integer page,
                                              Integer limit, String sort, String order);

	int updateById(LitemallIssueDTO issue);

	LitemallIssueDTO findById(Integer id);

}
