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
	@Test
	public void registerUsertest()
	{
		assert (userService.registerUser("test1", "123456", "test1", "","") != null);
		assert (userService.registerUser("test1", "123456", "test1", "","") == null);
		assert (userService.registerUser("test2", "123456", "test1", "h@wsu.edu","5099832123") != null);
		assert (userService.registerUser("", "", "", "","") == null);
		System.out.print("registertest success`");
	}
	@Test
	public void logintest()
	{
		User u1 = userService.login("hongkl","123456");
		assert (u1 != null);
		User u2 = userService.login("hongkl","123");
		assert (u2 == null);
		User u3 = userService.login("","");
		assert (u3 == null);
		System.out.print("logintest success`");
	}
}
