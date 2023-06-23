<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/etc/jsp_header.jspf"%>

<script type="text/Javascript" src="${pageContext.request.contextPath}/js/svcTblInfo.js"></script>
<script>
//메인페이지 제거
$(".top_navi_li").addClass("tab_on");
</script>
<input type="hidden" id="vw_cd" name="vw_cd" value="${tblInfoBean.vw_cd}" />
<input type="hidden" id="up_list_id" name="up_list_id" value="${tblInfoBean.up_list_id}" />
<input type="hidden" id="up_up_list_id" name="up_up_list_id" value="${tblInfoBean.up_up_list_id}" />
<input type="hidden" id="lvl" name="lvl" value="" />
<input type="hidden" id="max_lvl" name="max_lvl" value="${tblInfoBean.max_lvl}" />
<input type="hidden" id="siteGb" name="siteGb" value="${tblInfoBean.siteGb}" />
<input type="hidden" id="menuId2" name="menuId" value="<c:out value='${menuId}' />" />
<input type="hidden" id="rootId2" name="rootId" value="<c:out value='${rootId}' />" />
<input type="hidden" id="ix_no" name="ix_no" value="" />
<input type="hidden" id="ix_stats_nm_ko" name="ix_stats_nm_ko" value="" />
<input type="hidden" id="stats_code" name="stats_code" value="" />

<div class="stat_wrap">

	<!-- 탭 영역 -->
	<div class="stat_tab_wrap">
		<a href="javascript:;" class="stat_list_btn" id="tab_list">목록으로</a>
		<ul id="tbl_title" class="stat_tab_ul"></ul>
	</div>
	
	<!-- 검색 영역 -->
	<div class="stat_search_wrap">
		<div class="search_box stat_search placeholder_box">
			<label for="keyword">검색어를 입력하세요</label>
			<input id="keyword" name="keyword" type="text" class="search_input"> 
			<div class="submit_btn"><input type="button" id="btn_search" value="검색" title="검색"/></div>
		</div>
	</div>
	
	<!-- 통계표 보기 -->
	<div id="statView" class="stat_box">
		<div class="stat_grid" id="statList"></div>
	</div>
	
	<!-- 통계표 목록 보기 -->
	<div id="tbl_list" class="stat_box">
		<div class="stat_list_wrap list_num_3">
			<div class="stat_list_box list_01">
				<ul id="vwList" class="stat_list_ul">
					<li class="stat_list_li ${tblInfoBean.vw_cd eq 'MT_ATITLE' || tblInfoBean.vw_cd eq '' || tblInfoBean.vw_cd eq null ? 'tab_on' : ''}"><a href="javascript:void(0);" data-a="MT_ATITLE" data-b="주제별" class="stat_li_a">주제별</a></li>
					<li class="stat_list_li ${tblInfoBean.vw_cd eq 'MT_DTITLE' ? 'tab_on' : ''}"><a href="javascript:void(0);" data-a="MT_DTITLE" data-b="기관별" class="stat_li_a">기관별</a></li>
				</ul>
			</div>
			<div class="stat_list_box list_02">
				<ul id="fldList" class="stat_list_ul">
					<c:forEach var="fldResult" items="${fldList}" varStatus="fldStat">
						<li class="stat_list_li ${tblInfoBean.up_list_id eq fldResult.tbl_id || up_list_id eq fldResult.tbl_id ? 'tab_on' : ''}">
							<a href="javascript:;" data-a="${fldResult.tbl_id}" data-b="${fldResult.list_nm}" class="stat_li_a">										
								<c:out value="${fldResult.list_nm}" />										
							</a>
						</li>
					</c:forEach>					
				</ul>
			</div>
			<div class="stat_list_box list_03">
				<ul id="tblList" class="stat_list_ul">
					<c:forEach var="tblResult" items="${tblList}" varStatus="tblStat">
						<li class="stat_list_li">
							<a class="stat_li_a" href="javascript:;" id="showTblInfo" data-a="${tblResult.tbl_id}" data-b="${tblResult.list_nm}" data-c="${tblResult.vw_cd}" data-d="${tblResult.list_id }">
								<c:out value="${tblResult.list_nm}" />
							</a>
						</li>
					</c:forEach>				
				</ul>
			</div>
		</div>
	</div>
	
	<!-- 검색 결과 보기 -->
	<div id="searchView" class="stat_box">
		<div class="stat_search_result">
			<div class="stat_search_title">
				<span id="searchResultSpan" class="result"></span>
				<a id="search_close" href="javascript:;" class="search_close" title="검색결과 닫기">
					<span class="bar f_s_0">bar</span>
					<span class="bar f_s_0">bar</span>
				</a>					
			</div>
			<div class="stat_search_list">
				<ul id="searchResultInfo" class="stat_search_ul"></ul>
			</div>
		</div>
	</div>
</div>