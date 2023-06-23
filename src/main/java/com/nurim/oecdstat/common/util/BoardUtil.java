/*
 * @(#)BoardUtil.java    2004. 11. 4.
 *
 * Copyright (c) 2004 Nurimsoft co., KOREA
 * All rights reserved.
 *
 * This software is the confidential
 * and proprietary information of Nurimsoft co. ("Confidential Information").
 * You shall not disclose such Confidential Information
 * and shall use it only in accordance with
 * the terms of the license agreement you entered into
 * with Nurimsoft co.
 *
 * Created on 2004. 11. 4.
 */
package com.nurim.oecdstat.common.util;

import org.apache.commons.lang.StringUtils;

import com.nurim.oecdstat.common.bean.CommonBean;



/**
 * <b>Description :</b>
 * <pre>
 * 게시판/자료실에서 공통적으로 사용하는 UTILITY
 * </pre>
 * @author kykime
 * @version 2004. 11. 4.
 */
public class BoardUtil{
    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(BoardUtil.class);

    /**
     * 기본 생성자
     */
    public BoardUtil(){}

    /**
     * <pre>
     * 전체 페이지를 구하는 메소드로 전체 수를 입력받아
     * configuration.xml에 설정된 Page List Scale을 읽어서
     * 전체 페이지를 구합니다.
     *
     * configuration.xml에 Page List Scale이 설정이 안경우 기본값은 10을 가집니다.
     * </pre>
     * @param totalCnt 전체 데이터 수
     * @return 전체 페이지 수
     */
    public int getTotalPage(int totalCnt) {
        int pageListScale = 10;
        try {
        									//   차후에 게시판생성할때  값을 지정.
//            pageListScale = NumberUtils.toInt(ConfigReader.getConfig("/html/pagelistscale"), 10);
        }catch(Exception e){
            logger.error(e);
        }
        int totalPage = (new Double(Math.ceil((double)totalCnt / pageListScale))).intValue();

        return totalPage == 0 ? 1 : totalPage;
    }

    /**
     * <pre>
     * 전체 페이지를 구하는 메소드
     * Page당 리스트 수와 전체 데이터 수를 입력 받아서
     * 전체 페이지를 구합니다.
     * </pre>
     * @param pageListScale Page당 보여질 리스트 수
     * @param totalCnt 전체 데이터 수
     * @return 전체 페이지 수
     */
    public int getTotalPage(int pageListScale, int totalCnt){
        int totalPage = (new Double(Math.ceil((double)totalCnt / pageListScale))).intValue();

        return totalPage == 0 ? 1 : totalPage;
    }

    /**
     * <pre>
     * 전체 페이지를 구하는 메소드
     * Page당 리스트 수와 전체 데이터 수를 입력 받아서
     * 전체 페이지를 구합니다.
     * </pre>
     * @param pageListScale Page당 보여질 리스트 수
     * @param totalCnt 전체 데이터 수
     * @return 전체 페이지 수
     */
    public CommonBean setPagination(CommonBean commBean) {
    	CommonBean result = commBean;
    	int pageIndex = 1;
    	int firstIndex = 1;

    	if(commBean.getPageIndex() > 1) pageIndex = commBean.getPageIndex();

    	firstIndex = (pageIndex-1) * commBean.getPageUnit();
    	result.setFirstIndex(firstIndex);
    	result.setLastIndex(firstIndex + commBean.getPageUnit());

        return result;
    }

