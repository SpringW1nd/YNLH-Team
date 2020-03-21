package com.YNLH.park.service.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.YNLH.park.dao.entity.ParkingSpace;
import com.YNLH.park.dao.entity.RegisterBill;
import com.YNLH.park.dao.entity.Reservation;
import com.YNLH.park.dao.mapper.ParkMapper;
import com.YNLH.park.service.VehicleService;
import com.YNLH.park.service.BillService;

@Service
class VehicleServiceImpl implements VehicleService
{
	private static ApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext.xml");
	private static ParkMapper parkMapper = appCtx.getBean(ParkMapper.class);
	
	@Autowired
	BillService billService;
	
	public int vehicleEntry(String plateNumber)
	{
		RegisterBill regBill = null;
		String parkNumber = null;
		
		/* check the vehicle */
		if (billService.findActiveBill (plateNumber) != null)
		{
			//System.out.println(plateNumber + "has already entried...");
			return 0;			
		}
		
		try
		{
			/*1. check if exist reservation */
			Reservation Rev = parkMapper.findReservationByPlateNumber (plateNumber);
			if (Rev == null)
			{
				/*2.1 process as normal user, and try to allot a parking */
				ParkingSpace Ps = parkMapper.allotParking ();
				if (Ps == null)
				{
				    return 0;
				}
				
				parkNumber = Ps.getParkNumber ();
				
				/*3. initialize a bill */
				regBill = billService.initBill (0, 0, plateNumber, parkNumber, new Date(), null);
			}
			else
			{
				/*2.2 process as vip user, get the parking from reservation */
				parkNumber = Rev.getParkNumber();
				
				/*3. initialize a bill */
				regBill = billService.initBill (Rev.getRid(), Rev.getUid(), 
						            			plateNumber, parkNumber, new Date(), null);
			}
			
			if (regBill == null)
			{
				System.out.println("initialize regBill fail!!!!");
				return 0;
			}
			
			return 1;
		}
		catch (Exception e)
		{
			System.out.println(e);
			return 0;
		}
	}
	
	public RegisterBill vehicleExit(String plateNumber)
	{
		/*1. find the bill according to the plateNumber */
		RegisterBill regBill = billService.findActiveBill (plateNumber);
		if (regBill == null)
		{
			System.out.println("Get bill fail, plateNumber = " + plateNumber);
			return null;
		}
		
		if (regBill.getExitTime().getTime() == 0)
		{
			regBill.setExitTime(new Date());
		}
		
		/*2. complete the bill */
		double fee = billService.completeBill (regBill);
		regBill.setFee(fee);
		
		return regBill;
	}
	
	public boolean setExitTime(String plateNumber, int Hours)
	{
		try
		{ 
			RegisterBill regBill = billService.findActiveBill (plateNumber);
			
			long sec = regBill.getEntryTime().getTime() + Hours * 60 *60 * 1000;	
			Date exitTime = new Date (sec);

			parkMapper.setExitTime(regBill.getBid(), exitTime);
			
			return true;
		}
		catch (Exception e)
		{
			System.out.println(e);
			return false;
		}	
	}

}
