package com.co.jp.liushuang;

import java.util.List;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import com.co.jp.liushuang.persistence.entity.User;
import com.co.jp.liushuang.utils.BaseJunit4Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpringMyBatisTestBySpringTestFramework extends BaseJunit4Test {

	// 注入userService
	@Autowired
	private com.co.jp.liushuang.service.common.UserService userService;

	@Test
	//@Rollback(true)
	@Commit()
	public void test1AddUser() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		User user = new User();
		user.setUserId("liushuang");
		user.setUserName("劉双");
		user.setPassword("123");
		user.setCreateuserid("systemId");
		user.setUpdateuserid("systemId");
		userService.addUser(user);
	}

	@Test
	@Rollback(true)
	public void test2UpdateUser() {
		User user = new User();
		user.setUserId("liushuang");
		user.setUserName("劉双2");

		user.setPassword("123");

		userService.updateUser(user);
	}

	@Test
	@Rollback(true)
	public void test3DelUser() {
		userService.deleteUser("0987654321");
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
