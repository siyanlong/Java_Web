package siyl.cit.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import siyl.cit.model.User;
import siyl.cit.service.UserService;

public class TestMybatisByMethodTwo {
	private ApplicationContext ac;
	private UserService userService;
	
	@Before
	public void beforeMethod() {
		//获取上下文信息
		ac = new ClassPathXmlApplicationContext(new String[] { "spring.xml", "spring-mybatis.xml" });
		userService = (UserService) ac.getBean("userService");
	}
	
	@Test
	public void test() {
		User user = userService.getUserById(1);
		System.out.println(user);
	}
}
