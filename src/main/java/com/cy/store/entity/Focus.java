package com.cy.store.entity;

import java.io.Serializable;
import java.util.Objects;

public class Focus implements Serializable {
    private Integer fid;
    private Integer gid;
    private String uid;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Focus{" +
                "fid=" + fid +
                ", gid=" + gid +
                ", uid='" + uid + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Focus)) return false;
        Focus focus = (Focus) o;
        return Objects.equals(getFid(), focus.getFid()) &&
                Objects.equals(getGid(), focus.getGid()) &&
                Objects.equals(getUid(), focus.getUid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFid(), getGid(), getUid());
    }
}
