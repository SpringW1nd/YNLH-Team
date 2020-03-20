package com.YNLH.park.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.YNLH.park.dao.entity.User;
import com.YNLH.park.service.UserService;

public class UserTest extends BaseTestForSpring{
	@Autowired
	private UserService userService;
	
	@Test
	public void testExample() 
	{
		int uid = 33;
		User u = userService.findUserById(33);
		if (u == null)
		{
			System.out.print(uid + " get fail!!");
			return;
		}
		System.out.print(u.getAccount());
	}
}
