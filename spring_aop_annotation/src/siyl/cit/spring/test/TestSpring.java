package siyl.cit.spring.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import siyl.cit.spring.action.UserAction;
import siyl.cit.spring.model.User;

class TestSpring {

	// 创建Spring的工厂
	private BeanFactory factory = new ClassPathXmlApplicationContext("beans.xml");

	@Test
	public void testUser() {
		UserAction ua = factory.getBean("userAction", UserAction.class);
		User u = new User(1, "悟空");
		ua.setUser(u);
		ua.add();
		ua.delete();
		ua.load();
	}
}
