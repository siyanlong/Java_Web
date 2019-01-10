package siyl.cit.shopping.dao;

public interface IFactoryDao {
	public IUserDao createUserDao();

	public IAddressDao createAddressDao();
}
