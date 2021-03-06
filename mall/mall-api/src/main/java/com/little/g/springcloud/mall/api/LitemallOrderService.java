package com.little.g.springcloud.mall.api;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.mall.dto.LitemallOrderDTO;
import com.little.g.springcloud.mall.dto.UserOrderInfoDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface LitemallOrderService {

	int add(LitemallOrderDTO order);

	int count(Integer userId);

	LitemallOrderDTO findById(Integer orderId);

	LitemallOrderDTO findById(Integer userId, Integer orderId);

	int countByOrderSn(Integer userId, String orderSn);

	// TODO 这里应该产生一个唯一的订单，但是实际上这里仍然存在两个订单相同的可能性
	String generateOrderSn(Integer userId);

	PageInfo<LitemallOrderDTO> queryByOrderStatus(Integer userId, List<Short> orderStatus,
			Integer page, Integer limit, String sort, String order);

	PageInfo<LitemallOrderDTO> querySelective(Integer userId, String orderSn,
			LocalDateTime start, LocalDateTime end, List<Short> orderStatusArray,
			Integer page, Integer limit, String sort, String order);

	int updateWithOptimisticLocker(LitemallOrderDTO order);

	void deleteById(Integer id);

	int count();

	List<LitemallOrderDTO> queryUnpaid(int minutes);

	List<LitemallOrderDTO> queryUnconfirm(int days);

	LitemallOrderDTO findBySn(String orderSn);

	UserOrderInfoDTO orderInfo(Integer userId);

	List<LitemallOrderDTO> queryComment(int days);

	void updateAftersaleStatus(Integer orderId, Short statusReject);

}
