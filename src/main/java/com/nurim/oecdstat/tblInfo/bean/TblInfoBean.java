package com.nurim.oecdstat.tblInfo.bean;

import java.util.List;

import org.springframework.stereotype.Component;

import com.nurim.oecdstat.common.bean.CommonBean;
import com.nurim.oecdstat.meta.bean.MetaBean;


/**
 * 게시판 Bean
 *
 */
@Component
public class TblInfoBean extends CommonBean {
	/**
	 *
	 */
	private static final long serialVersionUID = -7203676060592263526L;

	/** 공통 */
	private String vw_cd;
	private String vw_nm;
	private String vw_sn;
	private String root_id;
	private String path;
	private String cmmt_dc;
	private String itm_nm;
	private String kwd;

	private String list_id;
	private int list_sn;
	private String org_id;
	private String tbl_id;
	private String pub_se;
	private String stat_id;
	private String lst_chn_de;
	private String lst_chn_nm;

	private String stat_field;
	private String stat_kind;
	private String stat_term;
	private String list_desc;
	private String desc_apart_at;
	private String up_vw_cd;
	private String up_list_id;
	private String up_up_list_id;
	private String list_cm;
	private String list_nm;
	private String list_nm2;
	private String list_eng_nm;
	private int ov_co;
	private int char_itm_co;
	private String tbl_nm;
	private String tbl_eng_nm;
	private String cmmt_at;
	private String subj_se;
	private String tbl_kd;
	private String tbl_disp_kd;
	private String ct_tp_at;
	private String dmm_disp_kd;
	private String rprsnt_org_kd;
	private String unit_id;
	private String obj_itm_pub_at;
	private int dim_co;
	private String fst_reg_de;
	private String fst_reg_nm;
	private String olap_at;
	private String olap_stl;
	private String app_at;
	private String stat_nm;
	private String cell_chk;
	private String gis_se;
	private String rec_tbl_se;
	private String olap_round_yn;
	private String dt_unit_yn;
	private String dim_unit_yn;
	private String wgt_yn;
	private String make_at;
	private String continue_yn;
	private String ref_project;
	private String continue_de;
	private String change_de;
	private int rel_sn;
	private String anal_cd;
	private String comp_cd;
	private String scr_kor;
	private String scr_eng;
	private String graph_at;
	private String itm_id;
	private String obj_var_id;
	private String up_list_nm;
	private String up_list_eng_nm;

	private String prd_de;
	private String prd_se;

	/** 추가 */
	private String lvl;
	private String re_search;
	private String list_path;
	private String list_eng_path;
	private int max_lvl;
	private String list_type;
	private int src_cnt;
	private int cnt;

	private String listId;
	private String upListId;
	private String gubun;
	private String statisticsText;

	/*정렬조건*/
	private String orderby;
	
	private String prd_info;
	private String dept_nm;
	private String dept_phon;
	
	//메타정보
	private List<MetaBean> metaList;
	private String stats_code;
	
