package com.liushuang.test.tool.commands;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.utils.Utils;
import com.liushuang.test.tool.webdriver.WebDriverControl;

public class CommandStoreWindowHandle implements Commandable {

	@Override
	public String getName() {

		return "STOREWINDOWHANDLE";
	}

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result) throws Exception {

		boolean res = true;

		if (!Utils.isEmpt(action.getTarget())) {

			drvctrl.setWindowHandle(action.getTarget(), drvctrl.driver().getWindowHandle());
		} else {

			res = false;
			result.setResult(action.getActionNo(), res, "Target value is Empty.");
			return false;
		}

		result.setResult(action.getActionNo(), res, "store frame is completed.");
		return res;
	}

}
