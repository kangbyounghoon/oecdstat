<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/etc/jsp_header.jspf"%>

<script type="text/javascript">
	$(function() {

		$("#btnSearchKeyword").click(function() {
			var form = $("#topfrm");

			if($("#searchWord").val() == "") {
				alert("<spring:message code='message.oecdstat.insertkeyworddc' text='Please enter a search term' />");
				return false;
			}

			form.attr("action", "<c:url value='/kor/search/SearchList.html'/>"); 
			form.submit();
		});

		// 검색 키워드 이벤트
		$("#search_kyword").keydown(function(key) {
			$("#searchWord").val($(this).val());

		    if(key.keyCode == 13) {
		    	$("#btnSearchKeyword").click();
		    }
		});
	});
	
	function moveSubMenu(menuUrl, menuId, rootId) {
		var url = "<%=request.getContextPath()%>" + menuUrl;
	
		$("#menuId").val(menuId);
		$("#rootId").val(rootId);
	
		$("#topfrm").attr("action", url);
		$("#topfrm").submit();
	}
</script>

<form name="topfrm" id="topfrm" method="post">
	<sec:csrfInput />
	<input type="hidden" id="menuId" name="menuId" value="<c:out value='${menuId}' />" />
	<input type="hidden" id="rootId" name="rootId" value="<c:out value='${rootId}' />" />
	<input type="hidden" id="searchWord" name="srchwrd" value="" />

	<div class="center">
		<nav class="navi">
			<h1 class="h1_logo">
<%-- 				<a href="<c:url value='/main.html'/>" class="top_logo"><img src="${pageContext.request.contextPath}/img/main/top_logo.png" alt="<spring:message code="message.oecdstat.site_logo" text="site logo" />"></a> --%>
				<a href="<c:url value='/kor/tblInfo/TblInfoList.html?vw_cd=MT_ATITLE'/>" class="top_logo"><img src="${pageContext.request.contextPath}/img/main/top_logo.png" alt="<spring:message code="message.oecdstat.site_logo" text="site logo" />"></a>
			</h1>
			
			<ul class="top_navi_ul">
				<c:forEach items="${menu}" var="menu" varStatus="status" begin="0" end="1"> 
					<c:set var="cls" value="" />
					<c:set var="qd" value="" />
					<c:set var="lvl" value="${menu.menu_level}" />
					
					<c:if test="${fn:indexOf(menu.menu_url, '?') eq -1}">
						<c:set var="qd" value="?" />
					</c:if>
					<c:if test="${fn:indexOf(menu.menu_url, '?') ne -1}">
						<c:set var="qd" value="&amp;" />
					</c:if>
					
					<c:if test="${rootId eq menu.up_menu_id}">
						<c:set var="cls" value="tab_on" />
					</c:if>
					
					<!-- 대메뉴  -->
					<li class="top_navi_li ${cls}">
						<a href="javascript:void(0);" class="top_navi" onclick="moveSubMenu('<c:out value="${menu.menu_url}" />', '<c:out value="${menu.menu_id}" />', '<c:out value="${menu.up_menu_id}" />');  return false;" >
							<span class="top_navi_span"><c:out value="${menu.menu_nm}" /></span>
						</a>
					</li>
				</c:forEach> 
			</ul>

		</nav><!--top_navi_end-->
	</div><!-- center_end-->
</form>