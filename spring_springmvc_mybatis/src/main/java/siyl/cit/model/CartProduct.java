package siyl.cit.model;

public class CartProduct {
    private Integer id;

    private Integer number;

    private Double price;

    private Integer oId;

    private Integer pId;

    public CartProduct(Integer id, Integer number, Double price, Integer oId, Integer pId) {
        this.id = id;
        this.number = number;
        this.price = price;
        this.oId = oId;
        this.pId = pId;
    }

    public CartProduct() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }
}