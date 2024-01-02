package com.liushuang.test.tool.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Action {

	private Scenario parent;
	private int actionNo;
	private String commandName;
	private String option;
	private String target;
	private String value;
	private String targets;

	public Action(Scenario parent, int actionNo, String commandName,
			String option, String target, String value, String targets) {
		this.parent = parent;
		this.actionNo = actionNo;
		this.commandName = commandName;
		this.option = option;
		this.target = target;
		this.value = value;
		this.targets = targets;

	}

}
