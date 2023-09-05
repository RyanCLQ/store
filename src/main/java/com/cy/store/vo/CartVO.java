package com.cy.store.vo;

import java.io.Serializable;
import java.util.Objects;

public class CartVO implements Serializable {
    private Integer ctid;
    private String uid;
    private Integer gid;
    private float price;
    private String gname;
    private String img;

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

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartVO)) return false;
        CartVO cartVO = (CartVO) o;
        return Float.compare(cartVO.getPrice(), getPrice()) == 0 &&
                Objects.equals(getCtid(), cartVO.getCtid()) &&
                Objects.equals(getUid(), cartVO.getUid()) &&
                Objects.equals(getGid(), cartVO.getGid()) &&
                Objects.equals(getGname(), cartVO.getGname()) &&
                Objects.equals(getImg(), cartVO.getImg());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCtid(), getUid(), getGid(), getPrice(), getGname(), getImg());
    }

    @Override
    public String toString() {
        return "CartVO{" +
                "ctid=" + ctid +
                ", uid='" + uid + '\'' +
                ", gid=" + gid +
                ", price=" + price +
                ", gname='" + gname + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
