<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/etc/jsp_header.jspf"%>

<c:if test="${searchBean.allSearchAt eq 'Y' or searchBean.up_menu_id eq '2030000'}">
	<c:set var="searchTitleMeta" value="" />
	<c:forEach items="${menu}" var="menu" varStatus="status">
		<c:if test="${menu.up_menu_id eq '2030000'}">
			<c:set var="searchTitleMeta" value="${menu.menu_nm}" />
		</c:if>
	</c:forEach>

	<form id="metaForm" name="metaForm" method="post" action="">
	</form>
	
	<form id="metaInfoForm" name="metaInfoForm" method="post" action="">
		<sec:csrfInput />
		<input type='hidden' id='menuId' name='menuId' value='2030000'/>
	</form>
	
	<div class="search_result_title m_t_20">
		<h3 class="txt_h3_no_icon f_l">
			<c:out value="${searchTitleMeta}" /> <span class="search_num">[<fmt:formatNumber type="currency" value="${searchBean.metaSearchCo}" pattern="###,###" /><spring:message code="message.oecdstat.count" text="Count" />]</span>
		</h3>

		<c:if test="${searchBean.allSearchAt eq 'Y' and searchBean.metaSearchCo gt 3}">
			<a href="javascript:;" class="more_btn" id="viewMoreMeta" data-menuId="2030000"><spring:message code="message.oecdstat.viewmore" text="View more" /></a>
		</c:if>
	</div>

	<!-- 테마통계 조회 결과 있는 경우 -->
	<c:if test="${not empty metaList and fn:length(metaList) gt 0}">
		<ul class="serch_result_ul">
			<c:set var="replaceKeyword" value="<span class='search_k'>${searchBean.srchwrd}</span>" />
			<c:forEach items="${metaList}" var="meta" varStatus="status">
				<li>
					<div class="result_title">
						<a href="javascript:;" class="result_title_link" id="showMetaView1_${status.index }" data-ixNo="${meta.ix_no }" data-statsThema="${meta.stats_thema }" data-statsCode = "${meta.stats_code }" data-ixStatsNmKo="${meta.ix_stats_nm_ko }">
							(<c:out value="${meta.ix_no }"/>) <c:out value="${fn:replace(meta.ix_stats_nm_ko, searchBean.srchwrd, replaceKeyword)}" escapeXml="false" />
						</a>
						<div class="stat_meta_btn">
							<a href="#meta_cnt_01${meta.ix_no }" id="showMetaView2_${status.index }" data-ixNo="${meta.ix_no }" data-statsThema="${meta.stats_thema }" data-statsCode = "${meta.stats_code }" data-ixStatsNmKo="${meta.ix_stats_nm_ko }" class="meta_btn meta_btn_01 f_s_0" title="지표 요약 정보">지표 요약 정보</a>
							<a href="#meta_cnt_02${meta.ix_no }" id="showMetaView3_${status.index }" data-ixNo="${meta.ix_no }" data-statsThema="${meta.stats_thema }" data-statsCode = "${meta.stats_code }" data-ixStatsNmKo="${meta.ix_stats_nm_ko }" class="meta_btn meta_btn_02 f_s_0" title="지표 정의 및 작성 정보">지표 정의 및 작성 정보</a>
							<a href="#meta_cnt_03${meta.ix_no }" id="showMetaView4_${status.index }" data-ixNo="${meta.ix_no }" data-statsThema="${meta.stats_thema }" data-statsCode = "${meta.stats_code }" data-ixStatsNmKo="${meta.ix_stats_nm_ko }" class="meta_btn meta_btn_03 f_s_0" title="지표 설명 자료">지표 설명 자료</a>
						</div>
					</div>
					<div class="result_loca">
						<p>위치</p><a href="javascript:;" id="showDetailMeta1_${status.index}" data-menuId="${meta.menu_id}" data-rootId="2030000" data-statsCode="${meta.stats_code }" data-upListCode="${meta.up_list_code }" data-topCodeId="${meta.top_code_id }"  data-statsThema="${meta.stats_thema }"><c:out value="${fn:replace(meta.list_path, searchBean.srchwrd, replaceKeyword)}" escapeXml="false" /></a>
					</div>					
					<div class="result_agency">
						<c:if test="${meta.instt ne null && meta.instt ne ''}">
							<p>기관</p><c:out value="${meta.instt}"/>
						</c:if>
					</div>
				</li>
			</c:forEach>
		</ul>
		<div id="metaInfoView" class="db_pop_wrap">
            <div class="db_pop">
                <div class="db_pop_top">
                   <h3 class="db_pop_tit">메타정보조회</h3>
                   <div class="close_btn_wrap">
                       <a href="javascript:;" class="close_btn" id="btn_close">
                           <span class="bar f_s_0">bar</span>
                           <span class="bar f_s_0">bar</span>
                       </a>
                   </div>
                </div>
            	<div class="db_pop_cont">
            		<div class="meta_view_box" id="metaViewList">
            		</div>
                </div>
             </div>
         </div>
		
		<!--page number-->
		<c:if test="${searchBean.allSearchAt ne 'Y'}">
			<div class="paging"><c:out value="${pagination}" escapeXml="false" /></div>
		</c:if>
	</c:if>
	<!-- 테마통계 조회 결과 없는 경우 -->
	<c:if test="${empty metaList or fn:length(metaList) le 0}">
		<ul class="serch_result_ul">
			<li class="no_result">
				<p><spring:message code="msg.resultnotfound" text="No Results Found." /></p>
			</li>
		</ul>
	</c:if>
</c:if>