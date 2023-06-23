package com.nurim.oecdstat.board.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nurim.oecdstat.board.bean.BoardBean;
import com.nurim.oecdstat.board.dao.BoardDAO;
import com.nurim.oecdstat.common.bean.FileBean;
import com.nurim.oecdstat.common.dao.CommonDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;

	@Autowired
	private CommonDAO commonDao;

	@Autowired
	private ServletContext servletContext;

	/**
	 * 게시판 목록 조회
	 * @param queryId, boardBean
	 * @return newList
	 * @throws SQLException 
	 */
	public List<BoardBean> selectBeanList(String queryId, BoardBean boardBean) throws SQLException {		
		List<BoardBean> list = boardDAO.selectBeanList(queryId ,boardBean);
		List<BoardBean> newList = new ArrayList<BoardBean>();

		for(int i=0; i < list.size(); i++){
			 List<FileBean> temp = commonDao.selectBeanList("file.selectBoardAttachList",list.get(i).getBoard_seq());
			 list.get(i).setFileList(temp);
			 newList.add(list.get(i));
		}
		
		return newList;
	}
	
	/**
	 * 게시판 상세보기
	 * @param queryId, boardBean
	 * @return result
	 * @throws SQLException 
	 */
	public BoardBean selectBean(String queryId, BoardBean boardBean) throws SQLException {
		BoardBean result = boardDAO.selectBean(queryId ,boardBean);
		return result;
	}
	
	public int selectInt(String queryId, BoardBean boardBean) throws SQLException {
		int result = boardDAO.selectInt(queryId ,boardBean);
		return result;
	}
	
	/**
	 * 게시판 조회수
	 * @param boardBean
	 * @return
	 * @throws SQLException 
	 */
	public boolean updateViewCount(String queryId, BoardBean boardBean) throws SQLException {
		boolean result = true;
		result = boardDAO.dataUpdate(queryId, boardBean);
		
		return result;
	}

	/**
	 * 게시글 삭제
	 * @param queryId, board_seq
	 * @return result
	 * @throws SQLException 
	 */
	@Override
	public boolean delete(String queryId, String board_seq) throws SQLException {
		boolean result = true;
		result = boardDAO.dataDelete(queryId ,board_seq);
		
		// 게시물 삭제 시 첨부파일 정보 함께 삭제
		List<FileBean> temp = commonDao.selectBeanList("file.selectBoardAttachList",board_seq);

		for(int i=0; i < temp.size(); i++) {
			FileBean tmpBean = temp.get(i);
			
			commonDao.deleteAttachFile(tmpBean.getFile_seq());
		}
		result = commonDao.deleteBoardAttachFile(board_seq);
		
		return result;
	}

	/**
	 * 게시글 등록
	 * @param queryId, boardBean
	 * @return result
	 * @throws SQLException 
	 */
	@Override
	public boolean insert(String queryId, BoardBean boardBean) throws SQLException {
		boolean result = true;
		result = boardDAO.dataInsert(queryId ,boardBean);
			
		return result;
	}
	
	/**
	 * 
	 * @param queryId, boardBean
	 * @return result
	 * @throws SQLException 
	 */
	@Override
	public String selectString(String queryId, BoardBean boardBean) throws SQLException {
		String result = boardDAO.selectString(queryId ,boardBean);
		return result;
	}

	/**
	 * 게시글 수정
	 * @param boardBean
	 * @return result
	 * @throws SQLException 
	 */
	@Override
	public boolean update(String queryId, BoardBean boardBean) throws SQLException {
		boolean result = true;
		result = boardDAO.dataUpdate(queryId ,boardBean);
		
		return result;
	}

	/**
	 * 문의사항 게시글 비밀번호 가져오기
	 * @param queryId
	 * @param boardBean
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public String selectBoardPwd(String queryId, BoardBean boardBean) throws SQLException  {
		return boardDAO.getSelectString(queryId, boardBean);
	}

	@Override
	public List<Map<String, Object>> selectMapList(String queryId, Map<String, Object> param) throws SQLException {
		return boardDAO.selectMapList(queryId, param);
	}
}