/*
 * @(#)CommonUtil.java    2004. 11. 4.
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

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

/**
 * <b>Description :</b>
 * <pre>
 * 웹 어플리케이션에서 사용하는 공용 메소드 모음
 * </pre>
 * @author kykime
 * @version 2004. 11. 4.
 */
public class CommonUtil {

    /**
     * str이 null 이거나 "" 일 경우 replaceStr을 return 함
     * @param str 원래 문장
     * @param replaceStr 바꿀 문장
     * @return 바뀐 문장
     * @deprecated org.apache.commons.lang.StringUtils.defaultIfEmpty(String, String)메소드를 이용하기 바랍니다.
     */
    public static String emptyToString(String str, String replaceStr) {
        String rtn = StringUtils.defaultString(replaceStr);
        if (StringUtils.isEmpty(str)) {
           return rtn;
        }
        return str;
    }

    
    /**
     * String을 byte단위로 잘라서 return
     * @param paramstr 원본 문장
     * @param lenLimit 자를 단위
     * @return 변경된 문장
     */
//    public static String getByteSubString(String paramstr, int lenLimit){
//        return getByteSubString(paramstr, lenLimit, "");
//    }

    /**
     * String을 byte단위로 잘라서 return
     * author okjsp.pe.kr
     * author http://www.javasun.net
     * modify kykime
     * @param paramstr 원본 문장
     * @param lenLimit 자를 단위
     * @param addStr 덧붙이는 문자
     * @return 변경된 문장
     */
    public static String getByteSubString(String paramstr, Integer lenLimit, String addStr) {
        if (paramstr == null) return "";

        String str = paramstr;
        StringBuffer sb = new StringBuffer();
        int subLen = 0;

        for (int i=0; (i < str.length() && subLen < lenLimit); i++) {
            if (Character.getType(str.charAt(i)) == Character.OTHER_LETTER) { // 2byte 문자 (한글, 한문,...)Character.OTHER_LETTER = 5
                subLen += 2;
            } else {
                subLen++;   // 영어소문자 (Character.LOWERCASE_LETTER = 2), space(Character.SPACE_SEPARATOR = 12), 특수 문자(Character.OTHER_PUNCTUATION  = 24)
            }

            sb.append(str.charAt(i));
        }

        byte[] byteStr = paramstr.getBytes();

        if (byteStr.length > lenLimit) {
            sb.append(addStr);
        }

        return sb.toString();
    }

	public static String subStringBytes(String szText, Integer nLength) { // 문자열 자르기

		String r_val = szText;
		int oF = 0, oL = 0, rF = 0, rL = 0;
		int nLengthPrev = 0;
		Pattern p = Pattern.compile("<(/?)([^<>]*)?>", Pattern.CASE_INSENSITIVE); // 태그제거 패턴

		r_val = p.matcher(r_val).replaceAll(""); // 태그 제거
		r_val = r_val.replaceAll("&amp;", "&");
		r_val = r_val.replaceAll("&#183;", "·");
		r_val = r_val.replaceAll("(!/|\r|\n|&nbsp;)", ""); // 공백제거

		try {
			byte[] bytes = r_val.getBytes("UTF-8"); // 바이트로 보관

			if(nLength > bytes.length) {
				return szText;
			}
			
			// x부터 y길이만큼 잘라낸다. 한글안깨지게.
			int j = 0;

			if (nLengthPrev > 0)
				while (j < bytes.length) {
					if ((bytes[j] & 0x80) != 0) {
						oF += 2;
						rF += 3;

						if (oF + 2 > nLengthPrev) {
							break;
						}

						j += 3;
					} else {
						if (oF + 1 > nLengthPrev) {
							break;
						}

						++oF;
						++rF;
						++j;
					}
				}

			j = rF;

			while (j < bytes.length) {
				if ((bytes[j] & 0x80) != 0) {
					if (oL + 2 > nLength) {
						break;
					}

					oL += 2;
					rL += 3;
					j += 3;
				} else {
					if (oL + 1 > nLength) {
						break;
					}

					++oL;
					++rL;
					++j;
				}
			}

			r_val = new String(bytes, rF, rL, "UTF-8"); // charset 옵션

			if (rF + rL + 3 <= bytes.length) {
				r_val += "...";
			} // ...을 붙일지말지 옵션
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return szText;
		} catch (Exception e) {
			e.printStackTrace();
			return szText;
		}

		return r_val;
	}

