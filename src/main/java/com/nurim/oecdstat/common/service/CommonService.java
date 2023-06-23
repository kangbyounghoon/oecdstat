package com.nurim.oecdstat.common.service;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface CommonService {	
	
	public String getString(String queryId, String code_id) throws Exception , SQLException;
	
	public void insertFileUpload(Map<String, Object> param, MultipartHttpServletRequest request) throws Exception;
	
	public void downFile(MultipartHttpServletRequest request) throws Exception;
}
