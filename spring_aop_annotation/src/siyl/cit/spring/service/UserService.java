package siyl.cit.spring.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import siyl.cit.spring.dao.IMessageDao;
import siyl.cit.spring.dao.IUserDao;
import siyl.cit.spring.model.User;

//@Component("userService")
@Service("userService") // 业务层一般使用@Service
public class UserService implements IUserService {
	private IUserDao userDao;
	private IMessageDao messageDao;

	public IUserDao getUserDao() {
		return userDao;
	}

	public IMessageDao getMessageDao() {
		return messageDao;
	}

	@Resource(name = "messageDao")
	public void setMessageDao(IMessageDao messageDao) {
		this.messageDao = messageDao;
	}

	// 默认通过名称注入，在JSR330中提供了@Inject来注入
	@Resource(name = "userDao")
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void add(User user) {
		messageDao.add();
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
