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
public class ParkServiceImpl implements ParkService{
	//logger
	private final static Logger logger = Logger.getLogger(ParkServiceImpl.class);
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
		
		
		if(this.higherLevel.getAvailableNum()>0)
		{
			Reservation reservation=new Reservation();
			reservation.setUid(uid);
			reservation.setStartDate(rStartDate);
			reservation.setEndDate(rEndDate);
			reservation.setPlateNumber(plateNumber);
			reservation.setName(user.getName());
			reservation.setEmail(user.getEmail());
			reservation.setPhone(user.getPhone());
			reservation.setParkingSpace("201");
			int rid=0;
			
			try{
				rid=parkMapper.makeReservation(reservation);		//rid=1 success, 0 fail
			}catch(Exception e) {}
			
			//this.addRegisterPlateNumber(uid, plateNumber);
			
			this.higherLevel.setAvailableNum(higherLevel.getAvailableNum()-1);
			
			reservation=this.findReservationByPlateNumber(plateNumber);
			rid=reservation.getRid();
			
			long minute=rEndDate.getTime()-rStartDate.getTime();
			this.addRegisterBill(uid,rid,minute*0.02/1000.0/60.0);		//fee rate: 0.02
			
			return reservation;
		}
		return null;
	}
	
	public List<Reservation> listReservation(String account)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		
		UserServiceImpl service=new UserServiceImpl();
		User user=service.findUser(account);
		
		int uid=user.getUid();
		
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
			this.higherLevel.setAvailableNum(higherLevel.getAvailableNum()+1);
			this.deleteRegisterBill(rid);
			try {
				parkMapper.cancelReservation(rid);
			}catch(Exception e) {}
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
		try {
			bid=parkMapper.addRegisterBill(registerBill);
		}catch(Exception e) {}
		registerBill.setBid(bid);
		return registerBill;
		
	}
	public List<RegisterBill> listRegisterBill(String account)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		
		UserServiceImpl service=new UserServiceImpl();
		User user=service.findUser(account);
		
		int uid=user.getUid();
		
		List<RegisterBill> bills=null;
		try{
			bills=parkMapper.listRegisterBill(uid);
		}catch(Exception e) {}
		return bills;
	}
	public RegisterBill findRegisterBill(int rid)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		RegisterBill registerBill=null;
		try{
			registerBill=parkMapper.findRegisterBill(rid);
		}catch(Exception e) {}
		return registerBill;
	}
	
	public boolean deleteRegisterBill(int rid)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		
		/*if(findRegisterBill(rid)==null)
		{
			return false;
		}
		else
		{	
		*/
		try {
				parkMapper.deleteRegisterBill(rid);
			}catch(Exception e) {}
		//}
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
			this.deleteRegisterBill(registerBill.getRid());
			registerBill.setFee(registerBill.getFee()+0.03*diff);
			this.addRegisterBill(registerBill.getUid(), registerBill.getRid(), registerBill.getFee());
		}
	}
	public boolean payBill(int rid)
	{
		RegisterBill registerBill=this.findRegisterBill(rid);
		this.deleteRegisterBill(rid);
		registerBill.setFee(0);
		this.addRegisterBill(registerBill.getUid(), registerBill.getRid(), registerBill.getFee());
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
		try {
			wid=parkMapper.addWalkInUser(walkInUser);
		}catch(Exception e) {}
		return walkInUser;
		
	}
	
	public WalkInUser findWalkInUser(String plateNumber)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		
		WalkInUser walkInUser=null;
		try{
			walkInUser=parkMapper.findWalkInUser(plateNumber);
		}catch(Exception e) {}
		return walkInUser;
		
	}
	
	public double walkUserOut(String plateNumber, Date wEndDate)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		
		WalkInUser walkInUser=findWalkInUser(plateNumber);
		if(walkInUser==null)
			return 0.0;
		walkInUser.setEndDate(wEndDate);
		double fee=(walkInUser.getEndDate().getTime()-walkInUser.getStartDate().getTime())/1000.0/60.0*0.03; //0.03 fee rate
		try {
			parkMapper.deleteWalkInUser(walkInUser.getWid());
		}catch(Exception e) {}
		return fee;
		
	}
	
	
	
}
