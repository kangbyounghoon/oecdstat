package com.nurim.oecdstat.meta.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nurim.oecdstat.meta.bean.MetaBean;
import com.nurim.oecdstat.meta.dao.MetaDAO;

@Service
public class MetaServiceImpl implements MetaService{

	@Autowired
	private MetaDAO metaDAO;
	
	/**
	 * 메타 목록 조회
	 * @param boardBean
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public List<MetaBean> selectBeanList(String queryId, MetaBean metaBean) throws SQLException {
		return metaDAO.selectBeanList(queryId, metaBean);
	}

	@Override
	public MetaBean selectBean(String queryId, MetaBean metaBean) throws SQLException {
		return metaDAO.selectBean(queryId, metaBean);
	}

	@Override
	public int selectInt(String queryId, MetaBean metaBean) throws SQLException {
		int result = metaDAO.selectInt(queryId, metaBean);
		return result;
	}

	@Override
	public String selectString(String queryId, MetaBean metaBean) throws SQLException {
		String result = metaDAO.selectString(queryId, metaBean);
		return result;
	}

}
