package com.cy.store.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class GoodsVO implements Serializable {
    private Integer gid;
    private Integer cid;
    private String uid;
    private String gname;
    private String price;
    private Date ctime;
    private String gdescribe;
    private String img;
    private Integer status;
    private Integer tag;
    private String username;
    private String phone;
    private Integer point;
    private Integer gnum;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getGnum() {
        return gnum;
    }

    public void setGnum(Integer gnum) {
        this.gnum = gnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GoodsVO)) return false;
        GoodsVO goodsVO = (GoodsVO) o;
        return Objects.equals(getGid(), goodsVO.getGid()) &&
                Objects.equals(getCid(), goodsVO.getCid()) &&
                Objects.equals(getUid(), goodsVO.getUid()) &&
                Objects.equals(getGname(), goodsVO.getGname()) &&
                Objects.equals(getPrice(), goodsVO.getPrice()) &&
                Objects.equals(getCtime(), goodsVO.getCtime()) &&
                Objects.equals(getGdescribe(), goodsVO.getGdescribe()) &&
                Objects.equals(getImg(), goodsVO.getImg()) &&
                Objects.equals(getStatus(), goodsVO.getStatus()) &&
                Objects.equals(getTag(), goodsVO.getTag()) &&
                Objects.equals(getUsername(), goodsVO.getUsername()) &&
                Objects.equals(getPhone(), goodsVO.getPhone()) &&
                Objects.equals(getPoint(), goodsVO.getPoint()) &&
                Objects.equals(getGnum(), goodsVO.getGnum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGid(), getCid(), getUid(), getGname(), getPrice(), getCtime(), getGdescribe(), getImg(), getStatus(), getTag(), getUsername(), getPhone(), getPoint(), getGnum());
    }

    @Override
    public String toString() {
        return "GoodsVO{" +
                "gid=" + gid +
                ", cid=" + cid +
                ", uid='" + uid + '\'' +
                ", gname='" + gname + '\'' +
                ", price='" + price + '\'' +
                ", ctime=" + ctime +
                ", gdescribe='" + gdescribe + '\'' +
                ", img='" + img + '\'' +
                ", status=" + status +
                ", tag=" + tag +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", point=" + point +
                ", gnum=" + gnum +
                '}';
    }
}
