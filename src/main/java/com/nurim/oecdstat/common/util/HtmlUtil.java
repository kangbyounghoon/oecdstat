/*
 * 
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
 * Created on 2004. 11. 5.
 */
package com.nurim.oecdstat.common.util;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <b>Description :</b>
 * <pre>
 * HTML에서 공통적으로 사용하는 UTILITY
 * </pre>
 * @author kykime
 * @version 2004. 11. 5.
 */
public class HtmlUtil{

    /**
     * <pre>
     * HTML TO TEXT
     * \n을 &lt;br&gt;로 처리 함
     * </pre>
     * @param str HTML문
     * @return Text
     */
    public static String htmlToText(String str){
        if (StringUtils.isEmpty(str)) return "";

        String rtnStr = StringEscapeUtils.escapeHtml(str);

        return StringUtils.replace(rtnStr, "\n", "<br>");
    }

    /**
     * HTML TO TEXT
     * <br>제외
     */
    public static String htmlToText2(String str){
        if (str == null) return "";

        String tmpStr = str;

        if (tmpStr.indexOf("\"") > -1) tmpStr= StringUtils.replace(tmpStr, "\"", "&quot;");
        if (tmpStr.indexOf("<") > -1) tmpStr = StringUtils.replace(tmpStr, "<", "&lt;");
        if (tmpStr.indexOf(">") > -1) tmpStr = StringUtils.replace(tmpStr, ">", "&gt;");

        return tmpStr;
    }

    /**
     * TEXT TO HTML
     */
    public static String textToHtml(String str){
        if (str == null) return "";

        String tmpStr = str;

        if (tmpStr.indexOf("&quot;") > -1) tmpStr= StringUtils.replace(tmpStr, "&quot;", "\"");
        if (tmpStr.indexOf("&lt;") > -1) tmpStr = StringUtils.replace(tmpStr, "&lt;", "<");
        if (tmpStr.indexOf("&gt;") > -1) tmpStr = StringUtils.replace(tmpStr, "&gt;", ">");
        if (tmpStr.indexOf("<br>") > -1) tmpStr = StringUtils.replace(tmpStr, "<br>", "\n");
        if (tmpStr.indexOf("<BR>") > -1) tmpStr = StringUtils.replace(tmpStr, "<BR>", "\n");
        if (tmpStr.indexOf("<p>") > -1) tmpStr = StringUtils.replace(tmpStr, "<p>", "\n\n");
        if (tmpStr.indexOf("<P>") > -1) tmpStr = StringUtils.replace(tmpStr, "<P>", "\n\n");

        return tmpStr;
    }

    /**
     * javascript return
     * @param comm javascript 명령어
     * @return 자바스크립트
     */
    public static String getScript(String comm){
        StringBuffer sb = new StringBuffer();
        sb.append("<script type=\"text/javascript\">\n");
        sb.append(comm).append("\n");
        sb.append("</script>");

        return sb.toString();
    }

    /**
     * 명령어로만 이루어진 javascript 출력
     * @param comm
     * @param out
     */
    public static void writeScript(String comm, JspWriter out) {
        try {
            out.println(getScript(comm));
        } catch(Exception e){
//            LogWrite.logWrite(e.toString(), "exception");
        }
    }

    /**
     * javascript return
     * @param msg javascript 메시지
     * @param comm javascript 명령어
     * @return 메시지를 포함한 자바스크립트
     */
    public static String getScript(String msg, String comm){
        StringBuffer sb = new StringBuffer();
        sb.append("<script type=\"text/javascript\">\n");
        sb.append(" alert(\"").append(msg).append("\");\n");
        sb.append(comm).append("\n");
        sb.append("</script>");

        return sb.toString();
    }

    /**
     * javascript return
     * @param msg javascript 메시지
     * @param comm javascript 명령어
     * @param out JspWriter
     */
    public static void getScript(String msg, String comm, JspWriter out){
        StringBuffer sb = new StringBuffer();
        sb.append("<script type=\"text/javascript\">\n");
        sb.append(" alert(\"").append(msg).append("\");\n");
        sb.append(comm).append("\n");
        sb.append("</script>");
        try{
            out.print(sb.toString());
        }catch(Exception e){
//            LogWrite.logWrite(e.toString(), "exception");
        }
    }

