package com.little.g.springcloud.mall.api;

import java.util.List;
import java.util.Map;

public interface StatService {

	List<Map> statUser();

	List<Map> statOrder();

	List<Map> statGoods();

}
