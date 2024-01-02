package com.liushuang.test.tool.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExcelCheckListRow {

	public ExcelCheckListRow() {
	}

	public ExcelCheckListRow(String action) {
		this.action = action;
	}
	/** 動作 */
	private String action;
	/** コマンド */

	private String command;

	/** オプション */
	private String option;

	/** ターゲット */
	private String target;

	/** 項目説明 */
	private String description;

	/** 値 */
	private String value;

	/** ターゲットリスト */
	private String targets;

}
