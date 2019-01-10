package siyl.cit.shopping.test;

import java.util.List;

import org.junit.Test;

import siyl.cit.shopping.dao.IAddressDao;
import siyl.cit.shopping.dao.IUserDao;
import siyl.cit.shopping.model.Address;
import siyl.cit.shopping.model.ShopDi;

public class TestAddressDao extends BaseTest {
	// private IAddressDao addressDao =
	// DaoUtil.createDaoFactory().createAddressDao();

	private IAddressDao addressDao;
	private IUserDao userDao;

	public IAddressDao getAddressDao() {
		return addressDao;
	}

	@ShopDi
	public void setAddressDao(IAddressDao addressDao) {
		this.addressDao = addressDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	@ShopDi
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Test
	public void testLoad() {
		Address address = addressDao.load(5);
		System.out.println(address.getName() + "," + address.getUser() + "," + address.getPostcode());
	}

	@Test
	public void testAdd() {
		Address address = new Address();
		address.setName("云南省大理州2223");
		address.setPhone("114");
		address.setPostcode("652000");
		addressDao.add(address, 2);
	}

	@Test
	public void testList() {
		List<Address> list = addressDao.list(1);
		for (Address a : list) {
			System.out.println(a.getName() + "," + a.getUser());
		}
	}
}
