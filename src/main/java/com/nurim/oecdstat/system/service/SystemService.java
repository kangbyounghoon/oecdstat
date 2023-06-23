package com.nurim.oecdstat.system.service;

import java.sql.SQLException;
import java.util.List;

import com.nurim.oecdstat.common.bean.ServiceBean;

public interface SystemService {

	public List<ServiceBean> selectBeanList(String queryId, ServiceBean serviceBean)  throws SQLException;
	
	public ServiceBean selectBean(String queryId, ServiceBean serviceBean)  throws SQLException;
	
	public int selectInt(String queryId, ServiceBean serviceBean)  throws SQLException;
	
	public String selectString(String queryId, ServiceBean serviceBean)  throws SQLException;
	
	public boolean insert(String queryId, ServiceBean serviceBean)  throws SQLException;
	
	public boolean update(String queryId, ServiceBean serviceBean)  throws SQLException;
	
	public boolean delete(String queryId, ServiceBean serviceBean)  throws SQLException;
	

	
	
	
}