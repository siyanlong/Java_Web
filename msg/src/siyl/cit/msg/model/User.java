package siyl.cit.msg.model;

public class User {
	private int id;
	private String username;
	private String password;
	private String nicknanme;
	private int status;
	private int type;
	  
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNicknanme() {
		return nicknanme;
	}
	public void setNicknanme(String nicknanme) {
		this.nicknanme = nicknanme;
	}
	
}
