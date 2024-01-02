package com.liushuang.test.tool.commands;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.utils.Utils;
import com.liushuang.test.tool.webdriver.WebDriverControl;

public class CommandInput implements Commandable {

	@Override
	public String getName() {

		return "INPUT";
	}

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result) throws Exception {

		ActionOption option = ActionOption.parseOption(action.getOption());

		String optSelector = option.get("SELECTOR");
		By selector = OptionSelector.build(optSelector, action.getTarget(), getName());

		StringBuilder comments = new StringBuilder();
		WebElement elemt = drvctrl.findElement(selector, action.getTargets(), comments);

		if (elemt == null) {
			result.setResult(action.getActionNo(), false, comments.toString());
			return false;
		} else if ("*".equals(action.getValue()) || Utils.isEmpt(action.getValue())) {
			elemt.sendKeys(Keys.CONTROL + "a");
			elemt.sendKeys(Keys.DELETE);
		} else {
			elemt.clear();
			elemt.sendKeys(action.getValue());
		}

		Boolean vrf = option.getBoolean("VERIFY", comments);

		if (vrf == null) {
			result.setResult(action.getActionNo(), false, comments.toString());
			return false;
		}

		if (vrf.booleanValue()) {
			String disp = elemt.getAttribute("value");
			if (!disp.equals(action.getValue())) {
				comments.append(String.format("入力検証エラー[入力値：%s;結果値；%s]", action.getValue(), disp));
				result.setResult(action.getActionNo(), true, comments.toString());
				return false;
			}
		}

		result.setResult(action.getActionNo(), true, comments.toString());
		return true;
	}

}
