package org.com.business.spring.boot.fw.core.annotation;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.com.business.spring.boot.fw.core.validator.NotEmptyValidator;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, METHOD, FIELD,ANNOTATION_TYPE, CONSTRUCTOR,PARAMETER })
@Constraint(validatedBy = { NotEmptyValidator.class })
public @interface NotEmpty {

	String field() default "";

	String message() default "{valid.empty.error}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}