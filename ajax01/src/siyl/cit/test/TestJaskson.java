package siyl.cit.test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import siyl.cit.model.Group;
import siyl.cit.model.User;
import siyl.cit.util.JsonUtil;

public class TestJaskson {

	@Test
	public void testUser() {
		StringWriter out = new StringWriter();
		JsonGenerator jg = null;
		try {
			// 1、创建JsonFactory
			JsonFactory jf = new JsonFactory();
			// 2、创建JsonGenerator
			jg = jf.createJsonGenerator(out);
			// 使用一种相对漂亮的格式输出
			jg.useDefaultPrettyPrinter();
			// 3、创建ObjectMapper,通过ObjectMapper来写对象
			User u = new User(1, "劳动法", "123", new Group(1, "财务处"));
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(jg, u);
			System.out.println(out.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (jg != null)
					jg.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testList() {
		StringWriter out = new StringWriter();
		JsonGenerator jg = null;
		try {
			// 1、创建JsonFactory
			JsonFactory jf = new JsonFactory();
			// 2、创建JsonGenerator
			jg = jf.createJsonGenerator(out);
			// 使用一种相对漂亮的格式输出
			// jg.useDefaultPrettyPrinter();
			// 3、创建ObjectMapper,通过ObjectMapper来写对象
			List<User> us = new ArrayList<User>();
			User user = new User(1, "劳动法", "123", new Group(1, "财务处"));
			us.add(user);
			user = new User(2, "猪八戒", "123", new Group(1, "财务处"));
			us.add(user);
			user = new User(3, "张学友", "123", new Group(2, "教务处"));
			us.add(user);
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(jg, us);
			System.out.println(out.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (jg != null)
					jg.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testJsonUtil() {
		List<User> us = new ArrayList<User>();
		User u = new User(1, "劳动法", "123", new Group(1, "财务处"));
		us.add(u);
		u = new User(2, "猪八戒", "123", new Group(1, "财务处"));
		us.add(u);
		u = new User(3, "张学友", "123", new Group(2, "教务处"));
		us.add(u);
		System.out.println(JsonUtil.getInstance().objToJson(us));
	}

	@Test
	public void testFromJson() {
		try {
			String json = "{\"id\":1,\"username\":\"杂货\",\"password\":\"123\",\"group\":{\"id\":1,\"name\":\"财务处\"}}";
			ObjectMapper mapper = new ObjectMapper();
			User u = mapper.readValue(json, User.class);
			System.out.println(u.getId() + "," + u.getUsername() + "," + u.getGroup().getName());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFromListJson() {
		try {
			String json = "[{\"id\":1,\"username\":\"劳动法\",\"password\":\"123\",\"group\":{\"id\":1,\"name\":\"财务处\"}},{\"id\":2,\"username\":\"猪八戒\",\"password\":\"123\",\"group\":{\"id\":1,\"name\":\"财务处\"}},{\"id\":3,\"username\":\"张学友\",\"password\":\"123\",\"group\":{\"id\":2,\"name\":\"教务处\"}}]";
			ObjectMapper mapper = new ObjectMapper();
			// 在把json转换为list的时候，不能直接存储Bean对象，list中存储的是Map对象
			/*
			 * List<User> us = mapper.readValue(json,List.class); for(User u:us) {
			 * System.out.println(u.getUsername()); }
			 */
			/**
			 * 所以的json都是通过map来存储的，不会直接存储bean，但是在开发中，把字符串转换为对象 一般只会对单个的对象转换，很少会用到对对象的完全转换
			 */
			List<Map<String, Object>> us = mapper.readValue(json, List.class);
			for (Map<String, Object> m : us) {
				System.out.println(m.get("id"));
				System.out.println(m.get("group"));
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testReadTree() {
		try {
			String json = "[{\"id\":1,\"username\":\"劳动法\",\"password\":\"123\",\"group\":{\"id\":1,\"name\":\"财务处\"}},{\"id\":2,\"username\":\"猪八戒\",\"password\":\"123\",\"group\":{\"id\":1,\"name\":\"财务处\"}},{\"id\":3,\"username\":\"张学友\",\"password\":\"123\",\"group\":{\"id\":2,\"name\":\"教务处\"}}]";
			ObjectMapper mapper = new ObjectMapper();
			/*
			 * 在Jaskson中提供了一种基于节点的读取方法
			 */
			JsonNode node = mapper.readTree(json);
			// 判断这个节点是否是数组
			System.out.println(node.isArray());
			System.out.println(node.size());
			System.out.println(node.get(0).get("username"));
			System.out.println(node.get(0).get("group").get("name"));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
