package com.liushuang.test.tool.commands;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.utils.Utils;
import com.liushuang.test.tool.webdriver.WebDriverControl;

public class CommandSave implements Commandable {

	@Override
	public String getName() {

		return "SAVE";
	}

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result) throws Exception {

		// drvctrl.driver().switchTo().defaultContent();

		String[] flds = action.getParent().getTestID().split("-", 2);

		Utils.outputHtml(flds[0], action, drvctrl.getPageSource());

		result.setResult(action.getActionNo(), true, "Save is completed.");
		return true;
	}

}
