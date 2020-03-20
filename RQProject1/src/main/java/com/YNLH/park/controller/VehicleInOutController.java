/******************************************************************
 * 
 * author: li,wen
 * date: 3/18/2020
 * description: vehicle entry and exit controller
 * 
 ******************************************************************/

package com.YNLH.park.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.YNLH.park.dao.entity.RegisterBill;
import com.YNLH.park.service.ParkService;

import net.sf.json.JSONObject;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


@Controller
@RequestMapping("/vehicle")
public class VehicleInOutController 
{
	@Autowired
	private ParkService parkService;
	
	@RequestMapping("/in")
	@ResponseBody
	public String vehicleIn(@RequestParam("plateNumber")String plateNumber) 
	{
		//System.out.print("===> vehicle:" + plateNumber + " try to entry!");
		JSONObject JsonStr = new JSONObject();
		JsonStr.put("status", parkService.vehicleEntry(plateNumber));
		
		return JsonStr.toString();
	}
	
	private String DateToString(Date date) 
	{
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
 
        return sdf.format(date);
    }
	
	@RequestMapping("/out")
	@ResponseBody
	public String vehicleOut(@RequestParam("plateNumber")String plateNumber) 
	{
		System.out.print("===> vehicle:" + plateNumber + " try to exit!");
		
		JSONObject BillInfo = new JSONObject();		
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
			System.out.println (new Timestamp (regBill.getEntryTime().getTime()));
			BillInfo.put("entryTime", DateToString (regBill.getEntryTime()));
			BillInfo.put("exitTime",  DateToString (regBill.getExitTime()));
		}
		
		return BillInfo.toString();
	}
	
	@RequestMapping("/pay")
	@ResponseBody
	public String vehiclePay(@RequestParam("bid")int bid) 
	{
		JSONObject Payed = new JSONObject();
		Payed.put("payed", parkService.payBill(bid));
		return Payed.toString();
	}
	
	@RequestMapping("/is-payed")
	@ResponseBody
	public String vehiclePayed(@RequestParam("bid")int bid) 
	{
		JSONObject Payed = new JSONObject();
		Payed.put("payed", parkService.isBillPayed(bid));
		return Payed.toString();
	}
	
	@RequestMapping("/set-exit-time")
	@ResponseBody
	public String vehiclePayed(@RequestParam("plateNumber")String plateNumber, @RequestParam("Hours")int Hours) 
	{
		JSONObject Payed = new JSONObject();
		Payed.put("status", parkService.setExitTime(plateNumber, Hours));
		return Payed.toString();
	}
}
