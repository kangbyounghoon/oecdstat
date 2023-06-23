<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/etc/jsp_header.jspf"%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/service.css">
<script>

/************************************************************************        

메타정보 조회 상단 버튼 관련 Script

************************************************************************/
$(document).ready(function(){
	$('.meta_anchor').click(function(e) {
		 $(this).addClass('tab_on');
		 $(this).siblings().removeClass('tab_on');
	});
});

</script>
<div class="stat_box">
	<div class="meta_view_box">
		<div class="meta_top">
			<div class="meta_title_wrap">
				<p class="meta_title">${codeNmBean.tbl_nm } : <span class="meta_t_span">${metaBean.ix_stats_nm_ko }</span></p>
			</div><!--meta_view_title-end-->
			<div class="meta_anchor_wrap">
				<a href="#meta_cnt_01${metaBean.ix_no }" class="meta_anchor tab_on">지표 요약 정보</a>
				<a href="#meta_cnt_02${metaBean.ix_no }" class="meta_anchor">지표 세부정의</a>
				<a href="#meta_cnt_03${metaBean.ix_no }" class="meta_anchor">지표 설명 자료</a>
			</div>
		</div>
		
		<div class="meta_cnt">
			${metaTable}
		</div>
	</div>
</div>

<style>
.modal_title{ text-align:left; font-size: 20px;font-weight: 800;margin-bottom: 20px; letter-spacing: 0; }
.modal_title .btn{ font-size:17px; float:none; min-width:100px; height:35px; line-height:35px; margin-left:10px; font-weight:600; }
.table_meta table{ border-top: 2px solid #009b90; border-collapse:collapse; }
.table_meta tr th,.table_meta tr td{ padding:20px; }
.table_meta tr th{ background:#f6f9f9; font-weight:800; }
.table_meta tr{ border-bottom:1px solid #d9d9d9; }
.table_meta tr td{ text-align:left; font-weight:600; line-height:22px; }

.list_wrap{ padding:20px; box-sizing:border-box; }
.list_wrap .pnt_btn { background-color: #009b90;color: #fff; }
.list_wrap .btn {padding: 0px 20px;text-align: center;box-sizing: border-box;cursor: pointer; }
</style>