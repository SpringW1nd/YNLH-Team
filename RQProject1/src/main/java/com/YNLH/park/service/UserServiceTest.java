package com.YNLH.park.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.YNLH.park.dao.entity.User;

import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class UserServiceTest {

	@Autowired
	private UserService userService;
	@Test
	public void testExample() {
		User u = userService.findUserById(33);
		System.out.print(u.getAccount());
	}
}
