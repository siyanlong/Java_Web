package siyl.cit.shopping.dao;

import siyl.cit.shopping.model.Pager;
import siyl.cit.shopping.model.User;

public interface IUserDao {
	public void add(User user);

	public void delete(int id);

	public void update(User user);

	public User loadByUsername(String username);

	public User load(int id);

	public Pager<User> find(String name);

	public User login(String username, String password);
}