package com.nurim.oecdstat.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.nurim.oecdstat.board.bean.BoardBean;

public interface BoardService {

	public List<BoardBean> selectBeanList(String queryId, BoardBean boardBean)  throws SQLException;

	public BoardBean selectBean(String queryId, BoardBean boardBean)  throws SQLException;
	
	public int selectInt(String queryId, BoardBean boardBean)  throws SQLException;
	
	public boolean updateViewCount(String queryId, BoardBean boardBean)  throws SQLException;

	public boolean delete(String queryId, String board_seq) throws SQLException;

	public boolean insert(String queryId, BoardBean boardBean) throws SQLException;

	public String selectString(String queryId, BoardBean boardBean) throws SQLException;

	public boolean update(String queryId, BoardBean boardBean) throws SQLException;

	/**
	 * 비밀글 비밀번호
	 * */
	public String selectBoardPwd(String queryId, BoardBean boardBean) throws SQLException;

	public List<Map<String, Object>> selectMapList(String queryId, Map<String, Object> param) throws SQLException;
}