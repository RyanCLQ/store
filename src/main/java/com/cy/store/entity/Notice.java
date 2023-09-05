package com.cy.store.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Notice implements Serializable {
    private Integer nid;
    private String aid;
    private String content;
    private Date ctime;

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "nid=" + nid +
                ", aid='" + aid + '\'' +
                ", content='" + content + '\'' +
                ", ctime=" + ctime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notice)) return false;
        Notice notice = (Notice) o;
        return Objects.equals(getNid(), notice.getNid()) &&
                Objects.equals(getAid(), notice.getAid()) &&
                Objects.equals(getContent(), notice.getContent()) &&
                Objects.equals(getCtime(), notice.getCtime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNid(), getAid(), getContent(), getCtime());
    }
}
