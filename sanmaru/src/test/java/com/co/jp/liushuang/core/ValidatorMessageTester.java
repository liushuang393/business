package com.co.jp.liushuang.core;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

//import me.gacl.service.UserServiceI;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidatorMessageTester {


	@Before
	public void before() {
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "spring-mvc.xml", "spring-mybatis.xml" });
		ac.getBeanDefinitionNames();
	}

	@Test
	public void test1() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Entity entity = new Entity();
		entity.setAge(12);
		entity.setName("admin");
		Set<ConstraintViolation<Entity>> constraintViolations = validator.validate(entity);
		for (ConstraintViolation<Entity> constraintViolation : constraintViolations) {
			System.out.println("对象属性:" + constraintViolation.getPropertyPath());
			System.out.println("国际化key:" + constraintViolation.getMessageTemplate());
			System.out.println("错误信息:" + constraintViolation.getMessage());
		}
	}

}