package com.YNLH.park.dao.entity;
import java.util.List;
import java.util.Date;

public class WalkInUser {
	private int wid;
	private String plateNumber;
	private Date wStartDate;
	private Date wEndDate;
	private double fee;
	
	public int getWid()
	{
		return wid;
	}
	public void setWid(int wid)
	{
		this.wid=wid;
	}
	
	public String getPlateNumber()
	{
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber)
	{
		this.plateNumber=plateNumber;
	}
	public Date getStartDate()
	{
		return wStartDate;
	}
	public void setStartDate(Date startDate)
	{
		this.wStartDate=startDate;
	}
	public Date getEndDate()
	{
		return wEndDate;
	}
	public void setEndDate(Date endDate)
	{
		this.wEndDate=endDate;
	}
	public double getFee()
	{
		return fee;
	}
	public void setFee(double fee)
	{
		this.fee=fee;
	}
	
}
