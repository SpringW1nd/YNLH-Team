package com.YNLH.park.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.YNLH.park.dao.entity.User;
import com.YNLH.park.service.UserService;
@RequestMapping("/user")
@Controller
public class UserController {
	//logger
	private final static Logger logger = Logger.getLogger(UserController.class);
	@Autowired//自动装配Service bean
	private UserService userService;
	
	
	
	@RequestMapping("/registerUser")
	public ModelAndView registerUser(@RequestParam("account") String account, @RequestParam("password") String password, @RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("phone") String phone)
	{
		try
		{
			userService.registerUser(account, password,name,email,phone);
		}
		catch(Exception e)
		{
			return new ModelAndView("registerFail");
		}
		return new ModelAndView("registerSuccess");
	}
	
	
	@RequestMapping("/listAllUser")
	public ModelAndView listAllUser() {
		logger.info("list All User");
		List<User> userList = userService.listUsers();
		return new ModelAndView("queryUser","userList",userList);
	}
	/*
	@RequestMapping("/test")
	public ModelAndView test(@RequestParam("account") String account, @RequestParam("password") String password)
	{
		User user=userService.login(account,password);
		return new ModelAndView("test","user",user);
	}/*
	public ModelAndView test(@RequestParam("uid") String uid)
	{
		int i=Integer.parseInt(uid);
		User user=userService.findUserById(i);
		return new ModelAndView("test","user",user);
	}
	
	public ModelAndView test(@RequestParam("account") String account)
	{
		User user=userService.findUser(account);
		return new ModelAndView("test","user",user);
	}
	*/
	/*public ModelAndView test(@RequestParam("account") String account, @RequestParam("password") String password, @RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("phone") String phone) {
		try
		{
			userService.registerUser(account, password,name,email,phone);
		}
		catch(Exception e)
		{
			return new ModelAndView("registerFail");
		}
		return new ModelAndView("registerSuccess");
	}*/
	/*
	@RequestMapping("/insertUser")
	public ModelAndView insertUser(@RequestParam("uid") int uid, @RequestParam("name") String name,@RequestParam("type") int type) {
		logger.info("insert All User");
		userService.insertUser(uid, name, type);
		return new ModelAndView("success");
	}
	*/
}
