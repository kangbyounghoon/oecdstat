<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ include file="/WEB-INF/etc/jsp_header.jspf"%>

<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
		<title><spring:message code="msg.title" text="Service System" /></title>
		<meta name="_csrf" content="${_csrf.token}">
		<meta name="_csrf_header" content="${_csrf.headerName}">
		
		<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js" charset="UTF-8"></script>
		<script src="${pageContext.request.contextPath}/js/common.js" charset="UTF-8"></script>
		<script src="${pageContext.request.contextPath}/js/common.js" charset="UTF-8"></script>
		<script>var ctx = "${pageContext.request.contextPath}"</script>

		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sub.css" />

		<style type="text/css">
			#Progress_Loading { z-index: 999; position: fixed; left: 0px; top: 0px; right: 0px; bottom: 0px; background: rgba(0,0,0,0.6); }
			#Progress_Loading img { position: absolute; left: 50%; top: 50%; margin-top: -25px; margin-left: -25px; }
		</style> 
	</head>

	<div id="Progress_Loading"><!-- 로딩바 -->
		<img src="${pageContext.request.contextPath}/img/loading.gif" alt="loading"/>
	</div>
			
	<body oncontextmenu="return false" ondragstart="return false">
		<tiles:insertAttribute name="body"></tiles:insertAttribute>
	</body>
</html>