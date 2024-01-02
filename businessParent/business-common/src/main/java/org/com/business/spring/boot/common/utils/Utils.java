package org.com.business.spring.boot.common.utils;

import org.com.business.spring.boot.common.SysConstant;

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

	/**
	 *左パディング
	 * @param instr
	 * @param ch
	 * @param len
	 * @return
	 */
    public static String appStr(String instr, char ch, int len) {
        StringBuilder strbu = new StringBuilder();
        int lenth = len;
        while (lenth > 0) {
            strbu.append(ch);
            lenth--;
        }

        if (instr == null) {
            return strbu.toString();
        } else {
            if (instr.length() >= len) {
                return instr;
            }
            strbu.append(instr);
            return strbu.toString().substring(instr.length());
        }
    }
}