package com.YNLH.park.dao.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.YNLH.park.dao.entity.*;

public interface ParkMapper {
	public int addPlateNumber(RegisterPlateNumber registerPlateNumber);
	public List<RegisterPlateNumber> listRegisterPlateNumber(int uid) throws Exception;
	public RegisterPlateNumber findRegisterPlateNumber(String plateNumber) throws Exception;
	public int makeReservation(Reservation reservation);
	public List<Reservation> listReservation(int uid) throws Exception;
	public Reservation findReservation(int rid) throws Exception;
	public Reservation findReservationByPlateNumber(String plateNumber) throws Exception;
	public boolean cancelReservation(int rid);
	
	/* ---------------------- liwen 3/17: bill management -----------------*/
	/* add a bill */
	public int addRegisterBill(RegisterBill registerBill) throws Exception;
	
	/* find the active bill through plate number */
	public RegisterBill findActiveBill(String plateNumber) throws Exception;
	
	/* complete bill while car exits */
	public void completeBill (@Param("bid") int bid, @Param("fee")double fee, @Param("exitTime")Date exitTime) throws Exception;
	
	/* pay the bill */
	public void payBill(@Param("bid") int bid) throws Exception;
	
	/* find bill through bill id*/
	public RegisterBill findBill(@Param("bid") int bid) throws Exception;
	
	/* for debug: set exit time */
	public void setExitTime(@Param("bid") int bid, @Param("exitTime")Date exitTime) throws Exception;
	
	public List<RegisterBill> listRegisterBill(int uid) throws Exception;
	public boolean deleteRegisterBill(int rid);
	/* ---------------------- liwen 3/17: bill management -----------------*/
	
	/* ---------------------- liwen 3/17: Parking Space management -----------------*/
	public ParkingSpace allotParking();
	public void updateParking(int level, String parkNumber, int status);
	public List<ParkingSpace> getIdleParking();
	/* ---------------------- liwen 3/17: Parking Space management -----------------*/
	
}
