package com.YNLH.park.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.YNLH.park.dao.entity.RegisterPlateNumber;
import com.YNLH.park.dao.entity.Reservation;
import com.YNLH.park.service.ParkService;
import com.YNLH.park.service.UserService;

@Controller
@RequestMapping("/reservation")
public class ReserveController {
	//logger
	private final static Logger logger = Logger.getLogger(ReserveController.class);
	
	@Autowired
	private ParkService ParkSrv;
	
	@RequestMapping("/makeReservation")
	public ModelAndView makeReservation(HttpServletRequest request) 
	{
		logger.info("makeReservation:");
		
		String username = (String) request.getAttribute("username");
		System.out.println("makeReservation username=====>" + username);
		List<RegisterPlateNumber> platList = ParkSrv.listRegisterPlateNumber (username);
	
		return new ModelAndView("makeReservation", "plateList", platList);
	}
	
	@RequestMapping("/reservation")
	public ModelAndView reservation(@RequestParam("name")String username, 
			                        @RequestParam("plateNumber")String plateNumber, 
			                        @RequestParam("RStartDate")Date RStartDate, 
			                        @RequestParam("REndDate")Date REndDate) 
	{
		logger.info("Reserve:");
		
		Reservation Rev = ParkSrv.makeReservation(username, RStartDate, REndDate, plateNumber);
		if (Rev == null)
		{
			return new ModelAndView("reservationFail", "Status", "Fail");
		}

		HashMap<String, Object> RevInfo = new HashMap<String, Object>();
		RevInfo.put("reservationNumber",  Rev.getRid()); 
		RevInfo.put("email",        Rev.getEmail());  
		RevInfo.put("name",         Rev.getName());
		RevInfo.put("RStartDate",   Rev.getStartDate()); 
		RevInfo.put("REndDate",     Rev.getEndDate()); 
		RevInfo.put("plateNumber",  Rev.getPlateNumber()); 
		RevInfo.put("parkspace",    Rev.getParkingSpace()); 

		return new ModelAndView("reservationSuccess", "RevInfo", RevInfo);
	}
	
	@RequestMapping("/reservationList")
	public ModelAndView ReserveQueryUser(HttpServletRequest request) 
	{
		System.out.println("ReserveQuery!!!!");
		
		String username = (String) request.getAttribute("username");
		List<Reservation> RevList = ParkSrv.listReservation(username);

        return new ModelAndView("reservationList", "reservationList", RevList);
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
