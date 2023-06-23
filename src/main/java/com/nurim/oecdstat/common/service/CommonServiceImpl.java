package com.nurim.oecdstat.common.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.nurim.oecdstat.common.bean.FileBean;
import com.nurim.oecdstat.common.dao.CommonDAO;
import com.nurim.oecdstat.common.util.FileUtils;
import com.nurim.oecdstat.common.util.HtmlUtil;

@Service
public class CommonServiceImpl implements CommonService{

	protected Log log = LogFactory.getLog(CommonServiceImpl.class);
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private CommonDAO commonDAO;
	
	@Resource
    private FileUtils fileUtils;	
	
	public String getString(String queryId, String code_id) {
		return commonDAO.getString(queryId, code_id);
	}
	
	@Override
    public void insertFileUpload(Map<String, Object> param, MultipartHttpServletRequest request) throws Exception {
		String board_seq = (String)param.get("board_seq");
		String delete_seq = (String)param.get("delete_fileSeq");
		
		//수정 시 삭제된 파일을 지움
		if(delete_seq != null){
			if(!"".equals(delete_seq)) { // tg_board 첨부파일
				String[] deleteSeq = delete_seq.split(",");
				Map<String, Object> deleteMap = new HashMap<String, Object>();
				for(int i = 0; i < deleteSeq.length; i++) {
					deleteMap.put("board_seq", board_seq);
					deleteMap.put("file_seq", deleteSeq[i]);
					
					FileBean filebean = null;
					if (board_seq != null) {
						filebean = commonDAO.selectBean("file.getRealName", deleteMap);
					}
					
					fileUtils.deleteFile(filebean.getDirectory(), filebean.getReal_name());
					
					if (board_seq != null) {
						commonDAO.dataDelete("file.deleteFile",deleteMap);// tg_board_attach 테이블만 삭제
					} 
				}
			}
		}
		
		List<Map<String, Object>> list = fileUtils.uploadFile(request);
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> vo = list.get(i);
            
            if(vo.get("DIRECTORY") != null) {
            	String file_seq = commonDAO.getString("file.selectNextFileSeq", vo);
            	String file_name = (String) vo.get("ORIGINAL_FILE_NAME");
            	String attech_type = file_name.substring(file_name.lastIndexOf(".") + 1, file_name.length());
            	
            	Map<String, Object> fileMap = new HashMap<String, Object>();
            	
            	fileMap.put("file_seq", file_seq); //tg_attach, tg_board_attach
            	fileMap.put("real_name", vo.get("STORED_FILE_NAME"));//tg_attach
            	fileMap.put("file_name", vo.get("ORIGINAL_FILE_NAME"));//tg_attach
            	fileMap.put("directory", vo.get("DIRECTORY"));//tg_attach
            	fileMap.put("board_seq", board_seq);//tg_board_attach
            	fileMap.put("attech_type",attech_type);//tg_board_attach
            	
            	if (board_seq != null) {
					commonDAO.insertBoardAttachFile(fileMap);
				}

            	commonDAO.insertAttachFile(fileMap);
            	
            	log.debug("---------- file start ----------");
            	log.debug("Origin_name : " + list.get(i).get("ORIGINAL_FILE_NAME"));
            	log.debug("Change_name : " + list.get(i).get("STORED_FILE_NAME"));
            	log.debug("size : " + list.get(i).get("FILE_SIZE"));
            	log.debug("---------- file end ----------\n");
            }
        }
    }

	@Override
    public void downFile(MultipartHttpServletRequest request) throws Exception {
		String result = fileUtils.downFile(request, response);
		String file_seq = (String) request.getAttribute("file_seq");

		if(result != null && !"".equals(result)) {
			// 다운로드 실패 시, 실패 메시지 안내
			HtmlUtil.BeforeUrl(result, response);
		} else {
			// 다운로드 성공 시, 다운로드 건수 업데이트
			if(file_seq != null && !"".equals(file_seq)) {
				Map<String, Object> param = new HashMap<String, Object>();

				Calendar cal = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

				param.put("file_seq", file_seq);
				param.put("dwld_de", format.format(cal.getTime()));

				commonDAO.updateAtchDwldCo(param);
			}
		}
    }
}