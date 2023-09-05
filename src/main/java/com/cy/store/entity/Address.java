package com.cy.store.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Address implements Serializable {
    private Integer adsid;
    private String uid;
    private String name;
    private String content;
    private Integer status;
    private String phone;
    private Date ctime;

    public Integer getAdsid() {
        return adsid;
    }

    public void setAdsid(Integer adsid) {
        this.adsid = adsid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(getAdsid(), address.getAdsid()) &&
                Objects.equals(getUid(), address.getUid()) &&
                Objects.equals(getName(), address.getName()) &&
                Objects.equals(getContent(), address.getContent()) &&
                Objects.equals(getStatus(), address.getStatus()) &&
                Objects.equals(getPhone(), address.getPhone()) &&
                Objects.equals(getCtime(), address.getCtime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAdsid(), getUid(), getName(), getContent(), getStatus(), getPhone(), getCtime());
    }

    @Override
    public String toString() {
        return "Address{" +
                "adsid=" + adsid +
                ", uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", ctime=" + ctime +
                '}';
    }

}
