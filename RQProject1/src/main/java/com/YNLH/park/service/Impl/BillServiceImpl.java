package com.YNLH.park.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.YNLH.park.dao.entity.RegisterBill;
import com.YNLH.park.dao.mapper.ParkMapper;
import com.YNLH.park.service.BillService;

@Service
public class BillServiceImpl implements BillService
{
	private static ApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext.xml");
	private static ParkMapper parkMapper = appCtx.getBean(ParkMapper.class);
	
	public RegisterBill initBill(int rid, int uid, 
                                 String plateNumber, String parkNumber, 
                                 Date entryTime, Date exitTime)
	{
		RegisterBill regBill = new RegisterBill (rid, uid, plateNumber, parkNumber, entryTime, exitTime);

		try
		{
			parkMapper.addRegisterBill(regBill);
		
			return regBill;
		}
		catch (Exception e)
		{
			System.out.println(e);
			return null;		
		}		
	}

	public RegisterBill findActiveBill(String plateNumber)
	{
		try
		{    
			return parkMapper.findActiveBill(plateNumber);
		}
		catch (Exception e)
		{
			System.out.println(e);
			return null;		
		}
	}

	public double getPakringTime (Date entryTime, Date exitTime)
	{
		long diff = exitTime.getTime() - entryTime.getTime();
		double days = diff / (1000 * 60 * 60 * 24);  

		double hours = (diff - days*(1000 * 60 * 60 * 24))/(1000* 60 * 60.0);

		return hours;
	}

	public double completeBill(RegisterBill regBill)
	{
		double fee = 0;	

		try
		{  		
			double parkTime = getPakringTime (regBill.getEntryTime(), regBill.getExitTime());

			if (regBill.getRid() != 0)
			{
				/* vip user: the charging strategy need to be considered */
				fee = 5 * parkTime;
			}
			else
			{
				fee = 10 * parkTime;
			}

			/* update bill to database */
			parkMapper.completeBill(regBill.getBid(), fee, regBill.getExitTime());
		}
		catch (Exception e)
		{
			System.out.println(e);	
		}

		return fee;
	}

	public boolean payBill(int bid)
	{
		try
		{ 
			parkMapper.payBill(bid);
			return true;
		}
		catch (Exception e)
		{
			System.out.println(e);
			return false;
		}
	}

	public boolean isBillPayed(int bid)
	{
		try
		{ 
			RegisterBill regBill = parkMapper.findBill(bid);
			if (regBill.getStatus() == 2)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
			return false;
		}	
	}

	public List<RegisterBill> listBill(String account)
	{
		return null;
	}
}
