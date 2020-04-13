package com.YNLH.park.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.YNLH.park.service.ManagerService;
import com.YNLH.park.service.UserService;

public class ManagerServiceTest extends BaseTestForSpring {
	
	@Autowired
	private ManagerService managerService;
	@Test
	public void testLogin() {
		
		try {
			managerService.login("admin", "123");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
