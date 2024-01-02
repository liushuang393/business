package com.liushuang.test.tool.side;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SideCommand {

	private String id;
	private String comment;
	private String command;
	private String target;
	private String[][] targets;
	private String value;
	
	private String opensWindow;
	private String windowHandleName;
	private String windowTimeout;
	

}
