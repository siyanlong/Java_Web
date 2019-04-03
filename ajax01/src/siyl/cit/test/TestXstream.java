package siyl.cit.test;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

import siyl.cit.model.Group;
import siyl.cit.model.Person;
import siyl.cit.model.User;

class TestXstream {

	@Test
	public void testPerson() {
		// 1、创建XStream对象
		XStream stream = new XStream();

		// 默认的名称使用的是包的名称可以通过alias改别名
		stream.alias("person", Person.class);

		// 改属性名也是可以的，一般不用
		stream.aliasField("username", Person.class, "name");

		// 如果希望把某个子节点修改为属性也可以，但是更不常用
		stream.useAttributeFor(Person.class, "id");

		// 2、创建对象
		Person person = new Person(1, "小武", 1900, 24);

		// 3、完成转换
		String xml = stream.toXML(person);

		// 4、输出
		System.out.println(xml);
	}

	@Test
	public void testUser() {
		XStream stream = new XStream();
		stream.alias("user", User.class);
		stream.alias("group", Group.class);
		User user = new User(1, "劳动法", "123", new Group(1, "财务处"));
		System.out.println(stream.toXML(user));
	}

	@Test
	public void testUsers() {
		XStream stream = new XStream();
		stream.alias("user", User.class);
		stream.alias("users", List.class);
		List<User> us = new ArrayList<User>();
		User user = new User(1, "劳动法", "123", new Group(1, "财务处"));
		us.add(user);
		us.add(new User(2, "张学友", "123", new Group(1, "财务处")));
		us.add(new User(3, "黎明", "123", new Group(1, "财务处")));
		us.add(new User(4, "郭富城", "123", new Group(1, "物理系")));
		System.out.println(stream.toXML(us));
	}

	@Test
	public void testGroup() {
		XStream stream = new XStream();
		stream.alias("user", User.class);
		stream.alias("group", Group.class);
		Group group = new Group();
		/*
		 * 默认情况在users这个节点中会像封装一个users的父亲节点，然后才封装user 有些时候可能不需要users可以使用
		 */
		stream.addImplicitCollection(Group.class, "users");
		group.addUser(new User(1, "老张", "123"));
		group.addUser(new User(2, "老李", "1234"));
		group.addUser(new User(3, "老刘", "12345"));
		group.addUser(new User(4, "老王", "123456"));
		System.out.println(stream.toXML(group));
	}

	@Test
	public void testFromXML() {
		XStream stream = new XStream();
		stream.alias("user", User.class);
		String xml = "<user><id>1</id><username>aaa</username><password>123</password>"
				+ "<group><id>1</id><name>财务处</name></group></user>";
		User user = (User) stream.fromXML(xml);
		System.out.println(
				user.getId() + "," + user.getUsername() + "," + user.getPassword() + "," + user.getGroup().getName());
	}

	@Test
	public void testFromListXML() {
		XStream stream = new XStream();
		stream.alias("user", User.class);
		stream.alias("users", List.class);
		List<User> us = (List<User>) stream
				.fromXML(TestXstream.class.getClassLoader().getResourceAsStream("users.xml"));
		for (User user : us) {
			System.out.println(user.getId() + "," + user.getUsername() + "," + user.getPassword() + ","
					+ user.getGroup().getName());
		}
	}

	@Test
	public void testJson() {
		// 转换json需要使用Jettison这个包
		XStream stream = new XStream(new JettisonMappedXmlDriver() {
			// 通过覆盖以下方法可以有效的删除json根节点
			// 一般不使用XStream来转换json，效率不高，效率比较高的Json工具是Jaskson
			@Override
			public HierarchicalStreamWriter createWriter(Writer out) {
				return new JsonWriter(out, JsonWriter.DROP_ROOT_MODE);
			}
		});
		stream.alias("group", Group.class);
		stream.alias("user", User.class);
		Group g = new Group();
		g.addUser(new User(1, "老张", "123"));
		g.addUser(new User(2, "老李", "1234"));
		g.addUser(new User(3, "老刘", "12345"));
		g.addUser(new User(4, "老王", "123456"));
		System.out.println(stream.toXML(g));
	}
}
