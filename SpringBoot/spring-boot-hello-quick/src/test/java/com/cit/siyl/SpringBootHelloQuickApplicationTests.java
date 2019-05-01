package com.cit.siyl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.cit.siyl.model.Person;

//RunWith注解中写SpringRunner是使用Spring的驱动器来运行，而不是JUnit的驱动器
@RunWith(SpringRunner.class)
//SpringBootTest注解用于声明这个是一个Spring Boot的单元测试，这样可以在测试期间很方便的类似编码一样进行自动注入
@SpringBootTest
public class SpringBootHelloQuickApplicationTests {

	@Autowired
	private Person person;
	
	//Spring的IOC容器
	@Autowired
	ApplicationContext ioc;
	
	//日志记录器
	Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void contextLoads() {
		System.out.println(person);
	}
	
	@Test
	public void testBeans() {
		//验证IOC容器中是否包含helloService组件
		boolean flag = ioc.containsBean("helloService");
		System.out.println(flag);
	}
	
	@Test
	public void testLogging() {
		//日志的级别
		//由低到高   trace < debug < info < warn < error
		//可以调整输出的日志级别；日志就只会在这个级别以后的高级别生效
		logger.trace("这是trace日志...");
		logger.debug("这是debug日志...");
		//Spring Boot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别：root级别
		logger.info("这是info日志...");
		logger.warn("这是warn日志...");
		logger.error("这是error日志...");
	}
}
