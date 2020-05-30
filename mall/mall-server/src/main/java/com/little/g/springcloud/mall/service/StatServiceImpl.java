package com.little.g.springcloud.mall.service;

import com.little.g.springcloud.mall.api.StatService;
import com.little.g.springcloud.mall.mapper.StatMapper;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(protocol = "dubbo")
public class StatServiceImpl implements StatService {

	@Resource
	private StatMapper statMapper;

	@Override
	public List<Map> statUser() {
		return statMapper.statUser();
	}

	@Override
	public List<Map> statOrder() {
		return statMapper.statOrder();
	}

	@Override
	public List<Map> statGoods() {
		return statMapper.statGoods();
	}

}
