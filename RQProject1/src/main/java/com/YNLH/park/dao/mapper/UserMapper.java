package com.YNLH.park.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.YNLH.park.dao.entity.User;
@Repository
public interface UserMapper {
	public int insertUser(User user);
	public List<User> selectAllUser();
}
