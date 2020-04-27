package com.YNLH.park.service.Impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.YNLH.park.dao.entity.Manager;
import com.YNLH.park.dao.entity.ParkingSpace;
import com.YNLH.park.dao.entity.RegisterBill;
import com.YNLH.park.dao.entity.User;
import com.YNLH.park.dao.mapper.ManagerMapper;
import com.YNLH.park.dao.mapper.ParkMapper;
import com.YNLH.park.dao.mapper.UserMapper;
import com.YNLH.park.service.ManagerService;

@Service
public class ManagerServicelmpl implements ManagerService {
	
	//logger
	private final static Logger logger = Logger.getLogger(ManagerServicelmpl.class);
	@Override
	public Manager login(String account, String password) throws Exception  {
		
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ManagerMapper managerMapper = ctx.getBean(ManagerMapper.class);
		Manager manager = null;
		manager = managerMapper.login(account, password);
		return manager;
		
	}
	@Override
	public List<ParkingSpace> selectParkingSpace(ParkingSpace parkingSpace) {
		List<ParkingSpace> resultList = null;
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ManagerMapper managerMapper = ctx.getBean(ManagerMapper.class);
		resultList = managerMapper.selectParkingSpace(parkingSpace);
		return resultList;
	}
	@Override
	public int updateParkingSpace(ParkingSpace parkingSpace) {
		int result = 1;
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ParkMapper parkMapper = ctx.getBean(ParkMapper.class);
		parkMapper.updateParking(parkingSpace);
		return result;
	}
	@Override
	public List<User> selectAllUser() {
		List<User> list = null;
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserMapper userMapper = ctx.getBean(UserMapper.class);
		list = userMapper.selectAllUser();
		return list;
	}
	@Override
	public User selectUserByuid(int uid) {
		User user = null;
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserMapper userMapper = ctx.getBean(UserMapper.class);
		try {
			user = userMapper.findUserById(uid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception in selectUserByuid:"+e.getMessage());
		}
		return user;
	}
	@Override
	public int updateUser(User user) {
		int result = 0;
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserMapper userMapper = ctx.getBean(UserMapper.class);
		result = userMapper.updateUser(user);
		return result;
	}
	@Override
	public List<RegisterBill> selectAllRegisterBill() {
		List<RegisterBill> list = null;
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		ManagerMapper managerMapper = ctx.getBean(ManagerMapper.class);
		list = managerMapper.selectAllRegisterBill();
		return list;
	}
}
