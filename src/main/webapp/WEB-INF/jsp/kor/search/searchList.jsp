<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/etc/jsp_header.jspf" %>

<script type="text/javascript">
$(function() {
	var form = $("#bodyForm");
	
	$("#metaInfoView").hide();
	
	// 전체 검색
	$("[id^='btnAllSearch']").click(function() {
		fn_search();
	});
	
 	// 키워드 keydown 이벤트
	$("#keyword").keydown(function (key) {
	    if (key.keyCode == 13) {
	    	fn_search();
	    }
	}); 
	
	// 더보기
	$("a[id^='viewMore']").click(function() {
		$("#up_menu_id").val($(this).attr("data-menuId"));

		if($("#srchwrd").val() != "") {
			form.attr("target", "_self");
			form.attr("action", "<c:url value='/kor/search/SearchList.html'/>");
			form.submit();
		}
	});
	
	// 통계표 보기
	$("a[id^='showStatView']").click(function() {
		stat_view($(this).attr("data-tblId"), $(this).attr("data-lang"));
	});
	
	// 메타정보 보기
	$("a[id^='showMetaView']").click(function() {
		fn_ajax_metaInfo($(this).attr("data-ixNo"), $(this).attr("data-ixStatsNmKo"), $(this).attr("data-statsThema"), $(this).attr("href"), $(this).attr("data-statsCode"));
	});
	
	// 통계표 위치 보기
	$("a[id^='showDetailTbl']").click(function() {
		fn_move_statLocation($(this).attr("data-menuId"), $(this).attr("data-rootId"), $(this).attr("data-vwCd"), $(this).attr("data-upListId"), $(this).attr("data-listId"));
	});
	
	// 메타 위치 보기
	$("a[id^='showDetailMeta']").click(function() {
		fn_move_metaLocation($(this).attr("data-menuId"), $(this).attr("data-rootId"), $(this).attr("data-topCodeId"), $(this).attr("data-upListCode"), $(this).attr("data-statsCode"), $(this).attr("data-statsThema"));
	});
	
	// 메타정보 지표관련 버튼 클릭
	$(document).on("click", "#btn_meta_sumry, #btn_meta_writng, #btn_meta_dta", function(){
		fn_add_metaInfoTabOn($(this).attr("href"), $(this).attr("data-a"));
	});
	
	// 메타정보 닫기 버튼 클릭
	$("#btn_close").click(function(){
		$("#metaInfoView").hide();
		$("#metaViewList").html("");
	});
	
	// tab_on
	var up_menu_id = $("#up_menu_id").val();

    if(up_menu_id == '2020000') {
         $("#viewMoreTabStat").addClass("tab_on");
	} else if(up_menu_id == '2030000') {
         $("#viewMoreTabMeta").addClass("tab_on");
	} else {
		$("#btnAllSearchDetail").addClass("tab_on");
	}
});

function fn_search(){
	var form = $("#bodyForm");
	
	$("#up_menu_id").val("");
	$("#allSearchAt").val("Y");

	form.attr("target", "_self");
	form.attr("action", "<c:url value='/kor/search/SearchList.html'/>");
	form.submit();
}

// 통계 위치 이동 함수
function fn_move_statLocation(menuId, rootId, vw_cd, up_list_id, list_id) {
	var statForm = $("#statDbForm");

	$("#menuId").remove();
	$("#rootId").remove();
	$("#vw_cd").remove();
	$("#up_list_id").remove();
	$("#list_id").remove();

	statForm.append("<input type='hidden' id='menuId' name='menuId' value='" + menuId + "' />");
	statForm.append("<input type='hidden' id='rootId' name='rootId' value='" + rootId + "' />");
	statForm.append("<input type='hidden' id='vw_cd' name='vw_cd' value='" + vw_cd + "' />");
	statForm.append("<input type='hidden' id='up_list_id' name='up_list_id' value='" + up_list_id + "' />");
	statForm.append("<input type='hidden' id='list_id' name='list_id' value='" + list_id + "' />");
	
	statForm.attr("action", "<c:url value='/kor/tblInfo/TblInfoListResult.html'/>");	
	statForm.attr("target", "_blank");
	statForm.submit();
}

// 메타정보 통신 함수
function fn_ajax_metaInfo(ix_no, ix_stats_nm_ko, stats_thema, divVal, stats_code){
	
	$.ajax({
		url : "<c:url value='/kor/metaInfo/metaInfoDetail.html'/>",
		data :  { 
			ix_no : ix_no, 
			ix_stats_nm_ko : ix_stats_nm_ko, 
			up_list_code_nm : stats_thema,
			stats_code : stats_code
		},
		type : "POST",
		async: false,
		beforeSend : function(xhr){
            xhr.setRequestHeader(header, token);
		},
		success : function(data) {
			if(data != null && data != "'" && data != 'undefined' ) {
				$("#metaInfoView").show();
				$("#metaViewList").append(data.metaTable);				
				
				if (divVal.indexOf("meta_cnt") >= 0){
					fn_add_metaInfoTabOn(divVal, ix_no);
				}
			}
		},
		error : function() {
	        return false;
	    }
	});
}

