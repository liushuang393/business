package org.com.business.spring.boot.fw.base.web.validator;

import org.com.business.spring.boot.fw.base.common.form.BZBS5001Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BZBS5001FormValidator implements Validator {

   @Autowired
     BZBS5001FormItemChecker itemCheck;

   @Autowired
    BZBS5001FormRelationChecker relationCheck;

    @Override
    public boolean supports(Class<?> paramClass) {
        return BZBS5001Form.class.isAssignableFrom(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        BZBS5001Form form = (BZBS5001Form) obj;
        itemCheck.check(form, errors);
        if (errors.hasErrors()) {
            return;
        }
        relationCheck.check(form, errors);
    }

}
