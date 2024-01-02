package com.co.jp.liushuang.controller.form;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import com.co.jp.liushuang.core.annotation.NotEmpty;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UserLoginForm implements Serializable {

	@Length(min = 5, max = 10)
	@NotEmpty(field = "userId")
	private String userId;

	private String userName;

	@NotEmpty(field = "password")
	private String password;

	private String locale = "en_US";

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
