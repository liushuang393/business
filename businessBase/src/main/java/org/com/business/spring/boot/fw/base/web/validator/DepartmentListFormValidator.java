package org.com.business.spring.boot.fw.base.web.validator;

import org.com.business.spring.boot.fw.base.common.form.DepartmentListForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DepartmentListFormValidator implements Validator {

   @Autowired
     DepartmentListFormItemChecker itemCheck;

   @Autowired
    DepartmentListFormRelationChecker relationCheck;

    @Override
    public boolean supports(Class<?> paramClass) {
        return DepartmentListForm.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        DepartmentListForm form = (DepartmentListForm) obj;
        itemCheck.check(form, errors);
        if (errors.hasErrors()) {
            return;
        }
        relationCheck.check(form, errors);
    }

}
