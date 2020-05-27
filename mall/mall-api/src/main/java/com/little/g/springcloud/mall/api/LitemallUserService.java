package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallUserDTO;
import com.little.g.springcloud.mall.vo.UserVo;

import java.util.List;

public interface LitemallUserService {

	LitemallUserDTO findById(Integer userId);

	UserVo findUserVoById(Integer userId);

	LitemallUserDTO queryByOid(String openId);

	void add(LitemallUserDTO user);

	int updateById(LitemallUserDTO user);

	List<LitemallUserDTO> querySelective(String username, String mobile, Integer page,
                                         Integer size, String sort, String order);

	int count();

	List<LitemallUserDTO> queryByUsername(String username);

	boolean checkByUsername(String username);

	List<LitemallUserDTO> queryByMobile(String mobile);

	List<LitemallUserDTO> queryByOpenid(String openid);

	void deleteById(Integer id);

}
