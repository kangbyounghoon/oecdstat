package com.nurim.oecdstat.meta.bean;

import org.springframework.stereotype.Component;

import com.nurim.oecdstat.common.bean.CommonBean;

@Component
public class MetaBean extends CommonBean{

	private static final long serialVersionUID = 6584360692140882435L;

	/**지표요약정보 컬럼*/
	/**통계코드*/
	private String stats_code;
	/**통계주제*/
	private String stats_thema;
	/**지표번호 - 3개 공통 기본키*/
	private String ix_no;
	/**지표관련소관부서1*/
	private String ix_relate_jrsd_dept1;
	/**지표관련소관기관1*/
	private String ix_relate_jrsd_instt1;
	/**지표관련소관부서2*/
	private String ix_relate_jrsd_dept2;
	/**지표관련소관기관2*/
	private String ix_relate_jrsd_instt2;
	/**제출지표(통계)명_한글*/
	private String ix_stats_nm_ko;
	/**제출지표(통계)명_영문*/
	private String ix_stats_nm_en;
	/**단위_한글*/
	private String ix_unit_ko;
	/**단위_영문*/
	private String ix_unit_en;
	/**통계정의 - 한글*/
	private String stats_dfn_ko;
	/**통계정의 - 영문*/
	private String stats_dfn_en;
	/**산출식*/
	private String comput_frmla;
	/**산출방법 - 한글*/
	private String comput_mth_ko;
	/**산출방법 - 영문*/
	private String comput_mth_en;
	/**제출지표명*/
	private String presentn_ix_nm;
	/**기관*/
	private String instt;
	/**통계자료명*/
	private String stats_dta_nm;
	/**승인여부*/
	private String confm_at;
	/**공표여부*/
	private String publict_at;
	/**비고*/
	private String remarks;
	/**Souce&methods*/
	private String source_method;
	
	/**지표정의 및 작성정보 컬럼*/
	/**OECD정의_한글*/
	private String oecd_dfn_ko;
	/**OECD정의_영문*/
	private String oecd_dfn_en;
	/**작성방법 - 한글*/
	private String writng_mth_ko;
	/**작성방법 - 영문*/
	private String writng_mth_en;
	
