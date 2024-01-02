package com.co.jp.liushuang.utils;

import com.co.jp.liushuang.common.SysConstant;

/**
 * Utils クラス
 */
public class Utils {

	/**
	 * ブランクを判断する
	 *
	 * @param str
	 *
	 * @return true：ブランク； false：ブランクではない
	 */
	public static boolean isNull(String str) {
		if (str != null && !SysConstant.BLANK.equals(str.trim())) {
			return false;
		} else {
			return true;
		}
	}
}