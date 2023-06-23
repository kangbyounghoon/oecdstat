package com.nurim.oecdstat.system.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nurim.oecdstat.common.bean.ServiceBean;
import com.nurim.oecdstat.system.dao.SystemDAO;

@Service
public class SystemServiceImpl implements SystemService {

	@Autowired
	private SystemDAO systemDAO;
	
	

	public List<ServiceBean> selectBeanList(String queryId, ServiceBean serviceBean) throws SQLException {
		List<ServiceBean> list = systemDAO.selectBeanList(queryId ,serviceBean);
		return list;
	}

	public ServiceBean selectBean(String queryId, ServiceBean serviceBean) throws SQLException {
		ServiceBean result = systemDAO.selectBean(queryId ,serviceBean);
		return result;
	}
	
	public int selectInt(String queryId, ServiceBean serviceBean) throws SQLException {
		int result = systemDAO.selectInt(queryId ,serviceBean);
		return result;
	}
	
	public String selectString(String queryId, ServiceBean serviceBean) throws SQLException {
		String result = systemDAO.selectString(queryId ,serviceBean);
		return result;
	}
	
	public boolean insert(String queryId, ServiceBean serviceBean) throws SQLException {
		boolean result = true;
		result = systemDAO.dataInsert(queryId ,serviceBean);
		return result;
	}

	
	public boolean update(String queryId, ServiceBean serviceBean) throws SQLException {
		boolean result = true;
		result = systemDAO.dataUpdate(queryId ,serviceBean);
		return result;
	}
	
	public boolean delete(String queryId, ServiceBean serviceBean) throws SQLException {
		boolean result = true;
		result = systemDAO.dataDelete(queryId ,serviceBean);
		return result;
	}
	/** ---------- */




}