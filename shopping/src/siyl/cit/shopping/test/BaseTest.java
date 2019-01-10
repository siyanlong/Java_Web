package siyl.cit.shopping.test;

import siyl.cit.shopping.util.DaoUtil;

public class BaseTest {
	public BaseTest() {
		DaoUtil.diDao(this);
	}
}
