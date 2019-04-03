package siyl.cit.model;

import java.util.ArrayList;
import java.util.List;

public class Group {
	private int id;
	private String name;
	List<User> users;

	public void addUser(User u) {
		if (users == null) {
			users = new ArrayList<User>();
		}
		users.add(u);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Group(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Group() {
	}

}
