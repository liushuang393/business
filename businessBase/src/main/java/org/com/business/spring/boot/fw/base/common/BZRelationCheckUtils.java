/**
 *
 */
package org.com.business.spring.boot.fw.base.common;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.alibaba.druid.util.StringUtils;

/**
 * @author liushuang
 *
 */
@Component
public class BZRelationCheckUtils {

	public void relationEmpty(List<String> checkValueNames, String messageId, Object[] errorArgs, Errors errors) {

		if (errors.hasErrors()) {
			return;
		}

		if (checkValueNames == null || checkValueNames.size() <= 0) {
			return;
		}

		for (String checkValueName : checkValueNames) {

			Object item1 = errors.getFieldValue(checkValueName);
			String itemValue1 = item1 == null ? "" : item1.toString();

			if (StringUtils.isEmpty(itemValue1)) {
				setErrorMessage(checkValueNames.get(0), messageId, errorArgs, null);

			}
		}

	}

	/**
	 * エラーメッセージを設定
	 *
	 * @param firstItemName 関連チェック用の項目名
	 * @param messageId     メッセージID
	 * @param errorArgs     埋め字
	 * @param errors
	 */
	public void setErrorMessage(String firstItemName, String messageId, Object[] errorArgs, Errors errors) {

		if (!StringUtils.isEmpty(firstItemName) && !StringUtils.isEmpty(messageId) && errors != null) {
			errors.rejectValue(firstItemName, messageId, errorArgs, null);

		}
	}
}
