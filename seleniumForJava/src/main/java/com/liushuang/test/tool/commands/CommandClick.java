package com.liushuang.test.tool.commands;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.utils.Utils;
import com.liushuang.test.tool.webdriver.WebDriverControl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommandClick implements Commandable {

	@Override
	public String getName() {

		return "CLICK";
	}

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result)
			throws Exception {

		ActionOption option = ActionOption.parseOption(action.getOption());

		By selector = OptionSelector.build(option.get("SELECTOR"), action.getTarget(), getName());

		StringBuilder comments = new StringBuilder();
		WebElement elemt = drvctrl.findElement(selector, action.getTargets(), comments);
		if (!"*".equals(action.getValue())) {
			drvctrl.setWindowHandle("window_handles", drvctrl.driver().getWindowHandles());
			log.info("CLICK:: window_handles が保存しました。");
		}

		if (elemt == null) {
			comments.append("冗長性なクリックの場合、この行のB列値「C」に変更してください。");

			result.setResult(action.getActionNo(), false, comments.toString());
			return false;
		}

		// String nowWindowHandle = drvctrl.driver().getWindowHandle();
		// log.info("CLICK:: Now WindowHandle name is {}。", nowWindowHandle);
		try {
			new WebDriverWait(drvctrl.driver(), Duration.ofSeconds(3))
					.until(ExpectedConditions.elementToBeClickable(elemt));
		} catch (Exception e) {
			log.warn("element is unClickable. ソースが正しいですかを確認してください。 ::{}", e.getMessage());
			comments.append(e.getMessage());
		}
		if (!Utils.isEmpt(action.getValue())) {

			String type = elemt.getAttribute("type");
			String tagName = elemt.getTagName();
			log.info("CLICK:: Type is {}. :: Tag Name is {}", type, tagName);
			if ("button".equalsIgnoreCase(tagName) || "a".equalsIgnoreCase(tagName)) {
				String[] flds = action.getParent().getTestID().split("-", 2);
				String title = drvctrl.driver().getTitle();
				if (Utils.isEmpt(title)) {
					title = action.getTarget();
				}
				String shotName = String.format("%s_%03d-%s", action.getParent().getTestID(), action.getActionNo(),
						title);
				Utils.outputImg(drvctrl.driver(), flds[0], shotName, drvctrl.getWebDriverType(), action.getTargets());
				comments.append("Shot is completed.");
				Thread.sleep(2000);
				// Utils.outputHtml(flds[0], action, drvctrl.getPageSource());
				// comments.append("html Save is completed.");
				try {
					Actions actions = new Actions(drvctrl.driver());
					actions.click(elemt).perform();
				} catch (Exception e) {
					try {
						// drvctrl.executeScript("arguments[0].scrollIntoView();", elemt);
						elemt.click();

					} catch (Exception e1) {
						log.warn(e1.getMessage());
					}
				}
			} // type="file"の場合、クリックすると、無視する。
			else if ("file".equalsIgnoreCase(type)) {

				// type= null の場合、クリックすると、無視する。
//			} else if (type == null) {

			} else {
				try {
					Actions actions = new Actions(drvctrl.driver());
					actions.click(elemt).perform();

				} catch (Exception e) {
					try {
						// drvctrl.executeScript("arguments[0].scrollIntoView();", elemt);
						elemt.click();

					} catch (Exception e1) {
						log.warn(e1.getMessage());
					}

				}
			}
		}

		if (!"*".equals(action.getValue())) {
			String newWH = drvctrl.waitForWindow(4000);
			drvctrl.setWindowHandle(action.getValue(), newWH);
			log.info("CLICK:: 新 windowHandleName name is {}。", newWH);
		}
		result.setResult(action.getActionNo(), true, comments.toString());
		Thread.sleep(1000);
		return true;
	}

}
