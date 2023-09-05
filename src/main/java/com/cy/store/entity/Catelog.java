package com.cy.store.entity;

import java.io.Serializable;
import java.util.Objects;

public class Catelog implements Serializable {
    private Integer cid;
    private String name;
    private Integer number;
    private Integer status;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Catelog{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Catelog)) return false;
        Catelog catelog = (Catelog) o;
        return Objects.equals(getCid(), catelog.getCid()) &&
                Objects.equals(getName(), catelog.getName()) &&
                Objects.equals(getNumber(), catelog.getNumber()) &&
                Objects.equals(getStatus(), catelog.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCid(), getName(), getNumber(), getStatus());
    }
}
