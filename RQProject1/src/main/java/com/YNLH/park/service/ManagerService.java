package com.YNLH.park.service;

import java.util.List;

import com.YNLH.park.dao.entity.Manager;
import com.YNLH.park.dao.entity.ParkingSpace;
import com.YNLH.park.dao.entity.RegisterBill;
import com.YNLH.park.dao.entity.User;

public interface ManagerService {
	public Manager login(String account, String password) throws Exception;
	public List<ParkingSpace> selectParkingSpace(ParkingSpace parkingSpace);
	public int updateParkingSpace(ParkingSpace parkingSpace);
	public List<User> selectAllUser();
	public User selectUserByuid(int uid);
	public int updateUser(User user);
	public List<RegisterBill> selectAllRegisterBill();
}