    /**
     * <pre>
     * 리스트 하단의 paging을 구해서 반환하는 메소드
     * 리스트 하단의 " [처음][before 10]1 2 3 4 5 ... [next 10][끝] " 형태로 페이징을 구해서 출력 합니다.
     *
     * </pre>
     * @param commBean   공통 Beans
     * @param adminYn 관리자페이지 여부
     * @return String
     */
    public String getPageIndex(CommonBean commBean, String serverPath, String adminYn) {
        int movePage = 0;
        int startPage = 0;
        int endPage = 0;
        int chkPage = getTotalPage(commBean.getPageUnit(), commBean.getTotCount());
        StringBuffer sb = new StringBuffer("");

        startPage = ((commBean.getPageIndex() - 1) / commBean.getPageListScale()) * commBean.getPageListScale() + 1;
        endPage = (((startPage - 1) + commBean.getPageListScale()) / commBean.getPageListScale()) * commBean.getPageListScale();
        if (chkPage <= endPage) {
            endPage = chkPage;
        }
        
        sb.append("<input type='hidden' id='pageIndex' name='pageIndex' value='1'/>");
        sb.append("<ul>");

        if (commBean.getPageIndex() > commBean.getPageListScale()) {
        	movePage = startPage - 1;

        	// 맨 앞으로
        	sb.append("<li><a class='page_btn' href=\"javascript:linkPage('").append(commBean.getPageUrl()).append("', '").append(1).append("', '").append(commBean.getOrderby()).append("', '").append(commBean.getDesc()).append("');\" title=\"맨처음으로\">");
            sb.append("<div><img src=\"").append(serverPath).append("/img/icon/icon_page_first.png\" alt='처음' title='처음'/></div></a></li>");

            // 5 페이지 앞으로
            sb.append("<li><a class='page_btn' href=\"javascript:linkPage('").append(commBean.getPageUrl()).append("', '").append(movePage).append("', '").append(commBean.getOrderby()).append("', '").append(commBean.getDesc()).append("');\" title=\"이전으로\">");
            sb.append("<div><img src=\"").append(serverPath).append("/img/icon/icon_page_prev.png\" alt='이전 10페이지 이동' title='이전 10페이지 이동'/></div></a></li>");
        } else {
        	// 맨 앞으로
            sb.append("<li><a class='page_btn'><div><img src=\"").append(serverPath).append("/img/icon/icon_page_first.png\" alt='처음' title='처음'/></div></a></li>");

            // 5 페이지 앞으로
            sb.append("<li><a class='page_btn'><div><img src=\"").append(serverPath).append("/img/icon/icon_page_prev.png\" alt='이전 10페이지 이동' title='이전 10페이지 이동'/></div></a></li>");
        }

        sb.append("\n");

        movePage = startPage;
        while(movePage <= endPage) {
            if (movePage == commBean.getPageIndex()) {
                sb.append("\n");
                sb.append("<li><a class='page_num page_on' href=\"javascript:;\">");
                sb.append(commBean.getPageIndex());
                sb.append("</a></li>");
            } else {
            	sb.append("<li><a class='page_num' href=\"javascript:linkPage('").append(commBean.getPageUrl()).append("', '").append(movePage).append("', '").append(commBean.getOrderby()).append("', '").append(commBean.getDesc()).append("');\">");
                sb.append(movePage);
                sb.append("</a></li>");
            }

            if (movePage == endPage) {
                sb.append("\n");
            }

            sb.append("\n");
            movePage++;
        }

        if (chkPage > endPage) {
        	movePage = endPage + 1;

            // 5 페이지 뒤로
            sb.append("<li><a class='page_btn' href=\"javascript:linkPage('").append(commBean.getPageUrl()).append("', '").append(movePage).append("', '").append(commBean.getOrderby()).append("', '").append(commBean.getDesc()).append("');\" title=\"다음으로\">");
            sb.append("<div><img src=\"").append(serverPath).append("/img/icon/icon_page_next.png\" alt='다음 10페이지 이동' title='다음 10페이지 이동'/></div></a></li>");

            // 맨 뒤로
            sb.append("<li><a class='page_btn' href=\"javascript:linkPage('").append(commBean.getPageUrl()).append("', '").append(chkPage).append("', '").append(commBean.getOrderby()).append("', '").append(commBean.getDesc()).append("');\" title=\"맨마지막으로\">");
            sb.append("<div><img src=\"").append(serverPath).append("/img/icon/icon_page_last.png\" alt='마지막' title='마지막'/></div></a></li>");
        } else {
        	// 5 페이지 뒤로
        	sb.append("<li><a class='page_btn'><div><img src=\"").append(serverPath).append("/img/icon/icon_page_next.png\" alt='다음 10페이지 이동' title='다음 10페이지 이동'/></div></a></li>");

            // 맨 뒤로
            sb.append("<li><a class='page_btn'><div><img src=\"").append(serverPath).append("/img/icon/icon_page_last.png\" alt='마지막' title='마지막'/></div></a></li>");
        }

        sb.append("</ul>\n");

        return sb.toString();
    }

