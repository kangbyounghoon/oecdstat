<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/etc/jsp_header.jspf"%>

<script type="text/javascript">
$(function() {
	
	// 검색 버튼 클릭
	$("#btn_search").click(function() {
		
		// 검색 함수
		fn_search();
	});

	// 키워드 keydown 이벤트
	$("#keyword").keydown(function (key) {
	    if (key.keyCode == 13) {
	    	
	    	// 검색 함수
	    	fn_search();
	    }
	});
});

// 검색 함수
function fn_search(){
	var form = $("#mainfrm");
	
	form.attr("action", "<c:url value='/kor/search/SearchList.html'/>");
	form.submit();
}

function fnSubDetail(menuUrl, rootid, menuid) {
	var url = "/oecdstat" + menuUrl;
	
	$("#menuId_main").val(menuid);
	$("#rootId_main").val(rootid);
	$("#mainfrm").attr("action", url);
	$("#mainfrm").submit();
}
</script>


<form name="mainfrm" id="mainfrm" method="post">
	<sec:csrfInput />
	<input type="hidden" id="menuId_main" name="menuId" value="" />
	<input type="hidden" id="rootId_main" name="rootId" value="" />
	
	<div class="main_search">
		<div class="search_box placeholder_box">
			<label for="keyword">검색어를 입력하세요</label>
			<input id="keyword" name="srchwrd" type="text" class="search_input">
			<div class="submit_btn"><input id="btn_search" type="button" value="검색" title="검색"></div>
		</div>
	</div>

	<div class="main_link_wrap">
		<a href="javascript:void(0);" onclick="fnSubDetail('/kor/tblInfo/TblInfoList.html?vw_cd=MT_ATITLE', '2020000', '2020101')" class="main_link_box">
			<h3 class="main_link_title">통계 서비스</h3>
			<p class="main_link_txt">주제별, 기관별 OECD 요구지표 제공</p>
		</a>
		<a href="javascript:void(0);" onclick="fnSubDetail('/kor/metaInfo/metaInfoList.html', '2030000', '2030101')" class="main_link_box">
			<h3 class="main_link_title">메타정보 서비스</h3>
			<p class="main_link_txt">통계표 단위별 통계설명 내용</p>
		</a>
	</div>
</form>