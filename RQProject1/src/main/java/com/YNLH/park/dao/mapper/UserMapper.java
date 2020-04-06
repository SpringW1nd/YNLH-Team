package com.YNLH.park.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.YNLH.park.dao.entity.User;
@Repository
public interface UserMapper {
	public int registerUser(User user);
	public int insertUser(User user);
	public List<User> selectAllUser();
	public User findUser(String account);
	public User findUserById(int uid);
	public User login(User user) throws Exception;
	public int updateUser(User user);
}
