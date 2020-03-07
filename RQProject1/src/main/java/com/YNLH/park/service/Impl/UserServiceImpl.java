package com.YNLH.park.service.Impl;

import java.util.List;

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

}
