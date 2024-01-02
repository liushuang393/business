package com.liushuang.test.tool.commands;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.webdriver.WebDriverControl;

public class CommandSleep implements Commandable {

	@Override
	public String getName() {

		return "SLEEP";
	}

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result) throws Exception {

		StringBuilder comment = new StringBuilder();

		try {
			String val = action.getValue().trim().toUpperCase();
			int wait = Integer.parseInt(val);
			Thread.sleep(wait);

			result.setResult(action.getActionNo(), true, comment.toString());
		} catch (Exception e) {
			result.setResult(action.getActionNo(), false, comment.toString());
			return true;
		}

		return true;
	}

}
