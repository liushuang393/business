package org.com.business.spring.boot.fw.persistence.entity;

import java.io.Serializable;
import java.util.Date;

public class Company implements Serializable {
    private String companyId;

    private String companyName;

    private Date establishmentDay;

    private String capital;

    private String capitalUnit;

    private String salesId;

    private String salesPersonName;

    private String homePage;

    private String eMail;

    private String tel;

    private String fax;

    private String createuserid;

    private Date createtime;

    private String updateuserid;

    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public String getCompanyId() {
        return companyId;
    }

    public Company withCompanyId(String companyId) {
        this.setCompanyId(companyId);
        return this;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public Company withCompanyName(String companyName) {
        this.setCompanyName(companyName);
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public Date getEstablishmentDay() {
        return establishmentDay;
    }

    public Company withEstablishmentDay(Date establishmentDay) {
        this.setEstablishmentDay(establishmentDay);
        return this;
    }

    public void setEstablishmentDay(Date establishmentDay) {
        this.establishmentDay = establishmentDay;
    }

    public String getCapital() {
        return capital;
    }

    public Company withCapital(String capital) {
        this.setCapital(capital);
        return this;
    }

    public void setCapital(String capital) {
        this.capital = capital == null ? null : capital.trim();
    }

    public String getCapitalUnit() {
        return capitalUnit;
    }

    public Company withCapitalUnit(String capitalUnit) {
        this.setCapitalUnit(capitalUnit);
        return this;
    }

    public void setCapitalUnit(String capitalUnit) {
        this.capitalUnit = capitalUnit == null ? null : capitalUnit.trim();
    }

    public String getSalesId() {
        return salesId;
    }

    public Company withSalesId(String salesId) {
        this.setSalesId(salesId);
        return this;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId == null ? null : salesId.trim();
    }

    public String getSalesPersonName() {
        return salesPersonName;
    }

    public Company withSalesPersonName(String salesPersonName) {
        this.setSalesPersonName(salesPersonName);
        return this;
    }

    public void setSalesPersonName(String salesPersonName) {
        this.salesPersonName = salesPersonName == null ? null : salesPersonName.trim();
    }

    public String getHomePage() {
        return homePage;
    }

    public Company withHomePage(String homePage) {
        this.setHomePage(homePage);
        return this;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage == null ? null : homePage.trim();
    }

    public String geteMail() {
        return eMail;
    }

    public Company witheMail(String eMail) {
        this.seteMail(eMail);
        return this;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail == null ? null : eMail.trim();
    }

    public String getTel() {
        return tel;
    }

    public Company withTel(String tel) {
        this.setTel(tel);
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getFax() {
        return fax;
    }

    public Company withFax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getCreateuserid() {
        return createuserid;
    }

    public Company withCreateuserid(String createuserid) {
        this.setCreateuserid(createuserid);
        return this;
    }

    public void setCreateuserid(String createuserid) {
        this.createuserid = createuserid == null ? null : createuserid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public Company withCreatetime(Date createtime) {
        this.setCreatetime(createtime);
        return this;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateuserid() {
        return updateuserid;
    }

    public Company withUpdateuserid(String updateuserid) {
        this.setUpdateuserid(updateuserid);
        return this;
    }

    public void setUpdateuserid(String updateuserid) {
        this.updateuserid = updateuserid == null ? null : updateuserid.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public Company withUpdatetime(Date updatetime) {
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
        sb.append(", companyId=").append(companyId);
        sb.append(", companyName=").append(companyName);
        sb.append(", establishmentDay=").append(establishmentDay);
        sb.append(", capital=").append(capital);
        sb.append(", capitalUnit=").append(capitalUnit);
        sb.append(", salesId=").append(salesId);
        sb.append(", salesPersonName=").append(salesPersonName);
        sb.append(", homePage=").append(homePage);
        sb.append(", eMail=").append(eMail);
        sb.append(", tel=").append(tel);
        sb.append(", fax=").append(fax);
        sb.append(", createuserid=").append(createuserid);
        sb.append(", createtime=").append(createtime);
        sb.append(", updateuserid=").append(updateuserid);
        sb.append(", updatetime=").append(updatetime);
        sb.append("]");
        return sb.toString();
    }
}