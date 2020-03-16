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
import com.YNLH.park.dao.entity.User;
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
		
		User U = (User) request.getSession(true).getAttribute("user");
		List<RegisterPlateNumber> platList = ParkSrv.listRegisterPlateNumber (U.getAccount());
		System.out.print("platList=====>"+platList.size());
		return new ModelAndView("makeReservation", "plateList", platList);
	}
	
	@RequestMapping("/reservation")
	public ModelAndView reservation(@RequestParam("name")String name, 
			                        @RequestParam("plateNumber")String plateNumber, 
			                        @RequestParam("RStartDate")Date RStartDate, 
			                        @RequestParam("REndDate")Date REndDate,
			                        HttpServletRequest request) 
	{
		logger.info("Reserve:");
		User U = (User)request.getSession(true).getAttribute("user");
		Reservation Rev = ParkSrv.makeReservation(U.getAccount(), RStartDate, REndDate, plateNumber);
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
		
		User U = (User) request.getSession(true).getAttribute("user");
		List<Reservation> RevList = ParkSrv.listReservation(U.getUid());
		System.out.print("RevList----->"+RevList.size());
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