	/**지표설명자료 컬럼*/
	/**통계명(한글)*/
	private String stats_nm_ko;
	/**통계명(영문)*/
	private String stats_nm_en;
	/**승인여부(한글)*/
	private String confm_at_ko;
	/**승인여부(영문)*/
	private String confm_at_en;
	/**승인번호(한글)*/
	private String confm_no_ko;
	/**승인번호(영문)*/
	private String confm_no_en;
	/**승인년도(한글)*/
	private String confm_year_ko;
	/**승인년도(영문)*/
	private String confm_year_en;
	/**작성기관(한글)*/
	private String writng_instt_ko;
	/**작성기관(영문)*/
	private String writng_instt_en;
	/**작성목적(한글)*/
	private String writng_purps_ko;
	/**작성목적(영문)*/
	private String writng_purps_en;
	/**작성대상(한글)*/
	private String writng_trget_ko;
	/**작성대상(영문)*/
	private String writng_trget_en;
	/**작성형태(한글)*/
	private String writng_stle_ko;
	/**작성형태(영문)*/
	private String writng_stle_en;
	/**관련법령(한글)*/
	private String relate_laword_ko;
	/**관련법령(영문)*/
	private String relate_laword_en;
	/**최초작성년도(한글)*/
	private String frst_writng_year_ko;
	/**최초작성년도(영문)*/
	private String frst_writng_year_en;
	/**최근작성년도(한글)*/
	private String recent_writng_year_ko;
	/**최근작성년도(영문)*/
	private String recent_writng_year_en;
	/**작성주기(한글)*/
	private String writng_cycle_ko;
	/**작성주기(영문)*/
	private String writng_cycle_en;
	/**작성체계(한글)*/
	private String writng_systm_ko;
	/**작성체계(영문)*/
	private String writng_systm_en;
	/**통계자료명(한글)*/
	private String stats_dta_nm_ko;
	/**통계자료명(영문)*/
	private String stats_dta_nm_en;
	/**발간물 - 공표여부(한글)*/
	private String p_publict_at_ko;
	/**발간물 - 공표여부(영문)*/
	private String p_publict_at_en;
	/**발간물 - 공표주기(한글)*/
	private String p_publict_cycle_ko;
	/**발간물 - 공표주기(영문)*/
	private String p_publict_cycle_en;
	/**발간물 - 공표일정(한글)*/
	private String p_publict_fx_ko;
	/**발간물 - 공표일정(영문)*/
	private String p_publict_fx_en;
	/**발간물 - 공표명(한글)*/
	private String p_publict_nm_ko;
	/**발간물 - 공표명(영문)*/
	private String p_publict_nm_en;
	/**전산망(정보시스템) - 공표여부(한글)*/
	private String c_publict_at_ko;
	/**전산망(정보시스템) - 공표여부(영문)*/
	private String c_publict_at_en;
	/**전산망(정보시스템) - 공표주기(한글)*/
	private String c_publict_cycle_ko;
	/**전산망(정보시스템) - 공표주기(영문)*/
	private String c_publict_cycle_en;
	/**전산망(정보시스템) - 공표일정(한글)*/
	private String c_publict_fx_ko;
	/**전산망(정보시스템) - 공표일정(영문)*/
	private String c_publict_fx_en;
	/**전산망(정보시스템) - 사이트명(한글)*/
	private String c_site_nm_ko;
	/**전산망(정보시스템) - 사이트명(영문)*/
	private String c_site_nm_en;
	/**공표관련정보 - 미공표사유(한글)*/
	private String un_publict_prvnsh_ko;
	/**공표관련정보 - 미공표사유(영문)*/
	private String un_publict_prvnsh_en;
	/**작성자 관련 정보 - 소속 기관명(한글)*/
	private String psitn_instt_nm_ko;
	/**작성자 관련 정보 - 소속 기관명(영문)*/
	private String psitn_instt_nm_en;
	/**작성자 관련 정보 - 소속 부서명(한글)*/
	private String psitn_dept_nm_ko;
	/**작성자 관련 정보 - 소속 부서명(영문)*/
	private String psitn_dept_nm_en;
	/**작성자 관련 정보 - 이름(한글)*/
	private String nm_ko;
	/**작성자 관련 정보 - 이름(영문)*/
	private String nm_en;
	/**작성자 관련 정보 - 전화번호(한글)*/
	private String telno_ko;
	/**작성자 관련 정보 - 전화번호(영문)*/
	private String telno_en;
	/**작성자 관련 정보 - 이메일(한글)*/
	private String email_ko;
	/**작성자 관련 정보 - 이메일(영문)*/
	private String email_en;
	/**지표 관련 정보 - 지표명(한글)*/
	private String ix_nm_ko;
	/**지표 관련 정보 - 지표명(영문)*/
	private String ix_nm_en;
	/**지표 관련 정보의 작성방법- 보고내용/조사문항(한글)*/
	private String report_cn_examin_qesitm_ko;
	/**지표 관련 정보의 작성방법- 보고내용/조사문항(영문)*/
	private String report_cn_examin_qesitm_en;
	/**지표 관련 정보의 작성방법- 범주(한글)*/
	private String ctgry_ko;
	/**지표 관련 정보의 작성방법- 범주(영문)*/
	private String ctgry_en;
	/**지표 관련 정보의 작성방법- 비고(한글)*/
	private String rm_ko;
	/**지표 관련 정보의 작성방법- 비고(영문)*/
	private String rm_en;
	/**지표 관련 정보의 공표- 발간물수록 여부(한글)*/
	private String pblcte_wrt_at_ko;
	/**지표 관련 정보의 공표- 발간물수록 여부(영문)*/
	private String pblcte_wrt_at_en;
	/**지표 관련 정보의 공표- 전산망수록 여부(한글)*/
	private String cmpt_ntwk_wrt_at_ko;
	/**지표 관련 정보의 공표- 전산망수록 여부(영문)*/
	private String cmpt_ntwk_wrt_at_en;
	/**지표 관련 정보의 공표- 미수록 여부(한글)*/
	private String un_wrt_prvonsh_ko;
	/**지표 관련 정보의 공표- 미수록 여부(영문)*/
	private String un_wrt_prvonsh_en;
	/**지표 관련 정보의 공표- 사이트 URL*/
	private String c_site_url;
	/**지표 관련 정보의 공표- 직책 또는 직위*/
	private String psition;
	
	/**공통*/
	/**사용여부*/
	private String use_at;
	/**등록일*/
	private String rgsde;
	
	/**검색영역*/
	/**검색영역 -기관*/
	private String src_instt;
	/**검색영역 - 메타주제*/
	private String src_stats_thema;
	/**검색영역 - 사용여부*/
	private String src_use_at;
	/**검색영역 - 키워드(통계명 한글, 영문)*/
	private String keyword;
	
	private int num;
	
	/** 엑셀 양식 다운로드(리스트화면 선택 값) */
	private String excelDwld_id; 
	/** 수정여부 */
	private String updt_at; 
	
