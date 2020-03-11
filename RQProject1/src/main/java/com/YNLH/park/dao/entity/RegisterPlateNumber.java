package com.YNLH.park.dao.entity;


public class RegisterPlateNumber {
	private int pid;
	private String plateNumber;
	private int uid;
	public int getPid()
	{
		return pid;
	}
	public void setPid(int pid)
	{
		this.pid=pid;
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
