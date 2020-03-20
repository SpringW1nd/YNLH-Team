package com.YNLH.park.dao.entity;

import java.util.Date;

public class Reservation {

	private int rid;
	private int uid;
	
	private Date rStartDate;
	private Date rEndDate;
	
	private String plateNumber;
	private String parkNumber;

	public Date getrStartDate() 
	{
		return rStartDate;
	}
	
	public void setrStartDate(Date rStartDate) 
	{
		this.rStartDate = rStartDate;
	}
	
	public Date getrEndDate() 
	{
		return rEndDate;
	}
	
	public void setrEndDate(Date rEndDate) 
	{
		this.rEndDate = rEndDate;
	}
	
	public int getRid()
	{
		return rid;
	}
	
	public void setRid(int rid)
	{
		this.rid=rid;
	}
	
	public String getPlateNumber()
	{
		return plateNumber;
	}
	
	public void setPlateNumber(String plateNumber)
	{
		this.plateNumber=plateNumber;
	}
	
	public int getUid()
	{
		return uid;
	}
	public void setUid(int uid)
	{
		this.uid=uid;
	}
	
	public String getParkNumber() 
	{
		return parkNumber;
	}
	
	public void setParkNumber(String parkNumber) 
	{
		this.parkNumber = parkNumber;
	}
	
}
