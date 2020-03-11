package com.YNLH.park.service.Impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.YNLH.park.dao.entity.*;
import com.YNLH.park.dao.mapper.*;
import com.YNLH.park.service.*;


public class ParkServiceImpl implements ParkService{
	//logger
	private final static Logger logger = Logger.getLogger(UserServiceImpl.class);
	private ParkingSpace groundLevel;
	private ParkingSpace higherLevel;
	
	ParkServiceImpl()
	{
		groundLevel=new ParkingSpace();
		higherLevel=new ParkingSpace();
		
		groundLevel.setLevel(1);
		groundLevel.setAvailableNum(10);
		
		higherLevel.setLevel(2);
		higherLevel.setAvailableNum(20);
	}
	

	
	public RegisterPlateNumber addRegisterPlateNumber(int uid, String plateNumber)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		
		RegisterPlateNumber registerPlateNumber=new RegisterPlateNumber();
		
		registerPlateNumber.setUid(uid);
		registerPlateNumber.setPlateNumber(plateNumber);
		
		int pid=0;
		//pid = parkMapper.addPlateNumber(registerPlateNumber);		
		
		registerPlateNumber.setPid(pid);
		
		
		return registerPlateNumber;
		
	}
	public List<RegisterPlateNumber> listRegisterPlateNumber(int uid)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		
		List<RegisterPlateNumber> registerPlateNumbers=null;
		//registerPlateNumbers=parkMapper.listRegisterPlateNumber(uid);
		return registerPlateNumbers;
		
	}
	
	public RegisterPlateNumber findRegisterPlateNumber(String plateNumber)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		
		RegisterPlateNumber registerPlateNumber=null;
		//registerPlateNumber=parkMapper.findRegisterPlateNumber(String plateNumber);
		return registerPlateNumber;
	}

	public Reservation makeReservation(int uid, Date rStartDate, Date rEndDate, String plateNumber)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		
		if(this.higherLevel.getAvailableNum()>0)
		{
			Reservation reservation=new Reservation();
			reservation.setUid(uid);
			reservation.setStartDate(rStartDate);
			reservation.setEndDate(rEndDate);
			reservation.setPlateNumber(plateNumber);
			int rid=0;
			//rid=parkMapper.makeReservation(reservation);
			
			this.addRegisterPlateNumber(uid, plateNumber);
			
			this.higherLevel.setAvailableNum(higherLevel.getAvailableNum()-1);
			
			long minute=rStartDate.getTime()-rEndDate.getTime();
			this.addRegisterBill(uid,rid,minute*0.02/1000.0/60.0);		//fee rate: 0.02
			
			return reservation;
		}
		return null;
	}
	
	public List<Reservation> listReservation(int uid)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		
		List<Reservation> reservations=null;
		//reservations=parkMapper.listReservation(uid);
		return reservations;
		
	}
	
	public Reservation findReservation(int rid)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		Reservation reservation=null;
		//reservation=parkMapper.findReservation(rid);
		return reservation;
	}
	public Reservation findReservationByPlateNumber(String plateNumber)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		Reservation reservation=null;
		//reservation=parkMapper.findReservationByPlateNumber(plateNumber);
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
			this.higherLevel.setAvailableNum(higherLevel.getAvailableNum()+1);
			this.deleteRegisterBill(rid);
			//return parkMapper.cancelReservation(rid);
		}
		return true;
	}
	public RegisterBill addRegisterBill(int uid,int rid,double fee)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		RegisterBill registerBill=new RegisterBill();
		registerBill.setUid(uid);
		registerBill.setRid(rid);
		registerBill.setFee(fee);
		
		int bid=0;
		//bid=parkMapper.addRegisterBill(registerBill);
		registerBill.setBid(bid);
		return registerBill;
		
	}
	public List<RegisterBill> listRegisterBill(int uid)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		
		List<RegisterBill> bills=null;
		//bills=parkMapper.listRegisterBill(uid);
		return bills;
	}
	public RegisterBill findRegisterBill(int rid)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		RegisterBill registerBill=null;
		//registerBill=parkMapper.findRegisterBill(rid);
		return registerBill;
	}
	
	public boolean deleteRegisterBill(int rid)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		if(findRegisterBill(rid)==null)
		{
			return false;
		}
		else
		{
			//return parkMapper.deleteRegisterBill(rid);
		}
		return true;
	}
	public boolean registerUserIn(String plateNumber,Date time)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		
		Reservation reservation=this.findReservationByPlateNumber(plateNumber);
		if(reservation!=null)
		{
			
			if(reservation.getStartDate().getTime()<time.getTime()&&reservation.getEndDate().getTime()>time.getTime())
			{
				return true;
			}
			return false;
		}
		else
		{
			return false;
		}
	}
	
	public void registerUserOut(String plateNumber, Date time)
	{
		this.higherLevel.setAvailableNum(higherLevel.getAvailableNum()+1);
		Reservation reservation=this.findReservationByPlateNumber(plateNumber);
		if(reservation.getEndDate().getTime()<time.getTime())		//time out
		{
			long diff=time.getTime()-reservation.getEndDate().getTime()/1000/60;
			RegisterBill registerBill=this.findRegisterBill(reservation.getRid());
			registerBill.setFee(registerBill.getFee()+0.03*diff);
		}
	}
	public boolean payBill(int rid)
	{
		RegisterBill registerBill=this.findRegisterBill(rid);
		registerBill.setFee(0);
		return true;
	}
	
	public int showGroundAvailable()
	{
		return this.groundLevel.getAvailableNum();
	}
	public int showResAvailable()
	{
		return this.higherLevel.getAvailableNum();
	}
	
	public WalkInUser walkUserIn(String plateNumber, Date wStartDate)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		
		WalkInUser walkInUser=new WalkInUser();
		walkInUser.setPlateNumber(plateNumber);
		walkInUser.setStartDate(wStartDate);
		int wid=0;
		//wid=parkMapper.addWalkInUser(walkInUser);
		return walkInUser;
		
	}
	
	public WalkInUser findWalkInUser(String plateNumber)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		
		WalkInUser walkInUser=null;
		//walkInUser=parkMapper.findWalkInUser(plateNumber);
		return walkInUser;
		
	}
	
	public double walkUserOut(String plateNumber, Date wEndDate)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		
		WalkInUser walkInUser=findWalkInUser(plateNumber);
		walkInUser.setEndDate(wEndDate);
		double fee=walkInUser.getEndDate().getTime()-walkInUser.getStartDate().getTime()/1000.0/60.0*0.03; //0.03 fee rate
		return fee;
		
	}
	
	
	
}
