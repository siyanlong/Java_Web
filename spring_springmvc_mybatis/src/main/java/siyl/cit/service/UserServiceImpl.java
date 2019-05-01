package siyl.cit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siyl.cit.dao.UserMapper;
import siyl.cit.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;

	public UserMapper getUserMapper() {
		return userMapper;
	}

	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public User getUserById(int id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<User> getAllUser() {
		return userMapper.getAllUser();
	}
	
	@Override
	public List<User> getAllUser_New() {
		return userMapper.getAllUser_New();
	}
}
