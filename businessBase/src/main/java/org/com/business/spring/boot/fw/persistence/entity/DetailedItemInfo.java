package org.com.business.spring.boot.fw.persistence.entity;

import java.io.Serializable;
import java.util.Date;

public class DetailedItemInfo implements Serializable {
    private String companyId;

    private String companyName;

    private String salesId;

    private String publicFlag;

    private Date workPeriodStart;

    private Date workPeriodEnd;

    private String workPlace;

    private String business;

    private String workEngineering;

    private String technology;

    private String japaneseLevelRequest;

    private String experienceYears;

    private Long hopeUnitPrice;

    private String interviewTimes;

    private String payoff;

    private String remarks;

    private String createuserid;

    private Date createtime;

    private String updateuserid;

    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public String getCompanyId() {
        return companyId;
    }

    public DetailedItemInfo withCompanyId(String companyId) {
        this.setCompanyId(companyId);
        return this;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public DetailedItemInfo withCompanyName(String companyName) {
        this.setCompanyName(companyName);
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getSalesId() {
        return salesId;
    }

    public DetailedItemInfo withSalesId(String salesId) {
        this.setSalesId(salesId);
        return this;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId == null ? null : salesId.trim();
    }

    public String getPublicFlag() {
        return publicFlag;
    }

    public DetailedItemInfo withPublicFlag(String publicFlag) {
        this.setPublicFlag(publicFlag);
        return this;
    }

    public void setPublicFlag(String publicFlag) {
        this.publicFlag = publicFlag == null ? null : publicFlag.trim();
    }

    public Date getWorkPeriodStart() {
        return workPeriodStart;
    }

    public DetailedItemInfo withWorkPeriodStart(Date workPeriodStart) {
        this.setWorkPeriodStart(workPeriodStart);
        return this;
    }

    public void setWorkPeriodStart(Date workPeriodStart) {
        this.workPeriodStart = workPeriodStart;
    }

    public Date getWorkPeriodEnd() {
        return workPeriodEnd;
    }

    public DetailedItemInfo withWorkPeriodEnd(Date workPeriodEnd) {
        this.setWorkPeriodEnd(workPeriodEnd);
        return this;
    }

    public void setWorkPeriodEnd(Date workPeriodEnd) {
        this.workPeriodEnd = workPeriodEnd;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public DetailedItemInfo withWorkPlace(String workPlace) {
        this.setWorkPlace(workPlace);
        return this;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace == null ? null : workPlace.trim();
    }

    public String getBusiness() {
        return business;
    }

    public DetailedItemInfo withBusiness(String business) {
        this.setBusiness(business);
        return this;
    }

    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }

    public String getWorkEngineering() {
        return workEngineering;
    }

    public DetailedItemInfo withWorkEngineering(String workEngineering) {
        this.setWorkEngineering(workEngineering);
        return this;
    }

    public void setWorkEngineering(String workEngineering) {
        this.workEngineering = workEngineering == null ? null : workEngineering.trim();
    }

    public String getTechnology() {
        return technology;
    }

    public DetailedItemInfo withTechnology(String technology) {
        this.setTechnology(technology);
        return this;
    }

    public void setTechnology(String technology) {
        this.technology = technology == null ? null : technology.trim();
    }

    public String getJapaneseLevelRequest() {
        return japaneseLevelRequest;
    }

    public DetailedItemInfo withJapaneseLevelRequest(String japaneseLevelRequest) {
        this.setJapaneseLevelRequest(japaneseLevelRequest);
        return this;
    }

    public void setJapaneseLevelRequest(String japaneseLevelRequest) {
        this.japaneseLevelRequest = japaneseLevelRequest == null ? null : japaneseLevelRequest.trim();
    }

    public String getExperienceYears() {
        return experienceYears;
    }

    public DetailedItemInfo withExperienceYears(String experienceYears) {
        this.setExperienceYears(experienceYears);
        return this;
    }

    public void setExperienceYears(String experienceYears) {
        this.experienceYears = experienceYears == null ? null : experienceYears.trim();
    }

    public Long getHopeUnitPrice() {
        return hopeUnitPrice;
    }

    public DetailedItemInfo withHopeUnitPrice(Long hopeUnitPrice) {
        this.setHopeUnitPrice(hopeUnitPrice);
        return this;
    }

    public void setHopeUnitPrice(Long hopeUnitPrice) {
        this.hopeUnitPrice = hopeUnitPrice;
    }

    public String getInterviewTimes() {
        return interviewTimes;
    }

    public DetailedItemInfo withInterviewTimes(String interviewTimes) {
        this.setInterviewTimes(interviewTimes);
        return this;
    }

    public void setInterviewTimes(String interviewTimes) {
        this.interviewTimes = interviewTimes == null ? null : interviewTimes.trim();
    }

    public String getPayoff() {
        return payoff;
    }

    public DetailedItemInfo withPayoff(String payoff) {
        this.setPayoff(payoff);
        return this;
    }

    public void setPayoff(String payoff) {
        this.payoff = payoff == null ? null : payoff.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public DetailedItemInfo withRemarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getCreateuserid() {
        return createuserid;
    }

    public DetailedItemInfo withCreateuserid(String createuserid) {
        this.setCreateuserid(createuserid);
        return this;
    }

    public void setCreateuserid(String createuserid) {
        this.createuserid = createuserid == null ? null : createuserid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public DetailedItemInfo withCreatetime(Date createtime) {
        this.setCreatetime(createtime);
        return this;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateuserid() {
        return updateuserid;
    }

    public DetailedItemInfo withUpdateuserid(String updateuserid) {
        this.setUpdateuserid(updateuserid);
        return this;
    }

    public void setUpdateuserid(String updateuserid) {
        this.updateuserid = updateuserid == null ? null : updateuserid.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public DetailedItemInfo withUpdatetime(Date updatetime) {
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
        sb.append(", salesId=").append(salesId);
        sb.append(", publicFlag=").append(publicFlag);
        sb.append(", workPeriodStart=").append(workPeriodStart);
        sb.append(", workPeriodEnd=").append(workPeriodEnd);
        sb.append(", workPlace=").append(workPlace);
        sb.append(", business=").append(business);
        sb.append(", workEngineering=").append(workEngineering);
        sb.append(", technology=").append(technology);
        sb.append(", japaneseLevelRequest=").append(japaneseLevelRequest);
        sb.append(", experienceYears=").append(experienceYears);
        sb.append(", hopeUnitPrice=").append(hopeUnitPrice);
        sb.append(", interviewTimes=").append(interviewTimes);
        sb.append(", payoff=").append(payoff);
        sb.append(", remarks=").append(remarks);
        sb.append(", createuserid=").append(createuserid);
        sb.append(", createtime=").append(createtime);
        sb.append(", updateuserid=").append(updateuserid);
        sb.append(", updatetime=").append(updatetime);
        sb.append("]");
        return sb.toString();
    }
}