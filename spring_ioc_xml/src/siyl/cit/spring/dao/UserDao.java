package siyl.cit.spring.dao;

import siyl.cit.spring.model.User;

public class UserDao implements IUserDao {

	@Override
	public void add(User user) {
		System.out.println("添加：" + user);
	}

	@Override
	public void delete(int id) {
		System.out.println("删除：" + id);
	}

	@Override
	public User load(int id) {
		System.out.println("加载：" + id);
		return null;
	}

}
