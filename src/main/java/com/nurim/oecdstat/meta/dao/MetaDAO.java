package com.nurim.oecdstat.meta.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.nurim.oecdstat.common.dao.AbstractDAO;
import com.nurim.oecdstat.meta.bean.MetaBean;

@Repository
public class MetaDAO extends AbstractDAO{
	public List<MetaBean> selectBeanList(String queryId, MetaBean metaBean) throws SQLException {
		List<MetaBean> list = selectList(queryId, metaBean);			

		return list;
	}
	
	public MetaBean selectBean(String queryId, MetaBean metaBean) throws SQLException {
		MetaBean list = (MetaBean) selectOne(queryId, metaBean);			
		
		return list;
	}
	
	public int selectInt(String queryId, MetaBean metaBean) throws SQLException {
		int result = getSelect(queryId, metaBean);			
		
		return result;
	}
	
	public String selectString(String queryId, MetaBean metaBean) throws SQLException{
		String result = (String) selectOne(queryId, metaBean);
		
		return result;
	}

	public List<MetaBean> selectBeanList(String queryId, String list_id) {
		List<MetaBean> list = selectList(queryId, list_id);
		return list;
	}
}
