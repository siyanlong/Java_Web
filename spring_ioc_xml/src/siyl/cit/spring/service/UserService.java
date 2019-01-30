package siyl.cit.spring.service;

import siyl.cit.spring.dao.IUserDao;
import siyl.cit.spring.model.User;

public class UserService implements IUserService {
	private IUserDao userDao;
	private IUserDao userJDBCDao;

	public IUserDao getUserJDBCDao() {
		return userJDBCDao;
	}

	public void setUserJDBCDao(IUserDao userJDBCDao) {
		this.userJDBCDao = userJDBCDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void add(User user) {
		userDao.add(user);
	}

	@Override
	public void delete(int id) {
		userDao.delete(id);
	}

	@Override
	public User load(int id) {
		return userDao.load(id);
	}

}
