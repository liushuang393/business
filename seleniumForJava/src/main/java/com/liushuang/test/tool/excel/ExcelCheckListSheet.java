package com.liushuang.test.tool.excel;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExcelCheckListSheet {

	/** シート名 */
	private String name;

	/***/
	private List<ExcelCheckListRow> rows;

}
