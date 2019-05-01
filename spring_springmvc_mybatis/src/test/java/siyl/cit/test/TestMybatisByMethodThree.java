package siyl.cit.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import siyl.cit.model.User;
import siyl.cit.service.UserService;

//使用RunWith(SpringJUnit4ClassRunner.class)注解作用相当于测试类继承于SpringJUnit4ClassRunner
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class TestMybatisByMethodThree {
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Test
	public void test() {
		User user = userService.getUserById(1);
		//System.out.println(user);
		System.out.println(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
		//System.out.println(JSON.toJSONString(user));
	}
	
	@Test
	public void testAllUser() {
		List<User> userList = userService.getAllUser();
		System.out.println(JSON.toJSONString(userList));
	}
	
	@Test
	public void testAllUser_New() {
		List<User> userList = userService.getAllUser_New();
		System.out.println(JSON.toJSONString(userList));
	}
}