	public String getStats_code() {
		return stats_code;
	}
	public void setStats_code(String stats_code) {
		this.stats_code = stats_code;
	}
	public List<MetaBean> getMetaList() {
		return metaList;
	}
	public void setMetaList(List<MetaBean> metaList) {
		this.metaList = metaList;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getKwd() {
		return kwd;
	}
	public void setKwd(String kwd) {
		this.kwd = kwd;
	}
	public String getVw_sn() {
		return vw_sn;
	}
	public void setVw_sn(String vw_sn) {
		this.vw_sn = vw_sn;
	}
	public String getRoot_id() {
		return root_id;
	}
	public void setRoot_id(String root_id) {
		this.root_id = root_id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getCmmt_dc() {
		return cmmt_dc;
	}
	public void setCmmt_dc(String cmmt_dc) {
		this.cmmt_dc = cmmt_dc;
	}
	public String getItm_nm() {
		return itm_nm;
	}
	public void setItm_nm(String itm_nm) {
		this.itm_nm = itm_nm;
	}
	public String getVw_nm() {
		return vw_nm;
	}
	public void setVw_nm(String vw_nm) {
		this.vw_nm = vw_nm;
	}
	public String getUpListId() {
		return upListId;
	}
	public void setUpListId(String upListId) {
		this.upListId = upListId;
	}
	public String getListId() {
		return listId;
	}
	public void setListId(String listId) {
		this.listId = listId;
	}
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	public String getVw_cd() {
		return vw_cd;
	}
	public void setVw_cd(String vw_cd) {
		this.vw_cd = vw_cd;
	}
	public String getList_id() {
		return list_id;
	}
	public void setList_id(String list_id) {
		this.list_id = list_id;
	}
	public int getList_sn() {
		return list_sn;
	}
	public void setList_sn(int list_sn) {
		this.list_sn = list_sn;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getTbl_id() {
		return tbl_id;
	}
	public void setTbl_id(String tbl_id) {
		this.tbl_id = tbl_id;
	}
	public String getPub_se() {
		return pub_se;
	}
	public void setPub_se(String pub_se) {
		this.pub_se = pub_se;
	}
	public String getStat_id() {
		return stat_id;
	}
	public void setStat_id(String stat_id) {
		this.stat_id = stat_id;
	}
	public String getLst_chn_de() {
		return lst_chn_de;
	}
	public void setLst_chn_de(String lst_chn_de) {
		this.lst_chn_de = lst_chn_de;
	}
	public String getLst_chn_nm() {
		return lst_chn_nm;
	}
	public void setLst_chn_nm(String lst_chn_nm) {
		this.lst_chn_nm = lst_chn_nm;
	}
	public String getStat_field() {
		return stat_field;
	}
	public void setStat_field(String stat_field) {
		this.stat_field = stat_field;
	}
	public String getStat_kind() {
		return stat_kind;
	}
	public void setStat_kind(String stat_kind) {
		this.stat_kind = stat_kind;
	}
	public String getStat_term() {
		return stat_term;
	}
	public void setStat_term(String stat_term) {
		this.stat_term = stat_term;
	}
	public String getList_desc() {
		return list_desc;
	}
	public void setList_desc(String list_desc) {
		this.list_desc = list_desc;
	}
	public String getDesc_apart_at() {
		return desc_apart_at;
	}
	public void setDesc_apart_at(String desc_apart_at) {
		this.desc_apart_at = desc_apart_at;
	}
	public String getUp_vw_cd() {
		return up_vw_cd;
	}
	public void setUp_vw_cd(String up_vw_cd) {
		this.up_vw_cd = up_vw_cd;
	}
	public String getUp_list_id() {
		return up_list_id;
	}
	public void setUp_list_id(String up_list_id) {
		this.up_list_id = up_list_id;
	}
	public String getList_cm() {
		return list_cm;
	}
	public void setList_cm(String list_cm) {
		this.list_cm = list_cm;
	}
	public String getList_nm() {
		return list_nm;
	}
	public void setList_nm(String list_nm) {
		this.list_nm = list_nm;
	}
	public String getList_eng_nm() {
		return list_eng_nm;
	}
	public void setList_eng_nm(String list_eng_nm) {
		this.list_eng_nm = list_eng_nm;
	}
	public int getOv_co() {
		return ov_co;
	}
	public void setOv_co(int ov_co) {
		this.ov_co = ov_co;
	}
	public int getChar_itm_co() {
		return char_itm_co;
	}
	public void setChar_itm_co(int char_itm_co) {
		this.char_itm_co = char_itm_co;
	}
	public String getTbl_nm() {
		return tbl_nm;
	}
	public void setTbl_nm(String tbl_nm) {
		this.tbl_nm = tbl_nm;
	}
	public String getTbl_eng_nm() {
		return tbl_eng_nm;
	}
	public void setTbl_eng_nm(String tbl_eng_nm) {
		this.tbl_eng_nm = tbl_eng_nm;
	}
	public String getCmmt_at() {
		return cmmt_at;
	}
	public void setCmmt_at(String cmmt_at) {
		this.cmmt_at = cmmt_at;
	}
	public String getSubj_se() {
		return subj_se;
	}
	public void setSubj_se(String subj_se) {
		this.subj_se = subj_se;
	}
	public String getTbl_kd() {
		return tbl_kd;
	}
	public void setTbl_kd(String tbl_kd) {
		this.tbl_kd = tbl_kd;
	}
	public String getTbl_disp_kd() {
		return tbl_disp_kd;
	}
	public void setTbl_disp_kd(String tbl_disp_kd) {
		this.tbl_disp_kd = tbl_disp_kd;
	}
	public String getCt_tp_at() {
		return ct_tp_at;
	}
	public void setCt_tp_at(String ct_tp_at) {
		this.ct_tp_at = ct_tp_at;
	}
	public String getDmm_disp_kd() {
		return dmm_disp_kd;
	}
	public void setDmm_disp_kd(String dmm_disp_kd) {
		this.dmm_disp_kd = dmm_disp_kd;
	}
	public String getRprsnt_org_kd() {
		return rprsnt_org_kd;
	}
	public void setRprsnt_org_kd(String rprsnt_org_kd) {
		this.rprsnt_org_kd = rprsnt_org_kd;
	}
	public String getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}
	public String getObj_itm_pub_at() {
		return obj_itm_pub_at;
	}
	public void setObj_itm_pub_at(String obj_itm_pub_at) {
		this.obj_itm_pub_at = obj_itm_pub_at;
	}
	public int getDim_co() {
		return dim_co;
	}
	public void setDim_co(int dim_co) {
		this.dim_co = dim_co;
	}
	public String getFst_reg_de() {
		return fst_reg_de;
	}
	public void setFst_reg_de(String fst_reg_de) {
		this.fst_reg_de = fst_reg_de;
	}
	public String getFst_reg_nm() {
		return fst_reg_nm;
	}
	public void setFst_reg_nm(String fst_reg_nm) {
		this.fst_reg_nm = fst_reg_nm;
	}
	public String getOlap_at() {
		return olap_at;
	}
	public void setOlap_at(String olap_at) {
		this.olap_at = olap_at;
	}
	public String getOlap_stl() {
		return olap_stl;
	}
	public void setOlap_stl(String olap_stl) {
		this.olap_stl = olap_stl;
	}
	public String getApp_at() {
		return app_at;
	}
	public void setApp_at(String app_at) {
		this.app_at = app_at;
	}
	public String getStat_nm() {
		return stat_nm;
	}
	public void setStat_nm(String stat_nm) {
		this.stat_nm = stat_nm;
	}
	public String getCell_chk() {
		return cell_chk;
	}
	public void setCell_chk(String cell_chk) {
		this.cell_chk = cell_chk;
	}
	public String getGis_se() {
		return gis_se;
	}
	public void setGis_se(String gis_se) {
		this.gis_se = gis_se;
	}
	public String getRec_tbl_se() {
		return rec_tbl_se;
	}
	public void setRec_tbl_se(String rec_tbl_se) {
		this.rec_tbl_se = rec_tbl_se;
	}
	public String getOlap_round_yn() {
		return olap_round_yn;
	}
	public void setOlap_round_yn(String olap_round_yn) {
		this.olap_round_yn = olap_round_yn;
	}
	public String getDt_unit_yn() {
		return dt_unit_yn;
	}
	public void setDt_unit_yn(String dt_unit_yn) {
		this.dt_unit_yn = dt_unit_yn;
	}
	public String getDim_unit_yn() {
		return dim_unit_yn;
	}
	public void setDim_unit_yn(String dim_unit_yn) {
		this.dim_unit_yn = dim_unit_yn;
	}
	public String getWgt_yn() {
		return wgt_yn;
	}
	public void setWgt_yn(String wgt_yn) {
		this.wgt_yn = wgt_yn;
	}
	public String getMake_at() {
		return make_at;
	}
	public void setMake_at(String make_at) {
		this.make_at = make_at;
	}
	public String getContinue_yn() {
		return continue_yn;
	}
	public void setContinue_yn(String continue_yn) {
		this.continue_yn = continue_yn;
	}
	public String getRef_project() {
		return ref_project;
	}
	public void setRef_project(String ref_project) {
		this.ref_project = ref_project;
	}
	public String getContinue_de() {
		return continue_de;
	}
	public void setContinue_de(String continue_de) {
		this.continue_de = continue_de;
	}
	public String getChange_de() {
		return change_de;
	}
	public void setChange_de(String change_de) {
		this.change_de = change_de;
	}
	public int getRel_sn() {
		return rel_sn;
	}
	public void setRel_sn(int rel_sn) {
		this.rel_sn = rel_sn;
	}
	public String getAnal_cd() {
		return anal_cd;
	}
	public void setAnal_cd(String anal_cd) {
		this.anal_cd = anal_cd;
	}
	public String getComp_cd() {
		return comp_cd;
	}
	public void setComp_cd(String comp_cd) {
		this.comp_cd = comp_cd;
	}
	public String getScr_kor() {
		return scr_kor;
	}
	public void setScr_kor(String scr_kor) {
		this.scr_kor = scr_kor;
	}
	public String getScr_eng() {
		return scr_eng;
	}
	public void setScr_eng(String scr_eng) {
		this.scr_eng = scr_eng;
	}
	public String getGraph_at() {
		return graph_at;
	}
	public void setGraph_at(String graph_at) {
		this.graph_at = graph_at;
	}
	public String getItm_id() {
		return itm_id;
	}
	public void setItm_id(String itm_id) {
		this.itm_id = itm_id;
	}
	public String getObj_var_id() {
		return obj_var_id;
	}
	public void setObj_var_id(String obj_var_id) {
		this.obj_var_id = obj_var_id;
	}

	/** 추가 */
	public String getLvl() {
		return lvl;
	}
	public void setLvl(String lvl) {
		this.lvl = lvl;
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
	public String getList_eng_path() {
		return list_eng_path;
	}
	public void setList_eng_path(String list_eng_path) {
		this.list_eng_path = list_eng_path;
	}
	public int getMax_lvl() {
		return max_lvl;
	}
	public void setMax_lvl(int max_lvl) {
		this.max_lvl = max_lvl;
	}
	public String getList_type() {
		return list_type;
	}
	public void setList_type(String list_type) {
		this.list_type = list_type;
	}
	public String getUp_list_nm() {
		return up_list_nm;
	}
	public void setUp_list_nm(String up_list_nm) {
		this.up_list_nm = up_list_nm;
	}
	public String getUp_list_eng_nm() {
		return up_list_eng_nm;
	}
	public void setUp_list_eng_nm(String up_list_eng_nm) {
		this.up_list_eng_nm = up_list_eng_nm;
	}
	public int getSrc_cnt() {
		return src_cnt;
	}
	public void setSrc_cnt(int src_cnt) {
		this.src_cnt = src_cnt;
	}

	public String getPrd_de() {
		return prd_de;
	}
	public void setPrd_de(String prd_de) {
		this.prd_de = prd_de;
	}
	public String getPrd_se() {
		return prd_se;
	}
	public void setPrd_se(String prd_se) {
		this.prd_se = prd_se;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	public String getStatisticsText() {
		return statisticsText;
	}
	public void setStatisticsText(String statisticsText) {
		this.statisticsText = statisticsText;
	}
	public String getList_nm2() {
		return list_nm2;
	}
	public void setList_nm2(String list_nm2) {
		this.list_nm2 = list_nm2;
	}
	public String getUp_up_list_id() {
		return up_up_list_id;
	}
	public void setUp_up_list_id(String up_up_list_id) {
		this.up_up_list_id = up_up_list_id;
	}
	public String getPrd_info() {
		return prd_info;
	}
	public void setPrd_info(String prd_info) {
		this.prd_info = prd_info;
	}
	public String getDept_nm() {
		return dept_nm;
	}
	public void setDept_nm(String dept_nm) {
		this.dept_nm = dept_nm;
	}
	public String getDept_phon() {
		return dept_phon;
	}
	public void setDept_phon(String dept_phon) {
		this.dept_phon = dept_phon;
	}
}