package com.liushuang.test.tool.commands;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.webdriver.WebDriverControl;

public class CommandAssertConfirmation implements Commandable {

	@Override
	public String getName() {

		return "ASSERTCONFIRMATION";
	}

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result)
			throws Exception {
		Thread.sleep(3000);
		new WebDriverWait(drvctrl.driver(), Duration.ofSeconds(2))
		.until(ExpectedConditions.alertIsPresent());
		boolean res = false;
		if (drvctrl.driver().switchTo().alert().getText().equals(action.getTarget())) {
			res = true;
		}

		result.setResult(action.getActionNo(), res, "");
		return true;
	}
}
