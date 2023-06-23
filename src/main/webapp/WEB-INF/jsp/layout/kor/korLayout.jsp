<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ include file="/WEB-INF/etc/jsp_header.jspf"%>

<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
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
		
		<style type="text/css">
			#Progress_Loading
			{
			 z-index: 999;
			 /*position: fixed;*/
			 position: absolute;	/* kisdistat scroll, size over */
			 left: 0px;
			 top: 0px;
			 right: 0px;
			 bottom: 0px;
			 background: rgba(0,0,0,0.6);
			}
			
			#Progress_Loading img{
			    position: absolute;
			    left: 50%;
			    top: 50%;
			    margin-top: -25px;
			    margin-left: -25px;
			}
		</style> 
		
		<script type="text/javascript">			
			$(document).ready(function() {
				$('#Progress_Loading').hide();
			});			
			
		</script>
	</head>

	<body>
		<div class="wrap">
		
			<div id="skipnavi">
			  <a href="#sub_section"><spring:message code="message.oecdstat.skipnavi_txt1" text="body_text shortcut" /></a>
			</div> 
			
			<!-- header : top_navi  -->
			<header id="header">
				<tiles:insertAttribute name="top"></tiles:insertAttribute>
			</header>
			
			<div id="Progress_Loading"><!-- 로딩바 -->
				<img src="${pageContext.request.contextPath}/img/loading.gif" alt="loading"/>
			</div>
			
			<!-- section : contents-->
			<section id="sub_section">
				<form id="bodyForm" name="bodyForm" method="post" >
					<div class="center" id="contents_body">
						<input type="hidden" id="sec_seq" name="sec_seq" value=""/>
						<input type="hidden" id="menu_id" name="menu_id" value="${menuId}"/>
						<sec:csrfInput />
						
						<tiles:insertAttribute name="body"></tiles:insertAttribute>				
					</div>
				 </form>
			</section>
		
			<!-- footer : banner, copyright -->
			<footer id="footer">
				<tiles:insertAttribute name="bottom"></tiles:insertAttribute>
			</footer>
		</div>
	
	</body> 
</html>