package com.cy.store.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Orders implements Serializable {
    private Integer oid;
    private Integer gid;
    private String sellid;
    private String buyid;
    private Float price;
    private String name;
    private String phone;
    private String content;
    private Integer status;
    private Date ctime;
    private Integer tag;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getSellid() {
        return sellid;
    }

    public void setSellid(String sellid) {
        this.sellid = sellid;
    }

    public String getBuyid() {
        return buyid;
    }

    public void setBuyid(String buyid) {
        this.buyid = buyid;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Orders)) return false;
        Orders orders = (Orders) o;
        return Objects.equals(getOid(), orders.getOid()) &&
                Objects.equals(getGid(), orders.getGid()) &&
                Objects.equals(getSellid(), orders.getSellid()) &&
                Objects.equals(getBuyid(), orders.getBuyid()) &&
                Objects.equals(getPrice(), orders.getPrice()) &&
                Objects.equals(getName(), orders.getName()) &&
                Objects.equals(getPhone(), orders.getPhone()) &&
                Objects.equals(getContent(), orders.getContent()) &&
                Objects.equals(getStatus(), orders.getStatus()) &&
                Objects.equals(getCtime(), orders.getCtime()) &&
                Objects.equals(getTag(), orders.getTag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOid(), getGid(), getSellid(), getBuyid(), getPrice(), getName(), getPhone(), getContent(), getStatus(), getCtime(), getTag());
    }

    @Override
    public String toString() {
        return "Orders{" +
                "oid=" + oid +
                ", gid=" + gid +
                ", sellid='" + sellid + '\'' +
                ", buyid='" + buyid + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", ctime=" + ctime +
                ", tag=" + tag +
                '}';
    }

}
