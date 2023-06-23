/**메타정보*/
var tblCnt = 5; //탭 최대 개수
var tabCnt = 0; //현재 열려있는 탭 개수

$(document).ready(function() {
	
	$("#bodyForm").attr("onsubmit", "return false;");
	
	$("#metaView").hide();
	$("#searchView").hide();
	
	// 통계 목록 화면 보이기
	$("#tab_list").click(function() {
		hideMetaContent();
		removeSrcResult();
		
		$("#meta_list").show();
		$("#metaView").hide();
	});
	
	// 메타정보 화면 제거 이벤트 생성
	$("#meta_title").on("click", "[id='delete']", function() {
		var index = $(this).parent().index();
		removeMetaInfo(index);
	});
	
	// 통계표 화면 보이기 이벤트 생성
	$("#meta_title").on("click", "[id='meta_ListTitle']", function() {
		var index = $(this).parent().index();
		
		hideMetaContent();
		$("#metaView").show();
		$("#meta_list").hide();
		$("#searchView").hide();

		$(this).parent().addClass("tab_on");
		$("[name='meta']").eq(index).show();
	});
	
	// 메뉴 클릭
	$("#topCodeList").on("click", "li a", function() {
		hideMetaContent();
		
		$("#topCodeList > li").each(function() {
			$(this).attr('class', 'stat_list_li');
		});
		
		$(this).closest("li").attr('class', 'stat_list_li tab_on');
		
		$("#top_code_id").val($(this).attr("data-a"));
		
		getList($(this).attr("data-a"), 1, $(this).attr("data-b"));
	});
	
	// 분야별검색 이벤트 연결 - 상세분야검색(주제1)
	$("#fldList").on("click", "li a", function() {
		hideMetaContent();

		$("#fldList > li").each(function() {
			$(this).attr('class', 'stat_list_li');
		});

		$(this).closest("li").attr('class', 'stat_list_li tab_on');

		getList($(this).attr("data-a"), 2, '', $(this).attr("data-b"));
		
		$("#fldDtList").scrollTop(0);		
	});
	
	// 상세분야별검색 이벤트 연결 - 상세분야검색(주제2)
	$("#fldDtList").on("click", "li a", function() {
		hideMetaContent();
			
		$("#fldDtList >li").each(function() {
			$(this).attr('class', 'stat_list_li');
		});
			
		if($("#top_code_id").val() != 'META002') {
			$(this).closest("li").attr('class', 'stat_list_li tab_on');
		}
			
		getList($(this).attr("data-a"), 3,'', '', $(this).attr("data-b"));
			
		$("#metaList").scrollTop(0);
	});
	
	// 메타정보 화면 추가 - 메타정보 목록 선택
	$("#metaInfoList, #fldDtList, #btn_sumry, #btn_writng, #btn_dta").on("click", "[id='showMetaInfo']", function() {
		if($(window).width() > 901){
			createMetaShow($(this).attr("data-a"), $(this).attr("data-b"), $(this).attr("data-c"), $(this).attr("data-d"), $(this).attr("data-e"), $(this).attr("data-f"));			
		} else {
			meta_view($(this).attr('data-a'), 'ko');
		}
	});
	
	// 통계표 화면 추가 - 검색결과 선택
	$("#searchResultInfo").on("click", "[id='showMetaInfo']", function() {
		if($(window).width() > 901){
			createMetaShow($(this).attr("data-a"), $(this).attr("data-b"), $(this).attr("data-c"), $(this).attr("data-d"), $(this).attr("data-e"), $(this).attr("data-f"));		
		} else {
			meta_view($(this).attr('data-a'), 'ko');
		}
	});
	
	// 메타정보 검색
	$("#btn_search").click(function() {
		fn_search();
	});

	// 검색 키워드 이벤트
	$("#keyword").keydown(function (key) {
	    if (key.keyCode == 13) {
	    	fn_search();
	    }
	});
	
	// 검색 결과 닫기 버튼 
	$("#search_close").click(function(){
		fn_searchClose();
	});
	
	$("[id^=meta]").on("click", ".meta_anchor", function() {
		$(this).addClass('tab_on');
		$(this).siblings().removeClass('tab_on');
		
		var href = $(this).attr("href");
		
		var metaDivId = "#meta"+$(this).attr("data-a");
		$(metaDivId).find(".meta_cnt").find(href).focus();
	});
		 
});

