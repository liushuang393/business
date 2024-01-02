package org.com.business.spring.boot.fw.base.service.system;

import java.util.List;

import org.com.business.spring.boot.fw.base.common.form.BZBS5001Form;
import org.com.business.spring.boot.fw.pages.BZPageInfo;
import org.com.business.spring.boot.fw.persistence.entity.MessageInfo;
import org.com.business.spring.boot.fw.persistence.entity.MessageInfoKey;

public interface MessageService {

    public int addMessage(String userId,BZBS5001Form form);

    public int updateMessage(String userId,BZBS5001Form form);

    public MessageInfo getMessageById(MessageInfoKey key);

    public BZPageInfo<MessageInfo> getMessageList(BZBS5001Form form,Integer page, Integer pageSize);

    public int deleteMessageInfo(MessageInfoKey key);

	public void setForm(BZBS5001Form form);

	public List<MessageInfo> getMessageById(String userId);
}