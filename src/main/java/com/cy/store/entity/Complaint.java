package com.cy.store.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Complaint implements Serializable {
    private Integer cptid; /*投诉号自增*/
    private Integer oid;/*订单号*/
    private String reason;/*投诉原因*/
    private String img;
    private Integer status;/*投诉的状态，0待处理，1已处理*/
    private Date ctime;/*创建时间*/

    public Integer getCptid() {
        return cptid;
    }

    public void setCptid(Integer cptid) {
        this.cptid = cptid;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Complaint)) return false;
        Complaint complaint = (Complaint) o;
        return Objects.equals(getCptid(), complaint.getCptid()) &&
                Objects.equals(getOid(), complaint.getOid()) &&
                Objects.equals(getReason(), complaint.getReason()) &&
                Objects.equals(getImg(), complaint.getImg()) &&
                Objects.equals(getStatus(), complaint.getStatus()) &&
                Objects.equals(getCtime(), complaint.getCtime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCptid(), getOid(), getReason(), getImg(), getStatus(), getCtime());
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "cptid=" + cptid +
                ", oid=" + oid +
                ", reason='" + reason + '\'' +
                ", img='" + img + '\'' +
                ", status=" + status +
                ", ctime=" + ctime +
                '}';
    }
}
