package com.YNLH.park.dao.entity;

import java.util.Date;

public class ParkingSpace {
	private int level;
	private String parkNumber;
	private int status;
	private int rid;
	private Date rStartDate;
	private Date rEndDate;
	private String plateNumber;
	private int uid;
	private String account;
	private String name;
	private String email;
	private String phone;
	
	public ParkingSpace () {}
	
	public ParkingSpace (int level, String parkNumber) 
	{
		this.level = level;
		this.parkNumber = parkNumber;
		this.status = 0;
	}
	
	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public Date getrStartDate() {
		return rStartDate;
	}

	public void setrStartDate(Date rStartDate) {
		this.rStartDate = rStartDate;
	}

	public Date getrEndDate() {
		return rEndDate;
	}

	public void setrEndDate(Date rEndDate) {
		this.rEndDate = rEndDate;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
