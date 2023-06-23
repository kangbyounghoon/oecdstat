package com.nurim.oecdstat.search.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nurim.oecdstat.common.util.BoardUtil;
import com.nurim.oecdstat.common.util.HtmlUtil;
import com.nurim.oecdstat.search.bean.SearchBean;
import com.nurim.oecdstat.search.service.SearchService;

@Controller
public class SearchController {
	/** Session */
	@Autowired private HttpSession session;
	/** 통합검색 Service Class */
	@Autowired private SearchService searchService;

	/**
	 * 통합검색 목록 조회
	 * @param param 통합검색 조건 파라미터 Map
	 * @return 통합검색 목록화면 JSP 파일 경로
	 * @exception Exception
	 * @see TABLE NAME : TN_STBL_INFO
	*/
	@RequestMapping("kor/search/SearchList.html")
	private String selectContentsList(SearchBean searchBean, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardUtil boardUtil = new BoardUtil();

		List<SearchBean> tblList = null;
		List<SearchBean> metaList = null;
		
		String lang = "kr";
		String allSearchAt = "Y";

		int tblSearchCo = 0;
		int metaSearchCo = 0;

		if(searchBean != null) {
			// 언어 설정
			Locale locale = (Locale)session.getAttribute("lang");

			if(locale != null) {
				lang = locale.toLanguageTag();

				if(lang != null && lang.indexOf("-") > -1) {
					lang = lang.substring(lang.indexOf("-") + 1).toLowerCase();
				}
			}

			searchBean.setLang(lang);

			searchBean = (SearchBean)boardUtil.setPagination(searchBean);

			// 통합검색
			if(searchBean.getSrchwrd() != null && !"".equals(searchBean.getSrchwrd())) {
				String up_menu_id = searchBean.getUp_menu_id();

				if(up_menu_id != null && !"".equals(up_menu_id)) {
					allSearchAt = "N";

					searchBean.setPageUrl("SearchList.html");
				} else {
					searchBean.setFirstIndex(0);
					searchBean.setLastIndex(3);
				}

				// 전체 검색 여부
				searchBean.setAllSearchAt(allSearchAt);

				// 통계서비스
				if("Y".equals(allSearchAt) || "2020000".equals(up_menu_id)) {
					tblList = searchService.selectMapList("search.selectTblList", searchBean);

					if(tblList.size() > 0) {
						tblSearchCo = Integer.parseInt(tblList.get(0).getTot_cnt());

						searchBean.setTotCount(tblSearchCo);
						searchBean.setTblSearchCo(tblList.get(0).getTot_cnt());
					} else {
						searchBean.setTblSearchCo("0");
					}
				}
				
				// 메타정보
				if("Y".equals(allSearchAt) || "2030000".equals(up_menu_id)) {
					metaList = searchService.selectMapList("search.selectMetaList", searchBean);
					
					if(metaList.size() > 0) {
						metaSearchCo = Integer.parseInt(metaList.get(0).getTot_cnt());
						
						searchBean.setTotCount(metaSearchCo);
						searchBean.setMetaSearchCo(metaList.get(0).getTot_cnt());
					} else {
						searchBean.setMetaSearchCo("0");
					}
				}

				if("N".equals(allSearchAt)) {
					request.setAttribute("pagination", boardUtil.getSearchPageIndex(searchBean, request.getContextPath(), "Y"));
				} else {
					searchBean.setAllSearchCo(String.valueOf(tblSearchCo + metaSearchCo));
					searchBean.setUp_menu_id("");
				}
			} else {
				HtmlUtil.BeforeUrl("검색어를 입력해주세요.", response);
			}
		} else {
			HtmlUtil.BeforeUrl("검색에 실패하였습니다.", response);
		}

		request.setAttribute("tblList", tblList);
		request.setAttribute("metaList", metaList);
		request.setAttribute("searchBean", searchBean);

		return "kor/search/searchList";
	}
}