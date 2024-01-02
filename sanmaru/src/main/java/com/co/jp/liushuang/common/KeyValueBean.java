package com.co.jp.liushuang.common;

public class KeyValueBean {

	private String value;
	private String key;

	public KeyValueBean() {
	}

	public KeyValueBean(String key, String value) {
		if (key != null) {
			this.key = key;
			this.value = value;
		}
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	//

}
