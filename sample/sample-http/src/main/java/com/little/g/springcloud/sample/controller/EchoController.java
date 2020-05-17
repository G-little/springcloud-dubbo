package com.little.g.springcloud.sample.controller;

import com.little.g.springcloud.sample.api.EchoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

	@Reference
	private EchoService echoService;

	@GetMapping("/echo/{str}")
	public String echo(@PathVariable String str) {
		return echoService.echo(str);
	}

}
