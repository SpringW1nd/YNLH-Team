package com.YNLH.park.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.YNLH.park.dao.entity.User;
import com.YNLH.park.service.UserService;

public class UserTest extends BaseTestForSpring{
	@Autowired
	private UserService userService;
	@Test
	public void testExample() {
		User u = userService.findUserById(33);
		System.out.print(u.getAccount());
	}
}
