package com.little.g.springcloud.mall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallOrderService;
import com.little.g.springcloud.mall.dto.LitemallOrderDTO;
import com.little.g.springcloud.mall.mapper.LitemallOrderMapper;
import com.little.g.springcloud.mall.mapper.OrderMapper;
import com.little.g.springcloud.mall.model.LitemallOrder;
import com.little.g.springcloud.mall.model.LitemallOrderExample;
import com.little.g.springcloud.mall.util.OrderUtil;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service(protocol = "dubbo")
public class LitemallOrderServiceImpl implements LitemallOrderService {

	@Resource
	private LitemallOrderMapper litemallOrderMapper;

	@Resource
	private OrderMapper orderMapper;

	@Override
	public int add(LitemallOrderDTO order) {
		order.setAddTime(LocalDateTime.now());
		order.setUpdateTime(LocalDateTime.now());
		return litemallOrderMapper
				.insertSelective(DTOUtil.convert2T(order, LitemallOrder.class));
	}

	@Override
	public int count(Integer userId) {
		LitemallOrderExample example = new LitemallOrderExample();
		example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
		return (int) litemallOrderMapper.countByExample(example);
	}

	@Override
	public LitemallOrderDTO findById(Integer orderId) {
		return DTOUtil.convert2T(litemallOrderMapper.selectByPrimaryKey(orderId),
				LitemallOrderDTO.class);
	}

	@Override
	public LitemallOrderDTO findById(Integer userId, Integer orderId) {
		LitemallOrderExample example = new LitemallOrderExample();
		example.or().andIdEqualTo(orderId).andUserIdEqualTo(userId)
				.andDeletedEqualTo(false);
		return DTOUtil.convert2T(litemallOrderMapper.selectOneByExample(example),
				LitemallOrderDTO.class);
	}

	private String getRandomNum(Integer num) {
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	@Override
	public int countByOrderSn(Integer userId, String orderSn) {
		LitemallOrderExample example = new LitemallOrderExample();
		example.or().andUserIdEqualTo(userId).andOrderSnEqualTo(orderSn)
				.andDeletedEqualTo(false);
		return (int) litemallOrderMapper.countByExample(example);
	}

	// TODO 这里应该产生一个唯一的订单，但是实际上这里仍然存在两个订单相同的可能性
	@Override
	public String generateOrderSn(Integer userId) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
		String now = df.format(LocalDate.now());
		String orderSn = now + getRandomNum(6);
		while (countByOrderSn(userId, orderSn) != 0) {
			orderSn = now + getRandomNum(6);
		}
		return orderSn;
	}

	@Override
	public PageInfo<LitemallOrderDTO> queryByOrderStatus(Integer userId,
			List<Short> orderStatus, Integer page, Integer limit, String sort,
			String order) {
		LitemallOrderExample example = new LitemallOrderExample();
		example.setOrderByClause(LitemallOrderDTO.Column.addTime.desc());
		LitemallOrderExample.Criteria criteria = example.or();
		criteria.andUserIdEqualTo(userId);
		if (orderStatus != null) {
			criteria.andOrderStatusIn(orderStatus);
		}
		criteria.andDeletedEqualTo(false);
		if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
			example.setOrderByClause(sort + " " + order);
		}

