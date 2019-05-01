package siyl.cit.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import siyl.cit.model.User;
import siyl.cit.service.UserService;

public class TestMybatisByMethodOne {

	@Test
	public void test() {
		//获取上下文信息
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] { "spring.xml", "spring-mybatis.xml" });
		UserService userService = (UserService) ac.getBean("userService");
		User user = userService.getUserById(1);
		System.out.println(user);
	}

}
