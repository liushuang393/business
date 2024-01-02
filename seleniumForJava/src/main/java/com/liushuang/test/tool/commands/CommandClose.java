package com.liushuang.test.tool.commands;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.webdriver.WebDriverControl;

public class CommandClose implements Commandable {

	@Override
	public String getName() {

		return "CLOSE";
	}

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result) throws Exception {

		// drvctrl.close();
		result.setResult(action.getActionNo(), true, "画面が切り替え。");
		return true;
	}

}
