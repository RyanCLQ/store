package com.cy.store.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Cart implements Serializable {
    private Integer ctid;
    private String uid;
    private Integer gid;
    private float price;
    private Date ctime;

    public Integer getCtid() {
        return ctid;
    }

    public void setCtid(Integer ctid) {
        this.ctid = ctid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return Float.compare(cart.getPrice(), getPrice()) == 0 &&
                Objects.equals(getCtid(), cart.getCtid()) &&
                Objects.equals(getUid(), cart.getUid()) &&
                Objects.equals(getGid(), cart.getGid()) &&
                Objects.equals(getCtime(), cart.getCtime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCtid(), getUid(), getGid(), getPrice(), getCtime());
    }

    @Override
    public String toString() {
        return "Cart{" +
                "ctid=" + ctid +
                ", uid='" + uid + '\'' +
                ", gid=" + gid +
                ", price=" + price +
                ", ctime=" + ctime +
                '}';
    }
}
