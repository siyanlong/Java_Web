package com.cit.siyl.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cit.siyl.exception.UserNotExistException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

// 异常处理器
@ControllerAdvice // 在SpringMVC中要成为异常处理器就要使用这个注解
public class MyExceptionHandler {

	// 使用这种方法浏览器客户端返回的都是json
	// @ResponseBody
	// @ExceptionHandler(UserNotExistException.class)
	// public Map<String,Object> handleException(Exception e){
	// Map<String,Object> map = new HashMap<>();
	// map.put("code","user.notexist");
	// map.put("message",e.getMessage());
	// return map;
	// }

	// 使用下面的方法浏览器就不会返回json数据啦
	@ExceptionHandler(UserNotExistException.class) // 要处理什么异常使用这个注解标注
	public String handleException(Exception e, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		// 传入我们自己的错误状态码 4xx 5xx
		/**
		 * Integer statusCode = (Integer) request
		 * .getAttribute("javax.servlet.error.status_code");
		 */
		request.setAttribute("javax.servlet.error.status_code", 500);
		map.put("code", "user.notexist");
		map.put("message", "用户出错啦");

		request.setAttribute("ext", map);
		// 转发到/error
		return "forward:/error";
	}
}
