/** 통계DB */
var tblCnt = 5;
var tabCnt = 0;

$(document).ready(function() {
	
	$("#bodyForm").attr("onsubmit", "return false;");
	
	$("#statView").hide();
	$("#searchView").hide();
	
	if($("#fldList > li").hasClass("on")) {
		$("#fldList").closest(".list_02").scrollTop($("#fldList").find(".on").position().top);
		
		$(function () {
			if($("#fldDtList > li").hasClass("on") == false){
				$("#fldList > li").filter(".on").children().trigger("click");
			}
		});
	}
	
	if($("#tblList >li").hasClass("on")) {
		$("#tblList").closest(".list_03").scrollTop($("#tblList").find(".on").position().top);
		
		$(function () {
			$("#tblList > li").filter(".on").children().trigger("click");
		});
	}
	
	// 통계 목록 화면 보이기
	$("#tab_list").click(function() {
		hideTblContent();
		removeSrcResult();
		
		$("#tbl_list").show();
		$("#statView").hide();
	});

	// 통계표 화면 제거 이벤트 생성
	$("#tbl_title").on("click", "[id='delete']", function() {
		var index = $(this).parent().index();
		removeTblInfo(index);
	});

	// 통계표 화면 보이기 이벤트 생성
	$("#tbl_title").on("click", "[id='db_ListTitle']", function() {
		var index = $(this).parent().index();
		
		hideTblContent();
		$("#statView").show();
		$("#tbl_list").hide();
		$("#searchView").hide();

		$(this).parent().addClass("tab_on");
		$("[name='tbl']").eq(index).show();
	});

	// 메뉴 클릭
	$("#vwList").on("click", "li a", function() {
		hideTblContent();
		
		$("#vwList > li").each(function() {
			$(this).attr('class', 'stat_list_li');
		});
		
		$(this).closest("li").attr('class', 'stat_list_li tab_on');
		
		getList($(this).attr("data-a"), 1, $(this).attr("data-b"));
	});
	
	// 분야별검색 이벤트 연결 - 상세분야검색 
	$("#fldList").on("click", "li a", function() {
		hideTblContent();

		$("#fldList > li").each(function() {
			$(this).attr('class', 'stat_list_li');
		});

		$(this).closest("li").attr('class', 'stat_list_li tab_on');

		getList($(this).attr("data-a"), 2, '', $(this).attr("data-z"));
		
		$("#fldDtList").scrollTop(0);
	});

	// 통계표 화면 추가 - 통계표 목록 선택
	$("#tblList").on("click", "[id='showTblInfo']", function() {
		if($(window).width() > 901){
			createTblShow($(this).attr("data-a"), $(this).attr("data-b"), $(this).attr("data-c"), $(this).attr("data-d"), $(this).attr("data-e"), $(this).attr("data-f"), $(this).attr("data-g"));			
		} else {
			stat_view($(this).attr('data-a'), 'ko');
		}
	});

	// 통계표 화면 추가 - 검색결과 선택
	$("#searchResultInfo").on("click", "[id='showTblInfo']", function() {
		if($(window).width() > 901){
			createTblShow($(this).attr("data-a"), $(this).attr("data-b"), $(this).attr("data-c"), $(this).attr("data-d"), $(this).attr("data-e"), $(this).attr("data-f"), $(this).attr("data-g"));			
		} else {
			stat_view($(this).attr('data-a'), 'ko');
		}
	});

	// 통계표 검색
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
});

