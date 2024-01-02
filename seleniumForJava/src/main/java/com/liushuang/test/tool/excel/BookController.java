package com.liushuang.test.tool.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.liushuang.test.tool.result.Action;
import com.liushuang.test.tool.result.ActionResult;
import com.liushuang.test.tool.result.Scenario;
import com.liushuang.test.tool.result.ScenarioResult;
import com.liushuang.test.tool.webdriver.WebDriverType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookController {

	/** シート名 */
	private static final String SHEET_NAME = "checklist";
	/** 最大行数 */
	private static final int ROW_MAX = 10000;

	/** No. */
	private static final int COL_NO = 0;
	/** マーク列No. */
	private static final int COL_MARK = 1;
	/** コマンド列No. */
	private static final int COL_COMMAND = 2;
	/** オプション列No. */
	private static final int COL_OPTIN = 3;
	/** ターゲット列No. */
	private static final int COL_TARGET = 4;
	/** 項目説明列No. */
	private static final int COL_EXPLAIN = 5;
	/** テスト値列No. */
	private static final int COL_CASE_START = 6;
	/** テスト結果列No. */
	private static final int COL_RESULT_DELTA_ISOK = 7;
	/** テスト日付列No. */
	private static final int COL_RESULT_DELTA_DATE = 8;
	/** テスト結果コメント列No. */
	private static final int COL_RESULT_DELTA_COMMENT = 9;
	/** ターゲットリスト列No.列No. */
	private static final int COL_TARGETS = 11;
	/** テスト結果列数 */
	private static final int COL_RESULT_CNT = 3;
	/** ブラウザ種類列No. */
	private static final int COL_BROWSER = 6;

	// ファイル名前
	private String fileName;
	// シート名前
	private Sheet sheet;
	// テスト行
	private Row testNoRow;
	// ブラウザ行
	private Row browserRow;
	// 終了行
	private Row endRow;

	private ActionRowList actionRowList;
	private ScenarioWithIndex request;

	/**
	 *
	 * @Description:BooKロード（シートAction読み込み）
	 * @param fileName
	 * @throws Exception
	 */
	public void load(String fileName) throws Exception {

		// 初期設定
		testNoRow = null;
		actionRowList = new ActionRowList();
		this.fileName = fileName;

//		// ファイルが開くか
//		try {
//			org.apache.commons.io.FileUtils.touch(new File(fileName));
//
//		} catch (IOException e) {
//			throw e;
//		}

		log.info("{}ファイルの読み込み中，ファイルが開いている場合は、閉じてください。", fileName);

		Workbook workbook = null;
		try (FileInputStream steam = new FileInputStream(fileName);) {
			workbook = WorkbookFactory.create(steam);
			sheet = workbook.getSheet(SHEET_NAME);

			int endRowIndex = -1;

			for (int i = 0; i < ROW_MAX && endRowIndex == -1; i++) {

				Row row = sheet.getRow(i);
				if (row == null) {
					break;
				}

				Cell markCell = row.getCell(COL_MARK);
				String markStr = getCellText(markCell);
				switch (markStr) {
				case "#":
					testNoRow = row;
					break;
				case "B":
					browserRow = row;
					break;
				case "A":
					Cell cmdCell = row.getCell(COL_COMMAND);
					String cmd = getCellText(cmdCell);
					if (isEmpty(cmd)) {
						workbook.close();
						throw new ExcelCellException("コマンド列値を入力してください！", cmdCell);
					}

					String otp = getCellText(row.getCell(COL_OPTIN));
					String tgt = getCellText(row.getCell(COL_TARGET));
					String tgts = getCellText(row.getCell(COL_TARGETS));

					ActionRow actRow = new ActionRow(cmd, otp, tgt, i, tgts);
					actionRowList.add(actRow);
					break;
				case "E":
					endRow = row;
					endRowIndex = row.getRowNum();
					break;
				case "C":
					break;
				default:
					workbook.close();
					throw new ExcelCellException(String.format("未知のマークです。[mark=%s]", markStr));
				}

			}

			if (testNoRow == null) {
				throw new ExcelCellException("\n0列目にNo.マーク「#」を指定してください。");
			}

			if (endRowIndex == -1) {
				throw new ExcelCellException("\n0列目に終了マーク「E」を指定してください。");
			}
		}
	}

	/**
	 *
	 * @Description:
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public ScenarioWithIndex nextActionList() throws Exception {
		// ブラウザ設定チェック
		check();

		Cell testNoCell = testNoRow.getCell(COL_CASE_START);
		String testNo = getCellText(testNoCell);
		log.trace("テストケースNo.[{}]", testNo);
		String fnameonly = fileName.substring(fileName.lastIndexOf('\\') + 1, fileName.lastIndexOf('.'));
		String testID = String.format("%s-%s", fnameonly, testNo);
		Scenario actionList = new Scenario(testID);
		ReverseIndex reverseIndex = new ReverseIndex();

		for (ActionRow actRow : actionRowList) {
			int rowId = actRow.getRowIndex();
			Row row = sheet.getRow(rowId);
			Cell valCell = row.getCell(COL_CASE_START);
			String val = getCellText(valCell);
			if (!isEmpty(val)) {
				log.trace("ID:{} ROW:{} CMD:{} OPT:{} TAG:{} VAL:{}", actionList.getTestID(), rowId,
						actRow.getCommandName(), actRow.getOption(), actRow.getTarget(), val);

				int actIdx = actionList.size();
				String cmdName = actRow.getCommandName();
				if ("INPUT".equals(cmdName) || "CLICK".equals(cmdName) || "VERIFY".equals(cmdName)
						|| "SELECT".equals(cmdName)) {
					if (actRow.getOption().toUpperCase().indexOf("SELECTOR") == -1) {
						actRow.setOption("Selector=ID," + actRow.getOption());
					}
				}

				Action act = new Action(actionList, actIdx, actRow.getCommandName(), actRow.getOption(),
						actRow.getTarget(), val, actRow.getTargets());
				actionList.add(act);
				reverseIndex.put(actIdx, rowId);
			} else {
				log.trace("ID:{} ROW:{} CMD:{} OPT:{} TAG:{}>>> SKIP", actionList.getTestID(), rowId,
						actRow.getCommandName(), actRow.getOption(), actRow.getTarget());
			}
		}
		request = new ScenarioWithIndex(actionList, reverseIndex);

		Cell browserCell = browserRow.getCell(COL_BROWSER);
		String browser = getCellText(browserCell);

		WebDriverType wdt = WebDriverType.valueOf(browser);
		request.getSenario().setBrowser(wdt);

		return request;

	}

	/**
	 * ブラウザ設定チェック
	 *
	 * @Description:
	 * @throws Exception
	 */
	private void check() throws Exception {
		if (browserRow != null) {
			Cell browserCell = browserRow.getCell(COL_BROWSER);
			String browser = getCellText(browserCell);
			if (isEmpty(browser)) {
				request = null;
				throw new Exception("マーク列の「B」行はブラウザ設定してください。");
			}
		}
	}

	/**
	 *
	 * @Description:
	 * @param resultlist
	 * @param reverseIndex
	 * @param fileName
	 * @throws Exception
	 */
	public void setResult(ScenarioResult resultlist, ReverseIndex reverseIndex) throws Exception {

		for (ActionResult res : resultlist) {
			int rowIdx = reverseIndex.get(res.getActionNo());
			Row row = sheet.getRow(rowIdx);
			row.getCell(COL_RESULT_DELTA_ISOK).setCellValue(Boolean.toString(res.isOK()));
			row.getCell(COL_RESULT_DELTA_DATE).setCellValue(res.getDate());
			row.getCell(COL_RESULT_DELTA_COMMENT).setCellValue(res.getComment());
		}

		try (FileOutputStream fo = new FileOutputStream(fileName);) {
			sheet.getWorkbook().write(fo);
		}
	}

	/**
	 *
	 * @Description:テスト結果クリア
	 * @throws Exception
	 */
	public void clear() throws Exception {

		for (int i = browserRow.getRowNum() + 1; i < endRow.getRowNum(); i++) {
			Row row = sheet.getRow(i);
			for (int j = COL_RESULT_DELTA_ISOK; j < COL_RESULT_DELTA_ISOK + COL_RESULT_CNT; j++) {
				row.getCell(j).setCellValue("");
			}
		}

		try (FileOutputStream fo = new FileOutputStream(fileName);) {
			sheet.getWorkbook().write(fo);
		}
	}

	/**
	 *
	 * @Description:セル値取得
	 * @param cell
	 * @return
	 * @throws Exception
	 */
	private static String getCellText(Cell cell) throws Exception {
		String str = "";
		if (cell != null) {
			CellType cellType = cell.getCellType();
			switch (cellType) {
			case BLANK:
				break;
			case STRING:
				str = cell.getStringCellValue();
				if (str == null) {
					str = "";
				} else {
					str = str.trim();
				}
			default:
				break;
			}
		}
		return str;
	}

	/**
	 *
	 * @Description:
	 * @param str
	 * @return
	 */
	private static boolean isEmpty(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		return false;
	}

	/**
	 * チェックリスト作成（シート）
	 * 
	 * @param templeFilePath
	 * @param excelBookName
	 * @param excelSheet
	 * @param starRowIdx
	 * @param string
	 * @throws IOException
	 */
	public void setChkListSheet(String templeFilePath, String excelBookName, ExcelCheckListSheet excelSheet,
			int starRowIdx, String cleanFlg) throws IOException {

		log.info("{}ファイルの作成開始。", excelBookName);

		String tempName = templeFilePath + "\\" + "CT_Template.xlsx";
		Path fromFile = Paths.get(tempName);
		Path excelBook = Paths.get(excelBookName);

		if (!isEmpty(cleanFlg) && "TRUE".equals(cleanFlg.toUpperCase())) {
			java.nio.file.Files.copy(fromFile, excelBook, StandardCopyOption.REPLACE_EXISTING);
		} else {
			java.nio.file.Files.copy(fromFile, excelBook);
		}

		Workbook workbook = null;
		Sheet temSheet = null;
		try (FileInputStream steam = new FileInputStream(excelBookName);) {
			workbook = WorkbookFactory.create(steam);

			// シートコピー TODO コピー先名：excelSheet.getName()
			temSheet = workbook.getSheet(SHEET_NAME);

			for (ExcelCheckListRow inRow : excelSheet.getRows()) {
				Row row = temSheet.getRow(starRowIdx++);

				row.getCell(COL_NO).setCellValue(starRowIdx - 5);
				row.getCell(COL_MARK).setCellValue(inRow.getAction());
				row.getCell(COL_COMMAND).setCellValue(inRow.getCommand());
				row.getCell(COL_OPTIN).setCellValue(inRow.getOption());
				row.getCell(COL_TARGET).setCellValue(inRow.getTarget());
				row.getCell(COL_EXPLAIN).setCellValue(inRow.getDescription());
				row.getCell(COL_CASE_START).setCellValue(inRow.getValue());
				row.getCell(COL_TARGETS).setCellValue(inRow.getTargets());
			}
		}

		try (FileOutputStream fo = new FileOutputStream(excelBookName);) {
			temSheet.getWorkbook().write(fo);
		}

		log.info("{}ファイルの作成終了。", excelBookName);

	}
}