    /**
     * 메시지를 띄우고 창을 닫음
     * javascript return
     * @param msg javascript 메시지
     * @param comm javascript 명령어
     * @param out JspWriter
     */
    public static void getScriptClose(String msg, JspWriter out){
        StringBuffer sb = new StringBuffer();
        sb.append("<script type=\"text/javascript\">\n");
        sb.append(" alert(\"").append(msg).append("\");\n");
        sb.append(" window.close();");
        sb.append("</script>");
        try{
            out.print(sb.toString());
        }catch(Exception e){
//            LogWrite.logWrite(e.toString(), "exception");
        }
    }

    /**
     * ResourceBundle로 부터 명령어를 읽어서 javascript return
     * @param bundleID javascript 메시지
     * @param comm javascript 명령어
     * @return 메시지를 포함한 자바스크립트
     */
    public static String getScriptBundle(String bundleID, String comm){
        String jsMsg = getBundleMsg(bundleID);
        String jsComm = comm;

        StringBuffer sb = new StringBuffer();
        sb.append("<script type=\"text/javascript\">\n");
        sb.append(" alert(\"").append(jsMsg).append("\");\n");
        sb.append(jsComm).append("\n");
        sb.append("</script>");

        return sb.toString();
    }

    /**
     * <pre>
     * ResourceBundle로 부터 메시지를 읽는 메소드
     * 리소스 클래스로 부터 리소스 번들을 찾을때에는 String 클래스의 인코딩 값이 특별한 처리가 필요 없지만
     * properties 파일에서 읽어 올때에는 ISO8859_1 인코딩으로 간주되어 있으므로 한글 처리가 필요하다.
     *
     * 리소스 번들은 다음과 같이 구성되어야 합니다.
     *
     * <b>msg.properties</b>
     * 메시지아이디=메시지 내용
     * </pre>
     * @param bundleID 리소스 번들 아이디(메시지 아이디)
     * @return 메시지
     */
    public static String getBundleMsg(String bundleID){
    	String msg = "지정된 메시지가 없습니다";
        java.util.ResourceBundle bundle = null;
        try{
            bundle = java.util.ResourceBundle.getBundle("msg");
            msg = new String(bundle.getString(bundleID).getBytes("8859_1"), "EUC-KR");
        }catch(Exception e){
        }
        return msg;
    }

    /**
     * <pre>
     * XML 에서 메시지를 읽는 메소드
     * message.xml을 이용하여 common 그룹의 메시지를 읽어 온다.
     * </pre>
     * @param xmlMsgId common message group 의 메시지 아이디
     * @return 메시지
     */
    public static String getXmlMsg(String xmlMsgId) {
    	String msg = "지정된 메시지가 없습니다.";
        try{
//            msg = ConfigReader.getMessage(xmlMsgId);
        }catch(Exception e){
//            LogWrite.logWrite(e.toString(), "exception");
        }
        return msg;
    }


    /**
     * <pre>
     * XML 에서 메시지를 읽어서 javascript return
     * message.xml을 이용하여 common 그룹의 메시지를 읽어 온다.
     * </pre>
     * @param xmlMsgId common message group 의 메시지 아이디
     * @param comm 자바 스크립트 커맨드
     * @return 자바스크립트
     */
    public static String getScriptXml(String xmlMsgId, String comm) {
        String jsMsg = getXmlMsg(xmlMsgId);
        String jsComm = comm;

        StringBuffer sb = new StringBuffer();
        sb.append("<script type=\"text/javascript\">\n");
        sb.append(" alert(\"").append(jsMsg).append("\");\n");
        sb.append(jsComm).append("\n");
        sb.append("</script>");

        return sb.toString();
    }

    /**
     * <pre>
     * XML 에서 메시지를 읽어서 javascript return
     * message.xml을 이용하여 common 그룹의 메시지를 읽어 온다.
     * </pre>
     * @param xmlMsgId common message group 의 메시지 아이디
     * @param comm 자바 스크립트 커맨드
     * @param out
     */
    public static void getScriptXml(String xmlMsgId, String comm, JspWriter out) {
        String jsMsg = getXmlMsg(xmlMsgId);
        String jsComm = comm;

        StringBuffer sb = new StringBuffer();
        sb.append("<script type=\"text/javascript\">\n");
        sb.append(" alert(\"").append(jsMsg).append("\");\n");
        sb.append(jsComm).append("\n");
        sb.append("</script>");

        try{
            out.print(sb.toString());
        }catch(Exception e){
//            LogWrite.logWrite(e.toString(), "exception");
        }
    }

