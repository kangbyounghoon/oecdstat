package com.nurim.oecdstat.meta.controller;

import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nurim.oecdstat.meta.bean.MetaBean;
import com.nurim.oecdstat.meta.service.MetaService;
import com.nurim.oecdstat.tblInfo.bean.TblInfoBean;

@Controller
public class MetaController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private MetaService metaService;

	boolean success = false;
	
	/**
	 * @throws IOException
	 * 메타 목록 조회
	 * @return "kor/metaInfo/metaInfoList"
	 * @throws
	 */
	@RequestMapping("kor/metaInfo/metaInfoList.html")
	private String selectMetaList(MetaBean metaBean, HttpServletResponse response, Model model) throws Exception {
		String returnUrl = "kor/metaInfo/metaInfoList";
		
		List<MetaBean> fldList = null;
		List<MetaBean> fldList_org = null;
		List<MetaBean> fldDtList = null;
		
		String up_list_code = "";
		
		if(metaBean.getTop_code_id() != null && metaBean.getTop_code_id() != "") {
			request.setAttribute("list_code", metaBean.getList_code());
		}

		// 메뉴 정보 없는 경우
		if(metaBean.getTop_code_id() == null || "".equals(metaBean.getTop_code_id())) {
			metaBean.setTop_code_id("META001");
			metaBean.setUp_list_code("");
		}

		// 메뉴 정보 없는 경우
		if(metaBean.getUp_list_code() == null || "".equals(metaBean.getUp_list_code())) {
			metaBean.setUp_list_code("");
		} else {
			up_list_code = metaBean.getUp_list_code();
			metaBean.setUp_list_code("");
		}

		if("META002".equals(metaBean.getTop_code_id())) {
			fldList = metaService.selectBeanList("meta.selectMetaOrgList",metaBean);
		} else {
			fldList = metaService.selectBeanList("meta.selectMetaList", metaBean);
		}

		// 주제별 메뉴 조회 시 상위 목록 ID 있는 경우
		if(up_list_code != null && !"".equals(up_list_code)) {
			metaBean.setUp_list_code(up_list_code);
			fldDtList = getMetaList(metaBean);
		}

		request.setAttribute("fldList", fldList);
		request.setAttribute("fldList_org", fldList_org);
		request.setAttribute("fldDtList", fldDtList);
		request.setAttribute("metaBean", metaBean);

		if("Y".equals(metaBean.getRe_search())) {
			returnUrl = "jsonView";

			model.addAttribute("fldList", fldList);
			model.addAttribute("metaBean", metaBean);
		}

		return returnUrl;
	}
	
	/**
	 * 메타정보 목록 결과
	 * 
	 * @param metaBean
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("kor/metaInfo/metaInfoListResult.html")
	private String selectTblInfoListResult(MetaBean metaBean, HttpServletResponse response, Model model) throws Exception {
		
		List<MetaBean> fldList = null;
		List<MetaBean> fldDtList = null;
		List<MetaBean> metaList = null;
		
		String top_code_id = "";
		String up_list_code = "";
		
		if(metaBean.getTop_code_id() != null && metaBean.getTop_code_id() != "") {
			top_code_id = metaBean.getTop_code_id();
			request.setAttribute("top_code_id",  metaBean.getTop_code_id());
			request.setAttribute("list_code", metaBean.getList_code());
		}
	
		// 메뉴 정보 없는 경우
		if(metaBean.getTop_code_id() == null || "".equals(metaBean.getTop_code_id())) {
			response.sendRedirect("metaInfoList.html");
			return null;
		}
	
		// 메뉴 정보 없는 경우
		if(metaBean.getUp_list_code() == null || "".equals(metaBean.getUp_list_code())) {
			metaBean.setUp_list_code("");
		}
	
		if("META002".equals(metaBean.getTop_code_id())) {
			fldList = metaService.selectBeanList("meta.selectMetaOrgList", metaBean);
		} else {
			top_code_id = metaBean.getTop_code_id();
			up_list_code = metaBean.getUp_list_code();
			metaBean.setUp_list_code("");
			fldList = metaService.selectBeanList("meta.selectMetaList", metaBean);
		}
	
		// 주제별 메뉴 조회 시 상위 목록 ID 있는 경우
		if(up_list_code != null && !"".equals(up_list_code)) {
			metaBean.setUp_list_code(up_list_code);
			fldDtList = metaService.selectBeanList("meta.selectMetaList", metaBean);
			
			metaBean.setList_code(metaBean.getStats_code());
			metaList = metaService.selectBeanList("meta.selectMetaInfoList", metaBean);
		}
	
		request.setAttribute("fldList", fldList);
		request.setAttribute("fldDtList", fldDtList);
		request.setAttribute("metaList", metaList);
		request.setAttribute("metaBean", metaBean);
	

		request.setAttribute("top_code_id", top_code_id);
		request.setAttribute("up_list_code", up_list_code);


		return "kor/metaInfo/metaInfoList";
	}

	/**
	 * 메타정보 검색 목록 조회
	 * 
	 * @param metaBean
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("kor/metaInfo/MetaInfoSearchList.html")
	private String selectTblInfoSearchList(MetaBean metaBean, HttpServletResponse response, Model model) throws Exception {
		
		List<MetaBean> metaList = metaService.selectBeanList("meta.searchMetaList", metaBean);

		model.addAttribute("metaList", metaList);
		model.addAttribute("metaBean", metaBean);

		return "jsonView";
	}
	
	// 메뉴 및 통계표 목록 조회
	public List<MetaBean> getMetaList(MetaBean metaBean) throws Exception {
		List<MetaBean> list = null;
		
		if(metaBean.getUp_list_code_nm() == null || metaBean.getUp_list_code_nm()== "") {
			list = metaService.selectBeanList("meta.selectMetaList", metaBean);
		} else {
			list = metaService.selectBeanList("meta.selectMetaInfoList", metaBean);
		}
		
		if(metaBean.getTop_code_id().equals("META002") && "META002".equals(metaBean.getTop_code_id())) {
			if(metaBean.getUp_up_list_code() != null && metaBean.getUp_up_list_code() != "") {
				metaBean.setUp_list_code_nm(metaBean.getUp_up_list_code());
				metaBean.setUp_list_code("");
				metaBean.setList_code("");
				list = metaService.selectBeanList("meta.selectMetaInfoList", metaBean);
			}
		}
		
		return list;
	}
	
	/**
	 * 메타정보 분야 목록 조회
	 * 
	 * @param metaBean
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("kor/metaInfo/metaFieldList.html")
	private String selectMetaFieldList(MetaBean metaBean, HttpServletResponse response, Model model) throws Exception {
		
		List<MetaBean> list = getMetaList(metaBean);
		
		model.addAttribute("list", list);
		model.addAttribute("metaBean", metaBean);

		return "jsonView";
	}
	
	@RequestMapping("kor/tblInfo/prevMetaInfo.html")
	private String selectMetaPrev(MetaBean metaBean, HttpServletResponse response, Model model) throws Exception {
		String table="";
		String table1 = ""; //지표요약정보
		String table2 = ""; //지표정의및작성정보
		String table3 = ""; //지표설명자료
		String table3_1 = ""; //공표관련정보 - 발간물
		String table3_2 = ""; 
		
		String tab_on1 = "";
		String tab_on2 = "";
		String tab_on3 = "";
		
		String totalTable3 = ""; //3. 지표 설명자료 최종 테이블
		String DecodeString = URLDecoder.decode(request.getParameter("ix_stats_nm_ko"), "UTF-8");
		
		metaBean.setIx_stats_nm_ko(DecodeString);
		
		int stats_relate_info_cnt = 13; //통계관련정보
		int publict_relate_info_cnt = 10; //공표관련정보
		int publict_relate_info_1_cnt = 4; //발간물
		int publict_relate_info_2_cnt = 5; //전산망(정보시스템)
		int writng_relate_info_cnt = 6; //작성자 관련 정보
		int ix_relate_info_cnt = 8; //지표관련정보
		int writngMth_cnt = 4; //작성방법
		int publict_cnt = 3; //공표
		
		MetaBean sumryBean = null;
		MetaBean writngBean = null;
		MetaBean dtaBean = null;
		MetaBean codeNmBean = null;
		String codeNmEn = "";
		
		if(metaBean != null) {
			if(!"".equals(metaBean.getIx_no()) && metaBean.getIx_no() != null) {
				sumryBean = metaService.selectBean("meta.selectIxSumryInfo",metaBean);
				writngBean = metaService.selectBean("meta.selectIxDfnWritngInfo",metaBean);
				dtaBean = metaService.selectBean("meta.selectIxDcDta", metaBean);
				
				if(metaBean.getStats_code() != null && metaBean.getStats_code() != "") {
					metaBean.setList_code(metaBean.getStats_code());
				}
//				codeNmEn = metaService.selectString("meta.selectCodeNmEn", metaBean);
				codeNmBean = metaService.selectBean("meta.selectCodeNm", metaBean);
			}
			
			if(metaBean.getA_href() != null) {
				if(metaBean.getA_href().equals("#meta_cnt_01"+sumryBean.getIx_no())) {
					tab_on1 = "tab_on";
				} else if (metaBean.getA_href().equals("#meta_cnt_02"+sumryBean.getIx_no())) {
					tab_on2 = "tab_on";
				} else {
					tab_on3 = "tab_on";
				} 
			} else {
				tab_on1 = "tab_on";
			}
			
			table1 += "<div id='meta_cnt_01"+metaBean.getIx_no()+"' class='cnt_box'>";
			table1 += "	<div class='cnt_title'>1. 지표 요약 정보</div>";
			table1 += "		<table class='cnt_table'>";
			table1 += "			<caption>지표 요약 정보</caption>";
			table1 += "			<colgroup>";
			table1 += "				<col width='15%'>";
			table1 += "				<col width='10%'>";
			table1 += "				<col width='25%'>";
			table1 += "				<col width='15%'>";
			table1 += "				<col width='10%'>";
			table1 += "				<col width='25%'>";
			table1 += "			</colgroup>";
			table1 += "			<tbody>";
			table1 += "				<tr class='cnt_tr'>";
			table1 += "					<th class='cnt_th' rowspan='1' scope='rowgroup'>지표관련 소관기관</th>";
			if(sumryBean.getIx_relate_jrsd_instt1() == null) {
				sumryBean.setIx_relate_jrsd_instt1("");
			} else {
				sumryBean.setIx_relate_jrsd_instt1(sumryBean.getIx_relate_jrsd_instt1().replace("\n", "<br>"));
			}
			table1 += "					<td class='cnt_td' rowspan='1' colspan='2'>"+sumryBean.getIx_relate_jrsd_instt1()+"</td>";
			table1 += "					<th class='cnt_th' rowspan='1' scope='rowgroup'>지표 작성기관</th>";
			
			if(sumryBean.getIx_relate_jrsd_instt2() == null) {
				sumryBean.setIx_relate_jrsd_instt2("");
			} else {
				sumryBean.setIx_relate_jrsd_instt2(sumryBean.getIx_relate_jrsd_instt2().replace("\n", "<br>"));
			}
			table1 += "					<td class='cnt_td' colspan='2' rowspan='1'>"+sumryBean.getIx_relate_jrsd_instt2()+"</td>";
			table1 += "				</tr>";
			table1 += "				<tr class='cnt_tr'>";
			
			if(sumryBean.getIx_relate_jrsd_dept1() == null) {
				sumryBean.setIx_relate_jrsd_dept1("");
			} else {
				sumryBean.setIx_relate_jrsd_dept1(sumryBean.getIx_relate_jrsd_dept1().replace("\n", "<br>"));
			}
			table1 += "					<th class='cnt_th' rowspan='1' scope='rowgroup'>지표관련 소관부서</th>";
			table1 += "					<td class='cnt_td' colspan='2'>"+sumryBean.getIx_relate_jrsd_dept1()+"</td>";
			if(sumryBean.getIx_relate_jrsd_dept2() == null) {
				sumryBean.setIx_relate_jrsd_dept2("");
			} else {
				sumryBean.setIx_relate_jrsd_dept2(sumryBean.getIx_relate_jrsd_dept2().replace("\n", "<br>"));
			}
			table1 += "					<th class='cnt_th' rowspan='1' scope='rowgroup'>지표 작성부서</th>";
			table1 += "					<td class='cnt_td' colspan='2'>"+sumryBean.getIx_relate_jrsd_dept2()+"</td>";
			table1 += "				</tr>";
			table1 += "				<tr class='cnt_tr'>";
			table1 += "					<th class='cnt_th' rowspan='2' scope='rowgroup'>지표명</th>";
			table1 += "					<th class='cnt_th' scope='row'>영문</th>";
			
			if(sumryBean.getIx_stats_nm_en() == null) {
				sumryBean.setIx_stats_nm_en("");
			} else {
				sumryBean.setIx_stats_nm_en(sumryBean.getIx_stats_nm_en().replace("\n", "<br>"));
			}
			table1 += "					<td class='cnt_td'>"+sumryBean.getIx_stats_nm_en()+"</td>";
			table1 += "					<th class='cnt_th' rowspan='2' scope='rowgroup'>단위</th>";
			table1 += "					<th class='cnt_th' scope='row'>영문</th>";
			
			if(sumryBean.getIx_unit_en() == null) {
				sumryBean.setIx_unit_en("");
			} else {
				sumryBean.setIx_unit_en(sumryBean.getIx_unit_en().replace("\n", "<br>"));
			}
			table1 += "					<td class='cnt_td'>"+sumryBean.getIx_unit_en()+"</td>";
			table1 += "				</tr>";
			table1 += "				<tr class='cnt_tr'>";
			table1 += "					<th class='cnt_th' scope='row'>한글</th>";
			
			if(sumryBean.getIx_stats_nm_ko() == null) {
				sumryBean.setIx_stats_nm_ko("");
			} else {
				sumryBean.setIx_stats_nm_ko(sumryBean.getIx_stats_nm_ko().replace("\n", "<br>"));
			}
			table1 += "					<td class='cnt_td'>"+sumryBean.getIx_stats_nm_ko()+"</td>";
			table1 += "					<th class='cnt_th' scope='row'>한글</th>";
			
			if(sumryBean.getIx_unit_ko() == null) {
				sumryBean.setIx_unit_ko("");
			} else {
				sumryBean.setIx_unit_ko(sumryBean.getIx_unit_ko().replace("\n", "<br>"));
			}
			table1 += "					<td class='cnt_td'>"+sumryBean.getIx_unit_ko()+"</td>";
			table1 += "				</tr>";
			table1 += "				<tr class='cnt_tr'>";
			table1 += "					<th class='cnt_th' rowspan='2' scope='rowgroup'>지표정의</th>";
			table1 += "					<th class='cnt_th' scope='row'>영문</th>";
			
			if(sumryBean.getStats_dfn_en() == null) {
				sumryBean.setStats_dfn_en("");
			} else {
				sumryBean.setStats_dfn_en(sumryBean.getStats_dfn_en().replace("\n", "<br>"));
			}
			table1 += "					<td class='cnt_td' colspan='4'>"+sumryBean.getStats_dfn_en()+"</td>";
			table1 += "				</tr>";
			table1 += "				<tr class='cnt_tr'>";
			table1 += "					<th class='cnt_th' scope='row'>한글</th>";
			
			if(sumryBean.getStats_dfn_ko() == null) {
				sumryBean.setStats_dfn_ko("");
			} else {
				sumryBean.setStats_dfn_ko(sumryBean.getStats_dfn_ko().replace("\n", "<br>"));
			}
			table1 += "					<td class='cnt_td' colspan='4'>"+sumryBean.getStats_dfn_ko()+"</td>";
			table1 += "				</tr>";
			table1 += "				<tr class='cnt_tr'>";
			table1 += "					<th class='cnt_th' scope='row'>산출식</th>";
			
			if(sumryBean.getComput_frmla() == null) {
				sumryBean.setComput_frmla("");
			} else {
				sumryBean.setComput_frmla(sumryBean.getComput_frmla().replace("\n", "<br>"));
			}
			table1 += "					<td class='cnt_td' colspan='5'>"+sumryBean.getComput_frmla()+"</td>";
			table1 += "				</tr>";
			table1 += "				<tr class='cnt_tr'>";
			table1 += "					<th class='cnt_th' scope='rowgroup'>산출방법</th>";
			if(sumryBean.getComput_mth_ko() == null) {
				sumryBean.setComput_mth_ko("");
			} else {
				sumryBean.setComput_mth_ko(sumryBean.getComput_mth_ko().replace("\n", "<br>"));
			}
			table1 += "					<td class='cnt_td' colspan='5'>"+sumryBean.getComput_mth_ko()+"</td>";
			table1 += "				</tr>";
			table1 += "				<tr class='cnt_tr'>";
			table1 += "					<th class='cnt_th' scope='rowgroup'>Source&methods</br>(OECD 제출용)</th>";
			if(sumryBean.getSource_method() == null) {
				sumryBean.setSource_method("");
			} else {
				sumryBean.setSource_method(sumryBean.getSource_method().replace("\n", "<br>"));
			}
			table1 += "					<td class='cnt_td' colspan='5'>"+sumryBean.getSource_method()+"</td>";
			table1 += "				</tr>";
			
			table1 += "				<tr class='cnt_tr'>";
			table1 += "					<th class='cnt_th' scope='row'>자료출처</th>";
			table1 += "					<td class='cnt_td p_0px' colspan='5'>";
			table1 += "						<div class='table_in_box_wrap'>";
			table1 += "							<div class='table_in_box w25pct'>";
			table1 += "							<div class='table_in_title'>통계명</div>";
			
			if(sumryBean.getPresentn_ix_nm() == null) {
				sumryBean.setPresentn_ix_nm("");
			} else {
				sumryBean.setPresentn_ix_nm(sumryBean.getPresentn_ix_nm().replace("\n", "<br>"));
			}
			table1 += "							<div class='table_in_txt'>"+sumryBean.getPresentn_ix_nm()+"</div>";
			table1 += "						</div>";
			table1 += "						<div class='table_in_box w25pct'>";
			table1 += "							<div class='table_in_title'>작성기관</div>";
			
			if(sumryBean.getInstt() == null) {
				sumryBean.setInstt("");
			} 
			table1 += "							<div class='table_in_txt'>"+sumryBean.getInstt()+"</div>";
			table1 += "						</div>";
			/*table1 += "						<div class='table_in_box'>";
			table1 += "							<div class='table_in_title'>통계(자료)명</div>";
			
			if(sumryBean.getStats_dta_nm() == null) {
				sumryBean.setStats_dta_nm("");
			}
			table1 += "							<div class='table_in_txt'>"+sumryBean.getStats_dta_nm()+"</div>";
			table1 += "						</div>";*/
			table1 += "						<div class='table_in_box w25pct'>";
			table1 += "							<div class='table_in_title'>통계승인여부</div>";
			
			if(sumryBean.getConfm_at() == null) {
				sumryBean.setConfm_at("");
			}
			table1 += "							<div class='table_in_txt'>"+sumryBean.getConfm_at()+"</div>";
			table1 += "						</div>";
			table1 += "						<div class='table_in_box w25pct'>";
			table1 += "							<div class='table_in_title'>공표여부</div>";
			
			if(sumryBean.getPublict_at() == null) {
				sumryBean.setPublict_at("");
			}
			table1 += "							<div class='table_in_txt'>"+sumryBean.getPublict_at()+"</div>";
			table1 += "						</div>";
			table1 += "					</div>";
			table1 += "				</td>";
			table1 += "			</tr>";
			table1 += "			<tr class='cnt_tr'>";	
			table1 += "				<th class='cnt_th' scope='row'>비고</th>";	
			if(sumryBean.getRemarks() == null) {
				sumryBean.setRemarks("");
			} else {
				sumryBean.setRemarks(sumryBean.getRemarks().replace("\n", "<br>"));
			}
			table1 += "				<td class='cnt_td' colspan='5'>"+sumryBean.getRemarks()+"</td>";	
			table1 += "			</tr>";
			table1 += "		</tbody>";
			table1 += "	</table>";
			table1 += "</div>";
			table1 += "<!--cnt_box-end-->";

			//2. 지표 세부정의 테이블
			table2 += "<div id='meta_cnt_02"+metaBean.getIx_no()+"' class='cnt_box'>";
			table2 += "<div class='cnt_title'>2. 지표 세부정의</div>";
			table2 += "	<table class='cnt_table'>";
			table2 += "		<caption>지표 세부정의</caption>";
			table2 += "		<colgroup>";
			table2 += "			<col width='50%'>";
			table2 += "			<col width='50%'>";
			table2 += "			</colgroup>";
			table2 += "		<tbody>";
			table2 += "			<tr class='cnt_tr'>";
			table2 += "				<th class='cnt_th' scope='col'>한글</th>";
			table2 += "				<th class='cnt_th' scope='col'>영문</th>";
			table2 += "			</tr>";
			
			if(writngBean.getOecd_dfn_ko() != null || writngBean.getOecd_dfn_en() != null) {
				table2 += "			<tr class='cnt_tr'>";
				if(writngBean.getOecd_dfn_ko() == null) {
					writngBean.setOecd_dfn_ko("");
				} else {
					writngBean.setOecd_dfn_ko(writngBean.getOecd_dfn_ko().replace("\n", "<br>"));
				}
				table2 += "				<td class='cnt_td'>"+writngBean.getOecd_dfn_ko()+"</td>";
				
				if(writngBean.getOecd_dfn_en() == null) {
					writngBean.setOecd_dfn_en("");
				} else {
					writngBean.setOecd_dfn_en(writngBean.getOecd_dfn_en().replace("\n", "<br>"));
				}
				table2 += "				<td class='cnt_td'>"+writngBean.getOecd_dfn_en()+"</td>";
				table2 += "			</tr>";
			}
			table2 += "		</tbody>";
			table2 += "</table>";
			table2+= "</div>";
			table2+= "<!--cnt_box-end-->";
			
			//3. 지표 설명 자료 테이블
			totalTable3+= "<div id='meta_cnt_03"+metaBean.getIx_no()+"' class='cnt_box'>";
			totalTable3+= "	<div class='cnt_title'>3. 지표 설명 자료</div>";
			totalTable3+= "	<table class='cnt_table'>";
			totalTable3+= "		<caption>지표 설명 자료</caption>";
			totalTable3+= "		<colgroup>";
			totalTable3+= "			<col width='10%'>";
			totalTable3+= "			<col width='10%'>";
			totalTable3+= "			<col width='12%'>";
			totalTable3+= "			<col width='34%'>";
			totalTable3+= "			<col width='34%'>";
			totalTable3+= "		</colgroup>";
				if(dtaBean.getStats_nm_en() == null && dtaBean.getConfm_at_en() == null && dtaBean.getConfm_no_en() == null
						&& dtaBean.getConfm_year_en() == null && dtaBean.getWritng_instt_en() == null && dtaBean.getWritng_purps_en() == null
						&& dtaBean.getWritng_trget_en() == null && dtaBean.getWritng_stle_en() == null && dtaBean.getRelate_laword_en() == null
						&& dtaBean.getFrst_writng_year_en() == null && dtaBean.getRecent_writng_year_en() == null 
						&& dtaBean.getWritng_cycle_en() == null && dtaBean.getWritng_systm_en() == null && dtaBean.getP_publict_at_en() == null
						&& dtaBean.getP_publict_cycle_en() == null && dtaBean.getP_publict_fx_en() == null && dtaBean.getP_publict_nm_en() == null
						&& dtaBean.getC_publict_at_en() == null && dtaBean.getC_publict_cycle_en() == null && dtaBean.getC_publict_fx_en() == null
						&& dtaBean.getC_site_nm_en() == null && dtaBean.getUn_publict_prvnsh_en() == null
						&& dtaBean.getPsitn_instt_nm_en() == null && dtaBean.getPsitn_dept_nm_en() == null && dtaBean.getNm_en() == null
//					  && dtaBean.getPsition() == null && dtaBean.getTelno_ko() == null && dtaBean.getEmail_ko() == null
						) {
				totalTable3+= "		<tbody>";
				totalTable3+= "			<tr class='cnt_tr'>";
				totalTable3+= "				<th class='cnt_th' scope='colgroup' colspan='3'>구분</th>";
				totalTable3+= "				<th class='cnt_th' scope='col' colspan='2'>정보</th>";
				totalTable3+= "			</tr>";
			}else {
				totalTable3+= "		<tbody>";
				totalTable3+= "			<tr class='cnt_tr'>";
				totalTable3+= "				<th class='cnt_th' scope='colgroup' colspan='3'>구분</th>";
				totalTable3+= "				<th class='cnt_th' scope='col'>한글</th>";
				totalTable3+= "				<th class='cnt_th' scope='col'>영문</th>";
				totalTable3+= "			</tr>";
			}
			totalTable3+= "			<tr class='cnt_tr'>";
			
			
			if(dtaBean.getStats_nm_ko() != null || dtaBean.getStats_nm_en() != null) {
				table3+= "				<th class='cnt_th' colspan='2' scope='row' style='text-align:left;'>통계명</th>";
				
				if(dtaBean.getStats_nm_ko() == null) {
					dtaBean.setStats_nm_ko("");
				} else {
					dtaBean.setStats_nm_ko(dtaBean.getStats_nm_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getStats_nm_en() == null) {
					dtaBean.setStats_nm_en("");
				} else {
					dtaBean.setStats_nm_en(dtaBean.getStats_nm_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3+= "				<td class='cnt_td' colspan='2' scope='row'>"+dtaBean.getStats_nm_ko()+"</td>";
				}else {
					table3+= "				<td class='cnt_td'>"+dtaBean.getStats_nm_ko()+"</td>";
					table3+= "				<td class='cnt_td'>"+dtaBean.getStats_nm_en()+"</td>";
				}
				
			} else {
				stats_relate_info_cnt -= 1;
			}
			table3+= "			</tr>";
			
			if(dtaBean.getConfm_at_ko() != null || dtaBean.getConfm_at_en() != null) {
				table3+= "			<tr class='cnt_tr'>";
				table3+= "				<th class='cnt_th' colspan='2' scope='row'  style='text-align:left;'>승인여부</th>";
				
				if(dtaBean.getConfm_at_ko() == null) {
					dtaBean.setConfm_at_ko("");
				} else {
					dtaBean.setConfm_at_ko(dtaBean.getConfm_at_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getConfm_at_en() == null) {
					dtaBean.setConfm_at_en("");
				} else {
					dtaBean.setConfm_at_en(dtaBean.getConfm_at_en().replace("\n", "<br>"));
				}
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3+= "				<td class='cnt_td' colspan='2' scope='row'>"+dtaBean.getConfm_at_ko()+"</td>";
				}else {
					table3+= "				<td class='cnt_td'>"+dtaBean.getConfm_at_ko()+"</td>";
					table3+= "				<td class='cnt_td'>"+dtaBean.getConfm_at_en()+"</td>";
				}
				table3+= "			</tr>";
			} else {
				stats_relate_info_cnt -= 1;
			}
			
			if(dtaBean.getConfm_no_ko() != null || dtaBean.getConfm_no_en() != null) {
				table3+= "			<tr class='cnt_tr'>";
				table3+= "				<th class='cnt_th' colspan='2' scope='row' style='text-align:left;'>승인번호</th>";
				
				if(dtaBean.getConfm_no_ko() == null) {
					dtaBean.setConfm_no_ko("");
				} else {
					dtaBean.setConfm_no_ko(dtaBean.getConfm_no_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getConfm_no_en() == null) {
					dtaBean.setConfm_no_en("");
				} else {
					dtaBean.setConfm_no_en(dtaBean.getConfm_no_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3+= "				<td class='cnt_td' colspan='2' scope='row'>"+dtaBean.getConfm_no_ko()+"</td>";
				}else {
					table3+= "				<td class='cnt_td'>"+dtaBean.getConfm_no_ko()+"</td>";
					table3+= "				<td class='cnt_td'>"+dtaBean.getConfm_no_en()+"</td>";
				}
				table3+= "			</tr>";
			} else {
				stats_relate_info_cnt -= 1;
			}
			
			if(dtaBean.getConfm_year_ko() != null || dtaBean.getConfm_year_en() != null) {
				table3+= "			<tr class='cnt_tr'>";
				table3+= "				<th class='cnt_th' colspan='2' scope='row' style='text-align:left;'>승인년도</th>";
				
				if(dtaBean.getConfm_year_ko() == null) {
					dtaBean.setConfm_year_ko("");
				} else {
					dtaBean.setConfm_year_ko(dtaBean.getConfm_year_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getConfm_year_en() == null) {
					dtaBean.setConfm_year_en("");
				} else {
					dtaBean.setConfm_year_en(dtaBean.getConfm_year_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3+= "				<td class='cnt_td' colspan='2' scope='row'>"+dtaBean.getConfm_year_ko()+"</td>";
				}else {
					table3+= "				<td class='cnt_td'>"+dtaBean.getConfm_year_ko()+"</td>";
					table3+= "				<td class='cnt_td'>"+dtaBean.getConfm_year_en()+"</td>";
				}
				table3+= "			</tr>";
			} else {
				stats_relate_info_cnt -= 1;
			}
			
			if(dtaBean.getWritng_instt_ko() != null || dtaBean.getWritng_instt_en() != null) {
				table3+= "			<tr class='cnt_tr'>";
				table3+= "				<th class='cnt_th' colspan='2' scope='row' style='text-align:left;'>작성기관</th>";
				
				if(dtaBean.getWritng_instt_ko() == null) {
					dtaBean.setWritng_instt_ko("");
				} else {
					dtaBean.setWritng_instt_ko(dtaBean.getWritng_instt_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getWritng_instt_en() == null) {
					dtaBean.setWritng_instt_en("");
				} else {
					dtaBean.setWritng_instt_en(dtaBean.getWritng_instt_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3+= "				<td class='cnt_td' colspan='2' scope='row'>"+dtaBean.getWritng_instt_ko()+"</td>";
				} else {
					table3+= "				<td class='cnt_td'>"+dtaBean.getWritng_instt_ko()+"</td>";
					table3+= "				<td class='cnt_td'>"+dtaBean.getWritng_instt_en()+"</td>";
				}
				table3+= "			</tr>";
			} else {
				stats_relate_info_cnt -= 1;
			}
			
			if(dtaBean.getWritng_purps_ko() != null || dtaBean.getWritng_purps_en() != null) {
				table3+= "			<tr class='cnt_tr'>";
				table3+= "				<th class='cnt_th' colspan='2' scope='row' style='text-align:left;'>작성목적</th>";
				
				if(dtaBean.getWritng_purps_ko() == null) {
					dtaBean.setWritng_purps_ko("");
				} else {
					dtaBean.setWritng_purps_ko(dtaBean.getWritng_purps_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getWritng_purps_en() == null) {
					dtaBean.setWritng_purps_en("");
				} else {
					dtaBean.setWritng_purps_en(dtaBean.getWritng_purps_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3+= "				<td class='cnt_td' colspan='2' scope='row'>"+dtaBean.getWritng_purps_ko()+"</td>";
				} else {
					table3+= "				<td class='cnt_td'>"+dtaBean.getWritng_purps_ko()+"</td>";
					table3+= "				<td class='cnt_td'>"+dtaBean.getWritng_purps_en()+"</td>";
				}
				table3+= "			</tr>";
			} else {
				stats_relate_info_cnt -= 1;
			}
			
			if(dtaBean.getWritng_trget_ko() != null || dtaBean.getWritng_trget_en() != null) {
				table3+= "			<tr class='cnt_tr'>";
				table3+= "				<th class='cnt_th' colspan='2' scope='row' style='text-align:left;'>작성대상</th>";
				
				if(dtaBean.getWritng_trget_ko() == null) {
					dtaBean.setWritng_trget_ko("");
				} else {
					dtaBean.setWritng_trget_ko(dtaBean.getWritng_trget_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getWritng_trget_en() == null) {
					dtaBean.setWritng_trget_en("");
				} else {
					dtaBean.setWritng_trget_en(dtaBean.getWritng_trget_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3 += "			<td class='cnt_td' colspan='2' scope='row'>"+ dtaBean.getWritng_trget_ko() +"</td> ";
				}else {
					table3+= "				<td class='cnt_td'>"+dtaBean.getWritng_trget_ko()+"</td>";
					table3+= "				<td class='cnt_td'>"+dtaBean.getWritng_trget_en()+"</td>";
				}
				
				table3+= "			</tr>";
			} else {
				stats_relate_info_cnt -= 1;
			}
			
			if(dtaBean.getWritng_stle_ko() != null || dtaBean.getWritng_stle_en() != null) {
				table3+= "			<tr class='cnt_tr'>";
				table3+= "				<th class='cnt_th' colspan='2' scope='row' style='text-align:left;'>작성형태</th>";
				
				if(dtaBean.getWritng_stle_ko() == null) {
					dtaBean.setWritng_stle_ko("");
				} else {
					dtaBean.setWritng_stle_ko(dtaBean.getWritng_stle_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getWritng_stle_en() == null) {
					dtaBean.setWritng_stle_en("");
				} else {
					dtaBean.setWritng_stle_en(dtaBean.getWritng_stle_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3+= "				<td class='cnt_td' colspan='2' scope='row'>"+dtaBean.getWritng_stle_ko()+"</td>";
				}else {
					table3+= "				<td class='cnt_td'>"+dtaBean.getWritng_stle_ko()+"</td>";
					table3+= "				<td class='cnt_td'>"+dtaBean.getWritng_stle_en()+"</td>";
				}
				table3+= "			</tr>";
			} else {
				stats_relate_info_cnt -= 1;
			}
			
			if(dtaBean.getRelate_laword_ko() != null || dtaBean.getRelate_laword_en() != null) {
				table3+= "			<tr class='cnt_tr'>";
				table3+= "				<th class='cnt_th' colspan='2' scope='row' style='text-align:left;'>관련법령</th>";
				
				if(dtaBean.getRelate_laword_ko() == null) {
					dtaBean.setRelate_laword_ko("");
				} else {
					dtaBean.setRelate_laword_ko(dtaBean.getRelate_laword_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getRelate_laword_en() == null) {
					dtaBean.setRelate_laword_en("");
				} else {
					dtaBean.setRelate_laword_en(dtaBean.getRelate_laword_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3+= "				<td class='cnt_td' colspan='2' scope='row'>"+dtaBean.getRelate_laword_ko()+"</td>";
				}else {
					table3+= "				<td class='cnt_td'>"+dtaBean.getRelate_laword_ko()+"</td>";
					table3+= "				<td class='cnt_td'>"+dtaBean.getRelate_laword_en()+"</td>";
				}
				table3+= "			</tr>";
			} else {
				stats_relate_info_cnt -= 1;
			}
			
			if(dtaBean.getFrst_writng_year_ko() != null || dtaBean.getFrst_writng_year_en() != null) {
				table3+= "			<tr class='cnt_tr'>";
				table3+= "				<th class='cnt_th' colspan='2' scope='row' style='text-align:left;'>최초작성년도</th>";
				
				if(dtaBean.getFrst_writng_year_ko() == null) {
					dtaBean.setFrst_writng_year_ko("");
				} else {
					dtaBean.setFrst_writng_year_ko(dtaBean.getFrst_writng_year_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getFrst_writng_year_en() == null) {
					dtaBean.setFrst_writng_year_en("");
				} else {
					dtaBean.setFrst_writng_year_en(dtaBean.getFrst_writng_year_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3+= "				<td class='cnt_td' colspan='2' scope='row'>"+dtaBean.getFrst_writng_year_ko()+"</td>";
				}else {
					table3+= "				<td class='cnt_td'>"+dtaBean.getFrst_writng_year_ko()+"</td>";
					table3+= "				<td class='cnt_td'>"+dtaBean.getFrst_writng_year_en()+"</td>";
				}
				
				table3+= "			</tr>";
			} else {
				stats_relate_info_cnt -= 1;
			}
			
			if(dtaBean.getRecent_writng_year_ko() != null || dtaBean.getRecent_writng_year_en() != null) {
				table3+= "			<tr class='cnt_tr'>";
				table3+= "				<th class='cnt_th' colspan='2' scope='row' style='text-align:left;'>최근작성년도</th>";
				
				if(dtaBean.getRecent_writng_year_ko() == null) {
					dtaBean.setRecent_writng_year_ko("");
				} else {
					dtaBean.setRecent_writng_year_ko(dtaBean.getRecent_writng_year_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getRecent_writng_year_en() == null) {
					dtaBean.setRecent_writng_year_en("");
				} else {
					dtaBean.setRecent_writng_year_en(dtaBean.getRecent_writng_year_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3+= "				<td class='cnt_td' colspan='2' scope='row'>"+dtaBean.getRecent_writng_year_ko()+"</td>";
				}else {
					table3+= "				<td class='cnt_td'>"+dtaBean.getRecent_writng_year_ko()+"</td>";
					table3+= "				<td class='cnt_td'>"+dtaBean.getRecent_writng_year_en()+"</td>";
				}
				table3+= "			</tr>";
			} else {
				stats_relate_info_cnt -= 1;
			}
			
			if(dtaBean.getWritng_cycle_ko() != null || dtaBean.getWritng_cycle_en() != null) {
				table3+= "			<tr class='cnt_tr'>";
				table3+= "				<th class='cnt_th' colspan='2' scope='row' style='text-align:left;'>작성주기</th>";
				
				if(dtaBean.getWritng_cycle_ko() == null) {
					dtaBean.setWritng_cycle_ko("");
				} else {
					dtaBean.setWritng_cycle_ko(dtaBean.getWritng_cycle_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getWritng_cycle_en() == null) {
					dtaBean.setWritng_cycle_en("");
				} else {
					dtaBean.setWritng_cycle_en(dtaBean.getWritng_cycle_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3+= "				<td class='cnt_td' colspan='2' scope='row'>"+dtaBean.getWritng_cycle_ko()+"</td>";
				}else {
					table3+= "				<td class='cnt_td'>"+dtaBean.getWritng_cycle_ko()+"</td>";
					table3+= "				<td class='cnt_td'>"+dtaBean.getWritng_cycle_en()+"</td>";
				}
				table3+= "			</tr>";
			} else {
				stats_relate_info_cnt -= 1;
			}
			
			if(dtaBean.getWritng_systm_ko() != null || dtaBean.getWritng_systm_en() != null) {
				table3+= "			<tr class='cnt_tr'>";
				table3+= "				<th class='cnt_th' colspan='2' scope='row' style='text-align:left;'>작성체계</th>";
				
				if(dtaBean.getWritng_systm_ko() == null) {
					dtaBean.setWritng_systm_ko("");
				} else {
					dtaBean.setWritng_stle_ko(dtaBean.getWritng_stle_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getWritng_systm_en() == null) {
					dtaBean.setWritng_systm_en("");
				} else {
					dtaBean.setWritng_systm_en(dtaBean.getWritng_systm_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3+= "				<td class='cnt_td' colspan='2' scope='row'>"+dtaBean.getWritng_systm_ko()+"</td>";
				}else {
					table3+= "				<td class='cnt_td'>"+dtaBean.getWritng_systm_ko()+"</td>";
					table3+= "				<td class='cnt_td'>"+dtaBean.getWritng_systm_en()+"</td>";
				}
				table3+= "			</tr>";
			} else {
				stats_relate_info_cnt -= 1;
			}
			if(stats_relate_info_cnt != 0) {
				totalTable3 += "				<th class='cnt_th' scope='rowgroup' rowspan='"+stats_relate_info_cnt+"'>통계 관련 정보</th>";
				totalTable3 += table3;
			}
			
			table3 = ""; //초기화
			
			//공표관련정보
			/*table3+= "			<tr class='cnt_tr'>";*/
			
			if(dtaBean.getP_publict_at_ko() != null || dtaBean.getP_publict_at_en() != null) {
				table3_1+= "				<th class='cnt_th' scope='row' style='text-align:left;'>공표여부</th>";
				
				if(dtaBean.getP_publict_at_ko() == null) {
					dtaBean.setP_publict_at_ko("");
				} else {
					dtaBean.setP_publict_at_ko(dtaBean.getP_publict_at_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getP_publict_at_en() == null) {
					dtaBean.setP_publict_at_en("");
				} else {
					dtaBean.setP_publict_at_en(dtaBean.getP_publict_at_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3_1+= "				<td class='cnt_td' colspan='2' scope='row'>"+dtaBean.getP_publict_at_ko()+"</td>";
				}else {
					table3_1+= "				<td class='cnt_td'>"+dtaBean.getP_publict_at_ko()+"</td>";
					table3_1+= "				<td class='cnt_td'>"+dtaBean.getP_publict_at_en()+"</td>";
				}
				table3_1+= "			</tr>";
			} else {
				publict_relate_info_cnt -= 1;
				publict_relate_info_1_cnt -= 1;
			}
			
			
			if(dtaBean.getP_publict_cycle_ko() != null || dtaBean.getP_publict_cycle_en() != null) {
				if(dtaBean.getP_publict_at_ko() != null || dtaBean.getP_publict_at_en() != null) {
					table3_1+= "			<tr class='cnt_tr'>";
				}
				table3_1+= "				<th class='cnt_th' scope='row' style='text-align:left;'>공표 주기</th>";
				
				if(dtaBean.getP_publict_cycle_ko() == null) {
					dtaBean.setP_publict_cycle_ko("");
				} else {
					dtaBean.setP_publict_cycle_ko(dtaBean.getP_publict_cycle_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getP_publict_cycle_en() == null) {
					dtaBean.setP_publict_cycle_en("");
				} else {
					dtaBean.setP_publict_cycle_en(dtaBean.getP_publict_cycle_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3_1+= "				<td class='cnt_td' colspan='2' scope='row'>"+dtaBean.getP_publict_cycle_ko()+"</td>";
				}else {
					table3_1+= "				<td class='cnt_td'>"+dtaBean.getP_publict_cycle_ko()+"</td>";
					table3_1+= "				<td class='cnt_td'>"+dtaBean.getP_publict_cycle_en()+"</td>";
				}
				table3_1+= "			</tr>";
			} else {
				publict_relate_info_cnt -= 1;
				publict_relate_info_1_cnt -= 1;
			}
			
			if(dtaBean.getP_publict_fx_ko() != null || dtaBean.getP_publict_fx_en() != null) {
				if(dtaBean.getP_publict_at_ko() != null || dtaBean.getP_publict_at_en() != null && dtaBean.getP_publict_cycle_ko() != null || dtaBean.getP_publict_cycle_en() != null) {
					table3_1+= "			<tr class='cnt_tr'>";
				}
				table3_1+= "				<th class='cnt_th' scope='row' style='text-align:left;'>공표 일정 (시기)</th>";
				
				if(dtaBean.getP_publict_fx_ko() == null) {
					dtaBean.setP_publict_fx_ko("");
				} else {
					dtaBean.setP_publict_fx_ko(dtaBean.getP_publict_fx_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getP_publict_fx_en() == null) {
					dtaBean.setP_publict_fx_en("");
				} else {
					dtaBean.setP_publict_fx_en(dtaBean.getP_publict_fx_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3_1+= "				<td class='cnt_td' colspan='2' scope='row'>"+dtaBean.getP_publict_fx_ko()+"</td>";
				}else {
					table3_1+= "				<td class='cnt_td'>"+dtaBean.getP_publict_fx_ko()+"</td>";
					table3_1+= "				<td class='cnt_td'>"+dtaBean.getP_publict_fx_en()+"</td>";
				}
				table3_1+= "			</tr>";
			} else {
				publict_relate_info_cnt -= 1;
				publict_relate_info_1_cnt -= 1;
			}
			
			if(dtaBean.getP_publict_nm_ko() != null || dtaBean.getP_publict_nm_en() != null) {
				if(dtaBean.getP_publict_at_ko() != null || dtaBean.getP_publict_at_en() != null && dtaBean.getP_publict_cycle_ko() != null || dtaBean.getP_publict_cycle_en() != null
						&& dtaBean.getP_publict_fx_ko() != null || dtaBean.getP_publict_fx_en() != null) {
					table3_1+= "			<tr class='cnt_tr'>";
				}
				table3_1+= "				<th class='cnt_th' scope='row' style='text-align:left;'>발간물명</th>";
				
				if(dtaBean.getP_publict_nm_ko() == null) {
					dtaBean.setP_publict_nm_ko("");
				} else {
					dtaBean.setP_publict_nm_ko(dtaBean.getP_publict_nm_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getP_publict_nm_en() == null) {
					dtaBean.setP_publict_nm_en("");
				} else {
					dtaBean.setP_publict_nm_en(dtaBean.getP_publict_nm_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3_1+= "				<td class='cnt_td' colspan='2' scope='row'>"+dtaBean.getP_publict_nm_ko()+"</td>";
				}else {
					table3_1+= "				<td class='cnt_td'>"+dtaBean.getP_publict_nm_ko()+"</td>";
					table3_1+= "				<td class='cnt_td'>"+dtaBean.getP_publict_nm_en()+"</td>";
				}
				table3_1+= "			</tr>";
			} else {
				publict_relate_info_cnt -= 1;
				publict_relate_info_1_cnt -= 1;
			}
			
			if(publict_relate_info_1_cnt != 0) {
				table3+= "				<th class='cnt_th' scope='rowgroup' rowspan='"+publict_relate_info_1_cnt+"' style='text-align:left;'>발간물</th>";
				table3 += table3_1;
			}
			
			table3_2 = "";
			table3_1 = ""; //초기화			
			
			if(dtaBean.getC_publict_at_ko() != null || dtaBean.getC_publict_at_en() != null) {
				table3_1+= "				<th class='cnt_th' scope='row' style='text-align:left;'>공표여부</th>";
				
				if(dtaBean.getC_publict_at_ko() == null) {
					dtaBean.setC_publict_at_ko("");
				} else {
					dtaBean.setC_publict_at_ko(dtaBean.getC_publict_at_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getC_publict_at_en() == null) {
					dtaBean.setC_publict_at_en("");
				} else {
					dtaBean.setC_publict_at_en(dtaBean.getC_publict_at_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3_1+= "				<td class='cnt_td' colspan='2' scope='row'>"+dtaBean.getC_publict_at_ko()+"</td>";
				}else {
					table3_1+= "				<td class='cnt_td'>"+dtaBean.getC_publict_at_ko()+"</td>";
					table3_1+= "				<td class='cnt_td'>"+dtaBean.getC_publict_at_en()+"</td>";
				}
				table3_1+= "			</tr>";
			} else {
				publict_relate_info_cnt -= 1;
				publict_relate_info_2_cnt -= 1;
			}
			
			
			if(dtaBean.getC_publict_cycle_ko() != null || dtaBean.getC_publict_cycle_en() != null) {
				if(dtaBean.getC_publict_at_ko() != null || dtaBean.getC_publict_at_en() != null) {
					table3_1+= "			<tr class='cnt_tr'>";
				}
				table3_1+= "				<th class='cnt_th' scope='row' style='text-align:left;'>공표 주기</th>";
				
				if(dtaBean.getC_publict_cycle_ko() == null) {
					dtaBean.setC_publict_cycle_ko("");
				} else {
					dtaBean.setC_publict_cycle_ko(dtaBean.getC_publict_cycle_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getC_publict_cycle_en() == null) {
					dtaBean.setC_publict_cycle_en("");
				} else {
					dtaBean.setC_publict_cycle_en(dtaBean.getC_publict_cycle_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3_1+= "				<td class='cnt_td' colspan='2' scope='row'>"+dtaBean.getC_publict_cycle_ko()+"</td>";
				}else {
					table3_1+= "				<td class='cnt_td'>"+dtaBean.getC_publict_cycle_ko()+"</td>";
					table3_1+= "				<td class='cnt_td'>"+dtaBean.getC_publict_cycle_en()+"</td>";
				}
				table3_1+= "			</tr>";
			} else {
				publict_relate_info_cnt -= 1;
				publict_relate_info_2_cnt -= 1;
			}
			
			if(dtaBean.getC_publict_fx_ko() != null || dtaBean.getC_publict_fx_en() != null) {
				if(dtaBean.getC_publict_at_ko() != null || dtaBean.getC_publict_at_en() != null && dtaBean.getC_publict_cycle_ko() != null || dtaBean.getC_publict_cycle_en() != null) {
					table3_1+= "			<tr class='cnt_tr'>";
				}
				table3_1+= "				<th class='cnt_th' scope='row' style='text-align:left;'>공표 일정 (시기)</th>";
				
				if(dtaBean.getC_publict_fx_ko() == null) {
					dtaBean.setC_publict_fx_ko("");
				} else {
					dtaBean.setC_publict_fx_ko(dtaBean.getC_publict_fx_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getC_publict_fx_en() == null) {
					dtaBean.setC_publict_fx_en("");
				} else {
					dtaBean.setC_publict_fx_en(dtaBean.getC_publict_fx_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3_1+= "				<td class='cnt_td' scope='row' colspan='2'>"+dtaBean.getC_publict_fx_ko()+"</td>";
				}else {
					table3_1+= "				<td class='cnt_td'>"+dtaBean.getC_publict_fx_ko()+"</td>";
					table3_1+= "				<td class='cnt_td'>"+dtaBean.getC_publict_fx_en()+"</td>";
				}
				table3_1+= "			</tr>";
			} else {
				publict_relate_info_cnt -= 1;
				publict_relate_info_2_cnt -= 1;
			}
			
			if(dtaBean.getC_site_nm_ko() != null || dtaBean.getC_site_nm_en() != null) {
				if(dtaBean.getC_publict_at_ko() != null || dtaBean.getC_publict_at_en() != null && dtaBean.getC_publict_cycle_ko() != null || dtaBean.getC_publict_cycle_en() != null
						&& dtaBean.getC_publict_fx_ko() != null || dtaBean.getC_publict_fx_en() != null) {
					table3_1+= "			<tr class='cnt_tr'>";
				}
				table3_1+= "				<th class='cnt_th' scope='row' style='text-align:left;'>사이트명</th>";
				
				if(dtaBean.getC_site_nm_ko() == null) {
					dtaBean.setC_site_nm_ko("");
				} else {
					dtaBean.setC_site_nm_ko(dtaBean.getC_site_nm_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getC_site_nm_en() == null) {
					dtaBean.setC_site_nm_en("");
				} else {
					dtaBean.setC_site_nm_en(dtaBean.getC_site_nm_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3_1+= "				<td class='cnt_td' colspan='2' scope='row'>"+dtaBean.getC_site_nm_ko()+"</td>";
				}else {
					table3_1+= "				<td class='cnt_td'>"+dtaBean.getC_site_nm_ko()+"</td>";
					table3_1+= "				<td class='cnt_td'>"+dtaBean.getC_site_nm_en()+"</td>";
				}
				table3_1+= "			</tr>";
			} else {
				publict_relate_info_cnt -= 1;
				publict_relate_info_2_cnt -= 1;
			}
			
			if(dtaBean.getC_site_url() != null) {
				if(dtaBean.getC_publict_at_ko() != null || dtaBean.getC_publict_at_en() != null && dtaBean.getC_publict_cycle_ko() != null || dtaBean.getC_publict_cycle_en() != null
						&& dtaBean.getC_publict_fx_ko() != null || dtaBean.getC_publict_fx_en() != null && dtaBean.getC_site_nm_en() != null || dtaBean.getC_site_nm_ko() != null) {
					table3_1+= "			<tr class='cnt_tr'>";
				}
				table3_1+= "				<th class='cnt_th' scope='row' style='text-align:left;'>사이트주소</th>";
				
				if(dtaBean.getC_site_url() == null) {
					dtaBean.setC_site_url("");
				} else {
					dtaBean.setC_site_url(dtaBean.getC_site_url().replace("\n", "<br>"));
				}
				table3_1+= "				<td class='cnt_td' colspan='2'>"+dtaBean.getC_site_url()+"</td>";
				table3_1+= "			</tr>";
			} else {
				publict_relate_info_cnt -= 1;
				publict_relate_info_2_cnt -= 1;
			}
			
			if(publict_relate_info_2_cnt != 0) {
				/*if(publict_relate_info_2_cnt == 0) {
					table3+="			<tr class='cnt_tr'>";
				}*/
				table3+= "				<th class='cnt_th' scope='rowgroup' rowspan='"+publict_relate_info_2_cnt+"' style='text-align:left;'>전산망<br>(정보시스템)</th>";
				table3 += table3_1;
			}
			
			table3_1 = ""; //초기화
			
			if(dtaBean.getUn_publict_prvnsh_ko() != null || dtaBean.getUn_publict_prvnsh_en() != null) {
				if(publict_relate_info_1_cnt != 0 || publict_relate_info_2_cnt != 0) {
					table3+= "			<tr class='cnt_tr'>";
				}
				table3+= "				<th class='cnt_th' colspan='2' scope='row' style='text-align:left;'>미공표 사유</th>";
				
				if(dtaBean.getUn_publict_prvnsh_ko() == null) {
					dtaBean.setUn_publict_prvnsh_ko("");
				} else {
					dtaBean.setUn_publict_prvnsh_ko(dtaBean.getUn_publict_prvnsh_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getUn_publict_prvnsh_en() == null) {
					dtaBean.setUn_publict_prvnsh_en("");
				} else {
					dtaBean.setUn_publict_prvnsh_en(dtaBean.getUn_publict_prvnsh_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3+= "				<td class='cnt_td' colspan='2' scope='row'>"+dtaBean.getUn_publict_prvnsh_ko()+"</td>";
				}else {
					table3+= "				<td class='cnt_td'>"+dtaBean.getUn_publict_prvnsh_ko()+"</td>";
					table3+= "				<td class='cnt_td'>"+dtaBean.getUn_publict_prvnsh_en()+"</td>";
				}
				table3+= "			</tr>";
			} else {
				publict_relate_info_cnt -= 1;
			}
			
			if(publict_relate_info_cnt != 0) {
				totalTable3+="				<tr class='cnt_tr'>	";
				totalTable3+= "				<th class='cnt_th' scope='rowgroup' rowspan='"+publict_relate_info_cnt+"'>공표 관련 정보</th>";
				totalTable3 += table3;
			}
			
			/*table3 += table3_2;
			totalTable3 += table3;*/
			
			table3_2 = ""; //초기화
			table3 = ""; //초기화
			
			totalTable3+= "			<tr class='cnt_tr'>";
			
			if(dtaBean.getPsitn_instt_nm_ko() != null || dtaBean.getPsitn_instt_nm_en() != null) {
				table3+= "				<th class='cnt_th' colspan='2' scope='row' style='text-align:left;'>소속 기관명</th>";
				
				if(dtaBean.getPsitn_instt_nm_ko() == null) {
					dtaBean.setPsitn_instt_nm_ko("");
				} else {
					dtaBean.setPsitn_instt_nm_ko(dtaBean.getPsitn_instt_nm_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getPsitn_instt_nm_en() == null) {
					dtaBean.setPsitn_instt_nm_en("");
				} else {
					dtaBean.setPsitn_instt_nm_en(dtaBean.getPsitn_instt_nm_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3+= "				<td class='cnt_td' colspan='2' scope='row'>"+dtaBean.getPsitn_instt_nm_ko()+"</td>";
				}else {
					table3+= "				<td class='cnt_td'>"+dtaBean.getPsitn_instt_nm_ko()+"</td>";
					table3+= "				<td class='cnt_td'>"+dtaBean.getPsitn_instt_nm_en()+"</td>";
				}
				table3+= "			</tr>";
				
			} else {
				writng_relate_info_cnt -= 1;
			}
			
			if(dtaBean.getPsitn_dept_nm_ko() != null || dtaBean.getPsitn_dept_nm_en() != null) {
				if(dtaBean.getPsitn_instt_nm_ko() != null || dtaBean.getPsitn_instt_nm_en() != null) {
					table3+= "			<tr class='cnt_tr'>";
				}
				table3+= "				<th class='cnt_th' colspan='2' scope='row' style='text-align:left;'>소속 부서명</th>";
				
				if(dtaBean.getPsitn_dept_nm_ko() == null) {
					dtaBean.setPsitn_dept_nm_ko("");
				} else {
					dtaBean.setPsitn_dept_nm_ko(dtaBean.getPsitn_dept_nm_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getPsitn_dept_nm_en() == null) {
					dtaBean.setPsitn_dept_nm_en("");
				} else {
					dtaBean.setPsitn_dept_nm_en(dtaBean.getPsitn_dept_nm_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3+= "				<td class='cnt_td' colspan='2' scope='row'>"+dtaBean.getPsitn_dept_nm_ko()+"</td>";
				}else {
					table3+= "				<td class='cnt_td'>"+dtaBean.getPsitn_dept_nm_ko()+"</td>";
					table3+= "				<td class='cnt_td'>"+dtaBean.getPsitn_dept_nm_en()+"</td>";
				}
				table3+= "			</tr>";
			} else {
				writng_relate_info_cnt -= 1;
			}
			
			if(dtaBean.getNm_ko() != null || dtaBean.getNm_en() != null) {
				if(dtaBean.getPsitn_instt_nm_ko() != null || dtaBean.getPsitn_instt_nm_en() != null && dtaBean.getPsitn_dept_nm_ko() != null || dtaBean.getPsitn_dept_nm_en() != null) {
					table3+= "			<tr class='cnt_tr'>";
				}
				table3+= "				<th class='cnt_th' colspan='2' scope='row' style='text-align:left;'>이름</th>";
				
				if(dtaBean.getNm_ko() == null) {
					dtaBean.setNm_ko("");
				} else {
					dtaBean.setNm_ko(dtaBean.getNm_ko().replace("\n", "<br>"));
				}
				if(dtaBean.getNm_en() == null) {
					dtaBean.setNm_en("");
				} else {
					dtaBean.setNm_en(dtaBean.getNm_en().replace("\n", "<br>"));
				}
				
				if((dtaBean.getStats_nm_en() == null || "".equals(dtaBean.getStats_nm_en())) 
						&& (dtaBean.getConfm_at_en() == null || "".equals(dtaBean.getConfm_at_en()))
						&& (dtaBean.getConfm_no_en() == null || "".equals(dtaBean.getConfm_no_en()))
						&& (dtaBean.getConfm_year_en() == null || "".equals(dtaBean.getConfm_year_en()))
						&& (dtaBean.getWritng_instt_en() == null  || "".equals(dtaBean.getWritng_instt_en()))
						&& (dtaBean.getWritng_purps_en() == null || "".equals(dtaBean.getWritng_purps_en()))
						&& (dtaBean.getWritng_trget_en() == null || "".equals(dtaBean.getWritng_trget_en()))
						&& (dtaBean.getWritng_stle_en() == null || "".equals(dtaBean.getWritng_stle_en()))
						&& (dtaBean.getRelate_laword_en() == null || "".equals(dtaBean.getRelate_laword_en()))
						&& (dtaBean.getFrst_writng_year_en() == null || "".equals(dtaBean.getFrst_writng_year_en())) 
						&& (dtaBean.getRecent_writng_year_en() == null || "".equals(dtaBean.getRecent_writng_year_en())) 
						&& (dtaBean.getWritng_cycle_en() == null || "".equals(dtaBean.getWritng_cycle_en())) 
						&& (dtaBean.getWritng_systm_en() == null || "".equals(dtaBean.getWritng_systm_en())) 
						&& (dtaBean.getP_publict_at_en() == null || "".equals(dtaBean.getP_publict_at_en()))
						&& (dtaBean.getP_publict_cycle_en() == null || "".equals(dtaBean.getP_publict_cycle_en())) 
						&& (dtaBean.getP_publict_fx_en() == null || "".equals(dtaBean.getP_publict_fx_en()))
						&& (dtaBean.getP_publict_nm_en() == null || "".equals(dtaBean.getP_publict_nm_en()))
						&& (dtaBean.getC_publict_at_en() == null || "".equals(dtaBean.getC_publict_at_en()))
						&& (dtaBean.getC_publict_cycle_en() == null || "".equals(dtaBean.getC_publict_cycle_en()))
						&& (dtaBean.getC_publict_fx_en() == null || "".equals(dtaBean.getC_publict_fx_en()))
						&& (dtaBean.getC_site_nm_en() == null || "".equals(dtaBean.getC_site_nm_en()))
						&& (dtaBean.getUn_publict_prvnsh_en() == null || "".equals(dtaBean.getUn_publict_prvnsh_en()))
						&& (dtaBean.getPsitn_instt_nm_en() == null  || "".equals(dtaBean.getPsitn_instt_nm_en()))
						&& (dtaBean.getPsitn_dept_nm_en() == null  || "".equals(dtaBean.getPsitn_dept_nm_en()))
						&& (dtaBean.getNm_en() == null || "".equals(dtaBean.getNm_en()))) {
					table3+= "				<td class='cnt_td' colspan='2' scope='row'>"+dtaBean.getNm_ko()+"</td>";
				}else {
					table3+= "				<td class='cnt_td'>"+dtaBean.getNm_ko()+"</td>";
					table3+= "				<td class='cnt_td'>"+dtaBean.getNm_en()+"</td>";
				}
				table3+= "			</tr>";
			} else {
				writng_relate_info_cnt -= 1;
			}
			
			if(dtaBean.getPsition() != null) {
				if(dtaBean.getPsitn_instt_nm_ko() != null || dtaBean.getPsitn_instt_nm_en() != null && dtaBean.getPsitn_dept_nm_ko() != null || dtaBean.getPsitn_dept_nm_en() != null && dtaBean.getNm_ko() != null || dtaBean.getNm_en() != null) {
					table3+= "			<tr class='cnt_tr'>";
				}
				table3+= "				<th class='cnt_th' colspan='2' scope='row' style='text-align:left;'>직책 또는 직위</th>";
				
				if(dtaBean.getPsition() == null) {
					dtaBean.setPsition("");
				} else {
					dtaBean.setPsition(dtaBean.getPsition().replace("\n", "<br>"));
				}
				table3+= "				<td class='cnt_td' colspan='2'>"+dtaBean.getPsition()+"</td>";
				table3+= "			</tr>";
			} else {
				writng_relate_info_cnt -= 1;
			}
			
			if(dtaBean.getTelno_ko() != null) {
				if(dtaBean.getPsitn_instt_nm_ko() != null || dtaBean.getPsitn_instt_nm_en() != null && dtaBean.getPsitn_dept_nm_ko() != null || dtaBean.getPsitn_dept_nm_en() != null
						&& dtaBean.getNm_ko() != null || dtaBean.getNm_en() != null && dtaBean.getPsition() != null) {
					table3+= "			<tr class='cnt_tr'>";
				}
				table3+= "				<th class='cnt_th' colspan='2' scope='row' style='text-align:left;'>전화번호</th>";
				
				if(dtaBean.getTelno_ko() == null) {
					dtaBean.setTelno_ko("");
				} else {
					dtaBean.setTelno_ko(dtaBean.getTelno_ko().replace("\n", "<br>"));
				}
				table3+= "				<td class='cnt_td' colspan='2'>"+dtaBean.getTelno_ko()+"</td>";
				
				table3+= "			</tr>";
			} else {
				writng_relate_info_cnt -= 1;
			}
			
			if(dtaBean.getEmail_ko() != null) {
				if(dtaBean.getPsitn_instt_nm_ko() != null || dtaBean.getPsitn_instt_nm_en() != null && dtaBean.getPsitn_dept_nm_ko() != null || dtaBean.getPsitn_dept_nm_en() != null
						&& dtaBean.getNm_ko() != null || dtaBean.getNm_en() != null && dtaBean.getTelno_ko() != null || dtaBean.getTelno_en() != null) {
					table3+= "			<tr class='cnt_tr'>";
				}
				table3+= "				<th class='cnt_th' colspan='2' scope='row' style='text-align:left;'>이메일</th>";
				
				if(dtaBean.getEmail_ko() == null) {
					dtaBean.setEmail_ko("");
				} else {
					dtaBean.setEmail_ko(dtaBean.getEmail_ko().replace("\n", "<br>"));
				}
				table3+= "				<td class='cnt_td' colspan='2'>"+dtaBean.getEmail_ko()+"</td>";
				
				table3+= "			</tr>";
			} else {
				writng_relate_info_cnt -= 1;
			}
			
			if(writng_relate_info_cnt != 0) {
				totalTable3 += "				<th class='cnt_th' scope='rowgroup' rowspan='"+writng_relate_info_cnt+"'>작성자 관련 정보</th>";
				totalTable3 += table3;
			}
			
			table3 = ""; //초기화
			
//			totalTable3+= "			<tr class='cnt_tr'>";
			
//			if(dtaBean.getIx_nm_ko() != null || dtaBean.getIx_nm_en() != null) {
//				table3_2 += "				<th class='cnt_th th_fff' colspan='2' scope='row'>지표명</th>";
//				if(dtaBean.getIx_nm_ko() == null) {
//					dtaBean.setIx_nm_ko("");
//				}
//				table3_2 += "				<td class='cnt_td'>"+dtaBean.getIx_nm_ko()+"</td>";
//				
//				if(dtaBean.getIx_nm_en() == null) {
//					dtaBean.setIx_nm_en("");
//				}
//				table3_2 += "				<td class='cnt_td'>"+dtaBean.getIx_nm_en()+"</td>";
//				table3_2+= "			</tr>";
//			} else {
//				ix_relate_info_cnt -= 1;
//			}
//			
//			String table3_3 = "";
//			
//			if(dtaBean.getReport_cn_examin_qesitm_ko() != null || dtaBean.getReport_cn_examin_qesitm_en() != null) {
//				table3_1+= "				<th class='cnt_th th_fff' scope='row'>보고내용/조사문항</th>";
//				
//				if(dtaBean.getReport_cn_examin_qesitm_ko() == null) {
//					dtaBean.setReport_cn_examin_qesitm_ko("");
//				}
//				table3_1+= "				<td class='cnt_td'>"+dtaBean.getReport_cn_examin_qesitm_ko()+"</td>";
//				
//				if(dtaBean.getReport_cn_examin_qesitm_en() == null) {
//					dtaBean.setReport_cn_examin_qesitm_en("");
//				}
//				table3_1+= "				<td class='cnt_td'>"+dtaBean.getReport_cn_examin_qesitm_en()+"</td>";
//				table3_1+= "			</tr>";
//				
//				table3_3 += table3_1;
//				table3_1="";
//			}else {
//				ix_relate_info_cnt -= 1;
//				writngMth_cnt -= 1;
//			}
//			
//			if(dtaBean.getComput_mth_ko() != null || dtaBean.getComput_mth_en() != null) {
//				if(dtaBean.getReport_cn_examin_qesitm_ko() != null || dtaBean.getReport_cn_examin_qesitm_en() != null) {
//					table3_1+= "			<tr class='cnt_tr'>";
//				}
//				table3_1+= "				<th class='cnt_th th_fff' scope='row'>산출방법</th>";
//				
//				if(dtaBean.getComput_mth_ko() == null) {
//					dtaBean.setComput_mth_ko("");
//				} else {
//					dtaBean.setComput_mth_ko(dtaBean.getComput_mth_ko().replace("\n", "<br>"));
//				}
//				table3_1+= "				<td class='cnt_td'>"+dtaBean.getComput_mth_ko()+"</td>";
//				
//				if(dtaBean.getComput_mth_en() == null) {
//					dtaBean.setComput_mth_en("");
//				} else {
//					dtaBean.setComput_mth_en(dtaBean.getComput_mth_en().replace("\n", "<br>"));
//				}
//				table3_1+= "				<td class='cnt_td'>"+dtaBean.getComput_mth_en()+"</td>";
//				table3_1+= "			</tr>";
//				
//				table3_3 += table3_1;
//				table3_1="";
//			}else {
//				ix_relate_info_cnt -= 1;
//				writngMth_cnt -= 1;
//			}
//			
//			if(dtaBean.getCtgry_ko() != null || dtaBean.getCtgry_en() != null) {
//				if(dtaBean.getReport_cn_examin_qesitm_ko() != null || dtaBean.getReport_cn_examin_qesitm_en() != null && dtaBean.getComput_mth_ko() != null || dtaBean.getComput_mth_en() != null) {
//					table3_1+= "			<tr class='cnt_tr'>";
//				}
//				table3_1+= "				<th class='cnt_th th_fff' scope='row'>범주</th>";
//				
//				if(dtaBean.getCtgry_ko() == null) {
//					dtaBean.setCtgry_ko("");
//				}	
//				table3_1+= "				<td class='cnt_td'>"+dtaBean.getCtgry_ko()+"</td>";
//				
//				if(dtaBean.getCtgry_en() == null) {
//					dtaBean.setCtgry_en("");
//				}	
//				table3_1+= "				<td class='cnt_td'>"+dtaBean.getCtgry_en()+"</td>";
//				table3_1+= "			</tr>";
//				
//				table3_3 += table3_1;
//				table3_1="";
//			}else {
//				ix_relate_info_cnt -= 1;
//				writngMth_cnt -= 1;
//			}
//			
//			if(dtaBean.getRm_ko() != null || dtaBean.getRm_en() != null) {
//				if(dtaBean.getReport_cn_examin_qesitm_ko() != null || dtaBean.getReport_cn_examin_qesitm_en() != null && dtaBean.getComput_mth_ko() != null 
//						|| dtaBean.getComput_mth_en() != null && dtaBean.getCtgry_ko() != null || dtaBean.getCtgry_en() != null) {
//					table3_1+= "			<tr class='cnt_tr'>";
//				}
//				table3_1+= "				<th class='cnt_th th_fff' scope='row'>비고</th>";
//				
//				table2 += "			<tr class='cnt_tr'>";
//				table2 += "				<th class='cnt_th' scope='row'>OECD 정의</th>";
//				
//				if(dtaBean.getRm_ko() == null) {
//					dtaBean.setRm_ko("");
//				} else {
//					dtaBean.setRm_ko(dtaBean.getRm_ko().replace("\n", "<br>"));
//				}
//				table3_1+= "				<td class='cnt_td'>"+dtaBean.getRm_ko()+"</td>";
//				
//				if(dtaBean.getRm_en() == null) {
//					dtaBean.setRm_en("");
//				} else {
//					dtaBean.setRm_en(dtaBean.getRm_en().replace("\n", "<br>"));
//				}
//				table3_1+= "				<td class='cnt_td'>"+dtaBean.getRm_en()+"</td>";
//				table3_1+= "			</tr>";
//				
//				table3_3 += table3_1;
//				table3_1="";
//			}else {
//				ix_relate_info_cnt -= 1;
//				writngMth_cnt -= 1;
//			}
//			
//			
//			if(writngMth_cnt != 0) {
//				if(dtaBean.getIx_nm_ko() != null || dtaBean.getIx_nm_en() != null) {
//					table3+="			<tr class='cnt_tr'>";
//				}
//				table3+= "				<th class='cnt_th th_fff' scope='rowgroup' rowspan='"+writngMth_cnt+"'>작성방법</th>";
//				table3 += table3_3;
//			}
//			
//			table3 += table3_1;
//			
//			table3_1 = ""; //초기화
//			
//			if(dtaBean.getPblcte_wrt_at_ko() != null || dtaBean.getPblcte_wrt_at_en() != null) {
//				table3_1+= "				<th class='cnt_th th_fff' scope='row'>발간물수록 여부</th>";
//				
//				if(dtaBean.getPblcte_wrt_at_ko() == null) {
//					dtaBean.setPblcte_wrt_at_ko("");
//				}	
//				table3_1+= "				<td class='cnt_td'>"+dtaBean.getPblcte_wrt_at_ko()+"</td>";
//				
//				if(dtaBean.getPblcte_wrt_at_en() == null) {
//					dtaBean.setPblcte_wrt_at_en("");
//				}
//				table3_1+= "				<td class='cnt_td'>"+dtaBean.getPblcte_wrt_at_en()+"</td>";
//				table3_1+= "			</tr>";
//			}else {
//				ix_relate_info_cnt -= 1;
//				publict_cnt -= 1;
//			}
//			
//			if(dtaBean.getCmpt_ntwk_wrt_at_ko() != null || dtaBean.getCmpt_ntwk_wrt_at_en() != null) {
//				if(dtaBean.getPblcte_wrt_at_ko() != null || dtaBean.getPblcte_wrt_at_en() != null) {
//					table3_1+= "			<tr class='cnt_tr'>";
//				}
//				table3_1+= "				<th class='cnt_th th_fff' scope='row'>전산망수록 여부</th>";
//				
//				if(dtaBean.getCmpt_ntwk_wrt_at_ko() == null) {
//					dtaBean.setCmpt_ntwk_wrt_at_ko("");
//				}				
//				table3_1+= "				<td class='cnt_td'>"+dtaBean.getCmpt_ntwk_wrt_at_ko()+"</td>";
//				
//				if(dtaBean.getCmpt_ntwk_wrt_at_en() == null) {
//					dtaBean.setCmpt_ntwk_wrt_at_en("");
//				}
//				table3_1+= "				<td class='cnt_td'>"+dtaBean.getCmpt_ntwk_wrt_at_en()+"</td>";
//				table3_1+= "			</tr>";
//			}else {
//				ix_relate_info_cnt -= 1;
//				publict_cnt -= 1;
//			}
//			
//			if(dtaBean.getUn_wrt_prvonsh_ko() != null || dtaBean.getUn_wrt_prvonsh_en() != null) {
//				if(dtaBean.getPblcte_wrt_at_ko() != null || dtaBean.getPblcte_wrt_at_en() != null || dtaBean.getCmpt_ntwk_wrt_at_ko() != null || dtaBean.getCmpt_ntwk_wrt_at_en() != null) {
//					table3_1+= "			<tr class='cnt_tr'>";
//				}
//				table3_1+= "				<th class='cnt_th th_fff' scope='row'>미수록 사유</th>";
//				
//				if(dtaBean.getUn_wrt_prvonsh_ko() == null) {
//					dtaBean.setUn_wrt_prvonsh_ko("");
//				}
//				table3_1+= "				<td class='cnt_td'>"+dtaBean.getUn_wrt_prvonsh_ko()+"</td>";
//				
//				if(dtaBean.getUn_wrt_prvonsh_en() == null) {
//					dtaBean.setUn_wrt_prvonsh_en("");
//				}
//				table3_1+= "				<td class='cnt_td'>"+dtaBean.getUn_wrt_prvonsh_en()+"</td>";
//				table3_1+= "			</tr>";
//			}else {
//				ix_relate_info_cnt -= 1;
//				publict_cnt -= 1;
//			}
//			
//			if(publict_cnt != 0) {
//				if(dtaBean.getIx_nm_ko() != null || dtaBean.getIx_nm_en() != null && writngMth_cnt != 0) {
//					table3+= "			<tr class='cnt_tr'>";
//				}
//				table3 += "				<th class='cnt_th th_fff' scope='rowgroup' rowspan='"+publict_cnt+"'>공표</th>";
//				table3 += table3_1;
//			}
//			
//			if(ix_relate_info_cnt != 0) {
//				totalTable3+= "				<th class='cnt_th' scope='rowgroup' rowspan='"+ix_relate_info_cnt+"'>지표 관련 정보</th>";
//				totalTable3 += table3_2;
//				totalTable3 += table3;
//			}
			
			totalTable3+= "		</tbody>";
			totalTable3+= "	</table>";
			totalTable3+= "</div>";
			totalTable3+= "<!--cnt_box-end-->";
			totalTable3+= "</div>";
			totalTable3+="</div>";
		}
		
		table = table1 + table2 + totalTable3;
		model.addAttribute("metaTable", table);
		request.setAttribute("codeNmBean", codeNmBean);
		return "kor/tblInfo/popup/previewMetaInfo";
	}	
}
