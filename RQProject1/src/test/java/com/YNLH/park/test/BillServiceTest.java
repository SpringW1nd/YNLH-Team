package com.YNLH.park.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.YNLH.park.dao.entity.RegisterBill;
import com.YNLH.park.dao.entity.User;
import com.YNLH.park.service.VehicleService;
import com.YNLH.park.service.BillService;
import com.YNLH.park.service.UserService;
import com.YNLH.park.dao.entity.User;

public class BillServiceTest extends BaseTestForSpring
{
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private BillService billService;
	
	@Autowired
	UserService userService;
	
	@Test
	public void NormalUserbillServiceTest() 
	{
		String plateNumber = "XA000NOR";
		String parkNumber  = "PK001";
		
		RegisterBill regBill =  billService.initBill(0, 0, plateNumber, parkNumber, new Date(), null);
		assert (regBill != null);
		assert (regBill.getBid() != 0);
		assert (regBill.getRid() == 0);
		assert (regBill.getUid() == 0);
		assert (plateNumber.equals(regBill.getPlateNumber()));
		assert (parkNumber.equals(regBill.getParkNumber()));
		
		regBill = billService.findActiveBill(plateNumber);
		assert (regBill != null);
		assert (regBill.getBid() != 0);
		assert (regBill.getRid() == 0);
		assert (regBill.getUid() == 0);
		assert (plateNumber.equals(regBill.getPlateNumber()));
		assert (parkNumber.equals(regBill.getParkNumber()));
		
		//public List<RegisterBill> listBill(String account);
		double free = billService.completeBill(regBill);
		assert (free < 1.0);
		
		boolean pay = billService.payBill(regBill.getBid());
		assert (pay == true);
		
		boolean isPay = billService.isBillPayed(regBill.getBid());
		assert (isPay == true);
		
		System.out.print("NormalUserbillServiceTest success!!!");
	}
}
