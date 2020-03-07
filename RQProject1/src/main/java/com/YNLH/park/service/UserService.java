package com.YNLH.park.service;

import java.util.List;

import com.YNLH.park.dao.entity.User;

public interface UserService {
	public int insertUser(int uid, String Name, int type);
	public List<User> selectAllUser();
}
