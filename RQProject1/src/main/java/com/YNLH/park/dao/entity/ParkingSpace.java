package com.YNLH.park.dao.entity;

public class ParkingSpace {
	private int level;
	private String parkNumber;
	private int status;
	
	public ParkingSpace () {}
	
	public ParkingSpace (int level, String parkNumber) 
	{
		this.level = level;
		this.parkNumber = parkNumber;
		this.status = 0;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public void setLevel(int level)
	{
		this.level=level;
	}
	
	public int getStatus()
	{
		return status;
	}
	
	public void setStatus(int status)
	{
		this.status = status;
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
