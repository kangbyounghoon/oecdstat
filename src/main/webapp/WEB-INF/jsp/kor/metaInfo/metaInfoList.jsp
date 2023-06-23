<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/etc/jsp_header.jspf"%>

<script type="text/Javascript" src="${pageContext.request.contextPath}/js/metaInfo.js"></script>


<input type="hidden" id="top_code_id" name="top_code_id" value="${metaBean.top_code_id }"/>

<div class="stat_wrap">

	<!-- 탭 영역 -->
	<div class="stat_tab_wrap">
		<a href="javascript:;" class="stat_list_btn" id="tab_list">목록으로</a>
		<ul class="stat_tab_ul" id="meta_title"></ul>
		<!--stat_tab_ul-end-->
	</div>
	<!--stat_tab_wrap-end-->

	<div class="stat_search_wrap">
		<div class="search_box stat_search placeholder_box">
			<label for="keyword">검색어를 입력하세요</label><input id="keyword" name="keyword" type="text" class="search_input">
			<div class="submit_btn"><input type="submit" id="btn_search" value="검색" title="검색" ></div>
		</div>
	</div>
	<!--stat_search_wrap-end-->

	<!-- 메타정보 보기 -->
	<div class="stat_box" id="metaView">
		<div class="meta_view_box" id="metaViewList">
		</div>
	</div>
	
	<!-- 메타정보 목록 -->
	<div class="stat_box" id="meta_list">
		<div class="stat_list_wrap list_num_${metaBean.top_code_id eq 'META001' ? '4' : '3' }">
			<!-- 주제별 : 4레벨 (클래스 : list_num_4) / 기관별 : 3레벨 (클래스 : list_num_3) -->

			<div class="stat_list_box list_01">
				<ul class="stat_list_ul" id="topCodeList">
				<!--선택된 리스트의 li태그에 tab_on클래스 넣어주세요. -->
					<li class="stat_list_li ${metaBean.top_code_id eq 'META001' || metaBean.top_code_id eq '' || metaBean.top_code_id eq null ? 'tab_on' : ''} "><a href="javascript:;" class="stat_li_a" data-a="META001" data-b="주제별">주제별</a></li>
					<li class="stat_list_li ${metaBean.top_code_id eq 'META002' ? 'tab_on' : ''}"><a href="javascript:;" class="stat_li_a" data-a="META002" data-b="기관별">기관별</a></li>
				</ul>
			</div>
			<!--stat_list_box-end-->

			<div class="stat_list_box list_02">
				<ul class="stat_list_ul" id="fldList">
					<c:forEach var="fldResult" items="${fldList}" varStatus="fldStat">
						<li class="stat_list_li ${up_list_code eq fldResult.list_code || metaBean.up_list_code eq fldResult.list_code ? 'tab_on' : '' }">
							<a href="javascript:;" class="stat_li_a" data-a="${fldResult.list_code}" data-b="${fldResult.list_code_nm}">										
								<c:out value="${fldResult.list_code_nm}" />										
							</a>
						</li>
					</c:forEach>
				</ul>
			</div>
			<!--stat_list_box-end-->

			<div class="stat_list_box list_03">
				<ul class="stat_list_ul" id="fldDtList">
				<c:forEach var="fldDtResult" items="${fldDtList }" varStatus="fldDtStat">
					<li class="stat_list_li ${metaBean.list_code eq fldDtResult.list_code || stats_code eq fldDtResult.list_code || fldDtResult.list_code eq listCode ? 'tab_on' : ''}">
						<a href="javascript:;" class="stat_li_a" data-a="${fldDtResult.list_code }" data-b="${fldDtResult.list_code_nm }">
							<c:out value="${fldDtResult.list_code_nm }"/>
						</a>
					</li>
				</c:forEach>
				</ul>
			</div>
			<!--stat_list_box-end-->

			<div class="stat_list_box list_04 meta_list_box">
				<!--메타정보 아이콘이 있어야되는 리스트의 경우 meta_list_box 클래스를 넣어주세요-->
				<ul class="stat_list_ul" id="metaInfoList">
				<c:forEach var="metaList" items="${metaList }" varStatus="metaStatus">
					<li class="stat_list_li"><a href="javascript:;" class="stat_li_a" id="showMetaInfo" data-a="${metaList.list_code }" data-b="${metaList.list_code_nm }" data-c="${metaBean.top_code_id }" data-d="${metaBean.list_code }" data-e="${metaBean.stats_thema }"><c:out value="${metaList.list_code_nm }"/></a>
						<div class="stat_meta_btn">
							<a href="javascript:;" id="showMetaInfo" class="meta_btn meta_btn_01 f_s_0" title="지표 요약 정보" data-a="${metaList.list_code }" data-b="${metaList.list_code_nm }" data-c="${metaBean.top_code_id }" data-d="${metaBean.up_list_code }" data-e="${metaList.list_code_nm }" data-f="#meta_cnt_01${metaList.list_code}" >지표 요약 정보</a> 
							<a href="javascript:;" id="showMetaInfo" class="meta_btn meta_btn_02 f_s_0" title="지표 정의 및 작성 정보"  data-a="${metaList.list_code }" data-b="${metaList.list_code_nm }" data-c="${metaBean.top_code_id }" data-d="${metaBean.up_list_code }" data-e="${metaList.list_code_nm }" data-f="#meta_cnt_02${metaList.list_code}" >지표 정의 및 작성 정보</a> 
							<a href="javascript:;" id="showMetaInfo" class="meta_btn meta_btn_03 f_s_0" title="지표 설명 자료"  data-a="${metaList.list_code }" data-b="${metaList.list_code_nm }" data-c="${metaBean.top_code_id }" data-d="${metaBean.up_list_code }" data-e="${metaList.list_code_nm }" data-f="#meta_cnt_03${metaList.list_code}" >지표 설명 자료</a>
						</div>
					</li>
				</c:forEach>
				</ul>
			</div>
			<!--stat_list_box-end-->
		</div>
		<!--stat_list_wrap-end-->
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
	<!--stat_box-end-->
</div>