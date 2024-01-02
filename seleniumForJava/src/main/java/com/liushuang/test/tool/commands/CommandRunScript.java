package com.liushuang.test.tool.commands;

import org.openqa.selenium.JavascriptExecutor;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.webdriver.WebDriverControl;

public class CommandRunScript implements Commandable {

	@Override
	public String getName() {

		return "RUNSCRIPT";
	}

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) drvctrl.driver();
		js.executeScript(action.getTarget());
		result.setResult(action.getActionNo(), true, "");

		return true;
	}

}
