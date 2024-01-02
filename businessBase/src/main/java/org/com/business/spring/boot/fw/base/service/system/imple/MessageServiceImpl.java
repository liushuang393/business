package org.com.business.spring.boot.fw.base.service.system.imple;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.com.business.spring.boot.common.KeyValueBean;
import org.com.business.spring.boot.common.utils.BZBeanUtils;
import org.com.business.spring.boot.fw.base.common.form.BZBS5001Form;
import org.com.business.spring.boot.fw.base.service.system.MessageService;
import org.com.business.spring.boot.fw.pages.BZPageInfo;
import org.com.business.spring.boot.fw.persistence.entity.MessageInfo;
import org.com.business.spring.boot.fw.persistence.entity.MessageInfoKey;
import org.com.business.spring.boot.fw.persistence.entity.User;
import org.com.business.spring.boot.fw.persistence.mapper.CustomMessageInfoMapper;
import org.com.business.spring.boot.fw.persistence.mapper.CustomUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

	@Resource
	CustomMessageInfoMapper customMessageInfoMapper;

	@Resource
	CustomUserMapper customUserMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int addMessage(String userId, BZBS5001Form form) {

		MessageInfo record = new MessageInfo();
		BZBeanUtils.copyBeanProperties(form, record);

		String str = customMessageInfoMapper.selectMaxKey(form.getUserId());
		str = String.valueOf(str == null ? 1 : Long.parseLong(str) + 1);
		record.setMessageId(org.com.business.spring.boot.common.utils.Utils
				.appStr(str, '0', 15));
		record.setCreateuserid(
				userId.length() > 10 ? userId.substring(0, 10) : userId);
		record.setUpdateuserid(
				userId.length() > 10 ? userId.substring(0, 10) : userId);
		return customMessageInfoMapper.insert(record);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateMessage(String userId, BZBS5001Form form) {

		MessageInfoKey key = new MessageInfoKey();
		BZBeanUtils.copyBeanProperties(form, key);
		MessageInfo record = customMessageInfoMapper.selectByPrimaryKey(key);

		BZBeanUtils.copyBeanProperties(form, record);
		return customMessageInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public BZPageInfo<MessageInfo> getMessageList(BZBS5001Form form,
			Integer page, Integer pageSize) {
		return null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int deleteMessageInfo(MessageInfoKey key) {
		return 0;
	}

	@Override
	public MessageInfo getMessageById(MessageInfoKey key) {
		return null;
	}

	@Override
	public void setForm(BZBS5001Form form) {
		List<KeyValueBean> beanList = new ArrayList<>();

		List<User> userList = customUserMapper.selectList(new User());

		for (User user : userList) {
			KeyValueBean bean = new KeyValueBean();
			bean.setKey(user.getUserId());
			bean.setValue(user.getUserName());
			beanList.add(bean);
		}
		form.setUserList(beanList);
	}

	@Override
	public List<MessageInfo> getMessageById(String userId) {
		MessageInfo entity = new MessageInfo();
		entity.setUserId(userId);

		return customMessageInfoMapper.selectList(entity);
	}

}
