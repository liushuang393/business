package com.liushuang.test.tool.result;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ActionResult {

	private int actionNo;
	private boolean isOK;
	private String comment;
	private Date date;

	public void setResult(int actNo, boolean isOK, String comment) {
		actionNo = actNo;
		this.isOK = isOK;
		this.comment = comment;
		this.date = new Date();
	}

}
