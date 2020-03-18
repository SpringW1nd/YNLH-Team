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

	
	/* -------------- liwen 3/17 : park space management begin -------------------- */
	public int getIdleParkCount();
	public List<ParkingSpace> getIdleParkSet();
	/* -------------- liwen 3/17: park space management end    -------------------- */
	
	/* -------------- liwen 3/17: vehicle entry/exit process begin -------------------- */
	public int vehicleEntry(String plateNumber);
	public RegisterBill vehicleExit(String plateNumber);
	/* -------------- liwen 3/17: vehicle entry/exit process end   -------------------- */
	
	/* -------------- liwen 3/17: bill management begin   -------------------- */
	public boolean payBill(int bid);
	
	public List<RegisterBill> listBill(String account);
	
	/* -------------- liwen 3/17: bill management begin   -------------------- */
	
	
}
