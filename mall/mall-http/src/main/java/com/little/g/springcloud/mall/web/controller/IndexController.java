package com.little.g.springcloud.mall.web.controller;

import com.little.g.springcloud.common.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试服务
 */
@RestController
@RequestMapping("/index")
@Slf4j
public class IndexController {

	/**
	 * 测试数据
	 * @return 测试数据
	 */
	@RequestMapping("/index")
	public Object index() {
		return ResponseUtil.ok("hello world, this is wx service");
	}

}
