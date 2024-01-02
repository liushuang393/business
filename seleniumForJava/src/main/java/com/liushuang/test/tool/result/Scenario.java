package com.liushuang.test.tool.result;

import java.util.ArrayList;

import com.liushuang.test.tool.webdriver.WebDriverType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Scenario extends ArrayList<Action> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String testID;
	private WebDriverType browser;

	public Scenario(String testID) {
		this.testID = testID;
	}

}
