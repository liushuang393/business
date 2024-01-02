package com.co.jp.liushuang.core.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.co.jp.liushuang.core.annotation.NotEmpty;

public class NotEmptyValidator implements ConstraintValidator<NotEmpty, Object> {

	private String field;
	private String message;

	@Override
	public void initialize(NotEmpty annotation) {

		this.field = annotation.field();
		this.message = annotation.message();
	}

	@Override
	public boolean isValid(Object str, ConstraintValidatorContext constraintValidatorContext) {
		if (str == null || "".equals(str.toString().trim())) {


//		constraintValidatorContext.disableDefaultConstraintViolation();
//			constraintValidatorContext.buildConstraintViolationWithTemplate("empty.error").addPropertyNode(field)
//					.addConstraintViolation();

			return false;
		}
		return true;
	}
}