package siyl.cit.model;

public class User {
    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private Integer type;

    public User(Integer id, String username, String password, String nickname, Integer type) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.type = type;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}