package siyl.cit.shopping.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import siyl.cit.shopping.mapper.UserMapper;
import siyl.cit.shopping.model.User;
import siyl.cit.shopping.util.MyBatisUtil;

public class TestMybatisByMapper {
	@Test
	public void testAdd() {
		SqlSession session = null;
		try {
			session = MyBatisUtil.createSession();
			User u = new User();
			u.setNickname("猪八戒");
			u.setPassword("222");
			u.setType(1);
			u.setUsername("bajie");
			session.getMapper(UserMapper.class).add(u);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			MyBatisUtil.closeSession(session);
		}
	}
}
