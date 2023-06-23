package com.nurim.oecdstat.search.bean;

import org.springframework.stereotype.Component;

import com.nurim.oecdstat.common.bean.CommonBean;


/**
 * 검색 Bean
 *
 * @author Administrator
 *
 */
@Component
public class SearchBean extends CommonBean {

	/** 직렬화에 사용되는 고유 아이디 */
	private static final long serialVersionUID = 1L;

/** 검색조건 */
	/** 검색어 */
	private String srchwrd = "";
	/** 검색언어(국문: kr, 영문: en) */
	private String lang = "";
	/** 전체 검색 */
    private String allSearchAt = "";

/** 결과 항목 */
	/** [공통] 메뉴명 */
	private String menu_nm = "";
	/** [공통] 메뉴경로 */
	private String menu_path = "";
	/** [공통] 제목 */
	private String subject = "";
	/** [공통] 전체건수 */
	private String tot_cnt = "";
	/** [공통] 등록일 */
	private String reg_date = "";
	/** [통계서비스] 서비스뷰 코드 */
	private String vw_cd = "";
	/** [통계서비스] 상위 서비스뷰 코드 */
	private String up_vw_cd = "";
	/** [통계서비스] 서비스뷰 코드명 */
	private String vw_nm = "";
	/** [통계서비스] 목록ID */
	private String list_id = "";
	/** [통계서비스] 상위 목록ID */
	private String up_list_id = "";
	/** [통계서비스] 목록명 */
	private String list_nm = "";
	/** [통계서비스] 기관ID */
	private String org_id = "";
	/** [통계서비스] 서비스뷰 순번 */
	private String vw_sn = "";
	/** [통계서비스] 통계표 순번 */
	private String result_sn = "";
	/** [통계서비스] 주석 */
	private String cmmt_dc = "";
	/** [통계서비스] 목록경로 */
	private String list_path = "";
	/** [테마통계, 통계서비스] 통계표ID */
	private String tbl_id = "";
	/** [테마통계, 통계서비스] 통계표명 */
	private String tbl_nm = "";
	/** [테마통계, 통계서비스] 주기 */
	private String prd_se = "";
	/** [테마통계, 통계서비스] 주기명 */
	private String prd_nm = "";
	/** [테마통계, 통계서비스] 시점 */
	private String prd_de = "";
	/** [테마통계, 통계서비스] 출처명 */
	private String stat_nm = "";
	/** [메타정보]  지표번호 */
	private String ix_no = "";
	/** [메타정보]  통계코드 */
	private String stats_code = "";
	/** [메타정보]  통계주제 */
	private String stats_thema = "";
	/** [메타정보]  기관 */
	private String instt = "";
	/** [메타정보]  제출지표 (통계)명_한글 */
	private String ix_stats_nm_ko = "";
	/** [메타정보]  상위코드 */
	private String up_list_code = "";
	/** [메타정보]  최상위코드 */
	private String top_code_id = "";
	
/** 건수 */
	/** 전체 검색 건수*/
	private String allSearchCo;
	/** 통계서비스 검색 건수*/
	private String tblSearchCo;
	/** 메타정보 검색 건수*/
	private String metaSearchCo;

