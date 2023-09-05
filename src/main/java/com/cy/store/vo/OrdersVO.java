package com.cy.store.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class OrdersVO implements Serializable {
    private Integer oid;
    private Integer gid;
    private String gname;
    private String img;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrdersVO)) return false;
        OrdersVO ordersVO = (OrdersVO) o;
        return Objects.equals(getOid(), ordersVO.getOid()) &&
                Objects.equals(getGid(), ordersVO.getGid()) &&
                Objects.equals(getGname(), ordersVO.getGname()) &&
                Objects.equals(getImg(), ordersVO.getImg()) &&
                Objects.equals(getSellid(), ordersVO.getSellid()) &&
                Objects.equals(getBuyid(), ordersVO.getBuyid()) &&
                Objects.equals(getPrice(), ordersVO.getPrice()) &&
                Objects.equals(getName(), ordersVO.getName()) &&
                Objects.equals(getPhone(), ordersVO.getPhone()) &&
                Objects.equals(getContent(), ordersVO.getContent()) &&
                Objects.equals(getStatus(), ordersVO.getStatus()) &&
                Objects.equals(getCtime(), ordersVO.getCtime()) &&
                Objects.equals(getTag(), ordersVO.getTag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOid(), getGid(), getGname(), getImg(), getSellid(), getBuyid(), getPrice(), getName(), getPhone(), getContent(), getStatus(), getCtime(), getTag());
    }

    @Override
    public String toString() {
        return "OrdersVO{" +
                "oid=" + oid +
                ", gid=" + gid +
                ", gname='" + gname + '\'' +
                ", img='" + img + '\'' +
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
