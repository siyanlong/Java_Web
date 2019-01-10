package siyl.cit.shopping.dao;

public class DAOFactory {
	public static IUserDao getUserDao() {
		return new UserDao();
	}

	public static IAddressDao getAddressDao() {
		return new AddressDao();
	}
}
