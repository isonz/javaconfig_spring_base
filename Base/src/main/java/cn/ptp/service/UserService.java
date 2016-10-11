package cn.ptp.service;

import cn.ptp.entity.User;

public interface UserService extends BaseService<User, Integer>
{
	public User findByUsername(String username);
}