function getList(list_code, level, title, up_up_list_code_param, up_list_code_nm_param) {	
	var gubun = "kor";
	var top_code_id = $("#top_code_id").val();
	var up_up_list_code = up_up_list_code_param;
	var up_list_code = list_code;
	var up_list_code_nm = up_list_code_nm_param;
	
	var url = "../../"+gubun+"/metaInfo/metaFieldList.html";
	
	if(level == 1) {
		url = "../../"+gubun+"/metaInfo/metaInfoList.html";
	}
	
	$.ajax({
		url : url,
		data :  { top_code_id : top_code_id, up_up_list_code : up_up_list_code, up_list_code : up_list_code, up_list_code_nm : up_list_code_nm, re_search : "Y", list_code : list_code},
		type : "POST",
		async: false,
		beforeSend : function(xhr){ /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
			xhr.setRequestHeader("AJAX", true);
            xhr.setRequestHeader(header, token);
		},
		success : function(data) {
			if(data != null && data != "'" && data != 'undefined' ) {
				if(level == 1) {
					$("#fldList").html("");
					$("#fldDtList").html("");
					$("#metaInfoList").html("");
					
					if(data.fldList != null) $("#fldList").html(createHtml(data.fldList, data.metaBean));

					if(data.metaBean.top_code_id == 'META001') {
						$('.stat_list_wrap').attr('class','stat_list_wrap list_num_4');		// 통계표 사이즈 변경
					} else {
						$('.stat_list_wrap').attr('class','stat_list_wrap list_num_3');		// 통계표 사이즈 변경
					}				

					/*$(".db_top span").html("* '" + title + "'통계를 이용할 수 있습니다");*/
				} else if(level == 2) {
					$("#fldDtList").html("");
					$("#metaInfoList").html("");

					var tblYn = false;
					
					if(top_code_id == "META002"){
						tblYn = true;
						$('.list_03').attr('class','stat_list_box list_03 meta_list_box');
					} else {
						$('.list_03').attr('class','stat_list_box list_03');
					}
					
					if(data.list != null) $("#fldDtList").html(createHtml(data.list, data.metaBean, tblYn));
				} else if(level == 3) {
					/*if($("#max_lvl").val() > 3) {
						if(data.list != null) $("#fldDtList2").html(createHtml(data.list, data.tblInfoBean));

						$("#metaInfoList").html("");
					} else {*/
						if(data.list != null) $("#metaInfoList").html(createHtml(data.list, data.metaBean, true));
					/*}*/
				}
				else {
					if(data.list != null) $("#metaInfoList").html(createHtml(data.list, data.metaBean, true));
				}
			}
		},
		error : function() {
	        return false;
	    }
	});
	return true;
}

//메타정보 검색
function fn_search() {
	var gubun = "kor";
	var top_code_id = $("#top_code_id").val();
	
	var top_code_nm = "";
	
	if(top_code_id == 'META001'){
		top_code_nm = "(주제별) ";
	} else {
		top_code_nm = "(기관별) ";
	}
	
	if($("#keyword").val() == "") {
		alert("검색하실 내용을 입력하세요.");

		$("#keyword").focus();

		return false;
	} else {
		$("#meta_list").hide();
		$("#metaView").hide();
		$("#searchView").show();
		
		$.ajax({
			url : "../../" + gubun + "/metaInfo/MetaInfoSearchList.html",
			data :  { top_code_id : top_code_id, up_list_code : null, keyword : $("#keyword").val() },
			type : "POST",
			async: false,
			beforeSend : function(xhr){ /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
	            xhr.setRequestHeader(header, token);
			},
			success : function(data) {
				if(data != null && data != "'" && data != 'undefined' ) {
					// 통계표
					if(data.metaList == null ) {
						$("#serachResultInfo").html("");
					} else {
						$("#searchResultSpan").html(top_code_nm + "\'<span class='keyword'>"+$("#keyword").val() +"</span>' 에 대한 검색결과가 <span class='count'>" + data.metaList.length + "건</span>입니다.");
						
						$("#searchResultInfo").html(createHtml(data.metaList, data.metaBean, true, true));
					}
				}
			},
			error : function() {
				return false;
			}
		});
		
		return true;
	}
}

