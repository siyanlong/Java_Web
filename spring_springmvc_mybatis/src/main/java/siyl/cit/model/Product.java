package siyl.cit.model;

public class Product {
    private Integer id;

    private String name;

    private Double price;

    private String img;

    private Integer stock;

    private Integer cId;

    private Integer status;

    private String intro;

    public Product(Integer id, String name, Double price, String img, Integer stock, Integer cId, Integer status, String intro) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
        this.stock = stock;
        this.cId = cId;
        this.status = status;
        this.intro = intro;
    }

    public Product() {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }
}