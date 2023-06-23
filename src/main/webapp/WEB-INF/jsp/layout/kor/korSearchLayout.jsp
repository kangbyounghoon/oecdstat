<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ include file="/WEB-INF/etc/jsp_header.jspf"%>

<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="author" content="한국보건사회연구원 OECD 통계포털">
		<meta name="keywords" content="한국보건사회연구원 KISDISTAT,OECD 통계포털">
		<meta name="description" lang="ko" content="한국보건사회연구원 KISDISTAT,OECD 통계포털">
		<meta name="copyright" content="(27872) 충청북도 진천군 덕산읍 정통로 18 한국보건사회연구원">
		<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" /> 

		<meta name="_csrf" content="${_csrf.token}">
		<meta name="_csrf_header" content="${_csrf.headerName}">

		<title><spring:message code="msg.title" text="Service System" /></title>

		<!-- css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sub.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/js/slick/slick.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/js/slick/slick-theme.css" />

		<!--jquery  -->
		<c:set var="userAgentInfo" value="${header['User-Agent']}" />

 		<c:if test="${fn:indexOf(fn:toLowerCase(userAgentInfo), 'safari') gt -1 and fn:indexOf(fn:toLowerCase(userAgentInfo), 'version') gt -1 and fn:substring(userAgentInfo, fn:indexOf(fn:toLowerCase(userAgentInfo), 'version')+8, fn:indexOf(fn:toLowerCase(userAgentInfo), 'version')+9) lt 8}">
		<script src="${pageContext.request.contextPath}/js/jquery.min.js" charset="UTF-8"></script>
		</c:if>
 		<c:if test="${fn:indexOf(fn:toLowerCase(userAgentInfo), 'safari') lt 0 or fn:indexOf(fn:toLowerCase(userAgentInfo), 'version') lt 0}">
		<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js" charset="UTF-8"></script>
		</c:if>

		<script src="${pageContext.request.contextPath}/js/layout.js" charset="UTF-8"></script>
		<script src="${pageContext.request.contextPath}/js/slick/slick.js" charset="UTF-8"></script>
		<script src="${pageContext.request.contextPath}/js/common.js" charset="UTF-8"></script>
		<script>var ctx = "${pageContext.request.contextPath}"</script>

		<style type="text/css">
			#Progress_Loading { z-index: 999; /*position: fixed;*/ position: absolute;	/* kisdistat scroll, size over */ left: 0px; top: 0px; right: 0px; bottom: 0px; background: rgba(0,0,0,0.6); }
			#Progress_Loading img { position: absolute; left: 50%; top: 50%; margin-top: -25px; margin-left: -25px; }
		</style>

		<script type="text/javascript">
			$(document).ready(function() {
				$('#Progress_Loading').hide();
			});
		</script>
	</head>

	<body class="${zoom_size}">
		<div class="wrap">
			<div id="skipnavi">
			  <a href="#sub_section"><spring:message code="message.oecdstat.skipnavi_txt1" text="body_text shortcut" /></a>
			</div>

			<!-- header : top_navi  -->
			<header id="header">
				<tiles:insertAttribute name="top"></tiles:insertAttribute>
			</header>
			
			<!-- mobile header -->
			<header id="mheader">
				<tiles:insertAttribute name="top_mobile"></tiles:insertAttribute>
			</header>

			<!-- sub_top_section : sub_top_title, sub_location  -->
			<section id="sub_top_section" class="sub_bg_02">
			<div class="center">
				<div class="sub_top_title">
					<h2><spring:message code="message.oecdstat.unitysearch" text="Unity Search" /></h2>
					<p><spring:message code="message.oecdstat.unitysearchdc" text="This is the integrated search page of the media statistics portal." /></p>
				</div>
			</div><!--center_end-->

			<div class="sub_location">
				<div class="center">
					<div class="sub_l_left">
						<ul>
							<li class="sub_l_btn">
<%-- 								<a href="<c:url value='/main.html'/>"><img src="${pageContext.request.contextPath}/img/icon/icon_sub_l_home.png" alt="<spring:message code="message.oecdstat.home" text="home" />" title="<spring:message code="message.oecdstat.home_icon" text="home icon" />"></a> --%>
								<a href="<c:url value='/kor/tblInfo/TblInfoList.html?vw_cd=MT_ATITLE'/>"><img src="${pageContext.request.contextPath}/img/icon/icon_sub_l_home.png" alt="<spring:message code="message.oecdstat.home" text="home" />" title="<spring:message code="message.oecdstat.home_icon" text="home icon" />"></a>
							</li>

							<li class="sub_l_menu">
								<a href="#none"><spring:message code="message.oecdstat.unitysearch" text="Unity Search" /></a>
							</li>
						</ul>
					</div><!--sub_l_left_end-->

					<div class="sub_l_right">

						<c:set var="pageUrl" value="${pageContext.request.requestURL }" />
						<c:set var="pageUrl" value="${fn:substring(pageUrl, 0, fn:indexOf(pageUrl, '/oecdstat/'))}" />
						<c:set var="pageHtml" value="${requestScope['javax.servlet.forward.request_uri']}" />

						<c:set var="pageParam" value="" />
						<c:set var="pageCopyParam" value="" />

						<c:if test="${paramValues ne null && not empty paramValues }" >
							<c:forEach var="par" items="${paramValues}" varStatus="index">
								<c:if test="${par.value[0] ne null && not empty par.value[0] }">
									<c:if test="${par.key ne '_csrf'}">
							        	<c:set var="pageParam" value="${par.key}=${par.value[0]}" />
							        	<c:set var="pageCopyParam" value="${pageCopyParam}${pageParam}|" />
									</c:if>
								</c:if>

							</c:forEach>
							<c:set var="pageCopyParam" value="${fn:substring(pageCopyParam, 0, fn:length(pageCopyParam)-1) }" />
						</c:if>

						<ul>
							<li class="sub_l_btn">
								<a href="javascript:;" onclick="fnPrint();"><img src="${pageContext.request.contextPath}/img/icon/icon_sub_l_print.png" alt="<spring:message code="message.oecdstat.print_icon" text="print" />"></a>
							</li>
							<li class="sub_l_btn">
								<a href="javascript:;" onclick="copyUrlInfo($(this), encodeURIComponent('${pageUrl}${pageHtml}?${pageCopyParam}'), 'sns_url');"><img src="${pageContext.request.contextPath}/img/icon/icon_sub_l_share.png" alt="<spring:message code="message.oecdstat.share_icon" text="share" />"></a>
							</li>
						</ul>
					</div><!--sub_l_right_end-->
				</div><!--center_end-->
			</div><!--sub_location_end-->
			</section>

			<tiles:insertAttribute name="top_section_ly"></tiles:insertAttribute>

			<div id="Progress_Loading"><!-- 로딩바 -->
				<img src="${pageContext.request.contextPath}/img/loading.gif" alt="loading"/>
			</div>

			<!-- section : contents-->
			<section id="sub_section">
				<form id="bodyForm" name="bodyForm" method="post" >
					<div class="contents center" id="contents_body">
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
			
			<!-- mobile footer -->
			<footer id="mfooter">
				<tiles:insertAttribute name="bottom_mobile"></tiles:insertAttribute>
			</footer>
		</div>
	</body>
</html>