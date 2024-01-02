package com.liushuang.test.tool.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RecentDestinations {

	private String id;
	private String origin;
	private String account;
}
