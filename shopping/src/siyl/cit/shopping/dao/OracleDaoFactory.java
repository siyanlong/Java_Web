package siyl.cit.shopping.dao;

public class OracleDaoFactory implements IFactoryDao {
	private static IFactoryDao factory = new OracleDaoFactory();

	private OracleDaoFactory() {
	}

	public static IFactoryDao getInstance() {
		return factory;
	}

	@Override
	public IUserDao createUserDao() {
		return new UserOracleDao();
	}

	@Override
	public IAddressDao createAddressDao() {
		return new AddressOracleDao();
	}

}
