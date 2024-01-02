package com.liushuang.test.tool.commands;

import org.openqa.selenium.By;

public class OptionSelector {

	public enum SelectorType {
		id,ID, name,NAME, css,CSS, linkText,LINKTEXT, xpath,XPATH, relative,RELATIVE
	}

	public static By build(String type, String target, String msgCommand) throws Exception {
		if (type == null) {
			throw new Exception(String.format("Selector未定。[command=\"%s\"]", msgCommand));
		}

		SelectorType selType = SelectorType.valueOf(type);
		By res;

		switch (selType) {
		case ID:
		case id:
			res = By.id(target);
			break;
		case NAME:
		case name:
			res = By.name(target);
			break;
		case XPATH:
		case xpath:
			res = By.xpath(target);
			break;
		case CSS:
		case css:
			res = By.cssSelector(target);
			break;
		case LINKTEXT:
		case linkText:
			res = By.linkText(target);
			break;
		case RELATIVE:
		case relative:
			res = null;
			break;
		default:
			throw new Exception(String.format("Selector未定。[command=\"%s\", SELECTOR=\"%s\"]", msgCommand, type));

		}
		return res;
	}
}
