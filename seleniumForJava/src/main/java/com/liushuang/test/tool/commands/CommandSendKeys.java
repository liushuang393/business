package com.liushuang.test.tool.commands;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.utils.Utils;
import com.liushuang.test.tool.webdriver.WebDriverControl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommandSendKeys implements Commandable {

	@Override
	public String getName() {

		return "SENDKEYS";
	}

	public static final Map<String, Integer> keysMap = new HashMap<>();

	@Override
	public boolean Action(WebDriverControl drvctrl, Action action, ActionResult result) throws Exception {

		WebElement ele = null;
		StringBuilder comment = new StringBuilder();

		String keyValue = action.getValue();
		String[] keys = keyValue.split("\\+");

		// コントロールがある場合、
		if (!Utils.isEmpt(action.getTarget())) {
			CharSequence[] sendKeys = new CharSequence[3];
			int i = 0;
			for (String key : keys) {
				CharSequence keyChar = getKey(key);
				if (keyChar != null) {
					sendKeys[i++] = keyChar;
				} else {
					sendKeys[i++] = key;
				}
			}

			ActionOption option = ActionOption.parseOption(action.getOption());
			String optSelector = option.get("SELECTOR");

			By selector = OptionSelector.build(optSelector, action.getTarget(), getName());
			ele = drvctrl.findElement(selector, action.getTargets(), comment);

			if (ele == null) {
				result.setResult(action.getActionNo(), false, comment.toString());
				return false;
			}

			if (sendKeys[0] == null) {
				comment.append("無効なキー:" + keyValue);
				result.setResult(action.getActionNo(), false, comment.toString());
				return false;

			} else {
				if (sendKeys[2] != null) {
					ele.sendKeys(sendKeys[0], sendKeys[1], Keys.chord(sendKeys[2]));
				} else if (sendKeys[1] != null) {
					ele.sendKeys(sendKeys[0], Keys.chord(sendKeys[1]));
				} else {
//					if (sendKeys[0].equals(Keys.ENTER)) {
//						System.setProperty("java.awt.headless", "false");
//						java.awt.Robot robot = new java.awt.Robot();
//						robot.keyPress(KeyEvent.VK_ENTER);
//						robot.keyRelease(KeyEvent.VK_ENTER);
//						System.setProperty("java.awt.headless", "true");
//					} else {
					ele.sendKeys(sendKeys[0]);
//					}
				}

				Thread.sleep(1000);
				comment.append("キー:" + keyValue);
				result.setResult(action.getActionNo(), true, comment.toString());
				return true;
			}

		} else {// コントロールが関係なし場合
			System.setProperty("java.awt.headless", "false");
			java.awt.Robot robot = new java.awt.Robot();

			List<Integer> sendKeys = new ArrayList<>();

			for (String key : keys) {
				Integer keyChar = getKeyForRobot(key);
				if (keyChar != null) {
					sendKeys.add(keyChar);
				} else {
					comment.append("無効なキー:" + key);
					result.setResult(action.getActionNo(), false, comment.toString());
					return false;
				}
			}

			for (int key : sendKeys) {
				robot.keyPress(key);
			}
			for (int key : sendKeys) {
				robot.keyRelease(key);
			}

			Thread.sleep(1000);
			System.setProperty("java.awt.headless", "true");
			comment.append("キー:" + sendKeys);
			result.setResult(action.getActionNo(), true, comment.toString());
			return true;

		}
	}

	private Integer getKeyForRobot(String keyString) {

		String tepKey = "";
		if (keyString == null) {
			return null;
		}

		keyString = keyString.replaceAll(" ", "");
		String[] arr = keyString.split("_");

		if (arr.length < 2) {
			tepKey = arr[0];
		} else {
			String tem = arr[1];
			arr = tem.split("}");
			tepKey = arr[0];
		}

		int keyEvent = 0;
		tepKey = tepKey.toUpperCase();
		String code = "VK_" + tepKey;

		Field f = null;

		try {

			f = KeyEvent.class.getDeclaredField(code);
			keyEvent = f.getInt(null);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}

		return keyEvent;
	}

	private Keys getKey(String keyString) {

		if (keyString == null) {
			return null;
		}

		keyString = keyString.replaceAll(" ", "");
		String[] arr = keyString.split("_");

		if (arr.length < 2) {
			return null;
		}

		String tem = arr[1];
		if (tem == null) {
			return null;
		}

		arr = tem.split("}");

		Keys keyChar = Keys.valueOf(arr[0]);

		return keyChar;
	}

}
