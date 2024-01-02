package com.liushuang.test.tool.excel;

import com.liushuang.test.tool.result.Scenario;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ScenarioWithIndex {

	private Scenario senario;
	private ReverseIndex reverseIndex;

	public ScenarioWithIndex(Scenario actionList, ReverseIndex reverseIndex) {
		senario = actionList;
		this.reverseIndex = reverseIndex;
	}

}
