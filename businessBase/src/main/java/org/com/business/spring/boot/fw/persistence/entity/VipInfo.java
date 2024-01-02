package org.com.business.spring.boot.fw.persistence.entity;

import java.io.Serializable;
import java.util.Date;

public class VipInfo implements Serializable {
    private String userId;

    private String userName;

    private String usernameFurikana;

    private String publicFlag;

    private String gender;

    private Date birthday;

    private String major;

    private String recentlyStation;

    private Date japanDate;

    private Date operationStartDate;

    private Date contractMaturity;

    private String japaneseLevel;

    private String goodLanguage;

    private String goodBusinessDivision;

    private String remarks;

    private Integer starRating;

    private String historyPath;

    private String createuserid;

    private Date createtime;

    private String updateuserid;

    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public VipInfo withUserId(String userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public VipInfo withUserName(String userName) {
        this.setUserName(userName);
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUsernameFurikana() {
        return usernameFurikana;
    }

    public VipInfo withUsernameFurikana(String usernameFurikana) {
        this.setUsernameFurikana(usernameFurikana);
        return this;
    }

    public void setUsernameFurikana(String usernameFurikana) {
        this.usernameFurikana = usernameFurikana == null ? null : usernameFurikana.trim();
    }

    public String getPublicFlag() {
        return publicFlag;
    }

    public VipInfo withPublicFlag(String publicFlag) {
        this.setPublicFlag(publicFlag);
        return this;
    }

    public void setPublicFlag(String publicFlag) {
        this.publicFlag = publicFlag == null ? null : publicFlag.trim();
    }

    public String getGender() {
        return gender;
    }

    public VipInfo withGender(String gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public VipInfo withBirthday(Date birthday) {
        this.setBirthday(birthday);
        return this;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMajor() {
        return major;
    }

    public VipInfo withMajor(String major) {
        this.setMajor(major);
        return this;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getRecentlyStation() {
        return recentlyStation;
    }

    public VipInfo withRecentlyStation(String recentlyStation) {
        this.setRecentlyStation(recentlyStation);
        return this;
    }

    public void setRecentlyStation(String recentlyStation) {
        this.recentlyStation = recentlyStation == null ? null : recentlyStation.trim();
    }

    public Date getJapanDate() {
        return japanDate;
    }

    public VipInfo withJapanDate(Date japanDate) {
        this.setJapanDate(japanDate);
        return this;
    }

    public void setJapanDate(Date japanDate) {
        this.japanDate = japanDate;
    }

    public Date getOperationStartDate() {
        return operationStartDate;
    }

    public VipInfo withOperationStartDate(Date operationStartDate) {
        this.setOperationStartDate(operationStartDate);
        return this;
    }

    public void setOperationStartDate(Date operationStartDate) {
        this.operationStartDate = operationStartDate;
    }

    public Date getContractMaturity() {
        return contractMaturity;
    }

    public VipInfo withContractMaturity(Date contractMaturity) {
        this.setContractMaturity(contractMaturity);
        return this;
    }

    public void setContractMaturity(Date contractMaturity) {
        this.contractMaturity = contractMaturity;
    }

    public String getJapaneseLevel() {
        return japaneseLevel;
    }

    public VipInfo withJapaneseLevel(String japaneseLevel) {
        this.setJapaneseLevel(japaneseLevel);
        return this;
    }

    public void setJapaneseLevel(String japaneseLevel) {
        this.japaneseLevel = japaneseLevel == null ? null : japaneseLevel.trim();
    }

    public String getGoodLanguage() {
        return goodLanguage;
    }

    public VipInfo withGoodLanguage(String goodLanguage) {
        this.setGoodLanguage(goodLanguage);
        return this;
    }

    public void setGoodLanguage(String goodLanguage) {
        this.goodLanguage = goodLanguage == null ? null : goodLanguage.trim();
    }

    public String getGoodBusinessDivision() {
        return goodBusinessDivision;
    }

    public VipInfo withGoodBusinessDivision(String goodBusinessDivision) {
        this.setGoodBusinessDivision(goodBusinessDivision);
        return this;
    }

    public void setGoodBusinessDivision(String goodBusinessDivision) {
        this.goodBusinessDivision = goodBusinessDivision == null ? null : goodBusinessDivision.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public VipInfo withRemarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getStarRating() {
        return starRating;
    }

    public VipInfo withStarRating(Integer starRating) {
        this.setStarRating(starRating);
        return this;
    }

    public void setStarRating(Integer starRating) {
        this.starRating = starRating;
    }

    public String getHistoryPath() {
        return historyPath;
    }

    public VipInfo withHistoryPath(String historyPath) {
        this.setHistoryPath(historyPath);
        return this;
    }

    public void setHistoryPath(String historyPath) {
        this.historyPath = historyPath == null ? null : historyPath.trim();
    }

    public String getCreateuserid() {
        return createuserid;
    }

    public VipInfo withCreateuserid(String createuserid) {
        this.setCreateuserid(createuserid);
        return this;
    }

    public void setCreateuserid(String createuserid) {
        this.createuserid = createuserid == null ? null : createuserid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public VipInfo withCreatetime(Date createtime) {
        this.setCreatetime(createtime);
        return this;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateuserid() {
        return updateuserid;
    }

    public VipInfo withUpdateuserid(String updateuserid) {
        this.setUpdateuserid(updateuserid);
        return this;
    }

    public void setUpdateuserid(String updateuserid) {
        this.updateuserid = updateuserid == null ? null : updateuserid.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public VipInfo withUpdatetime(Date updatetime) {
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
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", usernameFurikana=").append(usernameFurikana);
        sb.append(", publicFlag=").append(publicFlag);
        sb.append(", gender=").append(gender);
        sb.append(", birthday=").append(birthday);
        sb.append(", major=").append(major);
        sb.append(", recentlyStation=").append(recentlyStation);
        sb.append(", japanDate=").append(japanDate);
        sb.append(", operationStartDate=").append(operationStartDate);
        sb.append(", contractMaturity=").append(contractMaturity);
        sb.append(", japaneseLevel=").append(japaneseLevel);
        sb.append(", goodLanguage=").append(goodLanguage);
        sb.append(", goodBusinessDivision=").append(goodBusinessDivision);
        sb.append(", remarks=").append(remarks);
        sb.append(", starRating=").append(starRating);
        sb.append(", historyPath=").append(historyPath);
        sb.append(", createuserid=").append(createuserid);
        sb.append(", createtime=").append(createtime);
        sb.append(", updateuserid=").append(updateuserid);
        sb.append(", updatetime=").append(updatetime);
        sb.append("]");
        return sb.toString();
    }
}