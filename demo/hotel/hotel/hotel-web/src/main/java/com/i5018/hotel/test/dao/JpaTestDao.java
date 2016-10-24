package com.i5018.hotel.test.dao;

import com.i5018.common.dao.GenericRepository;
import com.i5018.hotel.test.bean.Employee;

public interface JpaTestDao extends GenericRepository<Employee, Integer> {
	
	Employee getByLastName(String lastName);
	
}
