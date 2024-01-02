package org.com.business.spring.boot.fw.base.web.validator;

import java.util.ArrayList;
import java.util.List;

import org.com.business.spring.boot.common.BZConstants;
import org.com.business.spring.boot.fw.base.common.BZRelationCheckUtils;
import org.com.business.spring.boot.fw.base.common.form.BZBS1001SerchForm;
import org.com.business.spring.boot.fw.core.i18n.LocaleMessageSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * @author
 *
 */
@Component
public class BZBS1001SerchFormRelationChecker {

    @Autowired
    LocaleMessageSourceService localeMessageSourceService;

    @Autowired
    BZRelationCheckUtils checkUtils;

    public void check(BZBS1001SerchForm form, Errors errors) {

//        List<String> checkValues = new ArrayList<>();
//        checkValues.add("departmentId");
//        checkValues.add("departmentName");
//
//        String messageId = "relation.valid.empty.error";
//
//        checkUtils.relationEmpty(checkValues, messageId,
//                new String[] { localeMessageSourceService.getMessage(BZConstants.BZ_VALIDLABEL + "." + "departmentId"),
//                        localeMessageSourceService.getMessage(BZConstants.BZ_VALIDLABEL + "." + "departmentName") },
//                errors);
//
    }
}

