package com.little.g.springcloud.mall.api;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.mall.dto.LitemallCollectDTO;

public interface LitemallCollectService {

	int count(int uid, Integer gid);

	PageInfo<LitemallCollectDTO> queryByType(Integer userId, Byte type, Integer page,
			Integer limit, String sort, String order);

	int countByType(Integer userId, Byte type);

	LitemallCollectDTO queryByTypeAndValue(Integer userId, Byte type, Integer valueId);

	void deleteById(Integer id);

	int add(LitemallCollectDTO collect);

	PageInfo<LitemallCollectDTO> querySelective(String userId, String valueId,
			Integer page, Integer size, String sort, String order);

}