    /**
     * <pre>
     * 리스트 하단의 paging을 구해서 반환하는 메소드
     * 리스트 하단의 " [처음][before 10]1 2 3 4 5 ... [next 10][끝] " 형태로 페이징을 구해서 출력 합니다.
     *
     * </pre>
     * @param commBean   공통 Beans
     * @param adminYn 관리자페이지 여부
     * @return String
     */
    public String getMblPageIndex(CommonBean commBean, String serverPath, String adminYn) {
        int movePage = 0;
        int startPage = 0;
        int endPage = 0;
        int chkPage = getTotalPage(commBean.getPageUnit(), commBean.getTotCount());

        StringBuffer sb = new StringBuffer("");

        startPage = ((commBean.getPageIndex() - 1) / 5) * 5 + 1;
        endPage = (((startPage - 1) + 5) / 5) * 5;

        if (chkPage <= endPage) {
            endPage = chkPage;
        }

        sb.append("<input type='hidden' id='pageIndex' name='pageIndex' value='1' />");
        sb.append("<ul class='paging'>");

        if (commBean.getPageIndex() > 5) {
        	movePage = startPage - 1;

        	// 맨 앞으로
//        	sb.append("<li><a href=\"javascript:linkPage('").append(commBean.getPageUrl()).append("', '").append(1).append("', '").append(commBean.getOrderby()).append("', '").append(commBean.getDesc()).append("');\" title=\"맨처음으로\">");
//            sb.append("<img src=\"").append(serverPath).append("/images/mobile/btn_paging_first.gif\" border='0' alt='처음' title='처음'/></a></li>");

            // 5 페이지 앞으로
            sb.append("<li><a href=\"javascript:linkPage('").append(commBean.getPageUrl()).append("', '").append(movePage).append("', '").append(commBean.getOrderby()).append("', '").append(commBean.getDesc()).append("');\" title=\"이전으로\">");
            sb.append("<img src=\"").append(serverPath).append("/img/mobile/btn_paging_prev.gif\" border='0' alt='이전 5페이지 이동' title='이전 5페이지 이동'/></a></li>");
        } else {
        	// 맨 앞으로
//            sb.append("<li><img src=\"").append(serverPath).append("/img/mobile/btn_paging_first.gif\" border='0' alt='처음' title='처음'/></li>");

            // 5 페이지 앞으로
            sb.append("<li><img src=\"").append(serverPath).append("/img/mobile/btn_paging_prev.gif\" border='0' alt='이전 5페이지 이동' title='이전 5페이지 이동'/></li>");
        }

        sb.append("\n");

        movePage = startPage;
        while(movePage <= endPage) {
            if (movePage == commBean.getPageIndex()) {
                sb.append("\n");
                sb.append("<li><strong><a href=\"javascript:;\">");
                sb.append(commBean.getPageIndex());
                sb.append("</a></strong></li>");
            } else {
            	sb.append("<li><a href=\"javascript:linkPage('").append(commBean.getPageUrl()).append("', '").append(movePage).append("', '").append(commBean.getOrderby()).append("', '").append(commBean.getDesc()).append("');\">");
                sb.append(movePage);
                sb.append("</a></li>");
            }

            if (movePage == endPage) {
                sb.append("\n");
            }

            sb.append("\n");
            movePage++;
        }

        if (chkPage > endPage) {
        	movePage = endPage + 1;

            // 5 페이지 뒤로
            sb.append("<li><a href=\"javascript:linkPage('").append(commBean.getPageUrl()).append("', '").append(movePage).append("', '").append(commBean.getOrderby()).append("', '").append(commBean.getDesc()).append("');\" title=\"다음으로\">");
            sb.append("<img src=\"").append(serverPath).append("/img/mobile/btn_paging_next.gif\" border='0' alt='다음 5페이지 이동' title='다음 5페이지 이동'/></a></li>");

            // 맨 뒤로
//            sb.append("<li><a href=\"javascript:linkPage('").append(commBean.getPageUrl()).append("', '").append(chkPage).append("', '").append(commBean.getOrderby()).append("', '").append(commBean.getDesc()).append("');\" title=\"맨마지막으로\">");
//            sb.append("<img src=\"").append(serverPath).append("/images/mobile/btn_paging_last.gif\" border='0' alt='마지막' title='마지막'/></a></li>");
        } else {
        	// 5 페이지 뒤로
        	sb.append("<li><img src=\"").append(serverPath).append("/img/mobile/btn_paging_next.gif\" border='0' alt='다음 5페이지 이동' title='다음 5페이지 이동'/></li>");

            // 맨 뒤로
//            sb.append("<li><img src=\"").append(serverPath).append("/images/mobile/btn_paging_last.gif\" border='0' alt='마지막' title='마지막'/></li>");
        }

        sb.append("</ul>\n");

        return sb.toString();
    }

