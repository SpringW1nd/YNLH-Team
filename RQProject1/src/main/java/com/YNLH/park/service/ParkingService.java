package com.YNLH.park.service;

import java.util.List;

import com.YNLH.park.dao.entity.ParkingSpace;

public interface ParkingService 
{
	public int getIdleParkCount();
	public List<ParkingSpace> getIdleParkSet();
	public int AddParking(ParkingSpace Ps);
	public int UpdateParkingStatus(int level, String parkNumber, int status);

}
