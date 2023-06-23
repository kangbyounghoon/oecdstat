package com.nurim.oecdstat.tblInfo.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nurim.oecdstat.tblInfo.bean.TblInfoBean;
import com.nurim.oecdstat.tblInfo.service.TblInfoService;


@Controller
public class TblInfoController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private TblInfoService tblInfoService;

	boolean success = false;

	/**
	 * 통계표 정보 목록 조회
	 * 
	 * @param tblInfoBean
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("kor/tblInfo/TblInfoList.html")
	private String selectTblInfoList(TblInfoBean tblInfoBean, HttpServletResponse response, Model model) throws Exception {
		String returnUrl = "kor/tblInfo/tblInfoList";
		
		List<TblInfoBean> fldList = null;
		
		String up_list_id = "0";
		
		if(tblInfoBean.getListId() != null && tblInfoBean.getListId() != "") {
			request.setAttribute("listId", tblInfoBean.getList_id());
		}

		// 메뉴 정보(vw_cd) 없는 경우
		if(tblInfoBean.getVw_cd() == null || "".equals(tblInfoBean.getVw_cd())) {
			tblInfoBean.setVw_cd("MT_ATITLE");
			tblInfoBean.setUp_list_id("0");
		}

		// 메뉴 정보(up_list_id) 없는 경우
		if(tblInfoBean.getUp_list_id() == null || "".equals(tblInfoBean.getUp_list_id())) {
			tblInfoBean.setUp_list_id("0");
		} else {
			up_list_id = tblInfoBean.getUp_list_id();
			tblInfoBean.setUp_list_id("0");
		}
		
		if("MT_DTITLE".equals(tblInfoBean.getVw_cd())){
			fldList = tblInfoService.selectBeanList("tblInfo.selectTblSrcOrgList",tblInfoBean);
		} else {
			
			// 전체 레벨 확인
			int level = tblInfoService.selectInt("tblInfo.selectTblMaxLevel", tblInfoBean);
			
			tblInfoBean.setMax_lvl(level);
			
			if(level > 0) {
				
				// 통계표 목록 조회
				fldList = getTblInfoList(tblInfoBean);
			}
		}

		// 주제별 메뉴 조회 시 상위 목록 ID 있는 경우
		if(up_list_id != null && !"0".equals(up_list_id)) {
			tblInfoBean.setUp_list_id(up_list_id);
		}

		request.setAttribute("fldList", fldList);
		request.setAttribute("tblInfoBean", tblInfoBean);

		if("Y".equals(tblInfoBean.getRe_search())) {
			returnUrl = "jsonView";

			model.addAttribute("fldList", fldList);
			model.addAttribute("tblInfoBean", tblInfoBean);
		}

		return returnUrl;
	}

	/**
	 * 통계표 분야 목록 조회
	 * 
	 * @param tblInfoBean
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("kor/tblInfo/TblInfoFieldList.html")
	private String selectTblInfoFieldList(TblInfoBean tblInfoBean, HttpServletResponse response, Model model) throws Exception {
		List<TblInfoBean> list = null;
		
		tblInfoBean.setLvl("");
		
		if("MT_DTITLE".equals(tblInfoBean.getVw_cd())) {
			list = tblInfoService.selectBeanList("tblInfo.selectTblSrcList",tblInfoBean);
		} else {
			list = getTblInfoList(tblInfoBean);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("tblInfoBean", tblInfoBean);

		return "jsonView";
	}

	/**
	 * 통계표 정보 검색 목록 조회
	 * 
	 * @param tblInfoBean
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("kor/tblInfo/TblInfoSearchList.html")
	private String selectTblInfoSearchList(TblInfoBean tblInfoBean, HttpServletResponse response, Model model) throws Exception {
		
		List<TblInfoBean> tblList = null;

		if("MT_DTITLE".equals(tblInfoBean.getVw_cd())) {
			tblList = tblInfoService.selectBeanList("tblInfo.selectTblSrcList",tblInfoBean);
		} else {
			tblList = getTblInfoList(tblInfoBean);
		}

		model.addAttribute("tblList", tblList);
		model.addAttribute("tblInfoBean", tblInfoBean);

		return "jsonView";
	}

	/**
	 * 통계표 정보 목록 결과
	 * 
	 * @param tblInfoBean
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("kor/tblInfo/TblInfoListResult.html")
	private String selectTblInfoListResult(TblInfoBean tblInfoBean, HttpServletResponse response, Model model) throws Exception {
		
		List<TblInfoBean> fldList = null;
		List<TblInfoBean> tblList = null;
		
		String up_list_id = "";
		String list_id = "";

		// 메뉴 정보 없는 경우
		if(tblInfoBean.getVw_cd() == null || "".equals(tblInfoBean.getVw_cd())) {
			response.sendRedirect("TblInfoList.html");
			return null;
		}

		// 메뉴 정보 없는 경우
		if(tblInfoBean.getUp_list_id() == null || "".equals(tblInfoBean.getUp_list_id())) {
			tblInfoBean.setUp_list_id("0");
		}

		if(!"".equals(tblInfoBean.getStat_id()) && tblInfoBean.getStat_id() != null) {
			up_list_id = tblInfoService.selectString("tblInfo.selectTblInfoStatNm",tblInfoBean);
		} else {
			up_list_id = tblInfoBean.getUp_list_id();
			list_id = tblInfoBean.getList_id();
		}

		tblInfoBean.setUp_list_id("0");

		fldList = getTblInfoList(tblInfoBean);

		if("MT_ATITLE".equals(tblInfoBean.getVw_cd())) {
			tblInfoBean.setMax_lvl(2);
			
			tblInfoBean.setUp_list_id(up_list_id);
			tblList = getTblInfoList(tblInfoBean);
		} else {
			tblInfoBean.setUp_list_id(up_list_id);
			tblList = getTblInfoList(tblInfoBean);
		}

		request.setAttribute("up_list_id", up_list_id);
		request.setAttribute("list_id", list_id);

		request.setAttribute("fldList", fldList);
		request.setAttribute("tblList", tblList);
		request.setAttribute("tblInfoBean", tblInfoBean);

		return "kor/tblInfo/tblInfoList";
	}

	/**
	 * 통계표 정보 목록 가져오기
	 * 
	 * @param tblInfoBean
	 * @return
	 * @throws Exception
	 */
	public List<TblInfoBean> getTblInfoList(TblInfoBean tblInfoBean) throws Exception {
		List<TblInfoBean> list = tblInfoService.selectBeanList("tblInfo.selectTblList",tblInfoBean);

		return list;
	}
	
}