    /**
     * <pre>
     * 리스트 하단의 paging을 구해서 반환하는 메소드
     * 리스트 하단의 " [처음][before 10]1 2 3 4 5 ... [next 10][끝] " 형태로 페이징을 구해서 출력 합니다.
     *
     * </pre>
     * @param commBean   공통 Beans
     * @param adminYn 관리자페이지 여부
     * @return String
     */
    public String getSmallPageIndex(CommonBean commBean, String serverPath, String adminYn) {
        int movePage = 0;
        int startPage = 0;
        int endPage = 0;
        int chkPage = getTotalPage(commBean.getPageUnit(), commBean.getTotCount());

        StringBuffer sb = new StringBuffer("");

        startPage = ((commBean.getPageIndex() - 1) / 5) * 5 + 1;
        endPage = (((startPage - 1) + 5) / 5) * 5;

        if (chkPage <= endPage) {
            endPage = chkPage;
        }

        sb.append("<input type='hidden' id='pageIndex' name='pageIndex' value='1' />");
		sb.append("<ul id='pagingList'>");

        if (commBean.getPageIndex() > 5) {
        	movePage = startPage - 1;

        	// 맨 앞으로
        	sb.append("<li><a class='page_btn' href=\"javascript:linkPage('").append(commBean.getPageUrl()).append("', '").append(1).append("', '").append(commBean.getOrderby()).append("', '").append(commBean.getDesc()).append("');\" title=\"맨처음으로\">");
            sb.append("<div><img src=\"").append(serverPath).append("/img/icon/icon_page_first.png\" alt='처음' title='처음'/></div></a></li>");

            // 5 페이지 앞으로
            sb.append("<li><a class='page_btn' href=\"javascript:linkPage('").append(commBean.getPageUrl()).append("', '").append(movePage).append("', '").append(commBean.getOrderby()).append("', '").append(commBean.getDesc()).append("');\" title=\"이전으로\">");
            sb.append("<div><img src=\"").append(serverPath).append("/img/icon/icon_page_prev.png\" alt='이전 5페이지 이동' title='이전 5페이지 이동'/></div></a></li>");
        } else {
        	// 맨 앞으로
            sb.append("<li><a class='page_btn'><div><img src=\"").append(serverPath).append("/img/icon/icon_page_first.png\" alt='처음' title='처음'/></div></a></li>");

            // 5 페이지 앞으로
            sb.append("<li><a class='page_btn'><div><img src=\"").append(serverPath).append("/img/icon/icon_page_prev.png\" alt='이전 5페이지 이동' title='이전 5페이지 이동'/></div></a></li>");
        }

        sb.append("\n");

        movePage = startPage;
        while(movePage <= endPage) {
            if (movePage == commBean.getPageIndex()) {
                sb.append("\n");
                sb.append("<li><a class='page_num page_on' href=\"javascript:;\">");
                sb.append(commBean.getPageIndex());
                sb.append("</a></li>");
            } else {
            	sb.append("<li><a class='page_num' href=\"javascript:linkPage('").append(commBean.getPageUrl()).append("', '").append(movePage).append("', '").append(commBean.getOrderby()).append("', '").append(commBean.getDesc()).append("');\">");
                sb.append(movePage);
                sb.append("</a></li>");
            }

            if (movePage == endPage) {
                sb.append("\n");
            }

            sb.append("\n");
            movePage++;
        }

        if (chkPage > endPage) {
        	movePage = endPage + 1;

            // 5 페이지 뒤로
            sb.append("<li><a class='page_btn' href=\"javascript:linkPage('").append(commBean.getPageUrl()).append("', '").append(movePage).append("', '").append(commBean.getOrderby()).append("', '").append(commBean.getDesc()).append("');\" title=\"다음으로\">");
            sb.append("<div><img src=\"").append(serverPath).append("/img/icon/icon_page_next.png\" alt='다음 5페이지 이동' title='다음 5페이지 이동'/></div></a></li>");

            // 맨 뒤로
            sb.append("<li><a class='page_btn' href=\"javascript:linkPage('").append(commBean.getPageUrl()).append("', '").append(chkPage).append("', '").append(commBean.getOrderby()).append("', '").append(commBean.getDesc()).append("');\" title=\"맨마지막으로\">");
            sb.append("<div><img src=\"").append(serverPath).append("/img/icon/icon_page_last.png\" alt='마지막' title='마지막'/></div></a></li>");
        } else {
        	// 5 페이지 뒤로
        	sb.append("<li><a class='page_btn'><div><img src=\"").append(serverPath).append("/img/icon/icon_page_next.png\" alt='다음 5페이지 이동' title='다음 5페이지 이동'/></div></a></li>");

            // 맨 뒤로
            sb.append("<li><a class='page_btn'><div><img src=\"").append(serverPath).append("/img/icon/icon_page_last.png\" alt='마지막' title='마지막'/></div></a></li>");
        }

        sb.append("</ul>\n");

        return sb.toString();
    }
    
