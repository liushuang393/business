package com.liushuang.test.tool.side;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SideTestDto {
	/** シナリオID */
	private String id;
	/** シナリオ名 */
	private String name;
	/**コマンド配列*/
	private SideCommand[] commands;

}
