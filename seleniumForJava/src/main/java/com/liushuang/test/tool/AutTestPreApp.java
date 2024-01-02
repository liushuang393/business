package com.liushuang.test.tool;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liushuang.test.tool.excel.BookController;
import com.liushuang.test.tool.excel.ExcelCheckListRow;
import com.liushuang.test.tool.excel.ExcelCheckListSheet;
import com.liushuang.test.tool.side.SideCommand;
import com.liushuang.test.tool.side.SideDto;
import com.liushuang.test.tool.side.SideTestDto;
import com.liushuang.test.tool.utils.Utils;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @Description: Side(JSON)からシナリオ作成クラス
 * @author liushuang
 *
 * @version 1.0
 */
@Slf4j
public class AutTestPreApp {
	private static final String TEST_URL = "TEST_URL";
	// private static final ResourceBundle config =
	// ResourceBundle.getBundle("config", new ResourceBundleUtf8Control());
	/** Sideのコマンド */
	private static final String[] SIDE_COMOND = { "open", "click", "type", "SHOT", "select", "selectWindow", "SAVE",
			"SLEEP", "selectFrame", "assertConfirmation", "webdriverChooseOkOnVisibleConfirmation", "verifyText",
			"verifyTitle", "storeTitle", "storeValue", "runScript", "sendKeys", "close", "mouseOver", "mouseOut",
			"doubleClick", "storeWindowHandle", "assertAlert", "webdriverChooseCancelOnVisibleConfirmation" };
	/** Excelシナリオ用コマンド */
	private static final String[] EXCEL_COMOND = { "OPEN", "CLICK", "INPUT", "SHOT", "SELECT", "WINDOWSWITCH", "SAVE",
			"SLEEP", "SELECTFRAME", "ASSERTCONFIRMATION", "WEBDRIVERCHOOSEOKONVISIBLECONFIRMATION", "VERIFYTEXT",
			"VERIFYTITLE", "GETTITLE", "GETVALUE", "RUNSCRIPT", "SENDKEYS", "CLOSE", "MOUSEOVER", "MOUSEOUT",
			"DOUBLECLICK", "STOREWINDOWHANDLE", "ASSERTALERT", "WEBDRIVERCHOOSECANCELONVISIBLECONFIRMATION" };

	/** 対応不要コマンド */
	private static final String[] NO_SUPPORT_COMOND = { "setWindowSize", "chooseOkOnNextConfirmation", "mouseDownAt",
			"mouseMoveAt", "mouseUpAt", "chooseCancelOnNextConfirmation" };
	private static final String INPUT_PATH = "INPUT_PATH";