    /**
     * <pre>
     * 리스트 하단의 paging을 구해서 반환하는 메소드
     * 리스트 하단의 " [처음][before 10]1 2 3 4 5 ... [next 10][끝] " 형태로 페이징을 구해서 출력 합니다.
     *
     * </pre>
     * @param commBean   공통 Beans
     * @param adminYn 관리자페이지 여부
     * @return String
     */
    public String getSearchPageIndex(CommonBean commBean, String serverPath, String adminYn) {
        int movePage = 0;
        int startPage = 0;
        int endPage = 0;
        int chkPage = getTotalPage(commBean.getPageUnit(), commBean.getTotCount());
        StringBuffer sb = new StringBuffer("");

        startPage = ((commBean.getPageIndex() - 1) / commBean.getPageListScale()) * commBean.getPageListScale() + 1;
        endPage = (((startPage - 1) + commBean.getPageListScale()) / commBean.getPageListScale()) * commBean.getPageListScale();
        if (chkPage <= endPage) {
            endPage = chkPage;
        }
        
        sb.append("<input type='hidden' id='pageIndex' name='pageIndex' value='1'/>");
        sb.append("<ul class='page_btn_wrap'>");

        if (commBean.getPageIndex() > commBean.getPageListScale()) {
        	movePage = startPage - 1;

        	// 처음으로
        	sb.append("<li class='page_li'><a class='page_btn_1 page_first f_s_0' href=\"javascript:linkPage('").append(commBean.getPageUrl()).append("', '").append(1).append("', '").append(commBean.getOrderby()).append("', '").append(commBean.getDesc()).append("');\" title=\"처음으로\">처음으로</a></li>");

            // 페이지 왼쪽이동
            sb.append("<li class='page_li'><a class='page_btn_1 page_left f_s_0' href=\"javascript:linkPage('").append(commBean.getPageUrl()).append("', '").append(movePage).append("', '").append(commBean.getOrderby()).append("', '").append(commBean.getDesc()).append("');\" title=\"페이지 왼쪽이동\">페이지 왼쪽이동</a></li>");
        } else {
        	// 처음으로
            sb.append("<li class='page_li'><a class='page_btn_1 page_first f_s_0'>처음으로</a></li>");

            // 페이지 왼쪽이동
            sb.append("<li class='page_li'><a class='page_btn_1 page_left f_s_0'>페이지 왼쪽이동</a></li>");
        }

        sb.append("\n");

        movePage = startPage;
        while(movePage <= endPage) {
            if (movePage == commBean.getPageIndex()) {
                sb.append("\n");
                sb.append("<li class='page_li page_on'><a class='page_btn page_num' href=\"javascript:;\">");
                sb.append(commBean.getPageIndex());
                sb.append("</a></li>");
            } else {
            	sb.append("<li class='page_li'><a class='page_btn page_num' href=\"javascript:linkPage('").append(commBean.getPageUrl()).append("', '").append(movePage).append("', '").append(commBean.getOrderby()).append("', '").append(commBean.getDesc()).append("');\">");
                sb.append(movePage);
                sb.append("</a></li>");
            }

            if (movePage == endPage) {
                sb.append("\n");
            }

            sb.append("\n");
            movePage++;
        }

        if (chkPage > endPage) {
        	movePage = endPage + 1;

            // 페이지 오른쪽이동
            sb.append("<li class='page_li'><a class='page_btn_1 page_right f_s_0' href=\"javascript:linkPage('").append(commBean.getPageUrl()).append("', '").append(movePage).append("', '").append(commBean.getOrderby()).append("', '").append(commBean.getDesc()).append("');\" title=\"페이지 오른쪽이동\">페이지 오른쪽이동</a></li>");

            // 끝으로
            sb.append("<li class='page_li'><a class='page_btn_1 page_last f_s_0' href=\"javascript:linkPage('").append(commBean.getPageUrl()).append("', '").append(chkPage).append("', '").append(commBean.getOrderby()).append("', '").append(commBean.getDesc()).append("');\" title=\"끝으로\">끝으로</a></li>");
        } else {
        	// 페이지 오른쪽이동
        	sb.append("<li class='page_li'><a class='page_btn_1 page_right f_s_0'>페이지 오른쪽이동</a></li>");

            // 끝으로
            sb.append("<li class='page_li'><a class='page_btn_1 page_last f_s_0'>끝으로</a></li>");
        }

        sb.append("</ul>\n");

        return sb.toString();
    }
    
