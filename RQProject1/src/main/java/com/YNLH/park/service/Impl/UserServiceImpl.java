package com.YNLH.park.service.Impl;

import java.util.List;
import com.YNLH.park.dao.entity.RegisterPlateNumber;

import javax.print.attribute.standard.Media;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.YNLH.park.dao.entity.User;
import com.YNLH.park.dao.mapper.UserMapper;
import com.YNLH.park.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	//logger
	private final static Logger logger = Logger.getLogger(UserServiceImpl.class);
	@Override
	public User registerUser(String account, String password, String name, String email, String phone)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserMapper userMapper = ctx.getBean(UserMapper.class);
		
		//if(!userMapper.findUser(account)*)
		//{
			//Jump to register failed page
		//}
		int uid=0;
		User user = new User();
		user.setUid(0);					//The uid we be set in the database automatically
		user.setAccount(account);
		user.setPassword(password);
		user.setName(name);
		user.setEmail(email);
		user.setPhone(phone);
		
		
		uid = userMapper.registerUser(user);		//return the uid of the new account	
		return user;
		
	}
	@Override
	public List<User> listUsers()
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserMapper userMapper = ctx.getBean(UserMapper.class);
		List<User> medialist = userMapper.selectAllUser();
		return medialist;
	}
	public User findUser(String account)		
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserMapper userMapper = ctx.getBean(UserMapper.class);
		User user=null;
		//user=userMapper.findUser(account);
		return user;
	}
	public User findUserById(int uid)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserMapper userMapper = ctx.getBean(UserMapper.class);
		User user=null;
		//user=userMapper.findUserById(uid);
		return user;
	}
	public User login(String account, String password)		//return uid
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserMapper userMapper = ctx.getBean(UserMapper.class);
		User user=null;
		//user=userMapper.login(account,password);
		return user;
	}
	
	
	
	//public int applyContract(Date startDate, Date endDate, int uid, int pid);
	
	
	
	
	
	//public int insertUser(int uid, String Name, int type);
	//public List<User> selectAllUser();
	
	
	
	
	
	
	
	
	/*
	@Override
	public int insertUser(int uid, String Name, int type) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserMapper userMapper = ctx.getBean(UserMapper.class);
		User user = new User();
		user.setUid(uid);
		user.setName(Name);
		user.setType(type);
		return userMapper.insertUser(user);
	}

	@Override
	public List<User> selectAllUser() {
		 ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		 UserMapper userMapper = ctx.getBean(UserMapper.class);
		 List<User> medialist = userMapper.selectAllUser();
		 return medialist;
	}
*/
}
