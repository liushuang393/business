package com.liushuang.test.tool.commands;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.webdriver.WebDriverControl;

public class CommandSelectFrame implements Commandable {

	@Override
	public String getName() {

		return "SELECTFRAME";
	}

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result) throws Exception {

		boolean res = true;
		int index = 0;
		String frameName = "";

		try {
			index = Integer.parseInt(action.getTarget());

		} catch (NumberFormatException e) {
			frameName = action.getTarget();
		}

		if ("".equals(frameName)) {
			drvctrl.driver().switchTo().frame(index);
		} else {
			if ("parent".equals(action.getTarget())) {
				drvctrl.driver().switchTo().defaultContent();
			} else {
				drvctrl.driver().switchTo().frame(frameName);
			}

		}

		result.setResult(action.getActionNo(), true, "select frame is completed.");
		return res;
	}
}