    /**
     * 쿠키 설정
     * @param response HttpServeletResponse
     * @param cookieName 쿠키 이름
     * @param cookieValue 쿠키 값
     */
    public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue) {
        setCookie(response, cookieName, cookieValue, "");
    }

    /**
     * 쿠키 설정
     * @param response HttpServeletResponse
     * @param cookieName 쿠키 이름
     * @param cookieValue 쿠키 값
     * @param domain 도메인
     */
    public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue, String domain){
        if (StringUtils.isNotBlank(cookieValue)){
            Cookie cookie = new Cookie (cookieName, cookieValue);
            cookie.setPath("/");
            if (StringUtils.isNotEmpty(domain)) {
                cookie.setDomain(domain);
            }
            response.addCookie(cookie);
        }
    }

    /**
     * 쿠키 읽기
     * @param request HttpServletRequest
     * @param cookieName 쿠키 이름
     * @return 쿠키 값
     */
    public static String getCookie(HttpServletRequest request, String cookieName){
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return "";

        String cookieValue = "";
        for (int i=0; i < cookies.length; i++){
            if (cookieName.equals(cookies[i].getName())){
                cookieValue = cookies[i].getValue();
                break;
            }
        }
        return cookieValue;
    }

    /**
     * 쿠키 1개 삭제
     * @param request HttpServletRequest
     * @param response HttpServeletResponse
     * @param cookieName 쿠키 이름
     */
    public static void delCookieOne(HttpServletRequest request, HttpServletResponse response, String cookieName){
        delCookieOne(request, response, cookieName, "");
    }

    /**
     * 쿠키 1개 삭제
     * @param request HttpServletRequest
     * @param response HttpServeletResponse
     * @param cookieName 쿠키 이름
     * @param domain 도메인
     */
    public static void delCookieOne(HttpServletRequest request, HttpServletResponse response, String cookieName, String domain){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (int i=0; i < cookies.length; i++){
                if (cookieName.equals(cookies[i].getName())){
                    Cookie cookie = new Cookie(cookies[i].getName(), "");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    if (StringUtils.isNotEmpty(domain)) {
                        cookie.setDomain(domain);
                    }
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }

    /**
     * 쿠키 전체 삭제
     * @param request HttpServletRequest
     * @param response HttpServeletResponse
     */
    public static void delCookie(HttpServletRequest request, HttpServletResponse response){
        delCookie(request, response, "");
    }

    /**
     * 쿠키 전체 삭제
     * @param request HttpServletRequest
     * @param response HttpServeletResponse
     * @param domain 도메인
     */
    public static void delCookie(HttpServletRequest request, HttpServletResponse response, String domain){
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        if (cookies != null){
            for (int i=0; i < cookies.length; i++){
                if (!cookies[i].getName().equals("JSESSIONID")){
                    cookie = new Cookie(cookies[i].getName(), "");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    if (StringUtils.isNotEmpty(domain)){
                        cookie.setDomain(domain);
                    }
                    response.addCookie(cookie);
                    cookie = null;
                }
            }
        }
    }

    /**
     * nodata check("", null, "null")
     * @param str 검사할 문자열
     * @return 데이터가 없을때 : true, 데이터가 있을때 : false
     */
    public static boolean isNoData(String str){
        boolean isResult = false;
        if(str == null
                || str.equals("")
                || str.toUpperCase().equals("NULL"))
            isResult = true;
        return isResult;
    }

    /**
     * Exception 발생 시 e.getMessage()의 String을 xxxException을 제외한 에러 메시지만 읽음
     * @param errMsg e.getMessage()
     * @return 에러 메시지
     */
    public static String getErrorMsg(String errMsg) {
        if (StringUtils.isEmpty(errMsg)) return "";
        String msg = errMsg.substring(errMsg.lastIndexOf(":") + 1, errMsg.length());
        msg = StringUtils.remove(msg,"\"");
        return msg;
    }

    /**
     * printStackTrace()를 String으로 변환
     * @deprecated use org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(Throwable throwable)
     * @param exp Exception Object
     * @return printStackTrace()를 String으로 변환
     */
    public static String getPrintStackTrace(Exception exp) {
        StringBuffer msg = new StringBuffer("");
        if (exp != null) {
            msg.append(exp.toString()).append("\n");
            StackTraceElement[] stElement = exp.getStackTrace();
            if (stElement != null) {
                for (int i=0; i < stElement.length; i++) {
                    msg.append("\t");
                    msg.append(stElement[i]).append("\n");
                }
                msg.append("\n");
            }
        }
        return msg.toString();
    }

    /**
     * 이메일 유효성 검사
     * @param email E-Mail Address
     * @return true : 정상
     */
    public static boolean isEmail(String email) {
        if (StringUtils.isEmpty(email)) return false;
        Pattern ptMust = Pattern.compile("^[a-zA-Z0-9\\-\\.\\_]+\\@[a-zA-Z0-9\\-\\.]+\\.([a-zA-Z]{2,3})$");
        Pattern ptDoNot = Pattern.compile("(@.*@)|(\\.\\.)|(@\\.)|(\\.@)|(^\\.)");
        Matcher matchMust = ptMust.matcher(email);
        Matcher matchDoNot = ptDoNot.matcher(email);

        return matchMust.find() && !matchDoNot.find();
    }


    /**
     * 이미지 width, height 구하기
     * @param imgPath
     * @return
     */
    public static int[] getImageSize(String imgPath) {
        int[] size = new int[2];

        if (StringUtils.isEmpty(imgPath)) {
            size[0] = 0;
            size[1] = 0;
            return size;
        }

        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(imgPath);
            bis = new BufferedInputStream(fis);
            BufferedImage bImage = ImageIO.read(bis);

            int thisWidth = (bImage == null) ? 0 : bImage.getWidth();
            int thisHeight = (bImage == null) ? 0 : bImage.getHeight();

            size[0] = thisWidth;
            size[1] = thisHeight;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (fis != null) fis.close(); } catch(Exception _ignored){}
            try { if (bis != null) bis.close(); } catch(Exception _ignored){}
        }
        return size;
    }

    /**
     * 이미지 사이즈를 읽어서 원래 사이즈가 max 사이즈 보다 크면 maxsize를
     * 반대의 경우
     * @param imgPath
     * @param maxWidth
     * @param maxHeigth
     * @return
     */
    public static int[] getImageSize(String imgPath, int maxWidth, int maxHeigth) {
        int[] size = getImageSize(imgPath);
        if (size[0] > maxWidth) {
            size[0] = maxWidth;
        }
        if (size[1] > maxHeigth) {
            size[1] = maxHeigth;
        }
        return size;
    }

    /**
     * HTML TAG 제거
     * @param str
     * @return
     */
    public static String removeTag(String str){
        if (str == null) return "";

        int lt = str.indexOf("<");
        if (lt != -1) {
            int gt = str.indexOf(">", lt);
            if ((gt != -1)) {
                str = str.substring(0, lt) + str.substring(gt + 1);
                str = removeTag(str);
            }
        }
        return str;
    }

    /**
     * 대소문자 구분없는 문자열 대체
     * 원저자 : www.jakartaproject.com GoodBug
     * @param mainStr
     * @param oldStr
     * @param newStr
     * @return
     */
    public static String replaceIgnoreCase(String mainStr, String oldStr, String newStr) {
        if (StringUtils.isEmpty(mainStr) || StringUtils.isEmpty(oldStr)) {
            return StringUtils.defaultString(mainStr);
        }

        Pattern pattern = Pattern.compile(oldStr, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(mainStr);
        return matcher.replaceAll(StringUtils.defaultString(newStr));
    }


  
    /**
     * 페이지 리스트용 숫자 파라메터 구하기
     * @param page 현재 페이지
     * @param pageListScale 페이지당 보여질 리스트 수
     * @param totalCount 전체 갯수
     * @return 파라메터 맵
     */
    public static Hashtable<String, String> getPageListParam(int page, int pageListScale, int totalCount){
        BoardUtil boardUtil = new BoardUtil();
        int totalPage = boardUtil.getTotalPage(pageListScale, totalCount);
        int num2 = pageListScale;
        if (page == totalPage) {
            num2 = totalCount % pageListScale;
            if (num2 == 0) num2 = pageListScale;
        }

        Hashtable<String, String> htListParam = new Hashtable<String, String>();
        htListParam.put("num1",  String.valueOf(page * pageListScale));
        htListParam.put("num2",  String.valueOf(num2));

        return htListParam;
    }

    // 로그인 여부 확인
    public String loginChk(HttpServletRequest request) throws IOException{
    	String msg = "loginT";
    	HttpSession session = request.getSession();
    	if(session.getAttribute("adminInfo") == null){
    		msg = "loginF";
    	}
    	return msg;
    }

    /**
	 * 사용자IP 리턴. 
	 * @param HttpServletRequest 
	 * @return clientIP 사용자IP
	 * @exception Exception
	 * @see 
	 */	
	public static String getClientIp( HttpServletRequest request ) throws Exception {
		//사용자IP
	    String clientIP = "";
	    clientIP = request.getHeader("Proxy-Client-Ip");
	    if(clientIP == null){
	        clientIP = request.getHeader("WL-Proxy-Client-IP");
	        if(clientIP == null){
	            clientIP = request.getHeader("X-Forwared-For");
	            if(clientIP == null){
	                clientIP = request.getRemoteAddr();
	            }
	        }
	    }
		return clientIP;
	}
	public static Date addMonth(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }
	
	public static String getRandomString(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
	
	public static String isNullToString(Object object) {

        String string = "";

        if (object != null && !object.equals("null") && object.toString().length() > 0) {
            string = object.toString().trim();
        }

        return string;
    }
}