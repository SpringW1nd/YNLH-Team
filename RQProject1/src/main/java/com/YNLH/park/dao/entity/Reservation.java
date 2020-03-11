package com.YNLH.park.dao.entity;

import java.util.List;
import java.util.Date;

public class Reservation {

	private int rid;
	private Date rStartDate;
	private Date rEndDate;
	private String plateNumber;
	private int uid;

	public int getRid()
	{
		return rid;
	}
	public void setRid(int rid)
	{
		this.rid=rid;
	}
	public Date getStartDate()
	{
		return rStartDate;
	}
	public void setStartDate(Date rStartDate)
	{
		this.rStartDate=rStartDate;
	}
	public Date getEndDate()
	{
		return rEndDate;
	}
	public void setEndDate(Date rEndDate)
	{
		this.rEndDate=rEndDate;
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
	
	
}
