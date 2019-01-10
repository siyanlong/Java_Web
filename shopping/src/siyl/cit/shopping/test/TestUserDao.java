package siyl.cit.shopping.test;

import org.junit.jupiter.api.Test;

import siyl.cit.shopping.dao.IUserDao;
import siyl.cit.shopping.model.Pager;
import siyl.cit.shopping.model.ShopDi;
import siyl.cit.shopping.model.SystemContext;
import siyl.cit.shopping.model.User;

public class TestUserDao extends BaseTest {

	/*
	 * private IUserDao userDao = null;
	 * 
	 * @BeforeEach public void init() { userDao = (IUserDao)
	 * DaoUtil.createDaoFactory_New().createDao("userDao"); }
	 */

	private IUserDao ud;

	public IUserDao getUd() {
		return ud;
	}

	@ShopDi("userDao")
	public void setUd(IUserDao ud) {
		this.ud = ud;
	}

	@Test
	public void testAdd() {
		User u = new User();
		u.setNickname("曹操3");
		u.setPassword("123");
		u.setType(1);
		u.setUsername("cccc1d12");
		ud.add(u);
	}

	@Test
	public void testLoad() {
		User user = ud.load(2);
		System.out.println(user.getId() + user.getPassword());
	}

	@Test
	public void testFind() {
		SystemContext.setPageOffset(0);
		SystemContext.setPageSize(10);
		SystemContext.setOrder("desc");
		SystemContext.setSort("username");
		Pager<User> pg = ud.find("张");
		for (User user : pg.getDatas()) {
			System.out.println(user);
		}
	}
}
