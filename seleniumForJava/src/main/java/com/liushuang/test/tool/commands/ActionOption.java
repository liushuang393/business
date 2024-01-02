package com.liushuang.test.tool.commands;

import java.util.HashMap;

public class ActionOption extends HashMap<String, String> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static ActionOption parseOption(String opt) {
		if (opt == null) {
			return null;
		}

		ActionOption res = new ActionOption();
		String[] optionList = opt.trim().split(",");
		for (String parseOpt : optionList) {
			String[] nameValue = parseOpt.split("=", 2);
			if (nameValue.length == 2) {
				res.put(nameValue[0].toUpperCase(), nameValue[1]);

			} else {
				res.put(nameValue[0].toUpperCase(), "");
			}
		}
		return res;
	}

	public Boolean getBoolean(String opt, StringBuilder comment) {
		if (this.containsKey(opt) == false) {
			return Boolean.FALSE;
		}

		String str = this.get(opt).trim().toLowerCase();
		Boolean res = Boolean.FALSE;
		if ("true".equals(str) || "1".equals(str)) {
			res = Boolean.TRUE;
		} else if ("false".equals(str) || "0".equals(str)) {
			res = Boolean.FALSE;
		} else {
			comment.append(String.format("Verifyオプションの値が正しくない", this.get(opt)));
		}
		return res;
	}
}
