package com.nurim.oecdstat.common.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * 검색 Bean
 *
 * @author Administrator 
 *
 */
@Component
public class CommonBean implements Serializable {

	private static final long serialVersionUID = 1L;

/** 페이징 */
	private int pageIndex = 1;				/** 현재페이지 */
	private int totCount = 0;					/** 검색된 전체 건수 */
	private int firstIndex = 0;					/** firstIndex */
	private int lastIndex = 10;					/** lastIndex */
	private int pageUnit = 10;				/** 조회할 목록 수  */
	private int pageListScale = 10;			/** 페이징 수 */
	private String pageUrl = "";				/** URL */
	private int totalPage = 0;

/** 기본 검색 조건 */
	private String searchType = "";			/** 검색 유형 */
	private String keyword = "";				/** 검색어 */
	private String src_st_dt = "";				/** 검색 시작 일자 */
	private String src_ed_dt = "";				/** 검색 종료 일자 */
	private String unitySearchAt = "N";		/** 통합검색 여부 */

/** 공통 결과 */
	private String rnum;

	/** 메뉴 관련 */
	private String menu_id;
	private String up_menu_id;
	private String menu_url;
	private String siteGb;
	private String uri;

	/** 정렬 */
	private String orderby;
	private String desc;	

	private Integer pageIdx = null;

	/** 현재 URL */
	private String currUri;	 /** 현재 URL1 */
	private String currUri2; /** 현재 URL2 */

	public Integer getPageIdx() {
		return pageIdx;
	}
	public void setPageIdx(Integer pageIdx) {
		this.pageIdx = pageIdx;
	}
/** 페이징 */
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotCount() {
		return totCount;
	}
	public void setTotCount(int totCount) {
		this.totCount = totCount;
	}

	public int getFirstIndex() {
		return firstIndex;
	}
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getPageListScale() {
		return pageListScale;
	}
	public void setPageListScale(int pageListScale) {
		this.pageListScale = pageListScale;
	}

	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}


	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
/** 기본 검색 조건 */
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSrc_st_dt() {
		return src_st_dt;
	}
	public void setSrc_st_dt(String src_st_dt) {
		this.src_st_dt = src_st_dt.replaceAll("-", "");
	}

	public String getSrc_ed_dt() {
		return src_ed_dt;
	}
	public void setSrc_ed_dt(String src_ed_dt) {
		this.src_ed_dt = src_ed_dt.replaceAll("-", "");
	}

	public String getUnitySearchAt() {
		return unitySearchAt;
	}
	public void setUnitySearchAt(String unitySearchAt) {
		this.unitySearchAt = unitySearchAt;
	}

/** 공통 결과 */
	public String getRnum() {
		return rnum;
	}
	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

/** 정렬 */
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/** 메뉴 관련 */
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getUp_menu_id() {
		return up_menu_id;
	}
	public void setUp_menu_id(String up_menu_id) {
		this.up_menu_id = up_menu_id;
	}
	public String getMenu_url() {
		return menu_url;
	}
	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}
	public String getSiteGb() {
		return siteGb;
	}
	public void setSiteGb(String siteGb) {
		this.siteGb = siteGb;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getCurrUri() {
		return currUri;
	}
	public void setCurrUri(String currUri) {
		this.currUri = currUri;
	}
	public String getCurrUri2() {
		return currUri2;
	}
	public void setCurrUri2(String currUri2) {
		this.currUri2 = currUri2;
	}
}