
package com.nurim.oecdstat.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.nurim.oecdstat.common.util.FileProperties;


@Component("fileUtils")
public class FileUtils {
	
	@Autowired
	private ServletContext servletContext;

	public String getFilePropPath(String type) {
		String path = "";		

		switch(type) {
			case "img" : path = FileProperties.getProperty("imgPath"); break;	
			case "thumbnail" : path = FileProperties.getProperty("thumbnailPath"); break;	
			case "media" : path = FileProperties.getProperty("mediaPath"); break;	
			case "excel" : path = FileProperties.getProperty("downExcel"); break;	
			default : path = FileProperties.getProperty("filePath"); break;	
		}

		return path;
	}

	public String getPath(String type) {
		String path = getFilePropPath(type);

		String context = servletContext.getRealPath(path)+File.separator;
    	path = context.replaceAll("\\\\", "/");
		
		return path;
	}

    public boolean deleteFile(String path, String storedFileName) {
    	
    	File file = new File(path + storedFileName);

        if(file.exists()){
            if(file.delete()) {
                return true;
            }
        }
        return false;
    }
    
    public List<Map<String,Object>> uploadFile(MultipartHttpServletRequest request) throws Exception{
    	    	
    	String[] typeList = {"down","img","media","excel"};               
        
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

        for(int i = 0; i < typeList.length; i++) {
        	List <MultipartFile> files = request.getFiles(typeList[i]);
        	
        	if(files.isEmpty() == false) {        		
        		
        		for(int k = 0; k < files.size(); k++) {
        			String empty = files.get(k).getOriginalFilename();
        			if(!empty.equals("")) {
            			list.add(upload(files.get(k), typeList[i]));		
            		}  
        		}
        		      		
        	}
        		
        }
        
        return list;
    }
    
    public Map<String, Object> upload(MultipartFile files, String type) throws Exception {
    	
    	String path = getPath(type);
    	
    	String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
         
        Map<String, Object> listMap = null;

        File file = new File(path);
        //경로가 존재하지 않을 경우 디렉토리를 만든다.
        if(file.exists() == false){
            file.mkdirs();
        }                        

        if(files.isEmpty() == false){
            //업로드한 파일의 확장자를 포함한 파일명이다.
            originalFileName = files.getOriginalFilename();
            
            //업로드한 파일의 마지막 .을 포함한 확장자를 substring 한 것.
            originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            //32자리의 숫자를 포함한 랜덤 문자열 + 확장자를 붙여준 파일명이다.
            storedFileName = CommonUtil.getRandomString() + originalFileExtension;
             
            file = new File(path + storedFileName);
            files.transferTo(file);
             
            listMap = new HashMap<String,Object>();
            //업로드할 당시의 파일이름
            listMap.put("ORIGINAL_FILE_NAME", originalFileName);
            //저장할 파일 이름
            listMap.put("STORED_FILE_NAME", storedFileName);
            listMap.put("FILE_SIZE", files.getSize());
            listMap.put("DIRECTORY", type);
            
        }     
    	
    	return listMap;
    }

	public String downFile(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = "";
//		String path = getPath((String)request.getAttribute("directory"));
		String type = (String)request.getAttribute("directory");
		String path = FileProperties.getProperty("cmsRealPath") + getFilePropPath(type);

		// 임시
		File file = new File(path + (String)request.getAttribute("file_name"));

		if(file.exists() == true) {
			String file_name = (String)request.getAttribute("fileNm");
			String fileName = null;
			String userAgent = request.getHeader("User-Agent");

			response.setContentType("application/download; charset=utf-8");
		    response.setContentLength((int)file.length());

		    if(userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1) {
		    	fileName = URLEncoder.encode(file_name, "utf-8");
		    	fileName = fileName.replaceAll("\\+", " ");
		    } else if(userAgent.indexOf("Mozilla") > -1) {
		    	fileName = new String(file_name.getBytes("utf-8"),"iso-8859-1");
		    }

		    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Connection", "close");

			try {
				OutputStream out = response.getOutputStream();
				FileInputStream fis = null;

				try {
					fis = new FileInputStream(file);
					FileCopyUtils.copy(fis, out);
				} finally {
					if(fis != null)
						IOUtils.closeQuietly(fis);
				}

				out.flush();
				out.close();
			} catch(Exception e) {
				System.err.println("[ERROR : file Download Failed!!] " + e.getMessage());
				result = "파일 다운로드를 실패하였습니다.";
			}
		} else {
			result = "파일이 존재하지 않습니다.";
		}

		return result;
	}

	/**
	 * 첨부파일 압축하여 다운로드
	 * @param param 다운로드 파일 관련 파라미터 Map
	 * @return request
	 * @return response
	 * @exception Exception
	*/
	@SuppressWarnings("unchecked")
	public String downZipFile(Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Map<String, Object>> fileList = null;
		ZipOutputStream zos = null;
		ZipEntry zentry = null;
		BufferedInputStream bis = null;
		String result = "";
		String userAgent = request.getHeader("User-Agent");

		if(param == null || param.get("fileList") == null) {
			return "파일이 존재하지 않습니다.";
		} else {
			fileList = (List<Map<String, Object>>) param.get("fileList");

			if(fileList.size() < 0) return "파일이 존재하지 않습니다.";
		}

		String fileType = (String) param.get("fileType");
		String zipFileNm = (String) param.get("fileNm");
		String path = FileProperties.getProperty("cmsRealPath") + getFilePropPath(fileType);

		try {
			if(userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Edge") > -1 || userAgent.indexOf("Trident") > -1) {
				zipFileNm = URLEncoder.encode(zipFileNm, "utf-8");
				zipFileNm = zipFileNm.replaceAll("\\+", " ");
			} else if(userAgent.indexOf("Mozilla") > -1) {
				zipFileNm = new String(zipFileNm.getBytes("utf-8"),"iso-8859-1");
		    }

			response.setHeader("Content-Disposition", "attachment; filename=\"" + zipFileNm + ".zip\"");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Connection", "close");

			OutputStream os = response.getOutputStream();

			zos = new ZipOutputStream(os);	// ZipOutputStream
			zos.setLevel(8); 						// 압축 레벨 - 최대 압축률은 9, 디폴트 8

			int bufferSize = 1024 * 2;
			for(Map<String, Object> temp : fileList) {
				File sourceFile = new File(path + (String) temp.get("real_name"));

				bis = new BufferedInputStream(new FileInputStream(sourceFile));
				zentry = new ZipEntry((String) temp.get("file_name"));
				zentry.setTime(sourceFile.lastModified());
				zos.putNextEntry(zentry);

				byte[] buffer = new byte[bufferSize];
				int cnt = 0;

				while((cnt = bis.read(buffer, 0, bufferSize)) != -1) {
					zos.write(buffer, 0, cnt);
				}

				zos.closeEntry();
			}

			zos.close();
			bis.close();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(zos != null) {
					zos.closeEntry();
					zos.close();
				}

				if(bis != null) bis.close();
			} catch(IOException e) {
				// Exception Handling
			}
		}

		return result;
	}

	/** 파일 존재 여부 확인 후 업로드 경로 조회(실제 경로 X) */
	public String getFileUploadPath(String type, String fileNm) throws Exception {
		String uploadPath = "";

		File file = new File(FileProperties.getProperty("cmsRealPath") + getFilePropPath(type) + fileNm);

		// 경로가 존재하는 경우에만 파일 경로 셋팅
		if(file.exists() != false) {
			uploadPath = FileProperties.getProperty("cmsUrl") + getFilePropPath(type) + fileNm;
        }

        return uploadPath;
	}
}