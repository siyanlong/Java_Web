package siyl.cit.spring.action;

import java.util.List;

import siyl.cit.spring.model.User;
import siyl.cit.spring.service.IUserService;

public class UserAction {
	private User user;
	private IUserService userService;
	private int id;
	private List<String> names;

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public UserAction() {
	}

	public UserAction(IUserService userService) {
		super();
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void add() {
		System.out.println(id);
		System.out.println(names);
		userService.add(user);
	}

	public void delete() {
		userService.delete(id);
	}

	public void load() {
		userService.load(id);
	}
}
