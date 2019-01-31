package siyl.cit.spring.dao;

import org.springframework.stereotype.Repository;

import siyl.cit.spring.model.User;

@Repository("userJDBCDao")
public class UserJDBCDao implements IUserDao {

	@Override
	public void add(User user) {
	}

	@Override
	public void delete(int id) {
	}

	@Override
	public User load(int id) {
		return null;
	}

}
