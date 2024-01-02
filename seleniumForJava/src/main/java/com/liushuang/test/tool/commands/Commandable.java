package com.liushuang.test.tool.commands;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.webdriver.WebDriverControl;
/**
 *
 * @Description:コマンド（Command）インターフェース
 * @author liushuang
 *
 * @version 1.0
 */
public interface Commandable {

	/** コマンド名 */
	public String getName();

	/** 動作実行 */
	public boolean Action(WebDriverControl drvctrl, Action action,
			ActionResult result) throws Exception;
}