function fn_move_metaLocation(menuId, rootId, top_code_id, up_list_code, stats_code, stats_thema) {
	var metaForm = $("#metaInfoForm");

	$("#menuId").remove();
	$("#rootId").remove();
	$("#top_code_id").remove();
	$("#up_list_code").remove();
	$("#stats_code").remove();
	$("#stats_thema").remove();

	metaForm.append("<input type='hidden' id='menuId' name='menuId' value='" + menuId + "' />");
	metaForm.append("<input type='hidden' id='rootId' name='rootId' value='" + rootId + "' />");
	metaForm.append("<input type='hidden' id='top_code_id' name='top_code_id' value='" + top_code_id + "' />");
	metaForm.append("<input type='hidden' id='up_list_code' name='up_list_code' value='" + up_list_code + "' />");
	metaForm.append("<input type='hidden' id='stats_code' name='stats_code' value='" + stats_code + "' />");
	metaForm.append("<input type='hidden' id='stats_thema' name='stats_thema' value='" + stats_thema + "' />");
	
	metaForm.attr("action", "<c:url value='/kor/metaInfo/metaInfoListResult.html'/>");	
	metaForm.attr("target", "_blank");
	metaForm.submit();
}

// 메타정보 탭온 추가 함수
function fn_add_metaInfoTabOn(divVal, ix_no){
	$("#btn_meta_sumry, #btn_meta_writng, #btn_meta_dta").removeClass("tab_on");
	
	if(divVal == "#meta_cnt_01" || divVal == "#meta_cnt_01" + ix_no){
		$("#btn_meta_sumry").addClass("tab_on");		
	} else if(divVal == "#meta_cnt_02" || divVal == "#meta_cnt_02" + ix_no){
		$("#btn_meta_writng").addClass("tab_on");		
	} else if(divVal == "#meta_cnt_03" || divVal == "#meta_cnt_03" + ix_no){
		$("#btn_meta_dta").addClass("tab_on");		
	}
	
	window.location.hash = divVal
}
</script>

<input type="hidden" id="searchRootId" name="rootId" value="">
<input type="hidden" id="searchMenuId" name="menuId" value="">
<input type="hidden" id="up_menu_id" name="up_menu_id" value="<c:out value='${searchBean.up_menu_id}' />">
<input type="hidden" id="allSearchAt" name="allSearchAt" value="<c:out value='${searchBean.allSearchAt}' />">
<input type="hidden" id="menu_url" name="menu_url" value="">

<input type="hidden" id="tbl_id" name="tbl_id" value="" />
<input type="hidden" id="src_part_cd" name="src_part_cd" value="" />
<input type="hidden" id="board_seq" name="board_seq" value="" />
<input type="hidden" id="cont_class" name="cont_class" value="" />
<input type="hidden" id="cont_date" name="cont_date" value="" />

<input type="hidden" id="keyword" name="keyword" value="" />
<input type="hidden" id="searchType" name="searchType" value="subject" />

<input type="hidden" id="allSearchCo" name="allSearchCo" value="<c:out value='${searchBean.allSearchCo}' />" />
<input type="hidden" id="tblSearchCo" name="tblSearchCo" value="<c:out value='${searchBean.tblSearchCo}' />" />
<input type="hidden" id="metaSearchCo" name="metaSearchCo" value="<c:out value='${searchBean.metaSearchCo}' />" />

<div class="center">
	<div class="main_search">
		<div class="search_box placeholder_box">
			<label for="keyword">검색어를 입력하세요</label>
			<input id="keyword" name="srchwrd" type="text" class="search_input" value="<c:out value='${searchBean.srchwrd}' />">
			<div class="submit_btn"><input type="submit" id="btnAllSearchTop" value="검색" title="검색"></div>
		</div>
	</div>
	
	<div class="search_result_box">
		<spring:message code="msg.keyword" text="Search word" /> " <span class="search_k"><c:out value= "${searchBean.srchwrd}" /></span> " <spring:message code="message.oecdstat.about" text="About" />
		<spring:message code="msg.all" text="All" />
		<span class="search_num"><fmt:formatNumber type="currency" value="${searchBean.allSearchAt eq 'Y' ? searchBean.allSearchCo : (searchBean.up_menu_id eq '2020000' ? searchBean.tblSearchCo : searchBean.metaSearchCo)}" pattern="###,###" /></span><spring:message code="message.oecdstat.searchcodc" text="Results were found." />
	</div>
	
	<c:set var="searchTitleStat" value="" />
	<c:set var="searchTitleMeta" value="" />
	
	<c:forEach items="${menu}" var="menu" varStatus="status">
		<c:choose>
			<c:when test="${menu.up_menu_id eq '2020000'}">
				<c:set var="searchTitleStat" value="${menu.menu_nm}" />
			</c:when>
			<c:when test="${menu.up_menu_id eq '2030000'}">
				<c:set var="searchTitleMeta" value="${menu.menu_nm}" />
			</c:when>
		</c:choose>
	</c:forEach>
	
	<div class="search_top_tab_wrap">
		<a href="javascript:;" id="btnAllSearchDetail" class="search_top_tab"><spring:message code="msg.all" text="All" /> <span class="search_num">[ <fmt:formatNumber type="currency" value="${searchBean.allSearchCo}" pattern="###,###" />건 ]</span></a>
		<a href="javascript:;" id="viewMoreTabStat" data-menuId="2020000" class="search_top_tab"><c:out value="${searchTitleStat}" /> <span class="search_num">[ <fmt:formatNumber type="currency" value="${searchBean.tblSearchCo}" pattern="###,###" />건 ]</span></a>
		<a href="javascript:;" id="viewMoreTabMeta" data-menuId="2030000" class="search_top_tab"><c:out value="${searchTitleMeta}" /> <span class="search_num">[  <fmt:formatNumber type="currency" value="${searchBean.metaSearchCo}" pattern="###,###" />건  ]</span></a>
	</div>
	
	<!-- 통계서비스 -->
	<c:import url="/WEB-INF/jsp/kor/search/in/search_tblList.jsp"></c:import>
	
	<!-- 메타정보 -->
	<c:import url="/WEB-INF/jsp/kor/search/in/search_metaList.jsp"></c:import>
</div>