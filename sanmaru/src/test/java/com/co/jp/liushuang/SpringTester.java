package com.co.jp.liushuang;

import java.util.List;
import java.util.UUID;

//import me.gacl.service.UserServiceI;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;

import com.co.jp.liushuang.persistence.entity.User;
import com.co.jp.liushuang.service.common.UserService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpringTester {

	private com.co.jp.liushuang.service.common.UserService userService;

	@Before
	public void before() {
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "spring-mvc.xml", "spring-mybatis.xml" });
		ac.getBeanDefinitionNames();
		userService = (UserService) ac.getBean("userService");
	}

	@Test
	@Rollback(true)
	public void test1AddUser() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		User user = new User();
		user.setUserId("1234567890");
		user.setUserName("劉双");

		user.setPassword("123");
		userService.addUser(user);
	}

	@Test
	@Rollback(true)
	public void test2UpdateUser() {
		User user = new User();
		user.setUserId("lius393");
		user.setUserName("劉双1");
		user.setPassword("123");
		userService.updateUser(user);
	}

	@Test
	@Rollback(true)
	public void test3DelUser() {
		userService.deleteUser("1234567890");
	}

	@Test
	public void testGetUser() {
		User user1 = userService.getUserById("lius393");
		if (user1 == null) {
			System.out.println("DB data: NULL ");
		} else {
			System.out.println("DB data:" + user1.toString());
		}

	}

	@Test
	public void test4GetUserList() {

		List<User> userList = userService.getUserList(new User());

		for (User user : userList) {
			System.out.println("DB data:" + user.toString());
		}

	}
}