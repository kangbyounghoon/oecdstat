<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ include file="/WEB-INF/etc/jsp_header.jspf"%>

<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="utf-8">
		<meta name="author" content="OECD 요구통계 입력 관리시스템">
		<meta name="keywords" content="OECD 요구통계 입력 관리시스템">
		<meta name="description" lang="ko" content="OECD 요구통계 입력 관리시스템">
		<meta name="copyright" content="세종특별자치시 시청대로 370 세종국책연구단지 사회정책동">
		<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
		
		<meta name="_csrf" content="${_csrf.token}">
		<meta name="_csrf_header" content="${_csrf.headerName}">
		
		<title><spring:message code="msg.title" text="Service System" /></title>

		<!-- css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/service.css" />
		
		<!--jquery  -->
		<c:set var="userAgentInfo" value="${header['User-Agent']}" />

		<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js" charset="UTF-8"></script>
		<script src="${pageContext.request.contextPath}/js/layout.js" charset="UTF-8"></script>
		<script src="${pageContext.request.contextPath}/js/common.js" charset="UTF-8"></script>
		<script>var ctx = "${pageContext.request.contextPath}"</script>
		
		<script type="text/javascript">			
			$(document).ready(function() {
			});			
		</script>
	</head>

	<body>
		<div class="wrap main_css">
		
			<!-- header : top_navi  -->
			<header id="header">
				<tiles:insertAttribute name="top"></tiles:insertAttribute>
			</header>
			
			<!-- main_section -->
			<section id="main_section">
				<div class="center">
					<tiles:insertAttribute name="body"></tiles:insertAttribute>
				</div>
			</section>
			
			<!-- footer : banner, copyright -->
			<footer id="footer">
				<tiles:insertAttribute name="bottom"></tiles:insertAttribute>
			</footer>
			
		</div>
	</body>
	
</html>