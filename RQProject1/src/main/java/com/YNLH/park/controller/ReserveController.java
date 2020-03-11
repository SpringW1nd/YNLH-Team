package com.YNLH.park.controller;

import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.YNLH.park.dao.entity.Reservation;
import com.YNLH.park.service.ParkService;

@RequestMapping("/reserve")
@Controller
public class ReserveController {
	//logger
	private final static Logger logger = Logger.getLogger(ReserveController.class);
	
	@Autowired
	private ParkService ParkSrv;
	
	@RequestMapping("/")
	public ModelAndView Reserve(@RequestParam("PlateNumber") String PlateNumber, 
			                    @RequestParam("StartTime") Date StartTime,
			                    @RequestParam("EndTime") Date EndTime) 
	{
		logger.info("Reserve:");
		
		Reservation Rev = ParkSrv.makeReservation(0, StartTime, EndTime, PlateNumber);
		if (Rev == null)
		{
			return new ModelAndView("Reserve", "Status", "Fail");
		}
		else
		{	
			return ReserveQuery (PlateNumber);
		}
	}
	
	@RequestMapping("/query")
	public ModelAndView ReserveQuery(@RequestParam("PlateNumber") String PlateNumber) 
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
