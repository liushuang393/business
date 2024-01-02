package com.co.jp.liushuang.core;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.co.jp.liushuang.controller.form.UserLoginForm;
public class Tv {
    public static void main(String[] args) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Entity entity = new Entity();
        entity.setAge(12);
        entity.setName("");
        Set<ConstraintViolation<Entity>> constraintViolations = validator.validate(entity);
        for (ConstraintViolation<Entity> constraintViolation : constraintViolations) {
            System.out.println("对象属性:"+constraintViolation.getPropertyPath());
            System.out.println("国际化key:"+constraintViolation.getMessageTemplate());
            System.out.println("错误信息:"+constraintViolation.getMessage());
        }

        UserLoginForm entity2 = new UserLoginForm();
        entity2.setPassword("");
        entity2.setUserId("");
        Set<ConstraintViolation<UserLoginForm>> constraintViolations2 = validator.validate(entity2);
        for (ConstraintViolation<UserLoginForm> constraintViolation : constraintViolations2) {
            System.out.println("对象属性:"+constraintViolation.getPropertyPath());
            System.out.println("国际化key:"+constraintViolation.getMessageTemplate());
            System.out.println("错误信息:"+constraintViolation.getMessage());
        }

    }
}
