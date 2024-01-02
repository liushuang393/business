package com.liushuang.test.tool.commands;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.utils.Utils;
import com.liushuang.test.tool.webdriver.WebDriverControl;

public class CommandAssertAlert implements Commandable {

	@Override
	public String getName() {

		return "ASSERTALERT";
	}

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result)
			throws Exception {
		StringBuilder comments = new StringBuilder();
		if (Utils.isEmpt(action.getTarget())) {

			result.setResult(action.getActionNo(), false, "Target value is Empty.");
			return false;
		}
		String alertText = "";
		boolean ret = true;
		try {
			Thread.sleep(1000);
			new WebDriverWait(drvctrl.driver(), Duration.ofSeconds(5))
					.until(ExpectedConditions.alertIsPresent());

			alertText = drvctrl.driver().switchTo().alert().getText();
			drvctrl.driver().switchTo().alert().accept();
			comments.append(alertText);
			ret = action.getTarget().equals(alertText);
			if (ret == false) {
				ret = action.getTarget().equals(alertText);
				comments.append(";複数確認アラート同時表示の場合,結果はfalseを無視してください。");
			}

		} catch (Exception e) {
			comments.append(e.getMessage());
			comments.append("非同期処理があり,サーバ側からレスポンスを取得しますが,待機画面が閉じらないかを確認してください。");
		}
		Thread.sleep(2000);
		result.setResult(action.getActionNo(), ret, comments.toString());
		return true;
	}

}
