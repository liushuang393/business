package com.liushuang.test.tool.excel;

import org.apache.poi.ss.usermodel.Cell;

public class ExcelCellException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ExcelCellException(Exception exp) {
		super(exp);
	}

	public ExcelCellException(String message) {
		super(message);
	}

	public ExcelCellException(String message, Cell cell) {
		super(String.format("%s[row=%d,col=%d]", message,
				cell.getRowIndex() + 1, cell.getColumnIndex() + 1));
	}
}