	public static void main(String[] args) {

		log.info("\n\n Side(JSON)からシナリオ作成 Start.");

		log.info("configを読み込み");
		ResourceBundle config = Utils.loadPropertieFile();
		String inputPath = config.getString(INPUT_PATH);
		String migrationFlg = config.getString("MIGRATION_FLG");
		Map<String, String> resourceMap = new HashMap<>();
		try {
			resourceMap.put(Utils.TEST_URL, config.getString(Utils.TEST_URL));
		} catch (Exception e) {
			resourceMap.put(Utils.TEST_URL, "");
			log.info("PropertyResourceBundle, key TEST_URL  Can't set ");
		}

		Utils.resourceMapThreadLocal.set(resourceMap);

		String fullInputPath = new File("").getAbsolutePath() + inputPath;
		String str = "正常";

		// Side用コマンドとExcel用コマンドマップ作成
		Map<String, String> comondMap = new HashMap<>();
		for (int i = 0; i < SIDE_COMOND.length; i++) {
			comondMap.put(SIDE_COMOND[i], EXCEL_COMOND[i]);
		}

		// Side用コマンドとExcel用コマンドマップ作成
		Map<String, String> noComondMap = new HashMap<>();
		for (int i = 0; i < NO_SUPPORT_COMOND.length; i++) {
			noComondMap.put(NO_SUPPORT_COMOND[i], "「" + NO_SUPPORT_COMOND[i] + "」が実現必要なし");
		}

		String cleanFlg = config.getString("CLEAR_CHECK_LIST_FILE_FLG");
		String excelpath = fullInputPath + "\\" + migrationFlg + "\\";
		if (!Utils.isEmpt(cleanFlg) && "TRUE".equals(cleanFlg.toUpperCase())) {
			Utils.clearFolder(excelpath, true);
		}

		File newdir = new File(excelpath);
		if (!newdir.exists()) {
			newdir.mkdirs();
		}

		List<String> files = Utils.getSideFiles(fullInputPath + "/side");
		for (String fileName : files) {

			// =================Side（JSON）ファイルを取り込む====================
			try {
				File file = new File(fileName);
				ObjectMapper mapper = new ObjectMapper();
				SideDto sideDto = null;
				JsonNode jsonNode = mapper.readTree(file);
				sideDto = mapper.treeToValue(jsonNode, SideDto.class);
				if (sideDto != null) {
					log.debug("Side(JSON) " + sideDto.toString());

					String excelBookName = sideDto.getName();
					String url = sideDto.getUrl();

					List<ExcelCheckListSheet> sheetList = new ArrayList<>();

					// ====================SideからExcel用コマンドRowリスト変換(１ファイルbook、多シート変換)===========
					for (int i = 0; i < sideDto.getTests().length; i++) {
						SideTestDto sideTestDto = sideDto.getTests()[i];

						String sheetName = sideTestDto.getName();

						ExcelCheckListSheet sheet = new ExcelCheckListSheet();
						sheet.setName(sheetName);
						List<ExcelCheckListRow> rowList = getExcelChkListRows(sideTestDto.getCommands(), comondMap,
								noComondMap, url);
						rowList.add(new ExcelCheckListRow("E"));
						sheet.setRows(rowList);

						sheetList.add(sheet);
					}

					// ====================Excelに出力（シナリオ作成）====================

					// Excel操作
					for (ExcelCheckListSheet excelSheet : sheetList) {

						/** Excelファイル操作 */
						BookController book = new BookController();
						// Bookロード
						int starRowIdx = 9;
						String templetePath = fullInputPath + "\\Templete";
						book.setChkListSheet(templetePath, excelpath + excelBookName + ".xlsx", excelSheet, starRowIdx,
								cleanFlg);

					}
				}

			} catch (Exception e) {
				str = "異常";
				log.error("file Name :{}", fileName);
				log.error(e.getMessage());
				e.getStackTrace();
			}
		}
		log.info("Side(JSON)からシナリオ作成 " + str + "終了");
	}

	/**
	 * ExcelCheckListRowsを取得（一sheet）
	 *
	 * @param commands
	 * @param comondMap
	 * @param noComondMap
	 * @param url
	 * @return
	 */
	private static List<ExcelCheckListRow> getExcelChkListRows(SideCommand[] commands, Map<String, String> comondMap,
			Map<String, String> noComondMap, String url) {

		List<ExcelCheckListRow> rows = new ArrayList<>();

		for (SideCommand sideComd : commands) {
			rows.add(chgCommand(comondMap, noComondMap, sideComd, url));

		}
		return rows;
	}

