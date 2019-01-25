package siyl.cit.shopping.test;

import org.junit.Test;

import siyl.cit.shopping.dao.IOrdersDao;
import siyl.cit.shopping.model.CartProduct;
import siyl.cit.shopping.model.Orders;
import siyl.cit.shopping.model.ShopDi;

public class TestOrderDao extends BaseTest {
	private IOrdersDao ordersDao;

	public IOrdersDao getOrdersDao() {
		return ordersDao;
	}

	@ShopDi
	public void setOrdersDao(IOrdersDao ordersDao) {
		this.ordersDao = ordersDao;
	}

	@Test
	public void testLoad() {
		Orders o = ordersDao.load(7);
		System.out.println(
				o.getPrice() + "," + o.getStatus() + "," + o.getAddress().getName() + "," + o.getUser().getNickname());
		for (CartProduct cp : o.getProducts()) {
			System.out.println(cp.getProduct().getName() + "," + cp.getNumber());
		}
	}
}
