package com.liushuang.test.tool.commands;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.utils.Utils;
import com.liushuang.test.tool.webdriver.WebDriverControl;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class CommandShot implements Commandable {

	@Override
	public String getName() {

		return "SHOT";
	}

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result) throws Exception {

		ActionOption option = ActionOption.parseOption(action.getOption());

		String optMode = option.get("MODE").toUpperCase().trim();

		drvctrl.driver().switchTo().defaultContent();

		String[] flds = action.getParent().getTestID().split("-", 2);
		String folder = Utils.getEvidenceFolder(flds[0]);

		String title = drvctrl.driver().getTitle();
		if (Utils.isEmpt(title)) {
			title = action.getTarget();
		}
		String shotName = String.format("%s_%03d-%s", action.getParent().getTestID(), action.getActionNo(), title);

		BufferedImage tImg = null;
		File newdir = new File(folder);
		newdir.mkdir();
		if ("ASHOT".contentEquals(optMode)) {
			Screenshot scr = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
					.takeScreenshot(drvctrl.driver());
			tImg = scr.getImage();
			ImageIO.write(tImg, "PNG", new File(folder + shotName + ".png"));
		} else if ("PAGE".equals(optMode)) {
			((JavascriptExecutor) drvctrl.driver()).executeScript("window.scrollTo(0,0)");
			int scrollCnt = 1;
			try {
				int tCnt = Integer.parseInt(action.getValue());
				if (tCnt > 0) {
					scrollCnt = tCnt;
				}
			} catch (NumberFormatException e) {
				throw e;
			}

			BufferedImage tmpImg = ImageIO.read(((TakesScreenshot) drvctrl.driver()).getScreenshotAs(OutputType.FILE));

			for (int i = 2; i <= scrollCnt; i++) {
				BufferedImage workImg = tmpImg;
				Actions act = new Actions(drvctrl.driver());
				act.sendKeys(Keys.PAGE_DOWN).perform();

				BufferedImage addImg = ImageIO
						.read(((TakesScreenshot) drvctrl.driver()).getScreenshotAs(OutputType.FILE));

				tmpImg = new BufferedImage(workImg.getWidth(), workImg.getHeight() + addImg.getHeight() + 3,
						BufferedImage.TYPE_INT_ARGB);
				Graphics g2 = tmpImg.getGraphics();
				g2.drawImage(workImg, 0, 0, null);
			}
		} else {
			tImg = ImageIO.read(((TakesScreenshot) drvctrl.driver()).getScreenshotAs(OutputType.FILE));
			ImageIO.write(tImg, "PNG", new File(folder + shotName + ".png"));
		}

		result.setResult(action.getActionNo(), true, "Shot is completed.");
		return true;
	}

}
