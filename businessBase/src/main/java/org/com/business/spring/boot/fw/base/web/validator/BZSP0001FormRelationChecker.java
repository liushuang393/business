package org.com.business.spring.boot.fw.base.web.validator;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.com.business.spring.boot.common.BZConstants;
import org.com.business.spring.boot.fw.base.common.BZRelationCheckUtils;
import org.com.business.spring.boot.fw.base.common.form.BZSP0001Form;
import org.com.business.spring.boot.fw.core.i18n.LocaleMessageSourceService;
import org.com.business.spring.boot.fw.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * @author
 *
 */
@Component
public class BZSP0001FormRelationChecker {

	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	@Autowired
	BZRelationCheckUtils checkUtils;

	@Resource
	private UserMapper userMapper;

	public void check(BZSP0001Form form, Errors errors) {

		// ユーザーIDが存在チェック
		String firstItemName = "userId";
		String messageId = "EBASW0003";

		if (userMapper.selectByPrimaryKey(form.getUserId()) != null) {
			checkUtils.setErrorMessage(firstItemName, messageId,
					new String[] {
							localeMessageSourceService.getMessage(BZConstants.BZ_VALIDLABEL + "." + firstItemName) },
					errors);
		}

	}
}
