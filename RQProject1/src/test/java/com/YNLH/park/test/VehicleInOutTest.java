package com.YNLH.park.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.YNLH.park.dao.entity.RegisterBill;
import com.YNLH.park.service.VehicleService;
import com.YNLH.park.service.BillService;

public class VehicleInOutTest extends BaseTestForSpring
{
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private BillService billService;
	
	@Test
	public void vehicleTest() 
	{
		String plateNumber = "XA00YN78";
		
		int result = vehicleService.vehicleEntry(plateNumber);
		assert (result == 1);
		
		result = vehicleService.vehicleEntry(plateNumber);
		assert (result == 0);
		
		vehicleService.setExitTime(plateNumber, 3);
		
		RegisterBill regBill = vehicleService.vehicleExit(plateNumber);
		assert (regBill != null);
		
		billService.payBill(regBill.getBid());
		assert (billService.isBillPayed(regBill.getBid()) == true);
		
		System.out.print("vehicleTest success!!!");
	}
}
