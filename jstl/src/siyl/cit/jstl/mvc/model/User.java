package siyl.cit.jstl.mvc.model;

public class User {
	private String username;
	private String nickname;
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public User(String username, String nickname, int age) {
		this.username = username;
		this.nickname = nickname;
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", nickname=" + nickname + "]";
	}
}
