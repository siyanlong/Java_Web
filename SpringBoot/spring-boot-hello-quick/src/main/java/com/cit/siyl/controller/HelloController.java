package com.cit.siyl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//这个类的所有方法返回的数据都直接写给浏览器（如果是对象自动转为json数据）
//@Controller
//@ResponseBody
@RestController
public class HelloController {
	@RequestMapping("/hello")
	public String hello() {
		return "Hello world quick!";
	}
}
