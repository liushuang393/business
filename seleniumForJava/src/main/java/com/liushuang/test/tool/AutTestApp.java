package com.liushuang.test.tool;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.liushuang.test.tool.commands.Commandable;
import com.liushuang.test.tool.excel.ExelController;
import com.liushuang.test.tool.excel.ScenarioWithIndex;
import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.result.EndType;
import com.liushuang.test.tool.result.ScenarioResult;
import com.liushuang.test.tool.utils.Utils;
import com.liushuang.test.tool.webdriver.WebDriverControl;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @Description: Excelシナリオ自動テストクラス
 * @author liushuang
 *
 * @version 1.0
 */
@Slf4j
public class AutTestApp {

	/** コマンドロード用 */
	private static final String[] commandAry = { "com.liushuang.test.tool.commands.CommandClick",
			"com.liushuang.test.tool.commands.CommandClose", "com.liushuang.test.tool.commands.CommandGetTitle",
			"com.liushuang.test.tool.commands.CommandGetValue", "com.liushuang.test.tool.commands.CommandInput",
			"com.liushuang.test.tool.commands.CommandOpen", "com.liushuang.test.tool.commands.CommandSave",
			"com.liushuang.test.tool.commands.CommandSelect", "com.liushuang.test.tool.commands.CommandShot",
			"com.liushuang.test.tool.commands.CommandSleep", "com.liushuang.test.tool.commands.CommandSelectFrame",
			"com.liushuang.test.tool.commands.CommandWindowSwitch",
			"com.liushuang.test.tool.commands.CommandAssertConfirmation",
			"com.liushuang.test.tool.commands.CommandWebdriverChooseOkOnVisibleConfirmation",
			"com.liushuang.test.tool.commands.CommandVerifyText", "com.liushuang.test.tool.commands.CommandVerifyTitle",
			"com.liushuang.test.tool.commands.CommandRunScript", "com.liushuang.test.tool.commands.CommandSendKeys",
			"com.liushuang.test.tool.commands.CommandClose", "com.liushuang.test.tool.commands.CommandDoubleClick",
			"com.liushuang.test.tool.commands.CommandMouseOver", "com.liushuang.test.tool.commands.CommandMouseOut",
			"com.liushuang.test.tool.commands.CommandStoreWindowHandle",
			"com.liushuang.test.tool.commands.CommandAssertAlert",
			"com.liushuang.test.tool.commands.CommandWebdriverChooseCancelOnVisibleConfirmation" };

	/** 命令マップ */
	private static final Map<String, Commandable> comds = loadCommands();

	/** 構成ファイル */
	// private static final ResourceBundle config =
	// ResourceBundle.getBundle("config", new ResourceBundleUtf8Control());

	private static Map<String, Commandable> loadCommands() {
		Map<String, Commandable> map = new HashMap<>();

		for (String cmd : commandAry) {
			Class<?> cmdClass;
			try {
				cmdClass = Class.forName(cmd);
				Commandable command;

				command = (Commandable) cmdClass.newInstance();
				map.put(command.getName(), command);
			} catch (Exception e) {
				e.printStackTrace();
				log.info("\n\n 単純なサービス・プロバイダ・ロード失敗した、Command名を確認してください。");
			}
		}

		return map;
	}

	public static void main(String[] args) {

		log.info("\n\n Selenium Test Start.");

		// Excel操作
		ExelController excel = new ExelController();

		try {

			log.info("configを読み込み");
			ResourceBundle config = Utils.loadPropertieFile();
			String url = "";
			try {
				url = config.getString(Utils.TEST_URL);
			} catch (Exception e) {
				log.info("PropertyResourceBundle, key TEST_URL  Can't set ");
			}
			String clearFlg = config.getString(Utils.CLEAR_FLG);
			String evidencePath = config.getString(Utils.EVIDENCE_PATH);
			String inputFilePath = config.getString(Utils.INPUT_PATH);
			String migrationFlg = config.getString(Utils.MIGRATION_FLG);// 移行前または移行後
			String inputFileFullPath = new File("").getAbsolutePath() + inputFilePath + migrationFlg + "\\";
			String evidenceFullPath = new File("").getAbsolutePath() + evidencePath + migrationFlg + "\\";
			String elementWait = config.getString(Utils.GET_ELEMENT_WAIT_TIME);

			Map<String, String> resourceMap = new HashMap<>();
			resourceMap.put(Utils.TEST_URL, url);
			resourceMap.put(Utils.INPUT_FULL_PATH, inputFileFullPath);
			resourceMap.put(Utils.EVIDENCE_FULL_PATH, evidenceFullPath);
			resourceMap.put(Utils.PRINT_WAIT_TIME, config.getString(Utils.PRINT_WAIT_TIME));
			resourceMap.put(Utils.EVIDENCE_PATH, config.getString(Utils.EVIDENCE_PATH));
			resourceMap.put(Utils.PRINT_FLG, config.getString(Utils.PRINT_FLG));
			resourceMap.put(Utils.WEB_DRIVER_VER, config.getString(Utils.WEB_DRIVER_VER));
			resourceMap.put(Utils.WINDOW_SIZE, config.getString(Utils.WINDOW_SIZE));
			resourceMap.put(Utils.GET_ELEMENT_WAIT_TIME, elementWait);
			resourceMap.put(Utils.AUTOMATIC_SCREENSHOT_FLG, config.getString(Utils.AUTOMATIC_SCREENSHOT_FLG));
			resourceMap.put(Utils.AUTOMATIC_SCREENSHOT_STOP_NAMES,
					config.getString(Utils.AUTOMATIC_SCREENSHOT_STOP_NAMES));
			Utils.resourceMapThreadLocal.set(resourceMap);

			// absolutePath1 = D:/workspace/seleniumForJava

			log.info("テスト TEST_URL:[{}]", url);

			// Step1: テストファイル初期化、命令読み込み
			excel.init(inputFileFullPath);

			// エビデンスクリア
			if (clearFlg != null && "TRUE".equals(clearFlg.trim().toUpperCase())) {
				Utils.clearFolder(evidenceFullPath, true);
				excel.clearResult();
			}
			// Step２:解像度設定し、テスト起動
			drivExe(excel, url);

			log.info("テスト 終了");
		} catch (Exception e) {
			log.error("テスト 異常終了：");
			log.error(e.getMessage());
			e.printStackTrace();
		} finally {
			Utils.killDriverProcess();
		}

	}

