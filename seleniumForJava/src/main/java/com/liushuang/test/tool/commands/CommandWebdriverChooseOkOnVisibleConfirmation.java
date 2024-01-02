package com.liushuang.test.tool.commands;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.webdriver.WebDriverControl;

public class CommandWebdriverChooseOkOnVisibleConfirmation implements Commandable {

	@Override
	public String getName() {

		return "WEBDRIVERCHOOSEOKONVISIBLECONFIRMATION";
	}

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result) throws Exception {

		boolean res = true;

		drvctrl.driver().switchTo().alert().accept();
		result.setResult(action.getActionNo(), true, "choose OK.");
		Thread.sleep(2000);
		return res;
	}
}
