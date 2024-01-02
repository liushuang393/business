package org.com.business.spring.boot.fw.persistence.entity;

import java.io.Serializable;
import java.util.Date;

public class Roll implements Serializable {
    private String rollId;

    private String rollName;

    private String remarks;

    private String createuserid;

    private Date createtime;

    private String updateuserid;

    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public String getRollId() {
        return rollId;
    }

    public Roll withRollId(String rollId) {
        this.setRollId(rollId);
        return this;
    }

    public void setRollId(String rollId) {
        this.rollId = rollId == null ? null : rollId.trim();
    }

    public String getRollName() {
        return rollName;
    }

    public Roll withRollName(String rollName) {
        this.setRollName(rollName);
        return this;
    }

    public void setRollName(String rollName) {
        this.rollName = rollName == null ? null : rollName.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public Roll withRemarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getCreateuserid() {
        return createuserid;
    }

    public Roll withCreateuserid(String createuserid) {
        this.setCreateuserid(createuserid);
        return this;
    }

    public void setCreateuserid(String createuserid) {
        this.createuserid = createuserid == null ? null : createuserid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public Roll withCreatetime(Date createtime) {
        this.setCreatetime(createtime);
        return this;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateuserid() {
        return updateuserid;
    }

    public Roll withUpdateuserid(String updateuserid) {
        this.setUpdateuserid(updateuserid);
        return this;
    }

    public void setUpdateuserid(String updateuserid) {
        this.updateuserid = updateuserid == null ? null : updateuserid.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public Roll withUpdatetime(Date updatetime) {
        this.setUpdatetime(updatetime);
        return this;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rollId=").append(rollId);
        sb.append(", rollName=").append(rollName);
        sb.append(", remarks=").append(remarks);
        sb.append(", createuserid=").append(createuserid);
        sb.append(", createtime=").append(createtime);
        sb.append(", updateuserid=").append(updateuserid);
        sb.append(", updatetime=").append(updatetime);
        sb.append("]");
        return sb.toString();
    }
}