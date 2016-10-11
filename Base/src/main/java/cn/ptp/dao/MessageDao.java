package cn.ptp.dao;

import org.springframework.data.repository.query.Param;

import cn.ptp.entity.Message;

public interface MessageDao 
{
	public Message findById(@Param("id") int id);
}
