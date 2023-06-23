package com.nurim.oecdstat.search.service;

import java.sql.SQLException;
import java.util.List;

import com.nurim.oecdstat.search.bean.SearchBean;

/**
 * 통합검색 Service 클래스
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

public interface SearchService {
	public List<SearchBean> selectMapList(String queryId, SearchBean searchBean) throws SQLException;
}
