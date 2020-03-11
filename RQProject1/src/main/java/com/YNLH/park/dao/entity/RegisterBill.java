package com.YNLH.park.dao.entity;

public class RegisterBill {
	private int bid;
	private int rid;	
	private int uid;
	private double fee;
	
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
}
