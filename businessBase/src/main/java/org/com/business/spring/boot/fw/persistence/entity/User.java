package org.com.business.spring.boot.fw.persistence.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String userId;

    private String userName;

    private String usernameFurikana;

    private String password;

    private String cellphoneNumber;

    private String email;

    private String weChat;

    private String createuserid;

    private Date createtime;

    private String updateuserid;

    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public User withUserId(String userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public User withUserName(String userName) {
        this.setUserName(userName);
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUsernameFurikana() {
        return usernameFurikana;
    }

    public User withUsernameFurikana(String usernameFurikana) {
        this.setUsernameFurikana(usernameFurikana);
        return this;
    }

    public void setUsernameFurikana(String usernameFurikana) {
        this.usernameFurikana = usernameFurikana == null ? null : usernameFurikana.trim();
    }

    public String getPassword() {
        return password;
    }

    public User withPassword(String password) {
        this.setPassword(password);
        return this;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public User withCellphoneNumber(String cellphoneNumber) {
        this.setCellphoneNumber(cellphoneNumber);
        return this;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber == null ? null : cellphoneNumber.trim();
    }

    public String getEmail() {
        return email;
    }

    public User withEmail(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getWeChat() {
        return weChat;
    }

    public User withWeChat(String weChat) {
        this.setWeChat(weChat);
        return this;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat == null ? null : weChat.trim();
    }

    public String getCreateuserid() {
        return createuserid;
    }

    public User withCreateuserid(String createuserid) {
        this.setCreateuserid(createuserid);
        return this;
    }

    public void setCreateuserid(String createuserid) {
        this.createuserid = createuserid == null ? null : createuserid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public User withCreatetime(Date createtime) {
        this.setCreatetime(createtime);
        return this;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateuserid() {
        return updateuserid;
    }

    public User withUpdateuserid(String updateuserid) {
        this.setUpdateuserid(updateuserid);
        return this;
    }

    public void setUpdateuserid(String updateuserid) {
        this.updateuserid = updateuserid == null ? null : updateuserid.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public User withUpdatetime(Date updatetime) {
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
        sb.append(", password=").append(password);
        sb.append(", cellphoneNumber=").append(cellphoneNumber);
        sb.append(", email=").append(email);
        sb.append(", weChat=").append(weChat);
        sb.append(", createuserid=").append(createuserid);
        sb.append(", createtime=").append(createtime);
        sb.append(", updateuserid=").append(updateuserid);
        sb.append(", updatetime=").append(updatetime);
        sb.append("]");
        return sb.toString();
    }
}