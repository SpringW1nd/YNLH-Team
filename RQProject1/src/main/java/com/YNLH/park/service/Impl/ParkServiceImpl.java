package com.YNLH.park.service.Impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.YNLH.park.dao.entity.*;
import com.YNLH.park.dao.mapper.*;
import com.YNLH.park.service.*;

@Service
public class ParkServiceImpl implements ParkService
{
	//logger
	private final static Logger logger = Logger.getLogger(ParkServiceImpl.class);
	
	private static ApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext.xml");
	private static ParkMapper parkMapper = appCtx.getBean(ParkMapper.class);
	
	public RegisterPlateNumber addRegisterPlateNumber(int uid, String plateNumber)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		
		RegisterPlateNumber registerPlateNumber=new RegisterPlateNumber();
		
		registerPlateNumber.setUid(uid);
		registerPlateNumber.setPlateNumber(plateNumber);
		
		int pid=0;
		try {
			pid = parkMapper.addPlateNumber(registerPlateNumber);			//1 for success. 0 for fail. Not the actual pid 
		}catch(Exception e) {}
			
		
		registerPlateNumber.setPid(pid);
		
		
		return registerPlateNumber;
		
	}
	public List<RegisterPlateNumber> listRegisterPlateNumber(String account)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		
		List<RegisterPlateNumber> registerPlateNumbers=null;
		
		UserServiceImpl service=new UserServiceImpl();
		User user=service.findUser(account);
		
		int uid=user.getUid();
		
		try{
			registerPlateNumbers=parkMapper.listRegisterPlateNumber(uid);
		}catch(Exception e) {}
		
		return registerPlateNumbers;
		
	}
	
	public RegisterPlateNumber findRegisterPlateNumber(String plateNumber)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		
		RegisterPlateNumber registerPlateNumber=null;
		try {
			registerPlateNumber=parkMapper.findRegisterPlateNumber(plateNumber);
		}catch(Exception e) {}
		return registerPlateNumber;
	}

	public Reservation makeReservation(String account, Date rStartDate, Date rEndDate, String plateNumber)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		UserServiceImpl service=new UserServiceImpl();
		User user=service.findUser(account);
		
		int uid=user.getUid();
		
		/* get ParkSpace */
		ParkingSpace Ps = parkMapper.allotParking ();
		if (Ps == null)
		{
			// to do
		}
		else
		{
			/* Update parking Status */
			// todo 
			
			Reservation reservation=new Reservation();
			reservation.setUid(uid);
			reservation.setPlateNumber(plateNumber);
			reservation.setParkNumber("x201");
			reservation.setrStartDate(rStartDate);
			reservation.setrEndDate(rEndDate);
			int rid=0;
			
			try
			{
				rid=parkMapper.makeReservation(reservation);		//rid=1 success, 0 fail
				rid = reservation.getRid();
			}
			catch(Exception e) {}
			
			return reservation;
		}

		return null;
	}
	
	public List<Reservation> listReservation(int uid)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		
		List<Reservation> reservations=null;
		try {
			reservations=parkMapper.listReservation(uid);
		}catch(Exception e) {}
		return reservations;
		
	}
	
	public Reservation findReservation(int rid)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		Reservation reservation=null;
		try {
			reservation=parkMapper.findReservation(rid);
		}catch(Exception e) {}
		return reservation;
	}
	public Reservation findReservationByPlateNumber(String plateNumber)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		Reservation reservation=null;
		try {
			reservation=parkMapper.findReservationByPlateNumber(plateNumber);
		}catch(Exception e) {}
		return reservation;
	}
	
	public boolean cancelReservation(int rid)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		if(findReservation(rid)==null)
		{
			return false;
		}
		else
		{
			/* update Parking Space */
			// to do 
			
			try {
				parkMapper.cancelReservation(rid);
			}catch(Exception e) {}
		}
		return true;
	}
	
	/* -------------- liwen 3/17: bill management begin   -------------------- */
	private RegisterBill initBill(int rid, int uid, 
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
	
	private RegisterBill findActiveBill(String plateNumber)
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
	
	private double getPakringTime (Date entryTime, Date exitTime)
	{
		long diff = exitTime.getTime() - entryTime.getTime();
	    double days = diff / (1000 * 60 * 60 * 24);  
	   
	    double hours = (diff - days*(1000 * 60 * 60 * 24))/(1000* 60 * 60.0);
	    
	    return hours;
	}
	
	private double completeBill(RegisterBill regBill)
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
	/* -------------- liwen 3/17: bill management begin   -------------------- */
	
	
	/* -------------- liwen 3/17 : park space management begin -------------------- */
	public int getIdleParkCount()
	{
		List<ParkingSpace> setPs = parkMapper.getIdleParking();
		if (setPs == null)
		{
			// for debug
			return 10;
		}
		
		return setPs.size();
	}

	public List<ParkingSpace> getIdleParkSet()
	{
		return parkMapper.getIdleParking();		
	}
	
	public int AddParking(int level, String parkNumber)
	{
		return 1;
	}
	
	public int UpdateParkingStatus(int level, String parkNumber, int status)
	{
		return 1;
	}
	/* -------------- liwen 3/17 : park space management end    -------------------- */
	
	
	/* -------------- liwen 3/17: vehicle entry/exit process begin ----------------- */
	public int vehicleEntry(String plateNumber)
	{
		RegisterBill regBill = null;
		String parkNumber = null;
		
		/* check the vehicle */
		if (findActiveBill (plateNumber) != null)
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
					//for debug
					Ps = new ParkingSpace (0, "PK001");
				    //return 0;
				}
				
				parkNumber = Ps.getParkNumber ();
				
				/*3. initialize a bill */
				regBill = initBill (0, 0, plateNumber, parkNumber, new Date(), null);
			}
			else
			{
				/*2.2 process as vip user, get the parking from reservation */
				parkNumber = Rev.getParkNumber();
				
				/*3. initialize a bill */
				regBill = initBill (Rev.getRid(), Rev.getUid(), 
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
			System.out.println("findReservationByPlateNumber exception!!!!");
			return 0;
		}
	}
	
	public RegisterBill vehicleExit(String plateNumber)
	{
		/*1. find the bill according to the plateNumber */
		RegisterBill regBill = findActiveBill (plateNumber);
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
		double fee = completeBill (regBill);
		regBill.setFee(fee);
		
		return regBill;
	}
	
	public boolean setExitTime(String plateNumber, int Hours)
	{
		try
		{ 
			RegisterBill regBill = findActiveBill (plateNumber);
			
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
	/* -------------- liwen 3/17: vehicle entry/exit process end   -------------------- */
	
}
