package com.liushuang.test.tool.commands;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.webdriver.WebDriverControl;

public class CommandWebdriverChooseCancelOnVisibleConfirmation implements Commandable {

	@Override
	public String getName() {

		return "WEBDRIVERCHOOSECANCELONVISIBLECONFIRMATION";
	}

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result) throws Exception {

		boolean res = true;

		drvctrl.driver().switchTo().alert().dismiss();
		result.setResult(action.getActionNo(), true, "choose Cancel.");
		Thread.sleep(2000);
		return res;
	}
}
