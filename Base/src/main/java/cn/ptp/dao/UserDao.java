package cn.ptp.dao;

import cn.ptp.entity.User;

public interface UserDao
{
	public User findById(int id);
	public User findByUsername(String username);
}
