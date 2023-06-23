package com.nurim.oecdstat.common.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.nurim.oecdstat.common.bean.FileBean;

@Repository
public class CommonDAO extends AbstractDAO{

	protected Log log = LogFactory.getLog(CommonDAO.class);

	public Object loginFailCount(String queryId, Object params) {
		return update(queryId, params);
	}

	public String getString(String queryId, String code_id) {
		printQueryId(queryId);
		return selectString(queryId, code_id);
    }
	
	public int getInt(String queryId, String admin_id) {
		printQueryId(queryId);
		return getSelect(queryId, admin_id);
	}
	
	public void insertFileUpload(Map<String, Object> map) {
        insert("common.insertFileUpload", map);
    }

	public List<FileBean> selectBeanList(String queryId, String board_seq) throws SQLException {
		List<FileBean> list = selectList(queryId, board_seq);			

		return list;
	}

	public FileBean selectBean(String queryId, Map<String, Object> map) {
		FileBean filebean = (FileBean) selectOne(queryId, map);			
		
		return filebean;
	}
	
	public boolean dataDelete(String queryId, Map<String, Object> deleteMap) {		
		delete(queryId, deleteMap);
		return true;
	}
	
	public List<FileBean> selectBeanList(String queryId, String[] contSeqList) {
		List<FileBean> list = selectList(queryId, contSeqList);			

		return list;
	}
	
	public List<FileBean> selectBeanList(String queryId, Map<String, Object> map) throws SQLException {
		List<FileBean> list = selectList(queryId, map);			

		return list;
	}
	
	/**
	 * 첨부파일 다음 번호 조회
	 * @param queryId, map
	 * @return result
	 */
	public String getString(String queryId, Map<String, Object> map) {
		String result = selectString(queryId, map);
		return result;
	}

	/**
	 * 게시판 첨부파일 등록
	 * @param map
	 * @return
	 */
	public void insertBoardAttachFile(Map<String, Object> map) {
		insert("file.insertBoardAttachFile", map);
	}

	/**
	 * 첨부파일 등록
	 * @param map
	 * @return
	 */
	public void insertAttachFile(Map<String, Object> map) {
		 insert("file.insertAttachFile", map);
	}

	/**
	 * 첨부파일 삭제
	 * @param file_seq
	 * @return
	 */
	public void deleteAttachFile(String file_seq) {
		insert("file.deleteAttachFile", file_seq);
	}

	/**
	 * 게시판 첨부파일 삭제
	 * @param board_seq
	 * @return
	 */
	public boolean deleteBoardAttachFile(String board_seq) {
		insert("file.deleteBoardAttachFile", board_seq);
    	return true;
	}

	/**
	 * 첨부파일 다운로드 건수 업데이트
	 * @param map
	 * @return
	 */
	public void updateAtchDwldCo(Map<String, Object> map) {
		 insert("file.updateAtchDwldCo", map);
	}
}