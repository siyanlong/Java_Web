package siyl.cit.shop.dao;

public class DAOFactory {
	public static IUserDao getUserDao() {
		return new UserDao();
	}
}
