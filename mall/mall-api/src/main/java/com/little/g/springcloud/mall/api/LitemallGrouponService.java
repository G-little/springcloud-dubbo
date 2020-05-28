package com.little.g.springcloud.mall.api;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.mall.dto.LitemallGrouponDTO;

import java.util.List;

public interface LitemallGrouponService {

	List<LitemallGrouponDTO> queryMyGroupon(Integer userId);

	List<LitemallGrouponDTO> queryMyJoinGroupon(Integer userId);

	LitemallGrouponDTO queryByOrderId(Integer orderId);

	List<LitemallGrouponDTO> queryJoinRecord(Integer id);

	LitemallGrouponDTO queryById(Integer id);

	LitemallGrouponDTO queryById(Integer userId, Integer id);

	int countGroupon(Integer grouponId);

	boolean hasJoin(Integer userId, Integer grouponId);

	int updateById(LitemallGrouponDTO groupon);

	int createGroupon(LitemallGrouponDTO groupon);

    PageInfo<LitemallGrouponDTO> querySelective(String rulesId, Integer page,
                                                Integer size, String sort, String order);

	List<LitemallGrouponDTO> queryByRuleId(int grouponRuleId);

}
