package com.YNLH.park.dao.entity;

import java.util.Date;

public class RegisterBill {
	private int bid;
	private int rid;	
	private int uid;
	private int status;
	
	private double fee;
	
	private String plateNumber;
	private String parkNumber;
	
	private Date entryTime;
	private Date exitTime;
	public RegisterBill ()
	{
		
	}
	
	public RegisterBill (int rid, int uid, 
			             String plateNumber, String parkNumber, 
			             Date entryTime, Date exitTime)
	{
		this.bid = 0;
		this.rid = rid;
		this.uid = uid;
		this.fee = 0.0;
		this.status = 0;
		
		this.plateNumber = plateNumber;
		this.parkNumber  = parkNumber;
		
		this.entryTime   = entryTime;
		this.exitTime    = exitTime;		
	}
	
	public int getBid()
	{
		return bid;
	}
	
	public void setBid(int bid)
	{
		this.bid=bid;
	}
	
	public int getRid()
	{
		return rid;
	}
	
	public void setUid(int uid)
	{
		this.uid=uid;
	}
	
	public int getUid()
	{
		return uid;
	}
	
	public void setRid(int rid)
	{
		this.rid=rid;
	}
	
	public double getFee()
	{
		return fee;
	}
	
	public void setFee(double fee)
	{
		this.fee=fee;
	}
	
	public int getStatus()
	{
		return status;
	}
	
	public void setStatus(int status)
	{
		this.status = status;
	}
	
	public Date getEntryTime()
	{
		return entryTime;
	}
	
	public void setEntryTime(Date entryTime)
	{
		this.entryTime = entryTime;
	}
	
	public Date getExitTime()
	{
		return exitTime;
	}
	
	public void setExitTime(Date exitTime)
	{
		this.exitTime = exitTime;
	}
	
	public String getPlateNumber()
	{
		return plateNumber;
	}
	
	public void setPlateNumber(String plateNumber)
	{
		this.plateNumber = plateNumber;
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
