package com.sboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@RequestMapping("/test")
	@ResponseBody
	public String home() {
		return "I am learning STS";
	}
	
	@RequestMapping("/test1")
	public String Test() {
		return "test";
	}
}
