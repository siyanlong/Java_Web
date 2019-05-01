package siyl.cit.model;

public class Address {
    private Integer id;

    private String name;

    private String phone;

    private String postcode;

    private Integer userId;

    public Address(Integer id, String name, String phone, String postcode, Integer userId) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.postcode = postcode;
        this.userId = userId;
    }

    public Address() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}