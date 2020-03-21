package com.YNLH.park.service.Impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.YNLH.park.dao.entity.ParkingSpace;
import com.YNLH.park.dao.mapper.ParkMapper;

@Service
public class ParkingServiceImpl 
{
	private static ApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext.xml");
	private static ParkMapper parkMapper = appCtx.getBean(ParkMapper.class);
	
	public int getIdleParkCount()
	{
		List<ParkingSpace> setPs = parkMapper.getIdleParking();
		if (setPs == null)
		{
			return 0;
		}
		
		return setPs.size();
	}

	public List<ParkingSpace> getIdleParkSet()
	{
		return parkMapper.getIdleParking();		
	}
	
	public int AddParking(int level, String parkNumber)
	{
		return 1;
	}
	
	public int UpdateParkingStatus(int level, String parkNumber, int status)
	{
		return 1;
	}
}
