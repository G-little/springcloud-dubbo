package com.little.g.springcloud.mall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallCouponUserService;
import com.little.g.springcloud.mall.dto.LitemallCouponUserDTO;
import com.little.g.springcloud.mall.mapper.LitemallCouponUserMapper;
import com.little.g.springcloud.mall.model.LitemallCouponUser;
import com.little.g.springcloud.mall.model.LitemallCouponUserExample;
import com.little.g.springcloud.mall.util.CouponUserConstant;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service(protocol = "dubbo")
public class LitemallCouponUserServiceImpl implements LitemallCouponUserService {

	@Resource
	private LitemallCouponUserMapper couponUserMapper;

	@Override
	public Integer countCoupon(Integer couponId) {
		LitemallCouponUserExample example = new LitemallCouponUserExample();
		example.or().andCouponIdEqualTo(couponId).andDeletedEqualTo(false);
		return (int) couponUserMapper.countByExample(example);
	}

	@Override
	public Integer countUserAndCoupon(Integer userId, Integer couponId) {
		LitemallCouponUserExample example = new LitemallCouponUserExample();
		example.or().andUserIdEqualTo(userId).andCouponIdEqualTo(couponId)
				.andDeletedEqualTo(false);
		return (int) couponUserMapper.countByExample(example);
	}

	@Override
	public void add(LitemallCouponUserDTO couponUser) {
		couponUser.setAddTime(LocalDateTime.now());
		couponUser.setUpdateTime(LocalDateTime.now());
		couponUserMapper
				.insertSelective(DTOUtil.convert2T(couponUser, LitemallCouponUser.class));
	}

	@Override
	public PageInfo<LitemallCouponUserDTO> queryList(Integer userId, Integer couponId,
			Short status, Integer page, Integer size, String sort, String order) {
		LitemallCouponUserExample example = new LitemallCouponUserExample();
		LitemallCouponUserExample.Criteria criteria = example.createCriteria();
		if (userId != null) {
			criteria.andUserIdEqualTo(userId);
		}
		if (couponId != null) {
			criteria.andCouponIdEqualTo(couponId);
		}
		if (status != null) {
			criteria.andStatusEqualTo(status);
		}
		criteria.andDeletedEqualTo(false);

		if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
			example.setOrderByClause(sort + " " + order);
		}

		if (!StringUtils.isEmpty(page) && !StringUtils.isEmpty(size)) {
			PageHelper.startPage(page, size);
		}

		return DTOUtil.convert2Page(couponUserMapper.selectByExample(example),
				LitemallCouponUserDTO.class);
	}

	@Override
	public List<LitemallCouponUserDTO> queryAll(Integer userId, Integer couponId) {
		PageInfo<LitemallCouponUserDTO> p = queryList(userId, couponId,
				CouponUserConstant.STATUS_USABLE, null, null, "add_time", "desc");
		if (p != null) {
			return p.getList();
		}
		return null;
	}

	@Override
	public List<LitemallCouponUserDTO> queryAll(Integer userId) {
		PageInfo<LitemallCouponUserDTO> p = queryList(userId, null,
				CouponUserConstant.STATUS_USABLE, null, null, "add_time", "desc");
		if (p != null) {
			return p.getList();
		}
		return null;
	}

	@Override
	public LitemallCouponUserDTO queryOne(Integer userId, Integer couponId) {
		PageInfo<LitemallCouponUserDTO> p = queryList(userId, couponId,
				CouponUserConstant.STATUS_USABLE, 1, 1, "add_time", "desc");
		if (p == null) {
			return null;
		}
		if (CollectionUtils.isEmpty(p.getList())) {
			return null;
		}
		return p.getList().get(0);
	}

	@Override
	public LitemallCouponUserDTO findById(Integer id) {
		return DTOUtil.convert2T(couponUserMapper.selectByPrimaryKey(id),
				LitemallCouponUserDTO.class);
	}

	@Override
	public int update(LitemallCouponUserDTO couponUser) {
		couponUser.setUpdateTime(LocalDateTime.now());
		return couponUserMapper.updateByPrimaryKeySelective(
				DTOUtil.convert2T(couponUser, LitemallCouponUser.class));
	}

	@Override
	public List<LitemallCouponUserDTO> queryExpired() {
		LitemallCouponUserExample example = new LitemallCouponUserExample();
		example.or().andStatusEqualTo(CouponUserConstant.STATUS_USABLE)
				.andEndTimeLessThan(LocalDateTime.now()).andDeletedEqualTo(false);
		return DTOUtil.convert2List(couponUserMapper.selectByExample(example),
				LitemallCouponUserDTO.class);
	}

}
