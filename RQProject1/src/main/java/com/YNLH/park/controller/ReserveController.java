package com.YNLH.park.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.YNLH.park.dao.entity.Reservation;
import com.YNLH.park.service.ParkService;
import com.YNLH.park.service.UserService;

@RequestMapping("/reservation")
@Controller
public class ReserveController {
	//logger
	private final static Logger logger = Logger.getLogger(ReserveController.class);
	
	@Autowired
	private ParkService ParkSrv;
	
	@Autowired
	private UserService UserSrv;
	
	@RequestMapping("/makeReservation")
	public ModelAndView makeReservation(@RequestParam("username")String username) 
	{
		logger.info("makeReservation:");
		
		// todo
	
		return new ModelAndView("makeReservation", "plateList", "plateList");
	}
	
	@RequestMapping("/reservation")
	public ModelAndView reservation(@RequestParam("name")String username, 
			                        @RequestParam("plateNumber")String plateNumber, 
			                        @RequestParam("RStartDate")Date RStartDate, 
			                        @RequestParam("REndDate")Date REndDate) 
	{
		logger.info("Reserve:");
		
		Reservation Rev = ParkSrv.makeReservation(0 /*username*/, RStartDate, REndDate, plateNumber);
		if (Rev == null)
		{
			return new ModelAndView("reservationFail", "Status", "Fail");
		}

		HashMap<String, Object> RevInfo = new HashMap<String, Object>();
		RevInfo.put("reservationNumber",  Rev.getRid()); 
		RevInfo.put("email",  "email");  
		RevInfo.put("name",   "name");
		RevInfo.put("RStartDate",   Rev.getStartDate()); 
		RevInfo.put("REndDate",     Rev.getEndDate()); 
		RevInfo.put("plateNumber",  Rev.getPlateNumber()); 
		RevInfo.put("parkspace",    "parkspace"); 

		return new ModelAndView("reservationSuccess", "RevInfo", RevInfo);
	}
	
	@RequestMapping("/reservationList")
	public ModelAndView ReserveQueryUser(@RequestParam("UserId") int UserId) 
	{
		System.out.println("ReserveQuery!!!!");
		List<Reservation> RevList = ParkSrv.listReservation(UserId);

        return new ModelAndView("queryUser", "ReserveList", RevList);
	}
	
	@RequestMapping("/cancel")
	public ModelAndView ReserveCancel(@RequestParam("reservationNumber") int ReserveId) 
	{
		System.out.println("ReserveCancel!!!!");
		boolean IsCancel = ParkSrv.cancelReservation(ReserveId);
		if (IsCancel)
		{
			return new ModelAndView("Cancel", "Status", true);
		}
		else
		{
			return new ModelAndView("Cancel", "Status", false);
		}	
	}
}