//리스트 생성
function createHtml(list, metaBean, tblYn, searchYn) {
	var html = "";
	
	if(list.length > 0) {
		for(var i in list) {
			if(!searchYn){
				if(!tblYn){
					html += "<li class='stat_list_li'>";
					html += "	<a class='stat_li_a' href='javascript:;' data-a='" + list[i].list_code + "' data-b='" + list[i].list_code_nm + "'>" + list[i].list_code_nm + "</a>";			
					html += "</li>";
				} else {
					html += "<li class='stat_list_li'>";
					html += "	<a class='stat_li_a' id='showMetaInfo' href='javascript:;' data-a='" + list[i].list_code + "' data-b='" + list[i].list_code_nm + "' data-c='" + metaBean.top_code_id + "' data-d='"+metaBean.up_list_code+"' data-e='" +metaBean.up_list_code_nm+ "'>" + list[i].list_code_nm + "</a>";
					html += "	<div class='stat_meta_btn'>";
					html += "		<a href='javascript:;' class='meta_btn meta_btn_01 f_s_0' id='showMetaInfo' title='지표 요약 정보' data-a='" + list[i].list_code + "' data-b='" + list[i].list_code_nm + "' data-c='" + metaBean.top_code_id + "' data-d='"+metaBean.up_list_code+"' data-e='" +metaBean.up_list_code_nm+ "' data-f='#meta_cnt_01" + list[i].list_code + "'>지표 요약 정보</a>";
					html += "		<a href='javascript:;' class='meta_btn meta_btn_02 f_s_0' id='showMetaInfo' title='지표 정의 및 작성 정보' data-a='" + list[i].list_code + "' data-b='" + list[i].list_code_nm + "' data-c='" + metaBean.top_code_id + "' data-d='"+metaBean.up_list_code+"' data-e='" +metaBean.up_list_code_nm+ "' data-f='#meta_cnt_02" + list[i].list_code + "'>지표 정의 및 작성 정보</a>";
					html += "		<a href='javascript:;' class='meta_btn meta_btn_03 f_s_0' id='showMetaInfo' title='지표 설명 자료' data-a='" + list[i].list_code + "' data-b='" + list[i].list_code_nm + "' data-c='" + metaBean.top_code_id + "' data-d='"+metaBean.up_list_code+"' data-e='" +metaBean.up_list_code_nm+ "' data-f='#meta_cnt_03" + list[i].list_code + "'>지표 설명 자료</a>";
					html += "	</div>"
					html += "</li>";
				}
			} else {
				if(metaBean.top_code_id == "META001") {
					var first = list[i].list_path.substring(0, list[i].list_path.lastIndexOf(' > '));
					var last = list[i].list_path.substring(list[i].list_path.lastIndexOf(' > '), list[i].list_path.length);
					
	                html += '<li class="stat_search_li">';
	                html += '		<a id="showMetaInfo" href="javascript:;" data-a="' + list[i].ix_no + '" data-b="' + list[i].ix_stats_nm_ko + '" data-c="' + metaBean.top_code_id + '" data-d="' + list[i].list_code + '" data-e="' + list[i].list_code_nm + '" class="stat_search_a">' + first + last.replace($("#keyword").val(), '<span class="keyword">' + $("#keyword").val() + '</span>') + '</a>';
	                html += "	<div class='f_r'>";
					html += "		<a href='javascript:;' class='meta_btn meta_btn_01 f_s_0' id='showMetaInfo' title='지표 요약 정보' data-a='" + list[i].ix_no + "' data-b='" + list[i].ix_stats_nm_ko + "' data-c='" + metaBean.top_code_id + "' data-d='"+ list[i].list_code+"' data-e='" +list[i].list_code_nm+ "' data-f='#meta_cnt_01" +list[i].ix_no+ "'>지표 요약 정보</a>";
					html += "		<a href='javascript:;' class='meta_btn meta_btn_02 f_s_0' id='showMetaInfo' title='지표 정의 및 작성 정보' data-a='" + list[i].ix_no + "' data-b='" + list[i].ix_stats_nm_ko + "' data-c='" + metaBean.top_code_id + "' data-d='"+ list[i].list_code+"' data-e='" +list[i].list_code_nm+ "' data-f='#meta_cnt_02" + list[i].ix_no + "'>지표 정의 및 작성 정보</a>";
					html += "		<a href='javascript:;' class='meta_btn meta_btn_03 f_s_0' id='showMetaInfo' title='지표 설명 자료' data-a='" + list[i].ix_no + "' data-b='" + list[i].ix_stats_nm_ko + "' data-c='" + metaBean.top_code_id + "' data-d='"+ list[i].list_code+"' data-e='" +list[i].list_code_nm+ "' data-f='#meta_cnt_03" + list[i].ix_no + "'>지표 설명 자료</a>";
					html += "	</div>"
	                html += '</li>';	
				} else {
					var first = list[i].list_path.substring(0, list[i].list_path.lastIndexOf(' > '));
					var last = list[i].list_path.substring(list[i].list_path.lastIndexOf(' > '), list[i].list_path.length);
					
	                html += '<li class="stat_search_li">';
	                html += '		<a id="showMetaInfo" href="javascript:;" data-a="' + list[i].ix_no + '" data-b="' + list[i].ix_stats_nm_ko + '" data-c="' + metaBean.top_code_id + '" data-e="'+ list[i].instt +'" class="stat_search_a">' + first + last.replace($("#keyword").val(), '<span class="keyword">' + $("#keyword").val() + '</span>') + '</a>';
	                html += "	<div class='f_r'>";
					html += "		<a href='javascript:;' class='meta_btn meta_btn_01 f_s_0' id='showMetaInfo' title='지표 요약 정보' data-a='" + list[i].ix_no + "' data-b='" + list[i].ix_stats_nm_ko + "' data-c='" + metaBean.top_code_id + "' data-e='"+ list[i].instt + "' data-f='#meta_cnt_01" +list[i].ix_no+ "'>지표 요약 정보</a>";
					html += "		<a href='javascript:;' class='meta_btn meta_btn_02 f_s_0' id='showMetaInfo' title='지표 정의 및 작성 정보' data-a='" + list[i].ix_no + "' data-b='" + list[i].ix_stats_nm_ko + "' data-c='" + metaBean.top_code_id + "' data-e='"+ list[i].instt + "' data-f='#meta_cnt_02" + list[i].ix_no + "'>지표 정의 및 작성 정보</a>";
					html += "		<a href='javascript:;' class='meta_btn meta_btn_03 f_s_0' id='showMetaInfo' title='지표 설명 자료' data-a='" + list[i].ix_no + "' data-b='" + list[i].ix_stats_nm_ko + "' data-c='" + metaBean.top_code_id + "' data-e='"+ list[i].instt + "' data-f='#meta_cnt_03" + list[i].ix_no + "'>지표 설명 자료</a>";
					html += "	</div>";
	                html += '</li>';	
				}
			}
		}
	}
	
	return html;
}

