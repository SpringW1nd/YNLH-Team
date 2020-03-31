package com.YNLH.park.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	
	@RequestMapping("/login")
	public ModelAndView Login (@RequestParam("username")String username, 
			                   @RequestParam("password")String password,
			                   HttpServletRequest request)
	{
		User U = null;
		if(request.getSession().getAttribute("user")==null) {
			
			try
			{
				U = userService.login(username, password);
				if (U == null)
				{
					return new ModelAndView("index","LoginFlag","0");
				}
			}
			catch(Exception e)
			{
				return new ModelAndView("index","LoginFlag","0");
			}
			
			request.getSession(true).setAttribute("user", U);
		}
		ModelAndView mnv = new ModelAndView("registeUserMainPage");
		mnv.addObject("user", U);
		return mnv;
		
	}
	
	@RequestMapping("/goTORegisteration")
	public ModelAndView goTORegisteration()
	{
		return new ModelAndView("registeration");
	}
	
	@RequestMapping("/registeration")
	public ModelAndView registerUser(@RequestParam("username") String username,
			                         @RequestParam("password") String password, 
			                         @RequestParam("comfirmPw") String comfirmPw, 
			                         @RequestParam("name") String name, 
			                         @RequestParam("email") String email, 
			                         @RequestParam("phoneNumber") String phone)
	{
	
		try
		{
			String eformat = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}"; // email format
			String pformat = "^[0-9]+(.[0-9]+)?$"; // phone format
			
			//User U = userService.registerUser(username, password, name, email, phone);
			//emall can be null, but if email is not null, it must be the correct email format.
			//password and comfirm password must be the same and name cannot be empty
			if (username == null|| password == null ||  !password.equals(comfirmPw) || name.isEmpty() || (!email.isEmpty() && !email.matches(eformat))|| (!phone.isEmpty() && !phone.matches(pformat)))  
			{
				return new ModelAndView("registerFail");
			}
			User U = userService.registerUser(username, password, name, email, phone);
			if (U == null)			//account exists
			{
				return new ModelAndView("registerFail");
			}

			
			
			
		}
		catch(Exception e)
		{
			return new ModelAndView("registerFail");
		}
		
		return new ModelAndView("registeUserMainPage");
	}
	
	
	@RequestMapping("/listAllUser")
	public ModelAndView listAllUser() {
		logger.info("list All User");
		List<User> userList = userService.listUsers();
		return new ModelAndView("queryUser","userList",userList);
	}
	@RequestMapping("/sighout")
	public ModelAndView sighout(HttpServletRequest request) {
		logger.info("sigh out");
		request.getSession().invalidate();
		return new ModelAndView("index");
	}
	/*
	@RequestMapping("/insertUser")
	public ModelAndView insertUser(@RequestParam("uid") int uid, @RequestParam("name") String name,@RequestParam("type") int type) {
		logger.info("insert All User");
		userService.insertUser(uid, name, type);
		return new ModelAndView("success");
	}
	*/
}
