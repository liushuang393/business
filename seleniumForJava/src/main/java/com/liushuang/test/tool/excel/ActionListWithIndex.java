package com.liushuang.test.tool.excel;

import com.liushuang.test.tool.result.Scenario;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ActionListWithIndex {

	private Scenario action;
	private ReverseIndex reverseIndex;

	public ActionListWithIndex(Scenario action, ReverseIndex reverseIndex) {
		this.action = action;
		this.reverseIndex = reverseIndex;

	}
}
