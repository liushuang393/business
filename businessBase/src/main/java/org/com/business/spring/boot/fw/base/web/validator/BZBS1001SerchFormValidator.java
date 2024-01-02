package org.com.business.spring.boot.fw.base.web.validator;

import org.com.business.spring.boot.fw.base.common.form.BZBS1001SerchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BZBS1001SerchFormValidator implements Validator {

   @Autowired
     BZBS1001SerchFormItemChecker itemCheck;

   @Autowired
    BZBS1001SerchFormRelationChecker relationCheck;

    @Override
    public boolean supports(Class<?> paramClass) {
        return BZBS1001SerchForm.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        BZBS1001SerchForm form = (BZBS1001SerchForm) obj;
        itemCheck.check(form, errors);
        if (errors.hasErrors()) {
            return;
        }
        relationCheck.check(form, errors);
    }

}