	/**분야별, 주제1, 주제2 코드*/
	private String list_code;
	private String up_list_code;
	private String up_list_code_nm;
	private String up_up_list_code;
	private String list_code_nm;
	private String list_ordr;
	private String list_code_nm_en;
	private String list_code_dc;
	private String list_code_dc_en;
	private String top_code_id;
	private String comments;
	private String sel_code_id;
	private String top_code_name;
	private String lower_list_code;
	private String lower_up_list_code;
	private String lower_list_code_nm;
	private String lower_list_code_nm_en;
	private String lower_list_ordr;
	private String cont_num_delete;
	private String Cont_num;
	private String lower_keyword;
	private String rn;
	private String roleList;
	
	
	/**주제2*/
	private String lower_code_id;
	private String lower_code_name;
	private String lower_lower_list_code;
	private String lower_lower_up_list_code;
	private String lower_lower_list_code_nm;
	private String lower_lower_list_code_nm_en;
	private String lower_lower_list_ordr;
	
	/**주제2의 키워드*/
	private String lower_lower_keyword;
	
	/**검색*/
	private String re_search;
	private String list_path;
	
	private String a_href;
	
	private String tbl_id;
	
	private String tbl_nm;
	
	public String getSource_method() {
		return source_method;
	}
	public void setSource_method(String source_method) {
		this.source_method = source_method;
	}
	public String getTbl_nm() {
		return tbl_nm;
	}
	public void setTbl_nm(String tbl_nm) {
		this.tbl_nm = tbl_nm;
	}
	public String getTbl_id() {
		return tbl_id;
	}
	public void setTbl_id(String tbl_id) {
		this.tbl_id = tbl_id;
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
	public String getIx_no() {
		return ix_no;
	}
	public void setIx_no(String ix_no) {
		this.ix_no = ix_no;
	}
	public String getIx_relate_jrsd_dept1() {
		return ix_relate_jrsd_dept1;
	}
	public void setIx_relate_jrsd_dept1(String ix_relate_jrsd_dept1) {
		this.ix_relate_jrsd_dept1 = ix_relate_jrsd_dept1;
	}
	public String getIx_relate_jrsd_dept2() {
		return ix_relate_jrsd_dept2;
	}
	public void setIx_relate_jrsd_dept2(String ix_relate_jrsd_dept2) {
		this.ix_relate_jrsd_dept2 = ix_relate_jrsd_dept2;
	}
	public String getIx_stats_nm_ko() {
		return ix_stats_nm_ko;
	}
	public void setIx_stats_nm_ko(String ix_stats_nm_ko) {
		this.ix_stats_nm_ko = ix_stats_nm_ko;
	}
	public String getIx_stats_nm_en() {
		return ix_stats_nm_en;
	}
	public void setIx_stats_nm_en(String ix_stats_nm_en) {
		this.ix_stats_nm_en = ix_stats_nm_en;
	}
	public String getIx_unit_ko() {
		return ix_unit_ko;
	}
	public void setIx_unit_ko(String ix_unit_ko) {
		this.ix_unit_ko = ix_unit_ko;
	}
	public String getIx_unit_en() {
		return ix_unit_en;
	}
	public void setIx_unit_en(String ix_unit_en) {
		this.ix_unit_en = ix_unit_en;
	}
	public String getStats_dfn_ko() {
		return stats_dfn_ko;
	}
	public void setStats_dfn_ko(String stats_dfn_ko) {
		this.stats_dfn_ko = stats_dfn_ko;
	}
	public String getStats_dfn_en() {
		return stats_dfn_en;
	}
	public void setStats_dfn_en(String stats_dfn_en) {
		this.stats_dfn_en = stats_dfn_en;
	}
	public String getComput_frmla() {
		return comput_frmla;
	}
	public void setComput_frmla(String comput_frmla) {
		this.comput_frmla = comput_frmla;
	}
	public String getPresentn_ix_nm() {
		return presentn_ix_nm;
	}
	public void setPresentn_ix_nm(String presentn_ix_nm) {
		this.presentn_ix_nm = presentn_ix_nm;
	}
	public String getInstt() {
		return instt;
	}
	public void setInstt(String instt) {
		this.instt = instt;
	}
	public String getStats_dta_nm() {
		return stats_dta_nm;
	}
	public void setStats_dta_nm(String stats_dta_nm) {
		this.stats_dta_nm = stats_dta_nm;
	}
	public String getConfm_at() {
		return confm_at;
	}
	public void setConfm_at(String confm_at) {
		this.confm_at = confm_at;
	}
	public String getPublict_at() {
		return publict_at;
	}
	public void setPublict_at(String publict_at) {
		this.publict_at = publict_at;
	}
	public String getOecd_dfn_ko() {
		return oecd_dfn_ko;
	}
	public void setOecd_dfn_ko(String oecd_dfn_ko) {
		this.oecd_dfn_ko = oecd_dfn_ko;
	}
	public String getOecd_dfn_en() {
		return oecd_dfn_en;
	}
	public void setOecd_dfn_en(String oecd_dfn_en) {
		this.oecd_dfn_en = oecd_dfn_en;
	}
	public String getWritng_mth_ko() {
		return writng_mth_ko;
	}
	public void setWritng_mth_ko(String writng_mth_ko) {
		this.writng_mth_ko = writng_mth_ko;
	}
	public String getWritng_mth_en() {
		return writng_mth_en;
	}
	public void setWritng_mth_en(String writng_mth_en) {
		this.writng_mth_en = writng_mth_en;
	}
	public String getStats_nm_ko() {
		return stats_nm_ko;
	}
	public void setStats_nm_ko(String stats_nm_ko) {
		this.stats_nm_ko = stats_nm_ko;
	}
	public String getStats_nm_en() {
		return stats_nm_en;
	}
	public void setStats_nm_en(String stats_nm_en) {
		this.stats_nm_en = stats_nm_en;
	}
	public String getConfm_at_ko() {
		return confm_at_ko;
	}
	public void setConfm_at_ko(String confm_at_ko) {
		this.confm_at_ko = confm_at_ko;
	}
	public String getConfm_at_en() {
		return confm_at_en;
	}
	public void setConfm_at_en(String confm_at_en) {
		this.confm_at_en = confm_at_en;
	}
	public String getConfm_no_ko() {
		return confm_no_ko;
	}
	public void setConfm_no_ko(String confm_no_ko) {
		this.confm_no_ko = confm_no_ko;
	}
	public String getConfm_no_en() {
		return confm_no_en;
	}
	public void setConfm_no_en(String confm_no_en) {
		this.confm_no_en = confm_no_en;
	}
	public String getConfm_year_ko() {
		return confm_year_ko;
	}
	public void setConfm_year_ko(String confm_year_ko) {
		this.confm_year_ko = confm_year_ko;
	}
	public String getConfm_year_en() {
		return confm_year_en;
	}
	public void setConfm_year_en(String confm_year_en) {
		this.confm_year_en = confm_year_en;
	}
	public String getWritng_instt_ko() {
		return writng_instt_ko;
	}
	public void setWritng_instt_ko(String writng_instt_ko) {
		this.writng_instt_ko = writng_instt_ko;
	}
	public String getWritng_instt_en() {
		return writng_instt_en;
	}
	public void setWritng_instt_en(String writng_instt_en) {
		this.writng_instt_en = writng_instt_en;
	}
	public String getWritng_purps_ko() {
		return writng_purps_ko;
	}
	public void setWritng_purps_ko(String writng_purps_ko) {
		this.writng_purps_ko = writng_purps_ko;
	}
	public String getWritng_purps_en() {
		return writng_purps_en;
	}
	public void setWritng_purps_en(String writng_purps_en) {
		this.writng_purps_en = writng_purps_en;
	}
	public String getWritng_trget_ko() {
		return writng_trget_ko;
	}
	public void setWritng_trget_ko(String writng_trget_ko) {
		this.writng_trget_ko = writng_trget_ko;
	}
	public String getWritng_trget_en() {
		return writng_trget_en;
	}
	public void setWritng_trget_en(String writng_trget_en) {
		this.writng_trget_en = writng_trget_en;
	}
	public String getWritng_stle_ko() {
		return writng_stle_ko;
	}
	public void setWritng_stle_ko(String writng_stle_ko) {
		this.writng_stle_ko = writng_stle_ko;
	}
	public String getWritng_stle_en() {
		return writng_stle_en;
	}
	public void setWritng_stle_en(String writng_stle_en) {
		this.writng_stle_en = writng_stle_en;
	}
	public String getRelate_laword_ko() {
		return relate_laword_ko;
	}
	public void setRelate_laword_ko(String relate_laword_ko) {
		this.relate_laword_ko = relate_laword_ko;
	}
	public String getRelate_laword_en() {
		return relate_laword_en;
	}
	public void setRelate_laword_en(String relate_laword_en) {
		this.relate_laword_en = relate_laword_en;
	}
	public String getFrst_writng_year_ko() {
		return frst_writng_year_ko;
	}
	public void setFrst_writng_year_ko(String frst_writng_year_ko) {
		this.frst_writng_year_ko = frst_writng_year_ko;
	}
	public String getFrst_writng_year_en() {
		return frst_writng_year_en;
	}
	public void setFrst_writng_year_en(String frst_writng_year_en) {
		this.frst_writng_year_en = frst_writng_year_en;
	}
	public String getRecent_writng_year_ko() {
		return recent_writng_year_ko;
	}
	public void setRecent_writng_year_ko(String recent_writng_year_ko) {
		this.recent_writng_year_ko = recent_writng_year_ko;
	}
	public String getRecent_writng_year_en() {
		return recent_writng_year_en;
	}
	public void setRecent_writng_year_en(String recent_writng_year_en) {
		this.recent_writng_year_en = recent_writng_year_en;
	}
	public String getWritng_cycle_ko() {
		return writng_cycle_ko;
	}
	public void setWritng_cycle_ko(String writng_cycle_ko) {
		this.writng_cycle_ko = writng_cycle_ko;
	}
	public String getWritng_cycle_en() {
		return writng_cycle_en;
	}
	public void setWritng_cycle_en(String writng_cycle_en) {
		this.writng_cycle_en = writng_cycle_en;
	}
	public String getWritng_systm_ko() {
		return writng_systm_ko;
	}
	public void setWritng_systm_ko(String writng_systm_ko) {
		this.writng_systm_ko = writng_systm_ko;
	}
	public String getWritng_systm_en() {
		return writng_systm_en;
	}
	public void setWritng_systm_en(String writng_systm_en) {
		this.writng_systm_en = writng_systm_en;
	}
	public String getStats_dta_nm_ko() {
		return stats_dta_nm_ko;
	}
	public void setStats_dta_nm_ko(String stats_dta_nm_ko) {
		this.stats_dta_nm_ko = stats_dta_nm_ko;
	}
	public String getStats_dta_nm_en() {
		return stats_dta_nm_en;
	}
	public void setStats_dta_nm_en(String stats_dta_nm_en) {
		this.stats_dta_nm_en = stats_dta_nm_en;
	}
	public String getP_publict_at_ko() {
		return p_publict_at_ko;
	}
	public void setP_publict_at_ko(String p_publict_at_ko) {
		this.p_publict_at_ko = p_publict_at_ko;
	}
	public String getP_publict_at_en() {
		return p_publict_at_en;
	}
	public void setP_publict_at_en(String p_publict_at_en) {
		this.p_publict_at_en = p_publict_at_en;
	}
	public String getP_publict_cycle_ko() {
		return p_publict_cycle_ko;
	}
	public void setP_publict_cycle_ko(String p_publict_cycle_ko) {
		this.p_publict_cycle_ko = p_publict_cycle_ko;
	}
	public String getP_publict_cycle_en() {
		return p_publict_cycle_en;
	}
	public void setP_publict_cycle_en(String p_publict_cycle_en) {
		this.p_publict_cycle_en = p_publict_cycle_en;
	}
	public String getP_publict_fx_ko() {
		return p_publict_fx_ko;
	}
	public void setP_publict_fx_ko(String p_publict_fx_ko) {
		this.p_publict_fx_ko = p_publict_fx_ko;
	}
	public String getP_publict_fx_en() {
		return p_publict_fx_en;
	}
	public void setP_publict_fx_en(String p_publict_fx_en) {
		this.p_publict_fx_en = p_publict_fx_en;
	}
	public String getP_publict_nm_ko() {
		return p_publict_nm_ko;
	}
	public void setP_publict_nm_ko(String p_publict_nm_ko) {
		this.p_publict_nm_ko = p_publict_nm_ko;
	}
	public String getP_publict_nm_en() {
		return p_publict_nm_en;
	}
	public void setP_publict_nm_en(String p_publict_nm_en) {
		this.p_publict_nm_en = p_publict_nm_en;
	}
	public String getC_publict_at_ko() {
		return c_publict_at_ko;
	}
	public void setC_publict_at_ko(String c_publict_at_ko) {
		this.c_publict_at_ko = c_publict_at_ko;
	}
	public String getC_publict_at_en() {
		return c_publict_at_en;
	}
	public void setC_publict_at_en(String c_publict_at_en) {
		this.c_publict_at_en = c_publict_at_en;
	}
	public String getC_publict_cycle_ko() {
		return c_publict_cycle_ko;
	}
	public void setC_publict_cycle_ko(String c_publict_cycle_ko) {
		this.c_publict_cycle_ko = c_publict_cycle_ko;
	}
	public String getC_publict_cycle_en() {
		return c_publict_cycle_en;
	}
	public void setC_publict_cycle_en(String c_publict_cycle_en) {
		this.c_publict_cycle_en = c_publict_cycle_en;
	}
	public String getC_publict_fx_ko() {
		return c_publict_fx_ko;
	}
	public void setC_publict_fx_ko(String c_publict_fx_ko) {
		this.c_publict_fx_ko = c_publict_fx_ko;
	}
	public String getC_publict_fx_en() {
		return c_publict_fx_en;
	}
	public void setC_publict_fx_en(String c_publict_fx_en) {
		this.c_publict_fx_en = c_publict_fx_en;
	}
	public String getC_site_nm_ko() {
		return c_site_nm_ko;
	}
	public void setC_site_nm_ko(String c_site_nm_ko) {
		this.c_site_nm_ko = c_site_nm_ko;
	}
	public String getC_site_nm_en() {
		return c_site_nm_en;
	}
	public void setC_site_nm_en(String c_site_nm_en) {
		this.c_site_nm_en = c_site_nm_en;
	}
	public String getUn_publict_prvnsh_ko() {
		return un_publict_prvnsh_ko;
	}
	public void setUn_publict_prvnsh_ko(String un_publict_prvnsh_ko) {
		this.un_publict_prvnsh_ko = un_publict_prvnsh_ko;
	}
	public String getUn_publict_prvnsh_en() {
		return un_publict_prvnsh_en;
	}
	public void setUn_publict_prvnsh_en(String un_publict_prvnsh_en) {
		this.un_publict_prvnsh_en = un_publict_prvnsh_en;
	}
	public String getPsitn_instt_nm_ko() {
		return psitn_instt_nm_ko;
	}
	public void setPsitn_instt_nm_ko(String psitn_instt_nm_ko) {
		this.psitn_instt_nm_ko = psitn_instt_nm_ko;
	}
	public String getPsitn_instt_nm_en() {
		return psitn_instt_nm_en;
	}
	public void setPsitn_instt_nm_en(String psitn_instt_nm_en) {
		this.psitn_instt_nm_en = psitn_instt_nm_en;
	}
	public String getPsitn_dept_nm_ko() {
		return psitn_dept_nm_ko;
	}
	public void setPsitn_dept_nm_ko(String psitn_dept_nm_ko) {
		this.psitn_dept_nm_ko = psitn_dept_nm_ko;
	}
	public String getPsitn_dept_nm_en() {
		return psitn_dept_nm_en;
	}
	public void setPsitn_dept_nm_en(String psitn_dept_nm_en) {
		this.psitn_dept_nm_en = psitn_dept_nm_en;
	}
	public String getNm_ko() {
		return nm_ko;
	}
	public void setNm_ko(String nm_ko) {
		this.nm_ko = nm_ko;
	}
	public String getNm_en() {
		return nm_en;
	}
	public void setNm_en(String nm_en) {
		this.nm_en = nm_en;
	}
	public String getTelno_ko() {
		return telno_ko;
	}
	public void setTelno_ko(String telno_ko) {
		this.telno_ko = telno_ko;
	}
	public String getTelno_en() {
		return telno_en;
	}
	public void setTelno_en(String telno_en) {
		this.telno_en = telno_en;
	}
	public String getEmail_ko() {
		return email_ko;
	}
	public void setEmail_ko(String email_ko) {
		this.email_ko = email_ko;
	}
	public String getEmail_en() {
		return email_en;
	}
	public void setEmail_en(String email_en) {
		this.email_en = email_en;
	}
	public String getIx_nm_ko() {
		return ix_nm_ko;
	}
	public void setIx_nm_ko(String ix_nm_ko) {
		this.ix_nm_ko = ix_nm_ko;
	}
	public String getIx_nm_en() {
		return ix_nm_en;
	}
	public void setIx_nm_en(String ix_nm_en) {
		this.ix_nm_en = ix_nm_en;
	}
	public String getReport_cn_examin_qesitm_ko() {
		return report_cn_examin_qesitm_ko;
	}
	public void setReport_cn_examin_qesitm_ko(String report_cn_examin_qesitm_ko) {
		this.report_cn_examin_qesitm_ko = report_cn_examin_qesitm_ko;
	}
	public String getReport_cn_examin_qesitm_en() {
		return report_cn_examin_qesitm_en;
	}
	public void setReport_cn_examin_qesitm_en(String report_cn_examin_qesitm_en) {
		this.report_cn_examin_qesitm_en = report_cn_examin_qesitm_en;
	}
	public String getComput_mth_ko() {
		return comput_mth_ko;
	}
	public void setComput_mth_ko(String comput_mth_ko) {
		this.comput_mth_ko = comput_mth_ko;
	}
	public String getComput_mth_en() {
		return comput_mth_en;
	}
	public void setComput_mth_en(String comput_mth_en) {
		this.comput_mth_en = comput_mth_en;
	}
	public String getCtgry_ko() {
		return ctgry_ko;
	}
	public void setCtgry_ko(String ctgry_ko) {
		this.ctgry_ko = ctgry_ko;
	}
	public String getCtgry_en() {
		return ctgry_en;
	}
	public void setCtgry_en(String ctgry_en) {
		this.ctgry_en = ctgry_en;
	}
	public String getRm_ko() {
		return rm_ko;
	}
	public void setRm_ko(String rm_ko) {
		this.rm_ko = rm_ko;
	}
	public String getRm_en() {
		return rm_en;
	}
	public void setRm_en(String rm_en) {
		this.rm_en = rm_en;
	}
	public String getPblcte_wrt_at_ko() {
		return pblcte_wrt_at_ko;
	}
	public void setPblcte_wrt_at_ko(String pblcte_wrt_at_ko) {
		this.pblcte_wrt_at_ko = pblcte_wrt_at_ko;
	}
	public String getPblcte_wrt_at_en() {
		return pblcte_wrt_at_en;
	}
	public void setPblcte_wrt_at_en(String pblcte_wrt_at_en) {
		this.pblcte_wrt_at_en = pblcte_wrt_at_en;
	}
	public String getCmpt_ntwk_wrt_at_ko() {
		return cmpt_ntwk_wrt_at_ko;
	}
	public void setCmpt_ntwk_wrt_at_ko(String cmpt_ntwk_wrt_at_ko) {
		this.cmpt_ntwk_wrt_at_ko = cmpt_ntwk_wrt_at_ko;
	}
	public String getCmpt_ntwk_wrt_at_en() {
		return cmpt_ntwk_wrt_at_en;
	}
	public void setCmpt_ntwk_wrt_at_en(String cmpt_ntwk_wrt_at_en) {
		this.cmpt_ntwk_wrt_at_en = cmpt_ntwk_wrt_at_en;
	}
	public String getUn_wrt_prvonsh_ko() {
		return un_wrt_prvonsh_ko;
	}
	public void setUn_wrt_prvonsh_ko(String un_wrt_prvonsh_ko) {
		this.un_wrt_prvonsh_ko = un_wrt_prvonsh_ko;
	}
	public String getUn_wrt_prvonsh_en() {
		return un_wrt_prvonsh_en;
	}
	public void setUn_wrt_prvonsh_en(String un_wrt_prvonsh_en) {
		this.un_wrt_prvonsh_en = un_wrt_prvonsh_en;
	}
	public String getUse_at() {
		return use_at;
	}
	public void setUse_at(String use_at) {
		this.use_at = use_at;
	}
	public String getRgsde() {
		return rgsde;
	}
	public void setRgsde(String rgsde) {
		this.rgsde = rgsde;
	}
	public String getSrc_instt() {
		return src_instt;
	}
	public void setSrc_instt(String src_instt) {
		this.src_instt = src_instt;
	}
	public String getSrc_stats_thema() {
		return src_stats_thema;
	}
	public void setSrc_stats_thema(String src_stats_thema) {
		this.src_stats_thema = src_stats_thema;
	}
	public String getSrc_use_at() {
		return src_use_at;
	}
	public void setSrc_use_at(String src_use_at) {
		this.src_use_at = src_use_at;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getExcelDwld_id() {
		return excelDwld_id;
	}
	public void setExcelDwld_id(String excelDwld_id) {
		this.excelDwld_id = excelDwld_id;
	}
	public String getList_code() {
		return list_code;
	}
	public void setList_code(String list_code) {
		this.list_code = list_code;
	}
	public String getUp_list_code() {
		return up_list_code;
	}
	public void setUp_list_code(String up_list_code) {
		this.up_list_code = up_list_code;
	}
	
	public String getUp_list_code_nm() {
		return up_list_code_nm;
	}
	public void setUp_list_code_nm(String up_list_code_nm) {
		this.up_list_code_nm = up_list_code_nm;
	}
	public String getUp_up_list_code() {
		return up_up_list_code;
	}
	public void setUp_up_list_code(String up_up_list_code) {
		this.up_up_list_code = up_up_list_code;
	}
	public String getList_code_nm() {
		return list_code_nm;
	}
	public void setList_code_nm(String list_code_nm) {
		this.list_code_nm = list_code_nm;
	}
	public String getList_ordr() {
		return list_ordr;
	}
	public void setList_ordr(String list_ordr) {
		this.list_ordr = list_ordr;
	}
	public String getList_code_nm_en() {
		return list_code_nm_en;
	}
	public void setList_code_nm_en(String list_code_nm_en) {
		this.list_code_nm_en = list_code_nm_en;
	}
	
	public String getList_code_dc() {
		return list_code_dc;
	}
	public void setList_code_dc(String list_code_dc) {
		this.list_code_dc = list_code_dc;
	}
	public String getList_code_dc_en() {
		return list_code_dc_en;
	}
	public void setList_code_dc_en(String list_code_dc_en) {
		this.list_code_dc_en = list_code_dc_en;
	}
	public String getTop_code_id() {
		return top_code_id;
	}
	public void setTop_code_id(String top_code_id) {
		this.top_code_id = top_code_id;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getSel_code_id() {
		return sel_code_id;
	}
	public void setSel_code_id(String sel_code_id) {
		this.sel_code_id = sel_code_id;
	}
	public String getTop_code_name() {
		return top_code_name;
	}
	public void setTop_code_name(String top_code_name) {
		this.top_code_name = top_code_name;
	}
	public String getLower_list_code() {
		return lower_list_code;
	}
	public void setLower_list_code(String lower_list_code) {
		this.lower_list_code = lower_list_code;
	}
	public String getLower_up_list_code() {
		return lower_up_list_code;
	}
	public void setLower_up_list_code(String lower_up_list_code) {
		this.lower_up_list_code = lower_up_list_code;
	}
	public String getLower_list_code_nm() {
		return lower_list_code_nm;
	}
	public void setLower_list_code_nm(String lower_list_code_nm) {
		this.lower_list_code_nm = lower_list_code_nm;
	}
	public String getLower_list_code_nm_en() {
		return lower_list_code_nm_en;
	}
	public void setLower_list_code_nm_en(String lower_list_code_nm_en) {
		this.lower_list_code_nm_en = lower_list_code_nm_en;
	}
	public String getLower_list_ordr() {
		return lower_list_ordr;
	}
	public void setLower_list_ordr(String lower_list_ordr) {
		this.lower_list_ordr = lower_list_ordr;
	}
	public String getCont_num_delete() {
		return cont_num_delete;
	}
	public void setCont_num_delete(String cont_num_delete) {
		this.cont_num_delete = cont_num_delete;
	}
	public String getCont_num() {
		return Cont_num;
	}
	public void setCont_num(String cont_num) {
		Cont_num = cont_num;
	}
	public String getLower_keyword() {
		return lower_keyword;
	}
	public void setLower_keyword(String lower_keyword) {
		this.lower_keyword = lower_keyword;
	}
	public String getRn() {
		return rn;
	}
	public void setRn(String rn) {
		this.rn = rn;
	}
	public String getRoleList() {
		return roleList;
	}
	public void setRoleList(String roleList) {
		this.roleList = roleList;
	}
	public String getLower_lower_keyword() {
		return lower_lower_keyword;
	}
	public void setLower_lower_keyword(String lower_lower_keyword) {
		this.lower_lower_keyword = lower_lower_keyword;
	}
	public String getLower_code_id() {
		return lower_code_id;
	}
	public void setLower_code_id(String lower_code_id) {
		this.lower_code_id = lower_code_id;
	}
	public String getLower_code_name() {
		return lower_code_name;
	}
	public void setLower_code_name(String lower_code_name) {
		this.lower_code_name = lower_code_name;
	}
	public String getLower_lower_list_code() {
		return lower_lower_list_code;
	}
	public void setLower_lower_list_code(String lower_lower_list_code) {
		this.lower_lower_list_code = lower_lower_list_code;
	}
	public String getLower_lower_up_list_code() {
		return lower_lower_up_list_code;
	}
	public void setLower_lower_up_list_code(String lower_lower_up_list_code) {
		this.lower_lower_up_list_code = lower_lower_up_list_code;
	}
	public String getLower_lower_list_code_nm() {
		return lower_lower_list_code_nm;
	}
	public void setLower_lower_list_code_nm(String lower_lower_list_code_nm) {
		this.lower_lower_list_code_nm = lower_lower_list_code_nm;
	}
	public String getLower_lower_list_code_nm_en() {
		return lower_lower_list_code_nm_en;
	}
	public void setLower_lower_list_code_nm_en(String lower_lower_list_code_nm_en) {
		this.lower_lower_list_code_nm_en = lower_lower_list_code_nm_en;
	}
	public String getLower_lower_list_ordr() {
		return lower_lower_list_ordr;
	}
	public void setLower_lower_list_ordr(String lower_lower_list_ordr) {
		this.lower_lower_list_ordr = lower_lower_list_ordr;
	}
	public String getUpdt_at() {
		return updt_at;
	}
	public void setUpdt_at(String updt_at) {
		this.updt_at = updt_at;
	}
	public String getRe_search() {
		return re_search;
	}
	public void setRe_search(String re_search) {
		this.re_search = re_search;
	}
	public String getList_path() {
		return list_path;
	}
	public void setList_path(String list_path) {
		this.list_path = list_path;
	}
	public String getA_href() {
		return a_href;
	}
	public void setA_href(String a_href) {
		this.a_href = a_href;
	}
	public String getIx_relate_jrsd_instt1() {
		return ix_relate_jrsd_instt1;
	}
	public void setIx_relate_jrsd_instt1(String ix_relate_jrsd_instt1) {
		this.ix_relate_jrsd_instt1 = ix_relate_jrsd_instt1;
	}
	public String getIx_relate_jrsd_instt2() {
		return ix_relate_jrsd_instt2;
	}
	public void setIx_relate_jrsd_instt2(String ix_relate_jrsd_instt2) {
		this.ix_relate_jrsd_instt2 = ix_relate_jrsd_instt2;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getC_site_url() {
		return c_site_url;
	}
	public void setC_site_url(String c_site_url) {
		this.c_site_url = c_site_url;
	}
	public String getPsition() {
		return psition;
	}
	public void setPsition(String psition) {
		this.psition = psition;
	}
	
}
