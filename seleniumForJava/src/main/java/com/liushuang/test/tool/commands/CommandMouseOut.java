package com.liushuang.test.tool.commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.webdriver.WebDriverControl;

public class CommandMouseOut implements Commandable {

	@Override
	public String getName() {

		return "MOUSEOUT";
	}

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result) throws Exception {
		// ActionOption option = ActionOption.parseOption(action.getOption());

		// By selector = OptionSelector.build(option.get("SELECTOR"),
		// action.getTarget(), getName());

		StringBuilder comments = new StringBuilder();
		// WebElement elemt = drvctrl.findElement(selector, comments);

//		if (elemt == null) {
//			comments.append("MOUSEOUTを無視した。tagName=" + action.getTarget());
//
//		}

		try {
			// Actions builder = new Actions(drvctrl.driver());
			// builder.moveToElement(elemt).perform();

			WebElement element = drvctrl.driver().findElement(By.tagName("body"));
			if (element == null) {
				comments.append("MOUSEOUTを無視した。tagName=body");
			} else {
				Actions builder = new Actions(drvctrl.driver());
				builder.moveToElement(element, 0, 0).perform();
			}
		} catch (Exception e) {
			comments.append("MOUSEOUTを無視した。tagName=body");
		}
		result.setResult(action.getActionNo(), true, "");
		return true;
	}

}
