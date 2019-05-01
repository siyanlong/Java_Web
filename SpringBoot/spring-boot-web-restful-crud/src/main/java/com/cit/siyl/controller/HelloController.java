package com.cit.siyl.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	// // 设置访问首页
	// @RequestMapping("/1234")
	// public String index() {
	// return "login";
	// }

	@ResponseBody
	@RequestMapping("/hello")
	public String helloWord() {
		return "Hello World!";
	}

	// 查出一些数据，在页面显示
	@RequestMapping("/success")
	public String success(Map<String, Object> map) {
		map.put("hello", "<h1>你好！</h1>");
		map.put("users", Arrays.asList("唐僧 ", "孙悟空 ", "沙僧"));
		return "success";
	}
}
