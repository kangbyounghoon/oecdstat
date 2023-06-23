package com.nurim.oecdstat.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.nurim.oecdstat.board.bean.BoardBean;
import com.nurim.oecdstat.common.dao.AbstractDAO;

@Repository
public class BoardDAO extends AbstractDAO {
	
	public List<BoardBean> selectBeanList(String queryId, BoardBean boardBean) throws SQLException {
		List<BoardBean> list = selectList(queryId, boardBean);			

		return list;
	}
	
	public BoardBean selectBean(String queryId,  BoardBean boardBean) {
		BoardBean result = (BoardBean) selectOne(queryId, boardBean);			

		return result;
	}
	
	public int selectInt(String queryId,  BoardBean boardBean) throws SQLException {
		int result = getSelect(queryId, boardBean);			
		
		return result;
	}
	
	public List<Map<String, Object>> selectMapList(String queryId, Map<String, Object> param) throws SQLException {
		List<Map<String, Object>> list = selectList(queryId, param);			

		return list;
	}

	public boolean dataInsert(String queryId, BoardBean boardBean) throws SQLException{
		insert(queryId, boardBean);
		return true;
	}
	
	public boolean dataUpdate(String queryId, BoardBean boardBean) {
		update(queryId, boardBean);
		return true;
	}

	public boolean dataDelete(String queryId, String board_seq) {
		delete(queryId, board_seq);
		return true;
	}

	/**
	 * 게시글 비밀번호 조회
	 * @param queryId, boardBean
	 * @exception SQLException
	 * */
	public String getSelectString(String queryId, BoardBean boardBean) throws SQLException{
		printQueryId(queryId);
		String result = (String) selectOne(queryId, boardBean);
		
		return result;
	}
}