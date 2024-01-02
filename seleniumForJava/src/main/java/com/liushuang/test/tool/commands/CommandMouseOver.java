package com.liushuang.test.tool.commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.webdriver.WebDriverControl;

public class CommandMouseOver implements Commandable {

	@Override
	public String getName() {

		return "MOUSEOVER";
	}

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result) throws Exception {
		ActionOption option = ActionOption.parseOption(action.getOption());

		By selector = OptionSelector.build(option.get("SELECTOR"), action.getTarget(), getName());

		StringBuilder comments = new StringBuilder();
		try {
			WebElement elemt = drvctrl.findElement(selector, action.getTargets(), comments);

			if (elemt == null) {
				comments.append("MOUSEOUTを無視した。tagName=" + action.getTarget());
				result.setResult(action.getActionNo(), true, comments.toString());
				return true;
			}

			Actions builder = new Actions(drvctrl.driver());
			builder.moveToElement(elemt).perform();
		} catch (Exception e) {
			comments.append("MOUSEOUTを無視した。tagName=" + action.getTarget());
		}

		result.setResult(action.getActionNo(), true, "");
		return true;
	}

}
