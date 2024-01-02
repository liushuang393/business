package com.liushuang.test.tool.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ActionRow {

	private String commandName;
	private String option;
	private String target;
	private int rowIndex;
	private String targets;

	public ActionRow(String commandName, String option, String target,
			int rowIndex, String targets) {
		this.commandName = commandName;
		this.option = option;
		this.target = target;
		this.rowIndex = rowIndex;
		this.targets = targets;
	}

}
