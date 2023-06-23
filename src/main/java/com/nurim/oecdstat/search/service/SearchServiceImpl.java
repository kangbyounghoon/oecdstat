package com.nurim.oecdstat.search.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nurim.oecdstat.search.bean.SearchBean;
import com.nurim.oecdstat.search.dao.SearchDAO;

/**
 * 통합검색 ServiceImpl 클래스
 * <p><b>NOTE:</b>
 * @author 공공사업1팀 함아름
 * @since 2020.09.06
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일			수정자           수정내용
 *  ---------			----------		---------------------------
 *   2020.09.06	함아름          최초 생성
 *
 * </pre>
 */

@Service
public class SearchServiceImpl implements SearchService {
	/** 통합검색 DAO Class */
	@Autowired private SearchDAO searchDAO;
	
	@Override
	public List<SearchBean> selectMapList(String queryId, SearchBean searchBean) throws SQLException {
		return searchDAO.selectMapList(queryId, searchBean);
	}
}
