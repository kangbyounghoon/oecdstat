package com.nurim.oecdstat.common.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ServiceBean extends CommonBean {
	
	private static final long serialVersionUID = 3648679656313993682L;

	
	private String num;
	private String menu_id;
	private String menu_nm;
	private String login_dt;
	private String src_st_dt;
	private String src_ed_dt;	
	private List<ServiceBean> subMenu = new ArrayList<ServiceBean>();
	
	/** 2. 코드관리 메뉴 */	
	private String code_id;
	private String up_code;
	private String code_name;
	private String code_eng_name;
	private String code_prop;
	private String top_code_id;
	private String comments;
	private String sel_code_id;
	private String top_up_code;
	private String top_code_name;
	private String top_code_prop;
	private String lower_code_id;
	private String lower_up_code;
	private String lower_code_name;
	private String lower_code_eng_name;
	private String lower_code_prop;
	private String lower_code_sn;
	private String cont_num_delete;
	private String Cont_num;
	private String lower_keyword;
	private String code_sn;
	private String code_desc;
	private String code_eng_desc;
	private String rn;
	private String roleList;

	private String up_menu_id;
	private String menu_url;
	private String menu_desc;
	private String menu_sort;
	private String menu_div;
	private String site_gb;
	private String reg_de;
	private String chn_de;
	private String menu_div_nm;
	private String site_gb_nm;
	private String reg_nm;
	private String chn_nm;
	private String path_menu_nm;
	private String substrMenuId;
	private String substrLen;
	private String is_leaf;
	private String menu_level;
	private String up_path_menu_nm;
	private String menu_sub_nm;
	private String src_menu_div;
	private String src_site_gb;
	private String src_menu_id;
	private String level;
	private String root_id;
	private String path;
	private String checked;
	private String statKind;
	
	//관련사이트
	private String seq;
	private String site_name;
	private String url;
	private String gubun;
	private String fst_reg_de;
	private String lst_chn_de;
	private String site_img;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getSite_name() {
		return site_name;
	}
	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	public String getFst_reg_de() {
		return fst_reg_de;
	}
	public void setFst_reg_de(String fst_reg_de) {
		this.fst_reg_de = fst_reg_de;
	}
	public String getLst_chn_de() {
		return lst_chn_de;
	}
	public void setLst_chn_de(String lst_chn_de) {
		this.lst_chn_de = lst_chn_de;
	}
	public String getSite_img() {
		return site_img;
	}
	public void setSite_img(String site_img) {
		this.site_img = site_img;
	}
	public String getStatKind() {
		return statKind;
	}
	public void setStatKind(String statKind) {
		this.statKind = statKind;
	}
	public List<ServiceBean> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<ServiceBean> subMenu) {
		this.subMenu = subMenu;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_nm() {
		return menu_nm;
	}
	public void setMenu_nm(String menu_nm) {
		this.menu_nm = menu_nm;
	}
	public String getLogin_dt() {
		return login_dt;
	}
	public void setLogin_dt(String login_dt) {
		this.login_dt = login_dt;
	}
	public String getSrc_st_dt() {
		return src_st_dt;
	}
	public void setSrc_st_dt(String src_st_dt) {
		this.src_st_dt = src_st_dt;
	}
	public String getSrc_ed_dt() {
		return src_ed_dt;
	}
	public void setSrc_ed_dt(String src_ed_dt) {
		this.src_ed_dt = src_ed_dt;
	}
	public String getCode_id() {
		return code_id;
	}
	public void setCode_id(String code_id) {
		this.code_id = code_id;
	}
	public String getUp_code() {
		return up_code;
	}
	public void setUp_code(String up_code) {
		this.up_code = up_code;
	}
	public String getCode_name() {
		return code_name;
	}
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}
	public String getCode_eng_name() {
		return code_eng_name;
	}
	public void setCode_eng_name(String code_eng_name) {
		this.code_eng_name = code_eng_name;
	}
	public String getCode_prop() {
		return code_prop;
	}
	public void setCode_prop(String code_prop) {
		this.code_prop = code_prop;
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
	public String getTop_up_code() {
		return top_up_code;
	}
	public void setTop_up_code(String top_up_code) {
		this.top_up_code = top_up_code;
	}
	public String getTop_code_name() {
		return top_code_name;
	}
	public void setTop_code_name(String top_code_name) {
		this.top_code_name = top_code_name;
	}
	public String getTop_code_prop() {
		return top_code_prop;
	}
	public void setTop_code_prop(String top_code_prop) {
		this.top_code_prop = top_code_prop;
	}
	public String getLower_code_id() {
		return lower_code_id;
	}
	public void setLower_code_id(String lower_code_id) {
		this.lower_code_id = lower_code_id;
	}
	public String getLower_up_code() {
		return lower_up_code;
	}
	public void setLower_up_code(String lower_up_code) {
		this.lower_up_code = lower_up_code;
	}
	public String getLower_code_name() {
		return lower_code_name;
	}
	public void setLower_code_name(String lower_code_name) {
		this.lower_code_name = lower_code_name;
	}
	public String getLower_code_eng_name() {
		return lower_code_eng_name;
	}
	public void setLower_code_eng_name(String lower_code_eng_name) {
		this.lower_code_eng_name = lower_code_eng_name;
	}
	public String getLower_code_prop() {
		return lower_code_prop;
	}
	public void setLower_code_prop(String lower_code_prop) {
		this.lower_code_prop = lower_code_prop;
	}
	public String getLower_code_sn() {
		return lower_code_sn;
	}
	public void setLower_code_sn(String lower_code_sn) {
		this.lower_code_sn = lower_code_sn;
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
	public String getCode_sn() {
		return code_sn;
	}
	public void setCode_sn(String code_sn) {
		this.code_sn = code_sn;
	}
	public String getCode_desc() {
		return code_desc;
	}
	public void setCode_desc(String code_desc) {
		this.code_desc = code_desc;
	}
	public String getCode_eng_desc() {
		return code_eng_desc;
	}
	public void setCode_eng_desc(String code_eng_desc) {
		this.code_eng_desc = code_eng_desc;
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
	public String getMenu_desc() {
		return menu_desc;
	}
	public void setMenu_desc(String menu_desc) {
		this.menu_desc = menu_desc;
	}
	public String getMenu_sort() {
		return menu_sort;
	}
	public void setMenu_sort(String menu_sort) {
		this.menu_sort = menu_sort;
	}
	public String getMenu_div() {
		return menu_div;
	}
	public void setMenu_div(String menu_div) {
		this.menu_div = menu_div;
	}
	public String getSite_gb() {
		return site_gb;
	}
	public void setSite_gb(String site_gb) {
		this.site_gb = site_gb;
	}
	public String getReg_de() {
		return reg_de;
	}
	public void setReg_de(String reg_de) {
		this.reg_de = reg_de;
	}
	public String getChn_de() {
		return chn_de;
	}
	public void setChn_de(String chn_de) {
		this.chn_de = chn_de;
	}
	public String getMenu_div_nm() {
		return menu_div_nm;
	}
	public void setMenu_div_nm(String menu_div_nm) {
		this.menu_div_nm = menu_div_nm;
	}
	public String getSite_gb_nm() {
		return site_gb_nm;
	}
	public void setSite_gb_nm(String site_gb_nm) {
		this.site_gb_nm = site_gb_nm;
	}
	public String getReg_nm() {
		return reg_nm;
	}
	public void setReg_nm(String reg_nm) {
		this.reg_nm = reg_nm;
	}
	public String getChn_nm() {
		return chn_nm;
	}
	public void setChn_nm(String chn_nm) {
		this.chn_nm = chn_nm;
	}
	public String getPath_menu_nm() {
		return path_menu_nm;
	}
	public void setPath_menu_nm(String path_menu_nm) {
		this.path_menu_nm = path_menu_nm;
	}
	public String getSubstrMenuId() {
		return substrMenuId;
	}
	public void setSubstrMenuId(String substrMenuId) {
		this.substrMenuId = substrMenuId;
	}
	public String getSubstrLen() {
		return substrLen;
	}
	public void setSubstrLen(String substrLen) {
		this.substrLen = substrLen;
	}
	public String getIs_leaf() {
		return is_leaf;
	}
	public void setIs_leaf(String is_leaf) {
		this.is_leaf = is_leaf;
	}
	public String getMenu_level() {
		return menu_level;
	}
	public void setMenu_level(String menu_level) {
		this.menu_level = menu_level;
	}
	public String getUp_path_menu_nm() {
		return up_path_menu_nm;
	}
	public void setUp_path_menu_nm(String up_path_menu_nm) {
		this.up_path_menu_nm = up_path_menu_nm;
	}
	public String getMenu_sub_nm() {
		return menu_sub_nm;
	}
	public void setMenu_sub_nm(String menu_sub_nm) {
		this.menu_sub_nm = menu_sub_nm;
	}
	public String getSrc_menu_div() {
		return src_menu_div;
	}
	public void setSrc_menu_div(String src_menu_div) {
		this.src_menu_div = src_menu_div;
	}
	public String getSrc_site_gb() {
		return src_site_gb;
	}
	public void setSrc_site_gb(String src_site_gb) {
		this.src_site_gb = src_site_gb;
	}
	public String getSrc_menu_id() {
		return src_menu_id;
	}
	public void setSrc_menu_id(String src_menu_id) {
		this.src_menu_id = src_menu_id;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
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
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	
	
		
}