package siyl.cit.shopping.mapper;

import java.util.List;
import java.util.Map;

import siyl.cit.shopping.model.User;

public interface UserMapper {
	public void add(User user);

	public void delete(int id);

	public void update(User user);

	public User loadByUsername(String username);

	public User load(int id);

	public List<User> find(Map<String, Object> params);

	public User login(String username, String password);

	public int find_count(Map<String, Object> params);
}