	/**
	 *
	 * Side用コマンドをExcel用コマンド変換する。
	 *
	 * @param no
	 * @param comondMap
	 * @param noComondMap
	 * @param sideComd
	 * @param url
	 * @return Excel用コマンドRow
	 */
	private static ExcelCheckListRow chgCommand(Map<String, String> comondMap, Map<String, String> noComondMap,
			SideCommand sideComd, String url) {

		ExcelCheckListRow row = new ExcelCheckListRow();

		if (sideComd != null) {

			String action = "A";
			String commd = sideComd.getCommand();
			String sidetarget = sideComd.getTarget();
			String[][] sidetargets = sideComd.getTargets();
			String option = "";
			String description = Utils.isEmpt(sideComd.getComment()) ? sidetarget : sideComd.getComment();
			String value = Utils.isEmpt(sideComd.getValue()) ? "*" : sideComd.getValue();
			String windowHandleName = sideComd.getWindowHandleName();
			List<String> sidetargetsList = new ArrayList<>();

			if (!Utils.isEmpt(sidetarget)) {

				String[] sidetargetArry = sidetarget.split("=", 2);
				if (sidetargetArry.length == 2) {
					if ("selectWindow".equals(commd)) {
						option = "MODE=OPEN";

					} else {
						String taget1 = sidetargetArry[0];
						String tagiet2 = sidetargetArry[1];
						boolean findFlg = false;
						boolean findFlg1 = false;
						for (int i = 0; i < sidetargets.length; i++) {
							String[] seleArr = sidetargets[i];
							// targetsを抽出
							sidetargetsList.add(seleArr[0]);

							if ("css".equals(taget1) && tagiet2.contains("nth-child") && !findFlg) {
								if (seleArr.length == 2 && "xpath:innerText".equals(seleArr[1])) {
									sidetargetArry = seleArr[0].split("=", 2);
									findFlg = true;
								} else if (seleArr.length == 2 && "xpath:idRelative".equals(seleArr[1])) {
									sidetargetArry = seleArr[0].split("=", 2);
									findFlg1 = true;
								} else {
									if (!findFlg1) {
										sidetargetArry = seleArr[0].split("=", 2);
									}
								}
							}
						}
						option = "Selector=" + sidetargetArry[0];
					}

					sidetarget = sidetargetArry[1];
				}
			}

			if ("click".equals(commd)) {

			} else if ("type".equals(commd)) {
				description = "入力欄: " + description;

			} else if ("select".equals(commd)) {
				description = "セレクトリスト: " + description;
				String[] varValue = value.split("=");
				if (varValue.length == 2) {
					value = varValue[1];
				} else {
					value = "*";
				}

			} else if ("selectFrame".equals(commd)) {
				description = "フレーム選択 ";

			} else if ("open".equals(commd)) {
				String configUrl = Utils.resourceMapThreadLocal.get().get(TEST_URL);
				if (!Utils.isEmpt(configUrl)) {
					// action = "C";
					sidetarget = Utils.TEST_URL;
				} else {
					// action = "C";
					sidetarget = url + sidetarget;
				}

				description = "ブラウザ・画面を開く";

			} else {
				if (Utils.isEmpt(comondMap.get(commd))) {
					action = "C";
				}
			}
			// "windowHandleName": "win9215",
			if (!Utils.isEmpt(windowHandleName)) {
				value = windowHandleName;
			}

			String[] comms = getComond(commd, comondMap, noComondMap, description);
			row.setAction(action);
			row.setCommand(comms[0]);
			row.setOption(option);
			row.setTarget(sidetarget);
			row.setDescription(comms[1]);
			row.setValue(value);
			row.setTargets(StringUtils.join(sidetargetsList.toArray(), "::"));
		}
		return row;
	}

	/**
	 * コマンドと説明を取得
	 *
	 * @param commd
	 * @param comondMap
	 * @param noComondMap
	 * @param description
	 * @return
	 */

	private static String[] getComond(String commd, Map<String, String> comondMap, Map<String, String> noComondMap,
			String description) {

		String temCod = comondMap.get(commd);
		String[] coms = { temCod, description };

		if (Utils.isEmpt(temCod)) {
			String nnoComond = noComondMap.get(commd);
			if (Utils.isEmpt(nnoComond)) {
				coms[0] = commd;
				coms[1] = "シナリオ作成ツールのカスタマイズが必要";
			} else {
				coms[0] = commd;
				coms[1] = nnoComond;
			}
		}
		return coms;
	}

}
