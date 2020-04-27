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
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("exception in addRegisterPlateNumber:"+e.getMessage());
		}
			
		
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
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("exception in listRegisterPlateNumber:"+e.getMessage());
		}
		
		return registerPlateNumbers;
		
	}
	
	public RegisterPlateNumber findRegisterPlateNumber(String plateNumber)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		
		RegisterPlateNumber registerPlateNumber=null;
		try {
			registerPlateNumber=parkMapper.findRegisterPlateNumber(plateNumber);
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("exception in findRegisterPlateNumber:"+e.getMessage());
		}
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
			return null;
		}
		else
		{
			Ps.setStatus(1);
 			parkMapper.updateParking(Ps);
			
			Reservation reservation=new Reservation();
			reservation.setUid(uid);
			reservation.setPlateNumber(plateNumber);
			reservation.setParkNumber(Ps.getParkNumber());
			reservation.setrStartDate(rStartDate);
			reservation.setrEndDate(rEndDate);
			int rid=0;
			
			try
			{
				rid=parkMapper.makeReservation(reservation);		//rid=1 success, 0 fail
				rid = reservation.getRid();
			}
			catch(Exception e) {
				e.printStackTrace();
				logger.error("exception in makeReservation:"+e.getMessage());
			}
			
 			return reservation;
		}

	}
	
	public List<Reservation> listReservation(int uid)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		
		List<Reservation> reservations=null;
		try {
			reservations=parkMapper.listReservation(uid);
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("exception in listReservation:"+e.getMessage());
		}
		return reservations;
		
	}
	
	public Reservation findReservation(int rid)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		Reservation reservation=null;
		try {
			reservation=parkMapper.findReservation(rid);
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("exception in findReservation:"+e.getMessage());
		}
		return reservation;
	}
	public Reservation findReservationByPlateNumber(String plateNumber)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		Reservation reservation=null;
		try {
			reservation=parkMapper.findReservationByPlateNumber(plateNumber);
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("exception in findReservationByPlateNumber:"+e.getMessage());
		}
		return reservation;
	}
	
	public boolean cancelReservation(int rid)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		Reservation reservation = findReservation(rid);
		if(reservation==null)
		{
			return false;
		}
		else
		{
			ParkingSpace Ps = parkMapper.findParking(reservation.getParkNumber());
			Ps.setStatus(0);
			parkMapper.updateParking(Ps);
			/* update Parking Space */
			// to do 
			
			try {
				parkMapper.cancelReservation(rid);
			}catch(Exception e) {
				e.printStackTrace();
				logger.error("exception in cancelReservation:"+e.getMessage());
			}
		}
		return true;
	}
}
