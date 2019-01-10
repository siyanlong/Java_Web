package siyl.cit.shopping.dao;

public class MysqlDaoFactory implements IFactoryDao {
	private static IFactoryDao factory = new MysqlDaoFactory();

	private MysqlDaoFactory() {

	}

	public static IFactoryDao getInstance() {
		return factory;
	}

	@Override
	public IUserDao createUserDao() {
		return new UserDao();
	}

	@Override
	public IAddressDao createAddressDao() {
		return new AddressDao();
	}

}