	/**
	 *
	 * @Description:テスト実施
	 * @param excel
	 * @param url
	 * @throws Exception
	 */
	private static void drivExe(ExelController excel, String url) throws Exception {

		// 警告情報設定リスト
		List<String> warnList = new ArrayList<>();
		// エラー情報設定リスト
		List<String> errorList = new ArrayList<>();

		WebDriverControl webDriverCtrl = new WebDriverControl();

		try {

			// 解像度設定：シナリオファイル（set window size）＞config（1920x1080）＞デフォルト（1024*768）
			String windowSize = Utils.resourceMapThreadLocal.get().get(Utils.WINDOW_SIZE);
			if (windowSize != null) {
				String[] ary = windowSize.split("x");
				if (ary.length == 2) {
					webDriverCtrl.setWindowSizeRow(Integer.parseInt(ary[0]));
					webDriverCtrl.setWindowSizeCol(Integer.parseInt(ary[1]));
				}
			}

			// WebDriverバージョン
			webDriverCtrl.setWebDriverVer(Utils.resourceMapThreadLocal.get().get(Utils.WEB_DRIVER_VER));
			// WebDriverにURL設定
			webDriverCtrl.setTestUrl(url);

			// シナリオ数
			int senarioCnt = 0;
			// エラー数
			int errorCnt = 0;
			// 警告
			int warrningCnt = 0;
			// シナリオインデックス

			ScenarioWithIndex scenario = null;

			// Excel中のActionの循環実行
			while ((scenario = excel.nextActionList()) != null) {
				senarioCnt++;
				log.info("TestNo:{} ID:{} BRW:{}", senarioCnt, scenario.getSenario().getTestID(),
						scenario.getSenario().getBrowser().toString());
				ScenarioResult result = new ScenarioResult(scenario.getSenario().getTestID());
				for (Action act : scenario.getSenario()) {
					log.info("Action ID:{} ANO:{} CMD:{} OPT:{} TAG:{} VAL:{}", scenario.getSenario().getTestID(),
							act.getActionNo(), act.getCommandName(), act.getOption(), act.getTarget(), act.getValue());

					Commandable cmd = comds.get(act.getCommandName().toUpperCase());
					ActionResult res = new ActionResult();
					boolean isContinue;
					if (cmd == null) {
						res.setResult(act.getActionNo(), false,
								String.format("コマンドがみつかりません。[{}]", act.getCommandName()));
						isContinue = false;
					} else {
						isContinue = cmd.Action(webDriverCtrl, act, res);
					}

					log.debug("RESULT ID:{} ANO:{} OK:{} CMM:{}", result.getTestID(), res.getActionNo(), res.isOK(),
							res.getComment());
					result.add(res);

					if (isContinue == false) {
						String errmsg = String.format("ERROR ID:%s ANO:%d OK:%b CMM:%s", result.getTestID(),
								res.getActionNo(), res.isOK(), res.getComment());
						errorList.add(errmsg);
						result.setEndtype(EndType.ERROR);
						break;
					}

					if (res.isOK() == false) {
						String errmsg = String.format("ERROR ID:%s ANO:%d OK:%b CMM:%s", result.getTestID(),
								res.getActionNo(), res.isOK(), res.getComment());
						warnList.add(errmsg);
						result.setEndtype(EndType.WARNING);
					}
				}

				// テスト結果設定
				excel.setResult(result, scenario.getReverseIndex());
				switch (result.getEndtype()) {
				case ERROR:
					errorCnt++;
					log.info("TestNo:{} ID:{} エラー終了!", senarioCnt, result.getTestID());
					break;
				case WARNING:
					warrningCnt++;
					log.info("TestNo:{} ID:{} 警告終了", senarioCnt, result.getTestID());
					break;
				case OK:
					log.info("TestNo:{} ID:{} 正常終了", senarioCnt, result.getTestID());
					break;
				default:
					log.info("TestNo:{} ID:{} ENDTYPE:{} 未知結果終了!", senarioCnt, result.getTestID(), result.getEndtype());
				}
			}

			log.info("テスト件数：");
			log.info(" 総数：{}　正常:{} 異常:{} 警告:{}", senarioCnt, senarioCnt - errorCnt - warrningCnt, errorCnt,
					warrningCnt);

			log.info("エラー情報：");
			for (String outTxt : errorList) {
				log.info(" {}", outTxt);
			}
			log.info("警告情報：");
			for (String outTxt : warnList) {
				log.info(" {}", outTxt);
			}

			webDriverCtrl.close();

		} finally {
			if (webDriverCtrl.driver() != null) {
				webDriverCtrl.driver().quit();
			}

		}
	}
}