//메타정보 숨기기
function hideMetaContent() {
	$("[name='meta']").each(function() {
		$(this).hide();
	});

	for(var i=0; i < tblCnt; i++) {
		$("[name='top_tab']").eq(i).removeClass("tab_on");
	}
}

//메타정보 화면 제거
function removeMetaInfo(index) {
	$("[name='top_tab']").eq(index).remove();
	$("[name='meta']").eq(index).remove();

	tabCnt -= 1;
	
	if($("[name=top_tab]").length > 0){
		$("[name=top_tab]").find("[id='meta_ListTitle']").last()[0].click();
	} else {
		$("#meta_list").show();
		$("#metaView").hide();
	}
}

//검색결과 화면 제거하기
function removeSrcResult() {

	$("#keyword").val("");
	$("#searchResultInfo").html("");
	$("#searchView").hide();
}

//첫번째 메타정보 닫기
function removeFstMetaInfo() {
	hideMetaContent();

	for(var i=0; i < tblCnt; i++) {
		if($("[name='meta']").eq(i).length > 0) {
			removeMetaInfo2(i);
			break;
		}
	}
}

//메타정보 화면 제거2
function removeMetaInfo2(index) {
	$("[name='top_tab']").eq(index).remove();
	$("[name='meta']").eq(index).remove();
	
	tabCnt -= 1;
}

