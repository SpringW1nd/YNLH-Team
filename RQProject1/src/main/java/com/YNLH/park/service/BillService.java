package com.YNLH.park.service;

import java.util.Date;
import java.util.List;

import com.YNLH.park.dao.entity.RegisterBill;

public interface BillService 
{
	public RegisterBill initBill(int rid, int uid, String plateNumber, String parkNumber, Date entryTime, Date exitTime);
	
	public RegisterBill findActiveBill(String plateNumber);
	public List<RegisterBill> listBill(String account);
	public double getPakringTime (Date entryTime, Date exitTime);
	public double completeBill(RegisterBill regBill);
	
	public boolean payBill(int bid);
	public boolean isBillPayed(int bid);
	
	

}
