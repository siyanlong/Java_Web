package siyl.cit.msg.dao;

import siyl.cit.msg.model.Pager;
import siyl.cit.msg.model.User;

public interface IUserDao {
	public void add(User user);

	public void delete(int user);

	public void update(User user);

	public User load(int id);

	public Pager<User> list(String condition);

	public User login(String username, String password);
}
