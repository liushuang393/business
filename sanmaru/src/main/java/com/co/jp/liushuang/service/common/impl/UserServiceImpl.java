package com.co.jp.liushuang.service.common.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.co.jp.liushuang.persistence.UserMapper;
import com.co.jp.liushuang.persistence.entity.User;
import com.co.jp.liushuang.service.common.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource
	UserMapper userMapper;

	@Override
	public User getUserById(String id) {
		logger.debug("開始：{}fff", "init");
		logger.info("UserServiceImpl.getUserById()：{}", id);

		User user = userMapper.selectByPrimaryKey(id);

		logger.debug("終了：{}", "init");
		return user;
	}

	@Override
	public void addUser(User user) {
		userMapper.insert(user);
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateByPrimaryKey(user);

	}

	@Override
	public List<User> getUserList(User user) {
		return userMapper.selectList(user);
	}

	@Override
	public int deleteUser(String userId) {
		return userMapper.deleteByPrimaryKey(userId);
	}

}
