package com.liushuang.test.tool.commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.webdriver.WebDriverControl;

public class CommandVerifyText implements Commandable {

	@Override
	public String getName() {

		return "VERIFYTEXT";
	}

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result) throws Exception {

		ActionOption option = ActionOption.parseOption(action.getOption());
		String optSelector = option.get("SELECTOR");
		By selector = OptionSelector.build(optSelector, action.getTarget(), getName());
		StringBuilder comment = new StringBuilder();
		WebElement ele = drvctrl.findElement(selector, action.getTargets(), comment);

		if (ele == null) {
			result.setResult(action.getActionNo(), false, comment.toString());
			return true;
		}

		// キャプチャーを撮る
//		if (!"ID".equalsIgnoreCase(optSelector)) {
//			String[] flds = action.getParent().getTestID().split("-", 2);
//			String shotName = String.format("%s_%03d-%s", action.getParent().getTestID(), action.getActionNo(), ans);
//			Utils.outputImg(drvctrl.driver(),flds[0],shotName);
//			
////			BufferedImage tImg = ImageIO.read(((TakesScreenshot) drvctrl.driver())
////					.getScreenshotAs(OutputType.FILE));
////			ImageIO.write(tImg, "PNG", new File(folder + shotName + ".png"));
//		}

		if (action.getValue().equals(ele.getText())) {
			result.setResult(action.getActionNo(), true, comment.toString());
			return true;
		} else {
			result.setResult(action.getActionNo(), false, comment.toString());
			return false;
		}
	}

}
