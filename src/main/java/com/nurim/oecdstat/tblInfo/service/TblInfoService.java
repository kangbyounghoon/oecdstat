package com.nurim.oecdstat.tblInfo.service;

import java.sql.SQLException;
import java.util.List;

import com.nurim.oecdstat.tblInfo.bean.TblInfoBean;

public interface TblInfoService {

	public List<TblInfoBean> selectBeanList(String queryId, TblInfoBean tblInfoBean)  throws SQLException;
	
	public TblInfoBean selectBean(String queryId, TblInfoBean tblInfoBean)  throws SQLException;
	
	public int selectInt(String queryId, TblInfoBean tblInfoBean)  throws SQLException;
	
	public String selectString(String queryId, TblInfoBean tblInfoBean) throws SQLException;
	
}