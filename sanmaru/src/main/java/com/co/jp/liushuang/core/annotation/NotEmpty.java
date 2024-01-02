package com.co.jp.liushuang.core.annotation;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.co.jp.liushuang.core.validator.NotEmptyValidator;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD,
		java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.CONSTRUCTOR,
		java.lang.annotation.ElementType.PARAMETER })
@Constraint(validatedBy = { NotEmptyValidator.class })
public @interface NotEmpty {

	String field() default "";

	String message() default "{empty.error}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}