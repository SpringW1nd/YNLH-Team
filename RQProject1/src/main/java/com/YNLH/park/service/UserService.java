package com.YNLH.park.service;

import java.util.List;

import com.YNLH.park.dao.entity.User;
import com.YNLH.park.dao.entity.RegisterPlateNumber;

public interface UserService {
	public User registerUser(String account, String password, String name, String email, String phone);
	//public int registerManager(String account, String password, String name, String email);
	public List<User> listUsers();		
	public User findUser(String account);		
	public User findUserById(int uid);	
	public User login(String account, String password);		
	
	//public int applyContract(Date startDate, Date endDate, int uid, int pid);

	
	//public int 
	
	
	
	
	//public int insertUser(int uid, String Name, int type);
	//public List<User> selectAllUser();
}
