package com.co.jp.liushuang;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.co.jp.liushuang.dto.User1;
import com.co.jp.liushuang.dto.User2;
import com.co.jp.liushuang.persistence.UserMapper;
import com.co.jp.liushuang.persistence.entity.User;

public class MybatisTester {

	@Test
	public void testSpring() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring-mybatis.xml"});

		context.getBeanDefinitionNames();
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");

		User user = new User();
		user.setUserId("liushuang");
		user.setUserName("劉双");
		user.setPassword("123");

		if (userMapper.selectByPrimaryKey("lius393") != null) {
			userMapper.updateByPrimaryKey(user);
		} else {
			userMapper.insert(user);
		}

		User user1 = userMapper.selectByPrimaryKey("lius393");

		Map<String,Object> map  = new HashMap<String,Object>();
		User user00 = new User();
		User1 user11 = new User1();
		User2 user22 = new User2();
		user00.setUserId("lius393");
		user11.setUserName(null);
		user22.setPassword("123");
		map.put("user0", user00);
		map.put("user1", user11);
		map.put("user2", user22);
		List<User> userList = userMapper.selectListByMap(map);
		if (userList != null) {
		    System.out.println("DB List size:" + userList.size());
		} else {
		    System.out.println("DB List size: 0");
		}



		System.out.println("DB data:" + user1.getUserName());

	}
}