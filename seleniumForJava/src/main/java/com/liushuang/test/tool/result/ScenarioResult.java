package com.liushuang.test.tool.result;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ScenarioResult extends ArrayList<ActionResult> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String testID;
	private EndType endtype;

	public ScenarioResult(String testId) {
		testID = testId;
		endtype = EndType.OK;
	}
}
