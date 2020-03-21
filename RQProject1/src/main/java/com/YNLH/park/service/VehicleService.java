/******************************************************************
 * 
 * author: li,wen
 * date: 3/18/2020
 * description: vehicle entry and exit service
 * 
 ******************************************************************/
package com.YNLH.park.service;

import com.YNLH.park.dao.entity.*;

public interface VehicleService 
{
	
	public int vehicleEntry(String plateNumber);
	public RegisterBill vehicleExit(String plateNumber);
	
	/* for debug: set exit time */
	public boolean setExitTime(String plateNumber, int Hours);

}
