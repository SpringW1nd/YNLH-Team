/******************************************************************
 * 
 * author: li,wen
 * date: 3/18/2020
 * description: vehicle entry and exit controller
 * 
 ******************************************************************/

package com.YNLH.park.controller;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.YNLH.park.dao.entity.RegisterBill;
import com.YNLH.park.service.ParkService;


@Controller
@RequestMapping("/vehicle")
public class VehicleInOutController 
{
	@Autowired
	private ParkService parkService;
	
	@RequestMapping("/in")
	public ModelAndView vehicleIn(@RequestParam("plateNumber")String plateNumber) 
	{
		int entryOk = parkService.vehicleEntry(plateNumber);
		if (entryOk == 0)
		{
			System.out.print("vehicle:" + plateNumber + " entry fail!");
			return new ModelAndView("vehicleIn", "status", 0);	
		}
		else
		{
			System.out.print("vehicle:" + plateNumber + " entry fail!");
			return new ModelAndView("vehicleIn", "status", 1);		
		}
	}
	
	@RequestMapping("/out")
	public ModelAndView vehicleOut(@RequestParam("plateNumber")String plateNumber) 
	{
		HashMap<String, Object> BillInfo = new HashMap<String, Object>();
		
		BillInfo.put("platNumber", plateNumber);
		
		RegisterBill regBill = parkService.vehicleExit(plateNumber);
		if (regBill == null)
		{
			/* get no bill, let it go */
			BillInfo.put("bid", 0);
			BillInfo.put("fee", 0.0);
			BillInfo.put("entryTime", "");
			BillInfo.put("exitTime",  "");
		}
		else
		{
			BillInfo.put("bid", regBill.getBid());
			BillInfo.put("fee", regBill.getFee());
			BillInfo.put("entryTime", regBill.getEntryTime());
			BillInfo.put("exitTime",  regBill.getExitTime());
		}
		
		return new ModelAndView("vehicleOut", "BillInfo", BillInfo);
	}
	
	@RequestMapping("/payed")
	public ModelAndView vehiclePayed(@RequestParam("bid")int bid) 
	{
		return new ModelAndView("vehiclePayed", "payed", parkService.payBill(bid));
	}
}
