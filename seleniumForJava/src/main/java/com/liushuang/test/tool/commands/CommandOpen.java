package com.liushuang.test.tool.commands;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.utils.Utils;
import com.liushuang.test.tool.webdriver.WebDriverControl;

public class CommandOpen implements Commandable {

	@Override
	public String getName() {

		return "OPEN";
	}

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result) throws Exception {

		drvctrl.setWebDriverType(action.getParent().getBrowser());
		String target = action.getTarget().trim();
		if (Utils.TEST_URL.equals(target)) {
			target = drvctrl.getTestUrl();
		}

		drvctrl.open(target);
		result.setResult(action.getActionNo(), true, "");

		return true;
	}

}