// 통계표 검색
function fn_search() {
	var gubun = "kor";
	var vw_cd = $("#vw_cd").val();
	
	var vw_nm = "";
	
	if(vw_cd == 'MT_ATITLE'){
		vw_nm = "(주제별) ";
	} else {
		vw_nm = "(기관별) ";
	}
	
	if($("#keyword").val() == "") {
		alert("검색하실 내용을 입력하세요.");

		$("#keyword").focus();

		return false;
	} else {
		$("#tbl_list").hide();
		$("#statView").hide();
		$("#searchView").show();
		
		$.ajax({
			url : "../../" + gubun + "/tblInfo/TblInfoSearchList.html",
			data :  { vw_cd : vw_cd, up_list_id : "0", lvl : $("#max_lvl").val(), keyword : $("#keyword").val() },
			type : "POST",
			async: false,
			beforeSend : function(xhr){ /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
				xhr.setRequestHeader("AJAX", true);
	            xhr.setRequestHeader(header, token);
			},
			success : function(data) {
				if(data != null && data != "'" && data != 'undefined' ) {
					// 통계표
					if(data.tblList == null ) {
						$("#serachResultInfo").html("");
					} else {
						$("#searchResultSpan").html(vw_nm + "\'<span class='keyword'>"+$("#keyword").val() +"</span>' 에 대한 검색결과가 <span class='count'>" + data.tblList.length + "건</span>입니다.");
						
						$("#searchResultInfo").html(createHtml(data.tblList, data.tblInfoBean, true, true));
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

// 통계표 화면 제거
function removeTblInfo(index) {
	$("[name='top_tab']").eq(index).remove();
	$("[name='tbl']").eq(index).remove();

	tabCnt -= 1;
	
	if($("[name=top_tab]").length > 0){
		$("[name=top_tab]").find("[id='db_ListTitle']").last()[0].click();
	} else {
		$("#tbl_list").show();
		$("#statView").hide();
	}
}

// 통계표 화면 제거2
function removeTblInfo2(index) {
	$("[name='top_tab']").eq(index).remove();
	$("[name='tbl']").eq(index).remove();
	
	tabCnt -= 1;
}

// 통계표 숨기기
function hideTblContent() {

	$("[name='tbl']").each(function() {
		$(this).hide();
	});

	for(var i=0; i < tblCnt; i++) {
		$("[name='top_tab']").eq(i).removeClass("tab_on");
	}
}

// 첫번째 통계표 닫기
function removeFstTblInfo() {
	hideTblContent();

	for(var i=0; i < tblCnt; i++) {
		if($("[name='tbl']").eq(i).length > 0) {
			removeTblInfo2(i);
			break;
		}
	}
}

/** 목록 및 통계표 조회 */
function getList(tbl_id, level, title, up_up_list_id_param) {
	var gubun = "kor";
	
	var url = "../../" + gubun + "/tblInfo/TblInfoFieldList.html";
	var vw_cd = $("#vw_cd").val();
	var up_list_id = tbl_id;
	var up_up_list_id = up_up_list_id_param;
	
	if(level == 1) {
		url = "../../" + gubun + "/tblInfo/TblInfoList.html";
		
		$("#up_list_id").val("0");
		$("#vw_cd").val(tbl_id);
		
		vw_cd = tbl_id;
		up_list_id = "0";
	}

	removeSrcResult();

	$.ajax({
		url : url,
		data :  { vw_cd : vw_cd, up_list_id : up_list_id, lvl : level, re_search : "Y", up_up_list_id :  up_up_list_id },
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
					$("#tblList").html("");
					
					if(data.fldList != null) $("#fldList").html(createHtml(data.fldList, data.tblInfoBean));

					$("#max_lvl").val(data.tblInfoBean.max_lvl);
				} else if(level == 2) {
					if(data.list != null) $("#tblList").html(createHtml(data.list, data.tblInfoBean, true));
					
					//미리보기
					$(".w_preview").click(function() {
						
						$("#ix_stats_nm_ko").val($(this).attr("data-b"));
						$("#ix_no").val($(this).attr("data-a"));
						$("#stats_code").val($(this).attr("data-c"));
						
						var stats_code = $("#stats_code").val();
						var ix_no = $("#ix_no").val();
						var ix_stats_nm_ko = $("#ix_stats_nm_ko").val();
						var url =  "/oecdstat/kor/tblInfo/prevMetaInfo.html?stats_code="+stats_code+"&ix_stats_nm_ko="+encodeURI(ix_stats_nm_ko)+"&ix_no="+ix_no;
						var width = 1550;
						var height = 800;
						var left = (screen.width/2)-(width/2);
						var top = (screen.height/2)-(height/2);
						
						window.open(url, "popup", "toolbar=no, scrollbars=yes, resizable=yes, width="+width+", height="+height+", left="+left+", top="+top );
						
					});
				}
			}
		},
		error : function() {
	        return false;
	    }
	});

	return true;
}

// 리스트 생성
function createHtml(list, tblInfoBean, tblYn, searchYn) {
	var html = "";
	
	if(list.length > 0) {
		for(var i in list) {
// 			alert(list[i].metaList.length);
			if(!searchYn){
				if(!tblYn){
					html += "<li class='stat_list_li'>";
					html += "<a class='stat_li_a' href='javascript:;' data-a='" + list[i].tbl_id + "' data-b='" + list[i].list_nm + "'>" + list[i].list_nm + "</a>";			
					html += "</li>";
				} else {
					html += "<li class='stat_list_li'>";
					html += "<a class='stat_li_a' id='showTblInfo' href='javascript:;' data-a='" + list[i].tbl_id + "' data-b='" + list[i].list_nm + "' data-c='" + list[i].vw_cd + "'>" + list[i].list_nm + "</a>";
					html += "	<div class='stat_meta_btn'>";
					for(var j in list[i].metaList){
						html += "		<a href='javascript:;' class='w_preview meta_btn meta_btn_01 f_s_0' id='showMetaInfo' title='" + list[i].metaList[j].list_code_nm + "' data-a='" + list[i].metaList[j].ix_no  + "' data-b='" + list[i].metaList[j].list_code_nm + "' data-c='" + list[i].metaList[j].stats_code + "'>지표 요약 정보</a>";
					}
					html += "	</div>";
					html += "</li>";
				}
			} else {
				var first = list[i].list_path.substring(0, list[i].list_path.lastIndexOf(' > '));
				var last = list[i].list_path.substring(list[i].list_path.lastIndexOf(' > '), list[i].list_path.length);
				
                html += '<li class="stat_search_li">';
                html += '<a id="showTblInfo" href="javascript:;" data-a="' + list[i].tbl_id + '" data-b="' + list[i].list_nm + '" data-c="' + list[i].vw_cd + '" data-d="' + list[i].list_id + '" data-z="' + list[i].up_up_list_id + '" class="stat_search_a">' + first + last.replace($("#keyword").val(), '<span class="keyword">' + $("#keyword").val() + '</span>') + '</a>';
                html += '</li>';
			}
		}
	}

	return html;
}

// 검색결과 화면 제거하기
function removeSrcResult() {

	$("#keyword").val("");
	$("#searchResultInfo").html("");
	$("#searchView").hide();
}

// 통계표 화면 추가
function createTblShow(tbl_id, tblTitle, vw_cd, list_id, lang, itm_id, obj_var_id) {
	var titleHtml = "";
	var contentHtml = "";
	var confirmMsg = "통계표는 최대 " + tblCnt +"개까지만 여실 수 있습니다.\n\n첫번째 통계표를 닫으시고\n선택하신 통계표를 여시겠습니까?";
	
	$("#tab_list").removeClass("tab_on");
	$("[name=top_tab]").removeClass("tab_on");
	
	// 같은 통계표 탭을 누른 경우
	if($("[id='tab"+tbl_id+"']").length > 0) {
		hideTblContent();
		$("#tbl_list").hide();
		$("#searchView").hide();
		
		$("#statView").show();
		$("#tab" + tbl_id).addClass("tab_on");
		$("#tbl" + tbl_id).show();
		
		return;
	}

	// 통계표 탭이 5개 이상인 경우
	if(tabCnt >= tblCnt) {
		if(confirm(confirmMsg)) {
			removeFstTblInfo();
		} else {
			return;
		}
	}
	
	// 타이틀 html 추가
    titleHtml += "<li id='tab" + tbl_id + "' class='stat_tab_li' name='top_tab'>";
	titleHtml += "	<a href='javascript:;' id='db_ListTitle' class='tab_title'>" + tblTitle + "</a>";
	titleHtml += "	<a href='javascript:;' class='tab_close' id='delete' title='탭 닫기'>";
	titleHtml += "		<span class='bar f_s_0'>bar</span>";
	titleHtml += "		<span class='bar f_s_0'>bar</span>";
	titleHtml += "	</a>";
	titleHtml += "</li>";
    
	$("#tbl_title").append(titleHtml);
	
	$("#tab" + tbl_id).addClass("tab_on");

	contentHtml += "<div id='tbl" + tbl_id + "' name='tbl'>";
	contentHtml += "<iframe id='tblInfoFrame' name='tblInfoFrame' frameborder='0' width='100%' height='800' scrolling='no' title='통계DB' ";
	contentHtml += "src='" + statUrl + "/statHtml/statHtml.do?mode=tab&amp;orgId=007&amp;tblId="+ tbl_id + 
					"&amp;vw_cd=" + vw_cd + "&amp;list_id=" + list_id + "&amp;scrId=&amp;seqNo=&amp;language=" + lang + 
					"&amp;obj_var_id=" + obj_var_id + "&amp;itm_id=" + itm_id + "&amp;conn_path=" + vw_cd + "&amp;path='></iframe>";
	contentHtml += "</div>";
	
	$("#tbl_list").hide();
	$("#statList").append(contentHtml);
	
	$("#searchView").hide();
	$("#statView").show();
	
	tabCnt += 1;
}

function fn_searchClose(){

	$("#keyword").val("");
	$("#searchResultInfo").html("");
	$("#searchView").hide();
	
	var topTabCnt = $("[name=top_tab]").length;
		
	if(topTabCnt > 0){
		if($("[name=top_tab]").hasClass("tab_on")){
			var tbl_id = $(".tab_on").attr("id");
			
			$("#statView").show();
			$("#" + tbl_id.replace("tab", "tbl")).show();
		} else {
			$("#tbl_list").show();
		}
	}else{
		$("#tbl_list").show();
	}
}