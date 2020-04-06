package com.YNLH.park.dao.mapper;

import java.util.List;
import java.util.Map;

import com.YNLH.park.dao.entity.Manager;
import com.YNLH.park.dao.entity.ParkingSpace;
import com.YNLH.park.dao.entity.RegisterBill;

public interface ManagerMapper {
	public Manager login(String account,String password) throws Exception;
	public List<ParkingSpace> selectParkingSpace(ParkingSpace parkingSpace);
	public int updateParkingSpace(ParkingSpace parkingSpace);
	public List<RegisterBill> selectAllRegisterBill(); 
}
