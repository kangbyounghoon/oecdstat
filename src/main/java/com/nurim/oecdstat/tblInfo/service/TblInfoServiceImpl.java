package com.nurim.oecdstat.tblInfo.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nurim.oecdstat.meta.bean.MetaBean;
import com.nurim.oecdstat.meta.dao.MetaDAO;
import com.nurim.oecdstat.tblInfo.bean.TblInfoBean;
import com.nurim.oecdstat.tblInfo.dao.TblInfoDAO;

@Service
public class TblInfoServiceImpl implements TblInfoService {

	@Autowired
	private TblInfoDAO tblInfoDAO;
	
	@Autowired 
	private MetaDAO metaDao;
	
	public List<TblInfoBean> selectBeanList(String queryId, TblInfoBean tblInfoBean) throws SQLException {
		List<TblInfoBean> list = tblInfoDAO.selectBeanList(queryId, tblInfoBean);
		
		if(list != null && list.size() > 0) {
			for(int i=0; i<list.size(); i++) {
				TblInfoBean temp = list.get(i);
				List<MetaBean> metaList = metaDao.selectBeanList("meta.selectMetaInfoList", temp.getTbl_id());
				
				if(metaList != null && metaList.size() > 0) {
					temp.setMetaList(metaList);
				}
			}
		}
		
		List<TblInfoBean> newList = new ArrayList<TblInfoBean>();
		
		for(int i=0; i < list.size(); i++) {
			List<MetaBean> temp = metaDao.selectBeanList("meta.selectMetaInfoList", list.get(i).getTbl_id());
			list.get(i).setMetaList(temp);
			newList.add(list.get(i));
		}
		return newList;
//		return tblInfoDAO.selectBeanList(queryId, tblInfoBean);
	}
	
	public TblInfoBean selectBean(String queryId, TblInfoBean tblInfoBean) throws SQLException {
		
		return tblInfoDAO.selectBean(queryId, tblInfoBean);
	}
	
	
	public int selectInt(String queryId, TblInfoBean tblInfoBean) throws SQLException {
		int result = tblInfoDAO.selectInt(queryId ,tblInfoBean);
		return result;
	}
	

	@Override
	public String selectString(String queryId, TblInfoBean tblInfoBean) throws SQLException {
		String result = tblInfoDAO.selectString(queryId ,tblInfoBean);
		return result;
	}



	
}