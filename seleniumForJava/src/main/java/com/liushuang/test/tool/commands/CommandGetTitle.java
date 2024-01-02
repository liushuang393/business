package com.liushuang.test.tool.commands;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.webdriver.WebDriverControl;
public class CommandGetTitle implements Commandable {

	@Override
	public String getName() {

		return "GETTITLE";
	}

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result) throws Exception {

		String text = drvctrl.driver().getTitle();
		result.setResult(action.getActionNo(), true, text);

		return true;
	}

}
