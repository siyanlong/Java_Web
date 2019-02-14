package siyl.cit.spring.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import siyl.cit.spring.action.UserAction;
import siyl.cit.spring.model.HelloWorld;
import siyl.cit.spring.model.User;

class TestSpring {

	// 创建Spring的工厂
	private BeanFactory factory = new ClassPathXmlApplicationContext("beans.xml");

	@Test
	void testHello() {
		// 通过工厂获取Spring的对象
		HelloWorld hello = (HelloWorld) factory.getBean("helloWorld", HelloWorld.class);

		// 此时的hello对象就是被Spring所管理的对象
		System.out.println(hello.hello());

		// 验证是否为单例模式
		HelloWorld hello2 = (HelloWorld) factory.getBean("helloWorld", HelloWorld.class);

		// 如果在bean中没有作scope的配置，默认是singleton（单例），当把bean中的scope设置为prototype的时候就是多例
		System.out.println(hello == hello2);
	}

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
