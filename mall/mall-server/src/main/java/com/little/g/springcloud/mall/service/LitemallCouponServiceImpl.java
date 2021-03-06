package com.little.g.springcloud.mall.service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallCouponService;
import com.little.g.springcloud.mall.dto.LitemallCouponDTO;
import com.little.g.springcloud.mall.mapper.LitemallCouponMapper;
import com.little.g.springcloud.mall.mapper.LitemallCouponUserMapper;
import com.little.g.springcloud.mall.model.LitemallCoupon;
import com.little.g.springcloud.mall.model.LitemallCoupon.Column;
import com.little.g.springcloud.mall.model.LitemallCouponExample;
import com.little.g.springcloud.mall.model.LitemallCouponUser;
import com.little.g.springcloud.mall.model.LitemallCouponUserExample;
import com.little.g.springcloud.mall.util.CouponConstant;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service(protocol = "dubbo")
public class LitemallCouponServiceImpl implements LitemallCouponService {

	@Resource
	private LitemallCouponMapper couponMapper;

	@Resource
	private LitemallCouponUserMapper couponUserMapper;

	private Column[] result = new Column[] { Column.id, Column.name, Column.desc,
			Column.tag, Column.days, Column.startTime, Column.endTime, Column.discount,
			Column.min };

	/**
	 * 查询，空参数
	 * @param offset
	 * @param limit
	 * @param sort
	 * @param order
	 * @return
	 */
	@Override
	public List<LitemallCouponDTO> queryList(int offset, int limit, String sort,
			String order) {
		return queryList(LitemallCouponExample.newAndCreateCriteria(), offset, limit,
				sort, order);
	}

	/**
	 * 查询
	 * @param criteria 可扩展的条件
	 * @param offset
	 * @param limit
	 * @param sort
	 * @param order
	 * @return
	 */
	public List<LitemallCouponDTO> queryList(LitemallCouponExample.Criteria criteria,
			int offset, int limit, String sort, String order) {
		criteria.andTypeEqualTo(CouponConstant.TYPE_COMMON)
				.andStatusEqualTo(CouponConstant.STATUS_NORMAL).andDeletedEqualTo(false);
		criteria.example().setOrderByClause(sort + " " + order);
		PageHelper.startPage(offset, limit);
		return DTOUtil.convert2List(
				couponMapper.selectByExampleSelective(criteria.example(), result),
				LitemallCouponDTO.class);
	}

	@Override
	public List<LitemallCouponDTO> queryAvailableList(Integer userId, int offset,
			int limit) {
		assert userId != null;
		// 过滤掉登录账号已经领取过的coupon
		LitemallCouponExample.Criteria c = LitemallCouponExample.newAndCreateCriteria();
		List<LitemallCouponUser> used = couponUserMapper
				.selectByExample(LitemallCouponUserExample.newAndCreateCriteria()
						.andUserIdEqualTo(userId).example());
		if (used != null && !used.isEmpty()) {
			c.andIdNotIn(used.stream().map(LitemallCouponUser::getCouponId)
					.collect(Collectors.toList()));
		}
		return queryList(c, offset, limit, "add_time", "desc");
	}

	@Override
	public List<LitemallCouponDTO> queryList(int offset, int limit) {
		return queryList(offset, limit, "add_time", "desc");
	}

	@Override
	public LitemallCouponDTO findById(Integer id) {
		return DTOUtil.convert2T(couponMapper.selectByPrimaryKey(id),
				LitemallCouponDTO.class);
	}

	@Override
	public LitemallCouponDTO findByCode(String code) {
		LitemallCouponExample example = new LitemallCouponExample();
		example.or().andCodeEqualTo(code).andTypeEqualTo(CouponConstant.TYPE_CODE)
				.andStatusEqualTo(CouponConstant.STATUS_NORMAL).andDeletedEqualTo(false);
		List<LitemallCouponDTO> couponList = DTOUtil.convert2List(
				couponMapper.selectByExample(example), LitemallCouponDTO.class);
		if (couponList.size() > 1) {
			throw new RuntimeException("");
		}
		else if (couponList.size() == 0) {
			return null;
		}
		else {
			return couponList.get(0);
		}
	}

	/**
	 * 查询新用户注册优惠券
	 * @return
	 */
	@Override
	public List<LitemallCouponDTO> queryRegister() {
		LitemallCouponExample example = new LitemallCouponExample();
		example.or().andTypeEqualTo(CouponConstant.TYPE_REGISTER)
				.andStatusEqualTo(CouponConstant.STATUS_NORMAL).andDeletedEqualTo(false);
		return DTOUtil.convert2List(couponMapper.selectByExample(example),
				LitemallCouponDTO.class);
	}

	@Override
	public PageInfo<LitemallCouponDTO> querySelective(String name, Short type,
			Short status, Integer page, Integer limit, String sort, String order) {
		LitemallCouponExample example = new LitemallCouponExample();
		LitemallCouponExample.Criteria criteria = example.createCriteria();

		if (!StringUtils.isEmpty(name)) {
			criteria.andNameLike("%" + name + "%");
		}
		if (type != null) {
			criteria.andTypeEqualTo(type);
		}
		if (status != null) {
			criteria.andStatusEqualTo(status);
		}
		criteria.andDeletedEqualTo(false);

		if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
			example.setOrderByClause(sort + " " + order);
		}

		PageHelper.startPage(page, limit);
		return DTOUtil.convert2Page(couponMapper.selectByExample(example),
				LitemallCouponDTO.class);
	}

	@Override
	public void add(LitemallCouponDTO coupon) {
		coupon.setAddTime(LocalDateTime.now());
		coupon.setUpdateTime(LocalDateTime.now());
		couponMapper.insertSelective(DTOUtil.convert2T(coupon, LitemallCoupon.class));
	}

	@Override
	public int updateById(LitemallCouponDTO coupon) {
		coupon.setUpdateTime(LocalDateTime.now());
		return couponMapper.updateByPrimaryKeySelective(
				DTOUtil.convert2T(coupon, LitemallCoupon.class));
	}

	@Override
	public void deleteById(Integer id) {
		couponMapper.logicalDeleteByPrimaryKey(id);
	}

	private String getRandomNum(Integer num) {
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		base += "0123456789";

		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 生成优惠码
	 * @return 可使用优惠码
	 */
	@Override
	public String generateCode() {
		String code = getRandomNum(8);
		while (findByCode(code) != null) {
			code = getRandomNum(8);
		}
		return code;
	}

	/**
	 * 查询过期的优惠券: 注意：如果timeType=0, 即基于领取时间有效期的优惠券，则优惠券不会过期
	 * @return
	 */
	public List<LitemallCouponDTO> queryExpired() {
		LitemallCouponExample example = new LitemallCouponExample();
		example.or().andStatusEqualTo(CouponConstant.STATUS_NORMAL)
				.andTimeTypeEqualTo(CouponConstant.TIME_TYPE_TIME)
				.andEndTimeLessThan(LocalDateTime.now()).andDeletedEqualTo(false);
		return DTOUtil.convert2List(couponMapper.selectByExample(example),
				LitemallCouponDTO.class);
	}

}
