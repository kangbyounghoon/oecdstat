/******************************************************** 
   파일명 : layout.js 
   설  명 : hover, 클릭시 나타나는 효과 관련 script 
    수정일            수정자        Version      Function 명 
  ---------        --------    ----------    --------------- 
  20.10.22        김수빈            1.0              최초 생성 

*********************************************************/


/************************************************************************        
                         
   Placeholder label태그로 대체하기 위한 Script	
   
************************************************************************/

jQuery(document).ready(function(){
			
	var placeholderTarget = $(".placeholder_box input[type=text]");
 
	placeholderTarget.focus(function(){
		$(this).siblings("label").fadeOut("fast");
	});
 
	placeholderTarget.focusout(function(){
		if($(this).val() == ""){
			$(this).siblings("label").fadeIn("fast");
		}
	});
 
});

/************************************************************************        
                         
   메타정보 조회 상단 버튼 관련 Script
   
************************************************************************/
$(document).ready(function(){
	$('.meta_anchor').click(function(e) {
		 $(this).addClass('tab_on');
		 $(this).siblings().removeClass('tab_on');
	});
});
