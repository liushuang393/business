package com.liushuang.test.tool.commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.webdriver.WebDriverControl;

public class CommandDoubleClick implements Commandable {

	@Override
	public String getName() {

		return "DOUBLECLICK";
	}

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result) throws Exception {
		ActionOption option = ActionOption.parseOption(action.getOption());

		By selector = OptionSelector.build(option.get("SELECTOR"), action.getTarget(), getName());

		StringBuilder comments = new StringBuilder();
		WebElement elemt = drvctrl.findElement(selector, action.getTargets(), comments);

		if (elemt == null) {
			result.setResult(action.getActionNo(), false, comments.toString());
			return false;
		}

		Actions builder = new Actions(drvctrl.driver());
		builder.doubleClick(elemt).perform();

		result.setResult(action.getActionNo(), true, "");
		return true;
	}

}
