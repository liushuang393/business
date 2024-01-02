package org.com.business.spring.boot.fw.persistence.entity;

import java.io.Serializable;
import java.util.Date;

public class MessageInfo extends MessageInfoKey implements Serializable {
    private String messageTitle;

    private String messageBody;

    private Integer cnt;

    private String createuserid;

    private Date createtime;

    private String updateuserid;

    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public String getMessageTitle() {
        return messageTitle;
    }

    public MessageInfo withMessageTitle(String messageTitle) {
        this.setMessageTitle(messageTitle);
        return this;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle == null ? null : messageTitle.trim();
    }

    public String getMessageBody() {
        return messageBody;
    }

    public MessageInfo withMessageBody(String messageBody) {
        this.setMessageBody(messageBody);
        return this;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody == null ? null : messageBody.trim();
    }

    public Integer getCnt() {
        return cnt;
    }

    public MessageInfo withCnt(Integer cnt) {
        this.setCnt(cnt);
        return this;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public String getCreateuserid() {
        return createuserid;
    }

    public MessageInfo withCreateuserid(String createuserid) {
        this.setCreateuserid(createuserid);
        return this;
    }

    public void setCreateuserid(String createuserid) {
        this.createuserid = createuserid == null ? null : createuserid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public MessageInfo withCreatetime(Date createtime) {
        this.setCreatetime(createtime);
        return this;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateuserid() {
        return updateuserid;
    }

    public MessageInfo withUpdateuserid(String updateuserid) {
        this.setUpdateuserid(updateuserid);
        return this;
    }

    public void setUpdateuserid(String updateuserid) {
        this.updateuserid = updateuserid == null ? null : updateuserid.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public MessageInfo withUpdatetime(Date updatetime) {
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
        sb.append(", messageTitle=").append(messageTitle);
        sb.append(", messageBody=").append(messageBody);
        sb.append(", cnt=").append(cnt);
        sb.append(", createuserid=").append(createuserid);
        sb.append(", createtime=").append(createtime);
        sb.append(", updateuserid=").append(updateuserid);
        sb.append(", updatetime=").append(updatetime);
        sb.append("]");
        return sb.toString();
    }
}