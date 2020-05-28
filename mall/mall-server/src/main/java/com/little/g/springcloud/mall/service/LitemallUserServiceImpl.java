package com.little.g.springcloud.mall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallUserService;
import com.little.g.springcloud.mall.dto.LitemallUserDTO;
import com.little.g.springcloud.mall.mapper.LitemallUserMapper;
import com.little.g.springcloud.mall.model.LitemallUser;
import com.little.g.springcloud.mall.model.LitemallUserExample;
import com.little.g.springcloud.mall.vo.UserVo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service(protocol = "dubbo")
public class LitemallUserServiceImpl implements LitemallUserService {

	@Resource
	private LitemallUserMapper userMapper;

	@Override
	public LitemallUserDTO findById(Integer userId) {
		return DTOUtil.convert2T(userMapper.selectByPrimaryKey(userId),
				LitemallUserDTO.class);
	}

	@Override
	public UserVo findUserVoById(Integer userId) {
		LitemallUserDTO user = findById(userId);
		UserVo userVo = new UserVo();
		userVo.setNickname(user.getNickname());
		userVo.setAvatar(user.getAvatar());
		return userVo;
	}

	@Override
	public LitemallUserDTO queryByOid(String openId) {
		LitemallUserExample example = new LitemallUserExample();
		example.or().andWeixinOpenidEqualTo(openId).andDeletedEqualTo(false);
		return DTOUtil.convert2T(userMapper.selectOneByExample(example),
				LitemallUserDTO.class);
	}

	@Override
	public void add(LitemallUserDTO user) {
		user.setAddTime(LocalDateTime.now());
		user.setUpdateTime(LocalDateTime.now());
		userMapper.insertSelective(DTOUtil.convert2T(user, LitemallUser.class));
	}

	@Override
	public int updateById(LitemallUserDTO user) {
		user.setUpdateTime(LocalDateTime.now());
		return userMapper
				.updateByPrimaryKeySelective(DTOUtil.convert2T(user, LitemallUser.class));
	}

	@Override
	public PageInfo<LitemallUserDTO> querySelective(String username, String mobile,
                                                    Integer page, Integer size, String sort, String order) {
		LitemallUserExample example = new LitemallUserExample();
		LitemallUserExample.Criteria criteria = example.createCriteria();

		if (!StringUtils.isEmpty(username)) {
			criteria.andUsernameLike("%" + username + "%");
		}
		if (!StringUtils.isEmpty(mobile)) {
			criteria.andMobileEqualTo(mobile);
		}
		criteria.andDeletedEqualTo(false);

		if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
			example.setOrderByClause(sort + " " + order);
		}

		PageHelper.startPage(page, size);
		return DTOUtil.convert2Page(userMapper.selectByExample(example),
				LitemallUserDTO.class);
	}

	@Override
	public int count() {
		LitemallUserExample example = new LitemallUserExample();
		example.or().andDeletedEqualTo(false);

		return (int) userMapper.countByExample(example);
	}

	@Override
	public List<LitemallUserDTO> queryByUsername(String username) {
		LitemallUserExample example = new LitemallUserExample();
		example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
		return DTOUtil.convert2List(userMapper.selectByExample(example),
				LitemallUserDTO.class);
	}

	@Override
	public boolean checkByUsername(String username) {
		LitemallUserExample example = new LitemallUserExample();
		example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
		return userMapper.countByExample(example) != 0;
	}

	@Override
	public List<LitemallUserDTO> queryByMobile(String mobile) {
		LitemallUserExample example = new LitemallUserExample();
		example.or().andMobileEqualTo(mobile).andDeletedEqualTo(false);
		return DTOUtil.convert2List(userMapper.selectByExample(example),
				LitemallUserDTO.class);
	}

	@Override
	public List<LitemallUserDTO> queryByOpenid(String openid) {
		LitemallUserExample example = new LitemallUserExample();
		example.or().andWeixinOpenidEqualTo(openid).andDeletedEqualTo(false);
		return DTOUtil.convert2List(userMapper.selectByExample(example),
				LitemallUserDTO.class);
	}

	@Override
	public void deleteById(Integer id) {
		userMapper.logicalDeleteByPrimaryKey(id);
	}

}
