package siyl.cit.spring.dao;

import siyl.cit.spring.model.LogInfo;
import siyl.cit.spring.model.User;

public interface IUserDao {
	@LogInfo("添加用户信息！")
	public void add(User user);

	@LogInfo("删除用户信息！")
	public void delete(int id);

	public User load(int id);
}