    /**
     * 답글 달기 앞에 = 붙이기
     * @param str 게시판 본문
     * @return 처리된 글
     */
    public String bodyReply(String str){
        if (StringUtils.isEmpty(str)) return "";

        int linenum = 0, i = 0;
        int str_len = str.length();
        String tmp = str;

        for (i=0; i < str_len; i++){
            if(tmp.charAt(i) == '\n'){
                linenum++;
            }
        }

        StringBuffer sb = new StringBuffer(str_len + linenum + 1);

        sb.append("= ");

        for (i=0; i < str_len; i++){
            if(tmp.charAt(i) == '\n'){
                sb.append("= ");
            }else{
                sb.append(tmp.charAt(i));
            }
        }

        return sb.toString();
    }




    /**
     * 다운로드 링크 생성
     * @param pageUrl 다운로드 링크 URL
     * @param fileName 파일 이름
     * @param fileSize 파일 크기
     * @param downCnt 다운로드 횟수
     * @param fileImg 아이콘 이미지 경로
     * @return 다운로드 링크
     */
    public String getFileLink(String pageUrl, String fileName, long fileSize, int downCnt, String fileImg){
        StringBuffer sb = new StringBuffer();
        if (fileSize != 0L){
            sb.append("<a href=\"");
            sb.append(pageUrl);
            sb.append("\"><img src=\"").append(fileImg).append("\" alt=\"[File]\" title=\"");
            sb.append("파일이름 : ");
            sb.append(fileName).append("\n");
            sb.append("크기 : \n");
            sb.append(fileSize).append(" bytes \n");
            sb.append("다운로드: ");
            sb.append(downCnt).append("회");
            sb.append("\" border=\"0\">  ");
            sb.append(fileName);
            sb.append("</a>");
        }
        return sb.toString();
    }


    /**
     * <pre>
     * 기본 검색 선택문 출력
     * select 태그를 이용한 기본 검색 선택문을 출력 합니다.
     * 해당 옵션 리스트는 다음과 같습니다.
     *
     * all      전체
     * subject  제목
     * writer   작성자
     * contents 내용
     * </pre>
     * @param paramkey 검색키(option value)
     * @return 검색 선택문
     */
    public String getSearchSelect(String paramkey){
        String jkey = paramkey;
        if (jkey == null) jkey = "";

        String[][] searchOption;
        searchOption = new String[][] {
                    {"all", "전체"},
                    {"subject", "제목"},
                    {"writer", "작성자"},
                    {"contents", "내용"}
                };
        StringBuffer sb = new StringBuffer();
        for (int i=0; i < searchOption.length; i++){
            sb.append("<option value=\"").append(searchOption[i][0]).append("\"");
            if (jkey.equals(searchOption[i][0])){
                 sb.append(" selected");
            }
            sb.append(">").append(searchOption[i][1]).append("</option>");
            sb.append("\n");
        }
        return sb.toString();
    }
}
