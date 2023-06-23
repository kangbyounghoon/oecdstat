<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>OECD 요구통계 입력 관리시스템</title>
<script>
	var path = "";
	// var url = "/main.html";
	var url = "/kor/tblInfo/TblInfoList.html?vw_cd=MT_ATITLE";
	if("${pageContext.request.contextPath}".indexOf("/oecdstat") > -1) path = "";

	location.href = "${pageContext.request.contextPath}" + path + url;
</script>
</head>
</html>
