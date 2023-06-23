package com.nurim.oecdstat.meta.service;

import java.sql.SQLException;
import java.util.List;

import com.nurim.oecdstat.meta.bean.MetaBean;

public interface MetaService {
	public List<MetaBean> selectBeanList(String queryId, MetaBean metaBean)  throws SQLException;
	
	public MetaBean selectBean(String queryId, MetaBean metaBean)  throws SQLException;
	
	public int selectInt(String queryId, MetaBean metaBean)  throws SQLException;
	
	public String selectString(String queryId, MetaBean metaBean) throws SQLException;
}
