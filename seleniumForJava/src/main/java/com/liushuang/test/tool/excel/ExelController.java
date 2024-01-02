package com.liushuang.test.tool.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.liushuang.test.tool.result.ScenarioResult;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @Description:Excel操作
 * @author liushuang
 *
 * @version 1.0
 */
@Slf4j
public class ExelController {

	/** テストファイル拡張子 */
	private static final String[] EXT_FILENAME = { ".xls", ".xlsx", ".xlsm" };

	/** Excelファイル操作 */
	private BookController book;

	/** Excelファイルリスト */
	private List<File> bookNames;

	/** Excelファイルインデックス */
	private int bookIndex = 0;

	/**
	 *
	 * @Description:テストファイル初期化、命令読み込み
	 * @throws Exception
	 */
	public void init(String filePath) throws Exception {

		bookNames = new ArrayList<>();

		log.info("Loading {} floder.", filePath);
		File dir = new File(filePath);
		File[] files = dir.listFiles();
		Arrays.sort(files, (a, b) -> a.getName().compareTo(b.getName()));
		for (File f : files) {
			for (String ext : EXT_FILENAME) {
				if (f.getName().endsWith(ext) && !f.getName().startsWith("~$")) {
					bookNames.add(f);
					log.info(" テスト対象ファイル：" + f.getPath());
				}
			}
		}
	}

	/**
	 *
	 * @Description:Actionリスト
	 * @return
	 * @throws Exception
	 */
	public ScenarioWithIndex nextActionList() throws Exception {
		// Actionリスト
		ScenarioWithIndex scenarioWithIndex = null;
		if (!"".equals(nextBook())) {
			// Actionリストを取得
			scenarioWithIndex = book.nextActionList();
		}
		return scenarioWithIndex;
	}

	/**
	 *
	 * @Description:次のBook設定(1Bookは１シート)
	 * @return ファイル名
	 * @throws Exception
	 */
	private String nextBook() throws Exception {
		// 次のファイルが存在しない場合
		if (bookNames.size() <= bookIndex) {
			return "";
		}

		// Book名取得
		book = new BookController();
		String fileName = bookNames.get(bookIndex).getPath();
		// Bookロード
		book.load(fileName);
		bookIndex++;

		return fileName;
	}

	/**
	 *
	 * @Description:テスト結果設定
	 * @param result
	 * @param reverseIndex
	 * @throws Exception
	 */
	public void setResult(ScenarioResult result, ReverseIndex reverseIndex) throws Exception {
		book.setResult(result, reverseIndex);
	}

	/**
	 *
	 * @Description:テスト結果クリア
	 * @throws Exception
	 */
	public void clearResult() throws Exception {
		for (File f : bookNames) {
			BookController bk = new BookController();
			bk.load(f.getPath());
			bk.clear();
		}
	}
}
