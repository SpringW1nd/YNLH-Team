package com.YNLH.park.service.Impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.YNLH.park.controller.ManagerControl;
import com.YNLH.park.dao.entity.RegisterBill;
import com.YNLH.park.dao.entity.User;
import com.YNLH.park.dao.mapper.ParkMapper;
import com.YNLH.park.service.BillService;

@Service
public class BillServiceImpl implements BillService
{
	private static ApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext.xml");
	private static ParkMapper parkMapper = appCtx.getBean(ParkMapper.class);
	private final static Logger logger = Logger.getLogger(BillServiceImpl.class);
	private int normalPrice = 10;
	private int vipPrice = 5;
	
	private void readParkingPrice ()
	{
		File file = new File("/RQProject1/src/main/resources/parkingcharge.properties");

		try
		{
			
			InputStreamReader reader = new InputStreamReader(this.getClass().getResourceAsStream("/parkingcharge.properties"));			
			BufferedReader bfReader = new BufferedReader(reader); 
            String line = ""; 
            while ((line = bfReader.readLine()) != null) 
            {
            	String value = line.substring(line.indexOf("=")+1);
                
                if (line.indexOf("normal") != -1)
                {	
                	normalPrice = Integer.parseInt(value);
                }
                else if (line.indexOf("vip") != -1)
                {
                	vipPrice = Integer.parseInt(value);
                }
            } 
            
            bfReader.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logger.error("exception in completeBill:"+e.getMessage());
			return;		
		}	
	}
	
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
			e.printStackTrace();
			logger.error("exception in initBill:"+e.getMessage());
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
			e.printStackTrace();
			logger.error("exception in findActiveBill:"+e.getMessage());
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
		readParkingPrice ();
		
		try
		{  		
			double parkTime = getPakringTime (regBill.getEntryTime(), regBill.getExitTime());

			//System.out.print("vipPrice = " + vipPrice + ",  normalPrice = " + normalPrice);
			if (regBill.getRid() != 0)
			{
				/* vip user: the charging strategy need to be considered */
				fee = vipPrice * parkTime;
			}
			else
			{
				fee = normalPrice * parkTime;
			}

			/* update bill to database */
			parkMapper.completeBill(regBill.getBid(), fee, regBill.getExitTime());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logger.error("exception in completeBill:"+e.getMessage());
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
			e.printStackTrace();
			logger.error("exception in payBill:"+e.getMessage());
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
			e.printStackTrace();
			logger.error("exception in isBillPayed:"+e.getMessage());
			return false;
		}	
	}

	public List<RegisterBill> listBill(String account)
	{
		UserServiceImpl userService = new UserServiceImpl();
		
		User user = userService.findUser(account);
		if (user == null)
		{
			return null;
		}
		
		try
		{
			List<RegisterBill> billList = parkMapper.listRegisterBill(user.getUid());
			
			return billList;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logger.error("exception in listBill:"+e.getMessage());
			return null;
		}
	}
}
