package siyl.cit.spring.dao;

import org.springframework.stereotype.Repository;

import siyl.cit.spring.model.User;

//等于完成了<bean id="userDao" class="siyl.cit.spring.UserDao"/>
//@Component("userDao")
@Repository("userDao") // @Repository一般用于DAO的注入
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
