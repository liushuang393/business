package com.co.jp.liushuang.persistence.entity;

import java.util.Date;

public class User {
    private String userId;

    private String userName;

    private String usernameFurikana;

    private String password;

    private String gender;

    private Date birthday;

    private Integer age;

    private String graduation;

    private String major;

    private String graduationYear;

    private String university;

    private Date joinedDate;

    private Integer department;

    private String post;

    private String title;

    private String recentlyStation;

    private Integer lengthOfStay;

    private Date japanDate;

    private Integer contractType;

    private Integer insuranceRelationship;

    private Date bizaMaturity;

    private Date passportMaturity;

    private String cellphoneNumber;

    private String email;

    private String postalCode;

    private String address;

    private Date contractMaturity;

    private Integer authorityId;

    private String createuserid;

    private Date createtime;

    private String updateuserid;

    private Date updatetime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUsernameFurikana() {
        return usernameFurikana;
    }

    public void setUsernameFurikana(String usernameFurikana) {
        this.usernameFurikana = usernameFurikana == null ? null : usernameFurikana.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGraduation() {
        return graduation;
    }

    public void setGraduation(String graduation) {
        this.graduation = graduation == null ? null : graduation.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear == null ? null : graduationYear.trim();
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university == null ? null : university.trim();
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getRecentlyStation() {
        return recentlyStation;
    }

    public void setRecentlyStation(String recentlyStation) {
        this.recentlyStation = recentlyStation == null ? null : recentlyStation.trim();
    }

    public Integer getLengthOfStay() {
        return lengthOfStay;
    }

    public void setLengthOfStay(Integer lengthOfStay) {
        this.lengthOfStay = lengthOfStay;
    }

    public Date getJapanDate() {
        return japanDate;
    }

    public void setJapanDate(Date japanDate) {
        this.japanDate = japanDate;
    }

    public Integer getContractType() {
        return contractType;
    }

    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }

    public Integer getInsuranceRelationship() {
        return insuranceRelationship;
    }

    public void setInsuranceRelationship(Integer insuranceRelationship) {
        this.insuranceRelationship = insuranceRelationship;
    }

    public Date getBizaMaturity() {
        return bizaMaturity;
    }

    public void setBizaMaturity(Date bizaMaturity) {
        this.bizaMaturity = bizaMaturity;
    }

    public Date getPassportMaturity() {
        return passportMaturity;
    }

    public void setPassportMaturity(Date passportMaturity) {
        this.passportMaturity = passportMaturity;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber == null ? null : cellphoneNumber.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode == null ? null : postalCode.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getContractMaturity() {
        return contractMaturity;
    }

    public void setContractMaturity(Date contractMaturity) {
        this.contractMaturity = contractMaturity;
    }

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public String getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(String createuserid) {
        this.createuserid = createuserid == null ? null : createuserid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateuserid() {
        return updateuserid;
    }

    public void setUpdateuserid(String updateuserid) {
        this.updateuserid = updateuserid == null ? null : updateuserid.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}