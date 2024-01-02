package org.com.business.spring.boot.fw.persistence.entity;

import java.io.Serializable;
import java.util.Date;

public class Department implements Serializable {
    private String departmentId;

    private String departmentName;

    private String responsiblePerson;

    private Date establishmentDay;

    private String remarks;

    private String createuserid;

    private Date createtime;

    private String updateuserid;

    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public String getDepartmentId() {
        return departmentId;
    }

    public Department withDepartmentId(String departmentId) {
        this.setDepartmentId(departmentId);
        return this;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Department withDepartmentName(String departmentName) {
        this.setDepartmentName(departmentName);
        return this;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public Department withResponsiblePerson(String responsiblePerson) {
        this.setResponsiblePerson(responsiblePerson);
        return this;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson == null ? null : responsiblePerson.trim();
    }

    public Date getEstablishmentDay() {
        return establishmentDay;
    }

    public Department withEstablishmentDay(Date establishmentDay) {
        this.setEstablishmentDay(establishmentDay);
        return this;
    }

    public void setEstablishmentDay(Date establishmentDay) {
        this.establishmentDay = establishmentDay;
    }

    public String getRemarks() {
        return remarks;
    }

    public Department withRemarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getCreateuserid() {
        return createuserid;
    }

    public Department withCreateuserid(String createuserid) {
        this.setCreateuserid(createuserid);
        return this;
    }

    public void setCreateuserid(String createuserid) {
        this.createuserid = createuserid == null ? null : createuserid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public Department withCreatetime(Date createtime) {
        this.setCreatetime(createtime);
        return this;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateuserid() {
        return updateuserid;
    }

    public Department withUpdateuserid(String updateuserid) {
        this.setUpdateuserid(updateuserid);
        return this;
    }

    public void setUpdateuserid(String updateuserid) {
        this.updateuserid = updateuserid == null ? null : updateuserid.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public Department withUpdatetime(Date updatetime) {
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
        sb.append(", departmentId=").append(departmentId);
        sb.append(", departmentName=").append(departmentName);
        sb.append(", responsiblePerson=").append(responsiblePerson);
        sb.append(", establishmentDay=").append(establishmentDay);
        sb.append(", remarks=").append(remarks);
        sb.append(", createuserid=").append(createuserid);
        sb.append(", createtime=").append(createtime);
        sb.append(", updateuserid=").append(updateuserid);
        sb.append(", updatetime=").append(updatetime);
        sb.append("]");
        return sb.toString();
    }
}