    /**
     * Servlet 에서 javascript 구문 생성 시 사용
     * @param response
     * @param script
     */
    public static void writeScript(HttpServletResponse response, String script){
        response.setContentType("text/html;charset=EUC-KR");
        PrintWriter out = null;
        try{
            out = response.getWriter();
            out.println("<html>");
            out.println("<body>");
            out.println(script);
            out.println("</body>");
            out.println("</html>");
        }catch (Exception e){
//            min.base.util.LogWrite.logWrite("error="+e.toString(), "exception");
        }finally{
            if(out != null) out.close();
        }
    }

    /**
     * 사용자에게 보여주는 메시지를 받아서 javascript history.go(-1); 생성
     * @param msg
     * @param out
     * @return
     */
    public static void writeScriptBack(String msg, JspWriter out) {
        getScript(msg, "history.go(-1);", out);
    }

    /**
     * 사용자에게 에러 메시지를 보여주고 javascript history.go(-1); 을 생성
     * @param msg Throwable.getMessage()
     * @param out JspWriter
     */
    public static void writeErrMsgScript(String msg, JspWriter out) {
        String errMsg = CommonUtil.getErrorMsg(msg);
        writeScriptBack(errMsg, out);
    }

    /**
     * &lt;select&gt;에서 &lt;option&gt;문 생성
     * @param paramkey 선택 키값
     * @param paramStr option value array
     * @return option 태그 문
     */
    public static String getOption(String paramkey, String[][] paramStr){
        if (paramStr == null || paramStr.length == 0) return "";

        String jkey = StringUtils.defaultString(paramkey);

        StringBuffer sb = new StringBuffer();
        for (int i=0; i < paramStr.length; i++){
            sb.append("<option value=\"").append(paramStr[i][0]).append("\"");
            if (jkey.equals(paramStr[i][0])){
                sb.append(" selected");
            }
            sb.append(">").append(paramStr[i][1]).append("</option>");
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * 검색어 존재 시 검색어 Highlight 생성
     * @param str 문자열
     * @param repstr 바꿀 문자열
     * @return 변경된 문장
     */
    public static String getHighlight(String str, String repstr){
        if (str == null || "".equals(str)) return "";

        if (repstr == null || "".equals(repstr)) return str;

        String highlightStr = "<font color=\"#CC3300\" style=\"background-color:#FFFF80;\">" + repstr + "</font>";
        return StringUtils.replace(str, repstr, highlightStr);
    }
    
    /**
     * 쌍따움표를 없애줌
     * @param str
     * @return
     */
    public static String ReplaceDoubleQuotes(String str){
    	str = str.replaceAll("\"","");
    	return str;
    } 	
    
    /**
     * 엔터 액션 제거
     * @param str
     * @return
     */
    public static String ReplaceGap(String str){
    	str = str.replaceAll("\r\n", "");
    	return str;
    }
    
    /**
     * Web Encoding 바꾸기
     * @param str
     * @return
     */
    public static String ReplaceWeb(String str){
    	str = str.replaceAll("\\+", " ");
    	str = str.replaceAll("%21", "!");
    	str = str.replaceAll("%23", "#");
    	str = str.replaceAll("%24", "\\$");
    	str = str.replaceAll("%25", "%");
    	str = str.replaceAll("%26", "&");
    	str = str.replaceAll("%27", "'");
    	str = str.replaceAll("%28", "(");
    	str = str.replaceAll("%29", ")");
    	str = str.replaceAll("%2A", "*");
    	str = str.replaceAll("%2B", "+");
    	str = str.replaceAll("%2C", ",");
    	str = str.replaceAll("%2D", "-");
    	str = str.replaceAll("%3B", ";");
    	str = str.replaceAll("%3D", "=");
    	str = str.replaceAll("%40", "@");
    	str = str.replaceAll("%5E", "^");
    	str = str.replaceAll("%5B", "[");
    	str = str.replaceAll("%5D", "]");
    	str = str.replaceAll("%5F", "_");
    	str = str.replaceAll("%7B", "{");
    	str = str.replaceAll("%7D", "}");
    	str = str.replaceAll("%7E", "~");
    	
    	return str;
    }
    
    /**
     * html코드 리플레이스
     * @param str
     * @return
     */
    public static String ReplaceCode(String str){
    	
//      str = str.replaceAll("\"","&gt;");
//      str = str.replaceAll("&", "&amp;");
      str = str.replaceAll("<", "&lt;");
      str = str.replaceAll(">", "&gt;");
      str = str.replaceAll("%00", null);
//      str = str.replaceAll("\"", "&#34;");
//      str = str.replaceAll("\'", "&#39;");
//      str = str.replaceAll("%", "&#37;");    
//      str = str.replaceAll("../", "");
      str = str.replaceAll("..\\\\", "");
//      str = str.replaceAll("./", "");
      str = str.replaceAll("%2F", "");
      str = str.replaceAll("\r\n", "<br>");

        return str;
    }
    
    public static int ReplaceCode(int num){
    	
    	String str = Integer.toString(num);
    	int num2 = 0;
    	
    	if(str != null){
            str = str.replaceAll("\"","&gt;");
            str = str.replaceAll("&", "&amp;");
            str = str.replaceAll("<", "&lt;");
            str = str.replaceAll(">", "&gt;");
            str = str.replaceAll("%00", null);
            str = str.replaceAll("\"", "&#34;");
            str = str.replaceAll("\'", "&#39;");
            str = str.replaceAll("%", "&#37;");    
            str = str.replaceAll("../", "");
            str = str.replaceAll("..\\\\", "");
            str = str.replaceAll("./", "");
            str = str.replaceAll("%2F", "");
    	}
    	num2 = Integer.parseInt(str);
    	return num2;
    }
    
    /**
     * 알림 창 띄우면서 URL로 이동
     * @param msg
     * @param url
     * @param res
     * @throws IOException
     */
    public static void UrlMove (String msg, String url, HttpServletResponse res) throws IOException{
    	res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = res.getWriter();
		pw.write("<script type='text/javascript'>");

		if(!"".equals(msg)) {
			pw.write("alert('"+msg+"');");
		}

	    pw.write("location.href='"+url+"'");
	    pw.write("</script>");
		pw.close();
		pw.flush();
    }

    /**
     * 알림창 띄우면서 전 화면으로 이동
     * @param msg
     * @param res
     * @throws IOException
     */
    public static void BeforeUrl (String msg, HttpServletResponse res) throws IOException{
    	res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = res.getWriter();
		pw.write("<script type='text/javascript'>");
		if (msg != ""){
			pw.write("alert('"+msg+"');");
		}
	    pw.write("history.go(-1);");
	    pw.write("</script>");
		pw.close();
		pw.flush();
    }

    /**
     * 팝업창 종료
     * @param msg
     * @param res
     * @throws IOException
     */
	public static void popUpOnlyClose(String msg, HttpServletResponse res) throws IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = res.getWriter();
		pw.write("<script>");

		if(msg != "") {
			pw.write("alert('"+msg+"');");
		}

		pw.write("window.close();");
		pw.write("</script>");
		pw.close();
		pw.flush();
	}

    /**
     * 팝업창 닫고 부모창 리로드
     * @param msg
     * @param res
     * @throws IOException
     */
	public static void popUpClose(String msg, HttpServletResponse res, Boolean success) throws IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = res.getWriter();
		pw.write("<script>");
		if (msg != ""){
			pw.write("alert('"+msg+"');");
		}
		pw.write("window.close();");
		pw.write("javascript:opener.location.href='javascript:pageReload("+ success + ");'");
		pw.write("</script>");
		pw.close();
		pw.flush();
	}
	
	 /**
     * 레이어 닫고 리로드 (모바일)
     * @param msg
     * @param res
     * @throws IOException
     */
	public static void MblpopUpClose(String msg, HttpServletResponse res, Boolean success) throws IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = res.getWriter();
		pw.write("<script>");
		if (msg != ""){
			pw.write("alert('"+msg+"');");
		}
		pw.write("javascript:window.location.href='javascript:pageReload("+ success + ");'");
		pw.write("</script>");
		pw.close();
		pw.flush();
	}
}