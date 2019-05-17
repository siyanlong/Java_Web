package com.cit.siyl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cit.siyl.component.LoginHandlerInterceptor;
import com.cit.siyl.component.MyLocalResolver;

//使用WebMvcConfigurer可以来扩展SpringMVC的功能
//@EnableWebMvc 添加@EnableWebMvc注解会接管SpringMVC，目前我们不要接管SpringMVC
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
	//添加视图映射
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("login");
		registry.addViewController("/index.html").setViewName("login");
		registry.addViewController("/main.html").setViewName("dashboard");
	}
		
	//注册拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//静态资源：*.css，*.js
		//Spring Boot已经做好了静态资源映射，因此不需要排除对静态资源的过滤，但需要排序登陆界面的拦截，要不然连登陆界面都访问不了了
		registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
			.excludePathPatterns("/index.html", "/", "/user/login");
	}

	@Bean//将组件注册在容器
	public LocaleResolver localeResolver() {
		return new MyLocalResolver();
	}
}
