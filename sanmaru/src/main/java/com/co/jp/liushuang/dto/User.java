package com.co.jp.liushuang.dto;

import java.io.Serializable;

public class User implements Serializable {

	private String userId;

	private String userName;

	private String password;

	private String locale = "en_US";

    public User() {
        System.out.println("new User()");
    }

    public User(String id) {
        System.out.println("new User()" + id);
    }
	public String getUserId() {
		return userId;
	}

    private void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

}