		PageHelper.startPage(page, limit);
		return DTOUtil.convert2Page(litemallOrderMapper.selectByExample(example),
				LitemallOrderDTO.class);
	}

	@Override
	public List<LitemallOrderDTO> querySelective(Integer userId, String orderSn,
			LocalDateTime start, LocalDateTime end, List<Short> orderStatusArray,
			Integer page, Integer limit, String sort, String order) {
		LitemallOrderExample example = new LitemallOrderExample();
		LitemallOrderExample.Criteria criteria = example.createCriteria();

		if (userId != null) {
			criteria.andUserIdEqualTo(userId);
		}
		if (!StringUtils.isEmpty(orderSn)) {
			criteria.andOrderSnEqualTo(orderSn);
		}
		if (start != null) {
			criteria.andAddTimeGreaterThanOrEqualTo(start);
		}
		if (end != null) {
			criteria.andAddTimeLessThanOrEqualTo(end);
		}
		if (orderStatusArray != null && orderStatusArray.size() != 0) {
			criteria.andOrderStatusIn(orderStatusArray);
		}
		criteria.andDeletedEqualTo(false);

		if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
			example.setOrderByClause(sort + " " + order);
		}

		PageHelper.startPage(page, limit);
		return DTOUtil.convert2List(litemallOrderMapper.selectByExample(example),
				LitemallOrderDTO.class);
	}

	@Override
	public int updateWithOptimisticLocker(LitemallOrderDTO order) {
		LocalDateTime preUpdateTime = order.getUpdateTime();
		order.setUpdateTime(LocalDateTime.now());
		return orderMapper.updateWithOptimisticLocker(preUpdateTime,
				DTOUtil.convert2T(order, LitemallOrder.class));
	}

	@Override
	public void deleteById(Integer id) {
		litemallOrderMapper.logicalDeleteByPrimaryKey(id);
	}

	@Override
	public int count() {
		LitemallOrderExample example = new LitemallOrderExample();
		example.or().andDeletedEqualTo(false);
		return (int) litemallOrderMapper.countByExample(example);
	}

	@Override
	public List<LitemallOrderDTO> queryUnpaid(int minutes) {
		LitemallOrderExample example = new LitemallOrderExample();
		example.or().andOrderStatusEqualTo(OrderUtil.STATUS_CREATE)
				.andDeletedEqualTo(false);
		return DTOUtil.convert2List(litemallOrderMapper.selectByExample(example),
				LitemallOrderDTO.class);
	}

	@Override
	public List<LitemallOrderDTO> queryUnconfirm(int days) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime expired = now.minusDays(days);
		LitemallOrderExample example = new LitemallOrderExample();
		example.or().andOrderStatusEqualTo(OrderUtil.STATUS_SHIP)
				.andShipTimeLessThan(expired).andDeletedEqualTo(false);
		return DTOUtil.convert2List(litemallOrderMapper.selectByExample(example),
				LitemallOrderDTO.class);
	}

	@Override
	public LitemallOrderDTO findBySn(String orderSn) {
		LitemallOrderExample example = new LitemallOrderExample();
		example.or().andOrderSnEqualTo(orderSn).andDeletedEqualTo(false);
		return DTOUtil.convert2T(litemallOrderMapper.selectOneByExample(example),
				LitemallOrderDTO.class);
	}

	@Override
	public Map<Object, Object> orderInfo(Integer userId) {
		LitemallOrderExample example = new LitemallOrderExample();
		example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
		List<LitemallOrderDTO> orders = DTOUtil.convert2List(
				litemallOrderMapper.selectByExampleSelective(example,
						LitemallOrder.Column.orderStatus, LitemallOrder.Column.comments),
				LitemallOrderDTO.class);

		int unpaid = 0;
		int unship = 0;
		int unrecv = 0;
		int uncomment = 0;
		for (LitemallOrderDTO order : orders) {
			if (OrderUtil.isCreateStatus(order)) {
				unpaid++;
			}
			else if (OrderUtil.isPayStatus(order)) {
				unship++;
			}
			else if (OrderUtil.isShipStatus(order)) {
				unrecv++;
			}
			else if (OrderUtil.isConfirmStatus(order)
					|| OrderUtil.isAutoConfirmStatus(order)) {
				uncomment += order.getComments();
			}
			else {
				// do nothing
			}
		}

		Map<Object, Object> orderInfo = new HashMap<Object, Object>();
		orderInfo.put("unpaid", unpaid);
		orderInfo.put("unship", unship);
		orderInfo.put("unrecv", unrecv);
		orderInfo.put("uncomment", uncomment);
		return orderInfo;

	}

	@Override
	public List<LitemallOrderDTO> queryComment(int days) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime expired = now.minusDays(days);
		LitemallOrderExample example = new LitemallOrderExample();
		example.or().andCommentsGreaterThan((short) 0).andConfirmTimeLessThan(expired)
				.andDeletedEqualTo(false);
		return DTOUtil.convert2List(litemallOrderMapper.selectByExample(example),
				LitemallOrderDTO.class);
	}

	@Override
	public void updateAftersaleStatus(Integer orderId, Short statusReject) {
		LitemallOrder order = new LitemallOrder();
		order.setId(orderId);
		order.setAftersaleStatus(statusReject);
		order.setUpdateTime(LocalDateTime.now());
		litemallOrderMapper.updateByPrimaryKeySelective(order);
	}

}
