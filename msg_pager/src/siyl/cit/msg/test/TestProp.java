package siyl.cit.msg.test;

import java.util.Properties;

import siyl.cit.msg.util.PropertiesUtil;

public class TestProp {

	public static void main(String[] args) {
		try {
			Properties prop = new Properties();
			prop.load(TestProp.class.getClassLoader().getResourceAsStream("jdbc.properties"));
			String username = prop.getProperty("username");
			System.out.println(username);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 测试是否为单例
		Properties p1 = PropertiesUtil.getJdbcProp();
		Properties p2 = PropertiesUtil.getJdbcProp();
		if (p1 == p2) {
			System.out.println(true);
		}
	}

}
