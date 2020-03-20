package com.YNLH.park.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.YNLH.park.dao.entity.RegisterBill;
import com.YNLH.park.service.ParkService;

public class VehicleInOutTest extends BaseTestForSpring
{
	@Autowired
	private ParkService parkService;
	
	@Test
	public void vehicleTest() 
	{
		String plateNumber = "XA00YN78";
		
		int result = parkService.vehicleEntry(plateNumber);
		assert (result == 1);
		
		result = parkService.vehicleEntry(plateNumber);
		assert (result == 0);
		
		parkService.setExitTime(plateNumber, 3);
		
		RegisterBill regBill = parkService.vehicleExit(plateNumber);
		assert (regBill != null);
		
		parkService.payBill(regBill.getBid());
		assert (parkService.isBillPayed(regBill.getBid()) == true);
		
		System.out.print("vehicleTest success!!!");
	}
}
