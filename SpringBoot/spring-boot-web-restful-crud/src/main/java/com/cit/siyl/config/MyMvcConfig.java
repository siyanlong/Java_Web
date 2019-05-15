package com.cit.siyl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cit.siyl.component.MyLocalResolver;

//使用WebMvcConfigurer可以来扩展SpringMVC的功能
//@EnableWebMvc 添加@EnableWebMvc注解会接管SpringMVC，目前我们不要接管SpringMVC
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("login");
		registry.addViewController("/index.html").setViewName("login");
	}
	
	@Bean//将组件注册在容器
	public LocaleResolver localeResolver() {
		return new MyLocalResolver();
	}
}
