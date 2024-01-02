package com.liushuang.test.tool.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AppState {

	private RecentDestinations recentDestinations;
	private String selectedDestinationId;
	private int version;
	private String pageSize;
}
