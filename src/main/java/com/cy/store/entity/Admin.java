package com.cy.store.entity;

import java.io.Serializable;
import java.util.Objects;

public class Admin implements Serializable {
    private String aid;
    private String username;
    private String pwd;
    private String salt;
    private String phone;
    private Integer role;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "aid='" + aid + '\'' +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", salt='" + salt + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin)) return false;
        Admin admin = (Admin) o;
        return Objects.equals(getAid(), admin.getAid()) &&
                Objects.equals(getUsername(), admin.getUsername()) &&
                Objects.equals(getPwd(), admin.getPwd()) &&
                Objects.equals(getSalt(), admin.getSalt()) &&
                Objects.equals(getPhone(), admin.getPhone()) &&
                Objects.equals(getRole(), admin.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAid(), getUsername(), getPwd(), getSalt(), getPhone(), getRole());
    }
}
