package org.com.business.spring.boot.fw.base.web.validator;

import org.com.business.spring.boot.fw.base.common.form.BZSP0001Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BZSP0001FormValidator implements Validator {

   @Autowired
     BZSP0001FormItemChecker itemCheck;

   @Autowired
    BZSP0001FormRelationChecker relationCheck;

    @Override
    public boolean supports(Class<?> paramClass) {
        return BZSP0001Form.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        BZSP0001Form form = (BZSP0001Form) obj;
        itemCheck.check(form, errors);
        if (errors.hasErrors()) {
            return;
        }
        relationCheck.check(form, errors);
    }

}
