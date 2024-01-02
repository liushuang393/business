package com.liushuang.test.tool.commands;

import java.util.Set;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.utils.Utils;
import com.liushuang.test.tool.webdriver.WebDriverControl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommandWindowSwitch implements Commandable {

	@Override
	public String getName() {

		return "WINDOWSWITCH";
	}

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result) throws Exception {

		ActionOption option = ActionOption.parseOption(action.getOption());

		String optMode = option.get("MODE").toUpperCase().trim();

		boolean res = true;
		switch (optMode) {
		case "OPEN":
			res = openAction(drvctrl, action, result);
			break;
		case "CLOSE":
			res = closeAction(drvctrl, action, result);
			break;
		default:
			result.setResult(action.getActionNo(), false, String.format("未知mode[%s]", optMode));
			return false;
		}
		return res;
	}

	private boolean closeAction(WebDriverControl drvctrl, com.liushuang.test.tool.result.Action action,
			ActionResult result) {
		StringBuilder comment = new StringBuilder();
		try {
			String handle = drvctrl.popWindowHandle();
			drvctrl.driver().switchTo().window(handle);

		} catch (Exception e) {
			comment.append("戻る対象のウインドウがありませんでした。");
			result.setResult(action.getActionNo(), false, comment.toString());
			return false;
		}
		result.setResult(action.getActionNo(), true, comment.toString());
		return true;
	}

	private boolean openAction(WebDriverControl drvctrl, com.liushuang.test.tool.result.Action action,
			ActionResult result) throws InterruptedException {
		StringBuilder comment = new StringBuilder();
		StringBuilder weindowTitles = new StringBuilder();

		boolean sucFlg = false;

		Object targethandlNmaeObj = drvctrl.getVarshandles().get(action.getTarget().replaceAll("[$|{|}]", ""));
		String targethandlNmae = "";
		if (targethandlNmaeObj != null) {
			targethandlNmae = targethandlNmaeObj.toString();
		}

		if (!Utils.isEmpt(targethandlNmae)) {
			sucFlg = true;
			drvctrl.driver().switchTo().window(targethandlNmae);
			drvctrl.setOpen(true);
			if ("root".equals(targethandlNmae)) {
				drvctrl.getVarshandles().put("window_handles", drvctrl.driver().getWindowHandles());
			}
			result.setResult(action.getActionNo(), true, comment.toString());
			log.info("WINDOWSWITCH:: target hand name = {} に遷移した。({})", targethandlNmae, drvctrl.driver().getTitle());
			Thread.sleep(2000);
			return true;
		}

		if (drvctrl.isOpen()) {
			Set<String> windowHandles = drvctrl.driver().getWindowHandles();
			String nowWindowHandle = drvctrl.driver().getWindowHandle();
			log.info("WINDOWSWITCH::now Window Handle: {} ", nowWindowHandle);
			for (String handle : windowHandles) {
				log.info("WINDOWSWITCH::WindowHandles: {} ", handle);
				drvctrl.driver().switchTo().window(handle);
				String windowTitle = drvctrl.driver().getTitle().trim();
				weindowTitles.append(windowTitle + ",");
				if (weindowTitles.toString().contains(action.getValue().trim())) {
					drvctrl.pushWindowHandle(nowWindowHandle);
					sucFlg = true;
					break;
				} else if (!handle.equals(nowWindowHandle)) {
					drvctrl.driver().switchTo().window(handle);
					sucFlg = true;
					break;
				}
			}
		}

		Set<String> windowHandles = (Set<String>) drvctrl.getVarshandles().get(WebDriverControl.WINDOW_HANDLES);
		if (!sucFlg && windowHandles != null) {
			for (String handle : windowHandles) {
				sucFlg = true;
				drvctrl.driver().switchTo().window(handle);
				drvctrl.driver().manage().window().fullscreen();
				drvctrl.setOpen(true);
				break;
			}
		}

		if (!sucFlg) {
			if (weindowTitles.length() > 0) {
				weindowTitles.setLength(weindowTitles.length() - 1);
			}
			comment.append(String.format("対象のウインドウ名が存在しません。 [window titles=%s]", weindowTitles.toString()));
			result.setResult(action.getActionNo(), false, comment.toString());
			return false;
		}

		result.setResult(action.getActionNo(), true, comment.toString());
		return true;
	}
}
