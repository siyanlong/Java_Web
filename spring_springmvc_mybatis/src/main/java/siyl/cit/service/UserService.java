package siyl.cit.service;

import java.util.List;

import siyl.cit.model.User;

public interface UserService {

	public User getUserById(int id);
	
	public List<User> getAllUser();
	
	public List<User> getAllUser_New();
}
