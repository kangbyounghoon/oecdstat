<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/etc/jsp_header.jspf" %>

<c:if test="${searchBean.allSearchAt eq 'Y' or searchBean.up_menu_id eq '2020000' }">
	<c:set var="searchTitleTbl" value="" />
	<c:forEach items="${menu}" var="menu" varStatus="status">
		<c:if test="${menu.up_menu_id eq '2020000'}">
			<c:set var="searchTitleTbl" value="${menu.menu_nm}" />
		</c:if>
	</c:forEach>

	<form id="statForm" name="statForm" method="post" action="">
	</form>
	
	<form id="statDbForm" name="statDbForm" method="post" action="">
		<sec:csrfInput />
		<input type='hidden' id='menuId' name='menuId' value='2020000'/>
	</form>

	<div class="search_result_title">
		<h3 class="txt_h3_no_icon f_l">
			<c:out value="${searchTitleTbl}" /> <span class="search_num">[<fmt:formatNumber type="currency" value="${searchBean.tblSearchCo}" pattern="###,###" /><spring:message code="message.oecdstat.count" text="Count" />]</span>
		</h3>

		<c:if test="${searchBean.allSearchAt eq 'Y' and searchBean.tblSearchCo gt 3}">
			<a href="javascript:;" class="more_btn" id="viewMoreTbl" data-menuId="2020000"><spring:message code="message.oecdstat.viewmore" text="View more" /></a>
		</c:if>
	</div>

	<!-- 통계서비스 조회 결과 있는 경우 -->
	<c:if test="${not empty tblList and fn:length(tblList) gt 0}">
		<ul class="serch_result_ul">
			<c:set var="replaceKeyword" value="<span class='search_k'>${searchBean.srchwrd}</span>" />
			<c:set var="startLi" value="<li>" />
			<c:set var="endLi" value="</li>" />
			<c:set var="oldResultSn" value="" />
			<c:set var="cmmtText" value="" />
			<c:forEach items="${tblList}" var="tbl" varStatus="status">
				<c:if test="${empty oldResultSn or tbl.result_sn ne oldResultSn}">
					<c:if test="${status.index gt 0}">
						<c:if test="${not empty cmmtText}"><div class="result_info"><p><spring:message code="message.oecdstat.comment" text="Include view point" /></p><c:out value="${cmmtText}" /></div></c:if>
						<c:out value="${endLi}" escapeXml="false" />
					</c:if>
					<c:out value="${startLi}" escapeXml="false" />
	
					<div class="result_title">
						<a href="javascript:;" class="result_title_link" id="showStatView1_${status.index}" data-tblId="${tbl.tbl_id}" data-lang="${searchBean.lang}">
							<c:out value="${fn:replace(tbl.tbl_nm, searchBean.srchwrd, replaceKeyword)}" escapeXml="false" />
							<span class="re_tit_date">[<spring:message code="message.oecdstat.prdde" text="Include view point" /> : <c:out value="${fn:replace(tbl.prd_nm, searchBean.srchwrd, replaceKeyword)}" escapeXml="false" />
							/ <c:out value="${fn:replace(tbl.prd_de, searchBean.srchwrd, replaceKeyword)}" escapeXml="false" />]</span>
						</a>
					</div>
					<div class="result_loca">
						<a href="javascript:;" id="showDetailTbl1_${status.index}" data-menuId="${tbl.menu_id}" data-rootId="2020000" data-vwCd="${tbl.vw_cd}" data-upListId="${tbl.up_list_id}" data-listId="${tbl.list_id}">
							<c:set var="menuIndex" value="${fn:indexOf(tbl.menu_path, '>')}" />
							<c:out value="${fn:substring(tbl.menu_path, 0, menuIndex)}" /> &gt; <c:out value="${tbl.vw_nm}" /> &gt; <c:out value="${fn:replace(tbl.list_path, searchBean.srchwrd, replaceKeyword)}" escapeXml="false" />
						</a>
					</div>
	
					<c:set var="oldResultSn" value="${tbl.result_sn}" />
	
					<c:set var="cmmtText" value="${not empty cmmtText ? ' ' : ''}${tbl.cmmt_dc}" />
				</c:if>
			</c:forEach>
		</ul>

		<!--page number-->
		<c:if test="${searchBean.allSearchAt ne 'Y'}">
			<div class="paging"><c:out value="${pagination}" escapeXml="false" /></div>
		</c:if>
	</c:if>
	<!-- 통계서비스 조회 결과 없는 경우 -->
	<c:if test="${empty tblList or fn:length(tblList) le 0}">
		<ul class="serch_result_ul">
			<li class="no_result">
				<p><spring:message code="msg.resultnotfound" text="No Results Found." /></p>
			</li>
		</ul>
	</c:if>
</c:if>