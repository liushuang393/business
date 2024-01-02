package com.liushuang.test.tool.side;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SideDto {

	/** テストプロジェクトID */
	private String id;
	/** テストプロジェクトVer. */
	private String version;
	/** テストプロジェクト名 */
	private String name;
	/** テストURL */
	private String url;
	/** シナリオ配列 */
	private SideTestDto[] tests;

	private Suite[] suites;
	private String[] urls;
	private String[] plugins;

}
