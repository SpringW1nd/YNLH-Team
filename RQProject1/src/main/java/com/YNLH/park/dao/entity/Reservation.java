package com.YNLH.park.dao.entity;

import java.util.List;
import java.util.Date;

public class Reservation {

	private int rid;
	private Date rStartDate;
	private Date rEndDate;
	private String plateNumber;
	private int uid;
	private String name;
	private String email;
	private String phone;
	private String parkingSpace;

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
	public String getParkingSpace() {
		return parkingSpace;
	}
	public void setParkingSpace(String parkingSpace) {
		this.parkingSpace = parkingSpace;
	}
	
}
