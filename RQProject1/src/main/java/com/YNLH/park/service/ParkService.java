package com.YNLH.park.service;
import java.util.List;
import java.util.Date;

import com.YNLH.park.dao.entity.*;


public interface ParkService {
	

	
	public RegisterPlateNumber addRegisterPlateNumber(int uid, String plateNumber);
	public List<RegisterPlateNumber> listRegisterPlateNumber(String account);	//List all PlateNumbers of a user
	public RegisterPlateNumber findRegisterPlateNumber(String plateNumber);
	
	
	
	public Reservation makeReservation(String account, Date rStartDate, Date rEndDate, String plateNumber);
	public List<Reservation> listReservation(int uid);
	public Reservation findReservation(int rid);
	public Reservation findReservationByPlateNumber(String plateNumber);
	public boolean cancelReservation(int rid);
	//public int extendReservation(int rid, Date REndDate);
	
	public boolean registerUserIn(String plateNumber,Date time);
	public void registerUserOut(String plateNumber,Date time);
	
	public WalkInUser walkUserIn(String plateNumber, Date wStartDate);
	public double walkUserOut(String plateNumber, Date wEndDate);	//return the bill
	
	//public int sendBillEmail(int uid);
	
	public int showGroundAvailable();
	public int showResAvailable();
	
	public RegisterBill addRegisterBill(int uid,int rid,double fee);
	public boolean deleteRegisterBill(int rid);
	public RegisterBill findRegisterBill(int rid);
	public List<RegisterBill> listRegisterBill(String account);		//List all bill of one user
	public boolean payBill(int bid);
	
	public WalkInUser findWalkInUser(String plateNumber);
	
}
