package com.YNLH.park.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.YNLH.park.dao.entity.Manager;
import com.YNLH.park.dao.entity.ParkingSpace;
import com.YNLH.park.dao.entity.RegisterBill;
import com.YNLH.park.dao.entity.RegisterPlateNumber;
import com.YNLH.park.dao.entity.User;
import com.YNLH.park.service.ManagerService;
import com.YNLH.park.service.ParkService;

@Controller
@RequestMapping("/manager")
public class ManagerControl {
	//logger
		private final static Logger logger = Logger.getLogger(ManagerControl.class);
		
		@Autowired
		private ManagerService managerService;
		@RequestMapping("/goToMangerLogin")
		public ModelAndView goToMangerLogin(HttpServletRequest request) 
		{
			logger.info("managerPage:");
			
			
			return new ModelAndView("managerLogin");
		}
		@RequestMapping("/login")
		public ModelAndView login(@RequestParam("username")String username, 
									@RequestParam("password")String password,
									HttpServletRequest request) 
		{
			logger.info("login:");
			Manager manager = null;
			if(request.getSession().getAttribute("manager")==null) {
				try {
					manager = managerService.login(username, password);
					if (manager == null)
					{
						
						return new ModelAndView("managerLogin","LoginFlag","-1");
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("exception in login"+e.getMessage());
					return new ModelAndView("managerLogin","LoginFlag","0");
				}
			
			}
			List<ParkingSpace> resultList = null;
			ParkingSpace parkingSpace = new ParkingSpace();
			parkingSpace.setParkNumber("-1");
			resultList = managerService.selectParkingSpace(parkingSpace);
			ModelAndView mav = new ModelAndView("managerMainPage");
			mav.addObject("resultList", resultList);
			mav.addObject("LoginFlag", "1");
			return mav;
		}
		@RequestMapping("/parkingSpaceDetail")
		public ModelAndView parkingSpaceDetail(@RequestParam("parkNumber")String parkNumber,HttpServletRequest request) 
		{
			logger.info("managerPage:");
			List<ParkingSpace> resultList = null;
			ParkingSpace parkingSpace = new ParkingSpace();
			parkingSpace.setParkNumber(parkNumber);
			resultList = managerService.selectParkingSpace(parkingSpace);
			
			ModelAndView mav = new ModelAndView("parkingSpaceDetail");
			mav.addObject("parkSpace", resultList.get(0));
			return mav;
		}
		@RequestMapping("/updateParkingSpace")
		public ModelAndView updateParkingSpace(@RequestParam("parkNumber")String parkNumber,@RequestParam("level")String level,
				@RequestParam("status")String status,HttpServletRequest request) 
		{
			logger.info("updateParkingSpace:");
			ParkingSpace parkingSpace = new ParkingSpace();
			parkingSpace.setParkNumber(parkNumber);
			parkingSpace.setLevel(Integer.parseInt(level));
			parkingSpace.setStatus(Integer.parseInt(status));
			int result = managerService.updateParkingSpace(parkingSpace);
			List<ParkingSpace> resultList = null;
			ParkingSpace parkingSpace2 = new ParkingSpace();
			parkingSpace.setParkNumber("-1");
			resultList = managerService.selectParkingSpace(parkingSpace);
			ModelAndView mav = new ModelAndView("managerMainPage");
			mav.addObject("resultList", resultList);
			return mav;
		}
		
		@RequestMapping("/managerPage")
		public ModelAndView managerPage(HttpServletRequest request) 
		{
			logger.info("managerPage:");
			List<ParkingSpace> resultList = null;
			ParkingSpace parkingSpace = new ParkingSpace();
			parkingSpace.setParkNumber("-1");
			resultList = managerService.selectParkingSpace(parkingSpace);
			ModelAndView mav = new ModelAndView("managerMainPage");
			mav.addObject("resultList", resultList);
			return mav;
		}
		@RequestMapping("/userManagement")
		public ModelAndView userManagement(HttpServletRequest request) 
		{
			logger.info("managerPage:");
			List<User> resultList = null;
			resultList = managerService.selectAllUser();
			ModelAndView mav = new ModelAndView("userManagement");
			mav.addObject("resultList", resultList);
			return mav;
		}
		@RequestMapping("/userDetail")
		public ModelAndView userDetail(@RequestParam("uid")String uid,HttpServletRequest request) 
		{
			logger.info("managerPage:");
			User user = null;
			user = managerService.selectUserByuid(Integer.parseInt(uid));
			
			ModelAndView mav = new ModelAndView("userDetail");
			mav.addObject("user", user);
			return mav;
		}
		@RequestMapping("/updateUser")
		public ModelAndView updateUser(@RequestParam("uid")String uid,@RequestParam("account")String account,
				@RequestParam("name")String name,
				@RequestParam("email")String email,
				@RequestParam("phone")String phone,
				@RequestParam("plateNumber")String plateNumber,
				HttpServletRequest request) 
		{
			logger.info("updateParkingSpace:");
			User user = new User();
			user.setUid(Integer.parseInt(uid));
			user.setAccount(account);
			user.setName(name);
			user.setEmail(email);
			user.setPhone(phone);
			user.setPlateNumber(plateNumber);
			int result = managerService.updateUser(user);
			List<User> resultList = null;
			resultList = managerService.selectAllUser();
			ModelAndView mav = new ModelAndView("userManagement");
			mav.addObject("resultList", resultList);
			return mav;
		}
		@RequestMapping("/billManagement")
		public ModelAndView billManagement(HttpServletRequest request) 
		{
			logger.info("managerPage:");
			List<RegisterBill> resultList = null;
			resultList = managerService.selectAllRegisterBill();
			ModelAndView mav = new ModelAndView("billManagement");
			mav.addObject("resultList", resultList);
			return mav;
		}
}