	public String getSrchwrd() {
		return srchwrd;
	}
	public void setSrchwrd(String srchwrd) {
		this.srchwrd = srchwrd;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getAllSearchAt() {
		return allSearchAt;
	}
	public void setAllSearchAt(String allSearchAt) {
		this.allSearchAt = allSearchAt;
	}
	public String getMenu_nm() {
		return menu_nm;
	}
	public void setMenu_nm(String menu_nm) {
		this.menu_nm = menu_nm;
	}
	public String getMenu_path() {
		return menu_path;
	}
	public void setMenu_path(String menu_path) {
		this.menu_path = menu_path;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTot_cnt() {
		return tot_cnt;
	}
	public void setTot_cnt(String tot_cnt) {
		this.tot_cnt = tot_cnt;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getVw_cd() {
		return vw_cd;
	}
	public void setVw_cd(String vw_cd) {
		this.vw_cd = vw_cd;
	}
	public String getUp_vw_cd() {
		return up_vw_cd;
	}
	public void setUp_vw_cd(String up_vw_cd) {
		this.up_vw_cd = up_vw_cd;
	}
	public String getVw_nm() {
		return vw_nm;
	}
	public void setVw_nm(String vw_nm) {
		this.vw_nm = vw_nm;
	}
	public String getList_id() {
		return list_id;
	}
	public void setList_id(String list_id) {
		this.list_id = list_id;
	}
	public String getUp_list_id() {
		return up_list_id;
	}
	public void setUp_list_id(String up_list_id) {
		this.up_list_id = up_list_id;
	}
	public String getList_nm() {
		return list_nm;
	}
	public void setList_nm(String list_nm) {
		this.list_nm = list_nm;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getVw_sn() {
		return vw_sn;
	}
	public void setVw_sn(String vw_sn) {
		this.vw_sn = vw_sn;
	}
	public String getResult_sn() {
		return result_sn;
	}
	public void setResult_sn(String result_sn) {
		this.result_sn = result_sn;
	}
	public String getCmmt_dc() {
		return cmmt_dc;
	}
	public void setCmmt_dc(String cmmt_dc) {
		this.cmmt_dc = cmmt_dc;
	}
	public String getList_path() {
		return list_path;
	}
	public void setList_path(String list_path) {
		this.list_path = list_path;
	}
	public String getTbl_id() {
		return tbl_id;
	}
	public void setTbl_id(String tbl_id) {
		this.tbl_id = tbl_id;
	}
	public String getTbl_nm() {
		return tbl_nm;
	}
	public void setTbl_nm(String tbl_nm) {
		this.tbl_nm = tbl_nm;
	}
	public String getPrd_se() {
		return prd_se;
	}
	public void setPrd_se(String prd_se) {
		this.prd_se = prd_se;
	}
	public String getPrd_nm() {
		return prd_nm;
	}
	public void setPrd_nm(String prd_nm) {
		this.prd_nm = prd_nm;
	}
	public String getPrd_de() {
		return prd_de;
	}
	public void setPrd_de(String prd_de) {
		this.prd_de = prd_de;
	}
	public String getStat_nm() {
		return stat_nm;
	}
	public void setStat_nm(String stat_nm) {
		this.stat_nm = stat_nm;
	}
	public String getIx_no() {
		return ix_no;
	}
	public void setIx_no(String ix_no) {
		this.ix_no = ix_no;
	}
	public String getStats_code() {
		return stats_code;
	}
	public void setStats_code(String stats_code) {
		this.stats_code = stats_code;
	}
	public String getStats_thema() {
		return stats_thema;
	}
	public void setStats_thema(String stats_thema) {
		this.stats_thema = stats_thema;
	}
	public String getInstt() {
		return instt;
	}
	public void setInstt(String instt) {
		this.instt = instt;
	}
	public String getIx_stats_nm_ko() {
		return ix_stats_nm_ko;
	}
	public void setIx_stats_nm_ko(String ix_stats_nm_ko) {
		this.ix_stats_nm_ko = ix_stats_nm_ko;
	}
	public String getUp_list_code() {
		return up_list_code;
	}
	public void setUp_list_code(String up_list_code) {
		this.up_list_code = up_list_code;
	}
	public String getTop_code_id() {
		return top_code_id;
	}
	public void setTop_code_id(String top_code_id) {
		this.top_code_id = top_code_id;
	}
	public String getAllSearchCo() {
		return allSearchCo;
	}
	public void setAllSearchCo(String allSearchCo) {
		this.allSearchCo = allSearchCo;
	}
	public String getTblSearchCo() {
		return tblSearchCo;
	}
	public void setTblSearchCo(String tblSearchCo) {
		this.tblSearchCo = tblSearchCo;
	}
	public String getMetaSearchCo() {
		return metaSearchCo;
	}
	public void setMetaSearchCo(String metaSearchCo) {
		this.metaSearchCo = metaSearchCo;
	}
}