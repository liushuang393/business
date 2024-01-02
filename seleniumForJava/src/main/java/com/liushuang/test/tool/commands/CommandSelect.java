package com.liushuang.test.tool.commands;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.utils.Utils;
import com.liushuang.test.tool.webdriver.WebDriverControl;

public class CommandSelect implements Commandable {

	@Override
	public String getName() {

		return "SELECT";
	}

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result) throws Exception {

		ActionOption option = ActionOption.parseOption(action.getOption());
		String optSelector = option.get("SELECTOR");
		By selector = OptionSelector.build(optSelector, action.getTarget(), getName());
		StringBuilder comment = new StringBuilder();
		WebElement ele = drvctrl.findElement(selector, action.getTargets(), comment);

		if (ele == null) {
			result.setResult(action.getActionNo(), false, comment.toString());
			return false;
		}

		try {

			if ("*".equals(action.getValue()) || Utils.isEmpt(action.getValue())) {
				new Select(ele).selectByIndex(0);
			} else {
				new Select(ele).selectByVisibleText(action.getValue());
			}

			result.setResult(action.getActionNo(), true, comment.toString());
		} catch (NoSuchElementException e) {
			result.setResult(action.getActionNo(), false, comment.toString());
			return false;
		}

		return true;
	}

}
