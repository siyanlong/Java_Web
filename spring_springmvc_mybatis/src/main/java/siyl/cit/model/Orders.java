package siyl.cit.model;

import java.util.Date;

public class Orders {
    private Integer id;

    private Date buyDate;

    private Date payDate;

    private Date confirmDate;

    private Integer status;

    private Integer userId;

    private Integer addressId;

    private Double price;

    public Orders(Integer id, Date buyDate, Date payDate, Date confirmDate, Integer status, Integer userId, Integer addressId, Double price) {
        this.id = id;
        this.buyDate = buyDate;
        this.payDate = payDate;
        this.confirmDate = confirmDate;
        this.status = status;
        this.userId = userId;
        this.addressId = addressId;
        this.price = price;
    }

    public Orders() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}