function createMetaShow(ix_no, ix_stats_nm_ko, top_code_id, up_list_code, up_list_code_nm, a_href) {
	var titleHtml = "";
	var contentHtml = "";
	var confirmMsg = "메타정보는 최대 " + tblCnt +"개까지만 여실 수 있습니다.\n\n첫번째 메타정보를 닫으시고\n선택하신 메타정보를 여시겠습니까?";
	var url = "";
	
	$("#tab_list").removeClass("tab_on");
	$("[name=top_tab]").removeClass("tab_on");
	
	// 같은 통계표 탭을 누른 경우
	if($("[id='tab"+ix_no+"']").length > 0) {
		hideMetaContent();
		$("#meta_list").hide();
		$("#searchView").hide();
		
		$("#metaView").show();
		$("#tab" + ix_no).addClass("tab_on");
		$("#meta" + ix_no).show();
		
		$($("#meta" + ix_no).find("a[href ='" +a_href+ "']")).addClass('tab_on');
		$($("#meta" + ix_no).find("a[href ='" +a_href+ "']")).siblings().removeClass('tab_on');
		
		window.location.href = a_href;
		
		return;
	}

	// 통계표 탭이 5개 이상인 경우
	if(tabCnt >= tblCnt) {
		if(confirm(confirmMsg)) {
			removeFstMetaInfo();
		} else {
			return;
		}
	}

	// 타이틀 html 추가
    titleHtml += "<li id='tab" + ix_no + "' class='stat_tab_li' name='top_tab'>";
	titleHtml += "	<a href='javascript:;' id='meta_ListTitle' class='tab_title'>" + ix_stats_nm_ko + "</a>";
	titleHtml += "	<a href='javascript:;' class='tab_close' id='delete' title='탭 닫기'>";
	titleHtml += "		<span class='bar f_s_0'>bar</span>";
	titleHtml += "		<span class='bar f_s_0'>bar</span>";
	titleHtml += "	</a>";
	titleHtml += "</li>";
    
	$("#meta_title").append(titleHtml);
	
	$("#tab" + ix_no).addClass("tab_on");
	
	//선택한 메타정보 불러오기
	$.ajax({
		url : "../../kor/metaInfo/metaInfoDetail.html",
		data :  {ix_no : ix_no, ix_stats_nm_ko : ix_stats_nm_ko, top_code_id : top_code_id, up_list_code : up_list_code, up_list_code_nm : up_list_code_nm, a_href : a_href},
		type : "POST",
		async: false,
		beforeSend : function(xhr){ /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
			xhr.setRequestHeader("AJAX", true);
            xhr.setRequestHeader(header, token);
		},
		success : function(data) {
			if(data != null && data != "'" && data != 'undefined' ) {
				$("#meta_list").hide();
				$("#metaViewList").append(data.metaTable);
			}
		},
		error : function() {
	        return false;
	    }
	});
	
	$("#searchView").hide();
	$("#metaView").show();
	window.location.hash = a_href;
	
	tabCnt += 1;
}

function fn_searchClose(){

	$("#keyword").val("");
	$("#searchResultInfo").html("");
	$("#searchView").hide();
	
	var topTabCnt = $("[name=top_tab]").length;
		
	if(topTabCnt > 0){
		if($("[name=top_tab]").hasClass("tab_on")){
			var ix_no = $(".tab_on").attr("id");
			
			$("#metaView").show();
			$("#" + ix_no.replace("tab", "meta")).show();
		} else {
			$("#meta_list").show();
		}
	}else{
		$("#meta_list").show();
	}
}