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

}
