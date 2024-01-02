package com.co.jp.liushuang.service.common;

import java.util.List;

import com.co.jp.liushuang.persistence.entity.User;

public interface UserService {

	public void addUser(User user);

	public void updateUser(User user);

	public User getUserById(String userId);

	public List<User> getUserList(User user);

	int deleteUser(String userId);
}