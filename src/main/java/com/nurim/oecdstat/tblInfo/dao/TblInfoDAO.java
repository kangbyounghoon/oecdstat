package com.nurim.oecdstat.tblInfo.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.nurim.oecdstat.common.dao.AbstractDAO;
import com.nurim.oecdstat.tblInfo.bean.TblInfoBean;


@Repository
public class TblInfoDAO extends AbstractDAO {
	
	public List<TblInfoBean> selectBeanList(String queryId, TblInfoBean tblInfoBean) throws SQLException {
		List<TblInfoBean> list = selectList(queryId, tblInfoBean);			

		return list;
	}
	
	public TblInfoBean selectBean(String queryId, TblInfoBean tblInfoBean) throws SQLException {
		TblInfoBean list = (TblInfoBean) selectOne(queryId, tblInfoBean);			
		
		return list;
	}
	
	
	public int selectInt(String queryId,  TblInfoBean tblInfoBean) throws SQLException {
		int result = getSelect(queryId, tblInfoBean);			
		
		return result;
	}
	
	
	public String selectString(String queryId, TblInfoBean tblInfoBean) throws SQLException{
		String result = (String) selectOne(queryId, tblInfoBean);
		
		return result;
	}
	

}