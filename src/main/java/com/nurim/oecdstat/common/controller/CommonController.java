package com.nurim.oecdstat.common.controller;

import java.net.URLDecoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.nurim.oecdstat.common.bean.FileBean;
import com.nurim.oecdstat.common.bean.ServiceBean;
import com.nurim.oecdstat.common.service.CommonService;
import com.nurim.oecdstat.common.util.FileUtils;
import com.nurim.oecdstat.common.util.HtmlUtil;

@Secured(value = { "enabled" })
@Controller
public class CommonController {
	
	protected Log log = LogFactory.getLog(CommonController.class);
	
	@Autowired
	private CommonService commonService;	
	
	@Autowired
	private HttpSession session;	

	@Resource
    private FileUtils fileUtils;

	/**
	 * 메인 화면
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/main.html", method = {RequestMethod.GET, RequestMethod.POST})
	public String mainPage(HttpServletRequest request) throws Exception {  
//		return "kor/main/main";
		return "redirect:/kor/tblInfo/TblInfoList.html?vw_cd=MT_ATITLE";
		
	}
	
	//크기 조정
	@RequestMapping("/ZoomIn.html")
	public ModelAndView zoomIn(HttpServletResponse response, HttpServletRequest request) throws Exception {
		ModelAndView model = new ModelAndView();
		// 크기조정 class 이름 세션에 저장
		session.setAttribute("zoom_size", request.getParameter("state_name"));
		model.addObject("result", true);
		model.setViewName("jsonView");
		return model;
	}
	
	@RequestMapping("comm/PrintContent.html")
	public String printInfo(HttpServletResponse response, HttpServletRequest request) throws Exception {
		return "kor/common/popup/print_popup";
	}
	
	//url 복사
	@SuppressWarnings("deprecation")
	@RequestMapping("comm/CopyUrl.html")
	public String metaInfo(ServiceBean serviceBean, HttpServletRequest request) throws Exception {
		String copyUrl = request.getParameter("copyUrl");
		
		copyUrl = URLDecoder.decode(copyUrl);
		copyUrl = copyUrl.replaceAll("\\|", "&amp;");

		request.setAttribute("copyUrl", copyUrl);

		return "kor/common/layer/urlCopyPopup";
	}
	
	@RequestMapping(value = "/fileUpload.html", method = RequestMethod.POST)
    public ModelAndView getFile(@RequestParam Map<String, Object> param, MultipartHttpServletRequest request) throws Exception {
        ModelAndView model = new ModelAndView("jsonView");
        
        request.setAttribute("directory", "file");
                
        commonService.insertFileUpload(param, request);
        
        model.addObject("result", true);        
        return model;
    }
	
	@RequestMapping(value = "/fileDownload.html", method = {RequestMethod.GET, RequestMethod.POST})
	private void downloadImg(ServiceBean serviceBean, FileBean fileBean, HttpServletResponse response, MultipartHttpServletRequest request) throws Exception {
		
		if(fileBean.getDirectory() == null || "".equals(fileBean.getDirectory())) fileBean.setDirectory("down");

		request.setAttribute("directory", fileBean.getDirectory());
		request.setAttribute("file_seq", fileBean.getFile_seq());
		request.setAttribute("file_name", fileBean.getReal_name());
		request.setAttribute("fileNm", fileBean.getFile_name());

		commonService.downFile(request);
	}

	/**
	 * PDF 미리보기
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/viewer/viewPdfPreview.html", method = RequestMethod.POST)
	public String searchPdfPreview(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String uploadPath = "";

		if(param != null) {
			uploadPath = fileUtils.getFileUploadPath((String)param.get("type"), (String)param.get("real_name"));
		}

		if(uploadPath != null && !"".equals(uploadPath)) {
			return "redirect:/viewer/web/viewer.htm?file=" + uploadPath;
		} else {
			HtmlUtil.popUpOnlyClose("파일이 존재하지 않습니다.", response);
			return null;
		}
	}
}