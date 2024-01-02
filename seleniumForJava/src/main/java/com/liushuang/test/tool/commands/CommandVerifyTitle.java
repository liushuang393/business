package com.liushuang.test.tool.commands;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.utils.Utils;
import com.liushuang.test.tool.webdriver.WebDriverControl;

public class CommandVerifyTitle implements Commandable {

	@Override
	public String getName() {

		return "VERIFYTITLE";
	}

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result) throws Exception {

		StringBuilder comment = new StringBuilder();

		String title = drvctrl.driver().getTitle();
		String taget = action.getTarget();
		if (Utils.isEmpt(title)) {
			title = taget;
		}

		// キャプチャーを撮る
		String[] flds = action.getParent().getTestID().split("-", 2);
		String shotName = String.format("%s_%03d-%s", action.getParent().getTestID(), action.getActionNo(), title);
		Utils.outputImg(drvctrl.driver(), flds[0], shotName, drvctrl.getWebDriverType(), action.getTargets());
		comment.append("Shot is completed.");

//			BufferedImage tImg = ImageIO.read(((TakesScreenshot) drvctrl.driver())
//					.getScreenshotAs(OutputType.FILE));
//			ImageIO.write(tImg, "PNG", new File(folder + shotName + ".png"));

		if (!Utils.isEmpt(taget) && taget.equals(drvctrl.driver().getTitle())) {
			result.setResult(action.getActionNo(), true, comment.toString());
			return true;
		}

		result.setResult(action.getActionNo(), false, comment.toString());
		return false;
	}

}
