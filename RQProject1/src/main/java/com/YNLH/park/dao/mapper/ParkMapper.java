package com.YNLH.park.dao.mapper;

public interface ParkMapper {
	public int addPlateNumber(RegisterPlateNumber registerPlateNumber);
	public list<RegisterPlateNumber> listRegisterPlateNumber(int uid) throws Exception;
	public RegisterPlateNumber findRegisterPlateNumber(String plateNumber) throws Exception;
	public int makeReservation(Reservation reservation);
	public list<Reservation> listReservation(int uid) throws Exception;
	public Reservation findReservation(int rid) throws Exception;
	public Reservation findReservationByPlateNumber(String plateNumber) throws Exception;
	public boolean cancelReservation(int rid);
	public int addRegisterBill(RegisterBill registerBill);
	public List<RegisterBill> listRegisterBill(int uid) throws Exception;
	public RegisterBill findRegisterBill(int rid) throws Exception;
	public boolean deleteRegisterBill(int rid);
	public int addWalkInUser(WalkInUser walkInUser);
	public WalkInUser findWalkInUser(String plateNumber) throws Exception;
	
}
