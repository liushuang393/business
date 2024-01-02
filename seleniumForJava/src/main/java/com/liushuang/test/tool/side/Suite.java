package com.liushuang.test.tool.side;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Suite {
	
	private String id;
	private String name;
	private boolean persistSession;
	private boolean parallel;
	private int timeout;
	private String[] tests;

}
