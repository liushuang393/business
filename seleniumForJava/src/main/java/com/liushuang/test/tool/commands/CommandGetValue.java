package com.liushuang.test.tool.commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.webdriver.WebDriverControl;

public class CommandGetValue implements Commandable {

	@Override
	public String getName() {

		return "GETVALUE";
	}

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result) throws Exception {

		ActionOption option = ActionOption.parseOption(action.getOption());

		String optSelector = option.get("SELECTOR");
		By selector = OptionSelector.build(optSelector, action.getTarget(), getName());

		StringBuilder comments = new StringBuilder();
		WebElement elemt = drvctrl.findElement(selector, action.getTargets(), comments);

		if (elemt == null) {
			result.setResult(action.getActionNo(), false, comments.toString());
			return false;
		}

		String text = elemt.getAttribute("value");
		// elemt.getAttribute("value");
		result.setResult(action.getActionNo(), true, text);
		return true;
	}

}
