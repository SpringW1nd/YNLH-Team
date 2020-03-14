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

import com.YNLH.park.dao.entity.*;
import com.YNLH.park.service.ParkService;

@RequestMapping("/reserve")
@Controller
public class ReserveController {
	//logger
	private final static Logger logger = Logger.getLogger(ReserveController.class);
	
	@Autowired
	private ParkService ParkSrv;
	
	
	@RequestMapping("/test")
	public ModelAndView test()
	{
		Date wStartDate=new Date(120,3,16,4,43);
		Date wEndDate=new Date(120,3,16,5,43);
		ParkSrv.walkUserIn("1234", wStartDate);
		double fee= ParkSrv.walkUserOut("1234", wEndDate);
		
		return new ModelAndView("test","fee",fee);
		/*
		Date rStartDate=new Date(120,3,16,4,43);		//120 ->1900+120=2020
		Date rEndDate=new Date(120,3,16,5,57);
		Reservation resv=ParkSrv.makeReservation(3, rStartDate, rEndDate, "9842");
		return new ModelAndView("test","resv",resv);
		*/
	}
	/*
	public ModelAndView test()
	{
		Reservation resv=ParkSrv.findReservationByPlateNumber("7890");
		return new ModelAndView("test","resv",resv);
	}
	*/
	
	/*
	public ModelAndView test()
	{
		Reservation resv=ParkSrv.findReservation(1);
		return new ModelAndView("test","resv",resv);
	}*/
	/*
	public ModelAndView test(@RequestParam("uid") String uid)
	{
		int i=Integer.parseInt(uid);
		List<Reservation> resvs=ParkSrv.listReservation(i);
		return new ModelAndView("test","resvs",resvs);
	}
	/*
	public ModelAndView test()
	{
		Date rStartDate=new Date(120,3,16,3,57);		//120 ->1900+120=2020
		Date rEndDate=new Date(120,3,16,4,57);
		Reservation resv=ParkSrv.makeReservation(3, rStartDate, rEndDate, "7890");
		return new ModelAndView("test","resv",resv);
	}
	
	
	public ModelAndView test()
	{
		Date rStartDate=new Date(2020,3,13,3,17);
		Date rEndDate=new Date(2020,3,13,4,17);
		Reservation resv=ParkSrv.makeReservation(2, rStartDate, rEndDate, "2345");
		return new ModelAndView("test","resv",resv);
	}
	*/
	
	/*
	public ModelAndView test(@RequestParam("plateNumber") String plateNumber)
	{
		RegisterPlateNumber plate=ParkSrv.findRegisterPlateNumber(plateNumber);
		return new ModelAndView("test","plate",plate);
	}*/
	
	/*
	public ModelAndView test(@RequestParam("uid") String uid)
	{
		int i=Integer.parseInt(uid);
		List<RegisterPlateNumber> plates=ParkSrv.listRegisterPlateNumber(i);
		return new ModelAndView("test","plates",plates);
	}
	
	public ModelAndView test(@RequestParam("uid") String uid, @RequestParam("plateNumber") String plateNumber)
	{
		int i=Integer.parseInt(uid);
		RegisterPlateNumber plate=ParkSrv.addRegisterPlateNumber(i,plateNumber);
		return new ModelAndView("test","plate",plate);
	}*/
	
	
	@RequestMapping("/")
	public ModelAndView Reserve(@RequestParam("UserId") int UserId,
			                    @RequestParam("PlateNumber") String PlateNumber, 
			                    @RequestParam("StartTime") Date StartTime,
			                    @RequestParam("EndTime") Date EndTime) 
	{
		logger.info("Reserve:");
		
		Reservation Rev = ParkSrv.makeReservation(UserId, StartTime, EndTime, PlateNumber);
		if (Rev == null)
		{
			return new ModelAndView("Reserve", "Status", "Fail");
		}
		else
		{	
			return ReserveQueryPn (PlateNumber);
		}
	}
	
	@RequestMapping("/queryPN")
	public ModelAndView ReserveQueryPn(@RequestParam("PlateNumber") String PlateNumber) 
	{
		System.out.println("ReserveQuery!!!!");
		Reservation Rev = ParkSrv.findReservationByPlateNumber(PlateNumber);
		
		HashMap<String, Object> RevInfo = new HashMap<String, Object>();
		RevInfo.put("ReserveId",   Rev.getRid()); 
		RevInfo.put("PlateNumber", Rev.getPlateNumber());  
		RevInfo.put("StartDate",   Rev.getStartDate());
		RevInfo.put("EndDate",     Rev.getEndDate()); 

        return new ModelAndView("RevInfo", RevInfo);
	}
	
	@RequestMapping("/queryUser")
	public ModelAndView ReserveQueryUser(@RequestParam("UserId") int UserId) 
	{
		System.out.println("ReserveQuery!!!!");
		List<Reservation> RevList = ParkSrv.listReservation(UserId);

        return new ModelAndView("queryUser", "ReserveList", RevList);
	}
	
	@RequestMapping("/cancel")
	public ModelAndView ReserveCancel(@RequestParam("ReserveId") int ReserveId) 
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
	
	@RequestMapping("/extend")
	public ModelAndView ReserveExtend(@RequestParam("ExTime") int ExTime) 
	{
		System.out.println("ReserveExtend!!!!");
		
		return new ModelAndView("Extend", "Status", "Not Implemented");
	}
}
