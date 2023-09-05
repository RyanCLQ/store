package com.cy.store.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Goods implements Serializable {
    private Integer gid;
    private Integer cid;
    private String uid;
    private String gname;
    private Float price;
    private Date ctime;
    private String gdescribe;
    private String img;
    private Integer status;
    private Integer tag;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getGdescribe() {
        return gdescribe;
    }

    public void setGdescribe(String gdescribe) {
        this.gdescribe = gdescribe;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Goods)) return false;
        Goods goods = (Goods) o;
        return Objects.equals(getGid(), goods.getGid()) &&
                Objects.equals(getCid(), goods.getCid()) &&
                Objects.equals(getUid(), goods.getUid()) &&
                Objects.equals(getGname(), goods.getGname()) &&
                Objects.equals(getPrice(), goods.getPrice()) &&
                Objects.equals(getCtime(), goods.getCtime()) &&
                Objects.equals(getGdescribe(), goods.getGdescribe()) &&
                Objects.equals(getImg(), goods.getImg()) &&
                Objects.equals(getStatus(), goods.getStatus()) &&
                Objects.equals(getTag(), goods.getTag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGid(), getCid(), getUid(), getGname(), getPrice(), getCtime(), getGdescribe(), getImg(), getStatus(), getTag());
    }

    @Override
    public String toString() {
        return "Goods{" +
                "gid=" + gid +
                ", cid=" + cid +
                ", uid='" + uid + '\'' +
                ", gname='" + gname + '\'' +
                ", price=" + price +
                ", ctime=" + ctime +
                ", gdescribe='" + gdescribe + '\'' +
                ", img='" + img + '\'' +
                ", status=" + status +
                ", tag=" + tag +
                '}';
    }
}
