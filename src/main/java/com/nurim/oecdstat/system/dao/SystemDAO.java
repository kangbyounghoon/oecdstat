package com.nurim.oecdstat.system.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.nurim.oecdstat.common.bean.ServiceBean;
import com.nurim.oecdstat.common.dao.AbstractDAO;

@Repository
public class SystemDAO extends AbstractDAO{

	protected Log log = LogFactory.getLog(SystemDAO.class);
	

	public List<ServiceBean> selectBeanList(String queryId, ServiceBean serviceBean) throws SQLException {
		List<ServiceBean> list = selectList(queryId, serviceBean);			

		return list;
	}

	public ServiceBean selectBean(String queryId,  ServiceBean serviceBean) {
		ServiceBean result = (ServiceBean) selectOne(queryId, serviceBean);			

		return result;
	}
	
	public int selectInt(String queryId,  ServiceBean serviceBean) throws SQLException {
		int result = getSelect(queryId, serviceBean);			
		
		return result;
	}
	
	public String selectString(String queryId,  ServiceBean serviceBean) throws SQLException {
//		String result = selectString(queryId, adminBean);
		String result = (String) selectOne(queryId, serviceBean);
		
		return result;
	}
	
	public boolean dataInsert(String queryId, ServiceBean serviceBean) throws SQLException{
		insert(queryId, serviceBean);
		return true;
	}
	
	public boolean dataUpdate(String queryId, ServiceBean serviceBean) {
		update(queryId, serviceBean);
		return true;
	}
	
	public boolean dataDelete(String queryId, ServiceBean serviceBean) {
		delete(queryId, serviceBean);
		return true;
	}

	
	
}