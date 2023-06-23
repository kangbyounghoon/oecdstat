package com.nurim.oecdstat.common.security;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import com.nurim.oecdstat.common.bean.ServiceBean;
import com.nurim.oecdstat.common.service.CommonService;
import com.nurim.oecdstat.system.service.SystemService;

public class CustomFilter extends GenericFilterBean {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SystemService menuService;
	
	@Autowired
	private CommonService commonService;	

	@Autowired 
	private HttpSession session;
			
	/**
	 * Default AJAX request Header
	 */
	private String ajaxHaeder = "AJAX";
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
						
		logger.info("@@@전처리@@@");

		HttpServletRequest req = (HttpServletRequest) request;
		
    	ServiceBean menuBean = new ServiceBean();
    	
    	String uri = req.getRequestURI();
    	String path = "";
    	String summary = "";
    	String menu_sort = "";
    	
    	logger.info("@@@전처리@@@"+ uri);
    	
		List<ServiceBean> mainMenu = new ArrayList<ServiceBean>();    	
		List<ServiceBean> mainMenu_new = new ArrayList<ServiceBean>();    	
    	List<ServiceBean> subMenu = new ArrayList<ServiceBean>();
    	List<ServiceBean> mainMenu_sort = new ArrayList<ServiceBean>();    	
        
        String rootId = StringUtils.defaultIfEmpty(request.getParameter("rootId"),"");
        String currentMenuId = StringUtils.defaultIfEmpty(request.getParameter("menuId"),"");       
        String zoom_size = StringUtils.defaultIfEmpty((String)session.getAttribute("zoom_size"),"zoom_size"); 	 							//크기 조정

        
        // 메인 페이지, 기타 페이지, 검색 페이지는 menuId, rootId 제거
        if(uri.indexOf("/main.html") > -1 || uri.indexOf("/etc/") > -1 || uri.indexOf("/search/") > -1) {
        	session.removeAttribute("menuId");
        	session.removeAttribute("rootId");

        	currentMenuId = "";
			rootId = "";
		} else if(uri.indexOf("/viewer/") > -1) {
			chain.doFilter(request, response);
			return;
		}
        
        if(isAjaxRequest(req)) {
        	chain.doFilter(request, response);
        	logger.info("@@@후처리@@@");
        } else {
        	if("".equals(currentMenuId)) currentMenuId = StringUtils.defaultIfEmpty((String)session.getAttribute("menuId"),"");
        	if("".equals(rootId)) rootId = StringUtils.defaultIfEmpty((String)session.getAttribute("rootId"),"");
        	
        	menuBean.setRoot_id(rootId);                
        	
        	try {
        		mainMenu = menuService.selectBeanList("menu.selectMainMenuList", menuBean);
        		mainMenu_new  = menuService.selectBeanList("menu.selectMainMenuViewListNew", menuBean);
        		
        		for(int i=0; i<mainMenu.size(); i++) {
        			menuBean.setRoot_id(mainMenu.get(i).getMenu_id());
        			
        			subMenu = menuService.selectBeanList("menu.selectSubMenuViewList", menuBean);			
        			mainMenu_new.get(i).setSubMenu(subMenu);
        			
        			for(int j=0;j<subMenu.size();j++) {
        				if(currentMenuId.equals(subMenu.get(j).getMenu_id())) {
        					path = subMenu.get(j).getPath();
        					summary = subMenu.get(j).getMenu_desc();
        					break;
        				}
        			}
        		}
        		
        		//sub_location 리스트
        		if(currentMenuId != "") {
        			menuBean.setRoot_id(rootId);
        			mainMenu_sort = menuService.selectBeanList("menu.selectSubMenuList", menuBean);
        			
        			ServiceBean detail = new ServiceBean();
        			detail.setMenu_id(rootId);
        			detail = menuService.selectBean("menu.selectMenuDetail", detail);
        			
        			ServiceBean detail_sub = new ServiceBean();
        			detail_sub.setMenu_id(currentMenuId);
        			detail_sub = menuService.selectBean("menu.selectMenuDetail", detail_sub);
        			
        			if(detail != null && !"".equals(detail)){
        				menu_sort = detail.getMenu_sort();
        			}
        			
        			session.setAttribute("menunm", detail.getMenu_nm());
        			session.setAttribute("menudesc", detail.getMenu_desc());
        			
        			session.setAttribute("menunm_sub", detail_sub.getMenu_nm());
        			session.setAttribute("menudesc_sub", detail_sub.getMenu_desc());
        			
        			session.setAttribute("menu_sort", menu_sort);
        			session.setAttribute("sub", mainMenu_sort);
        		}
        		
        	} catch (SQLException e) {
        		logger.error("sql exception", e);
        	} catch (Exception e) {
        		logger.error("exception", e);
        	}       		
        	
        	request.setAttribute("menu", mainMenu_new);
        	request.setAttribute("subMenu", subMenu);
        	session.setAttribute("menuId", currentMenuId);        
        	session.setAttribute("rootId", rootId);        
        	request.setAttribute("path", path);
        	request.setAttribute("summary", summary);
        	
        	//크기 조정
        	request.setAttribute("zoom_size", zoom_size);
        	
        	chain.doFilter(request, response);
        	
        	logger.info("@@@후처리@@@");
        }
	}
	
	private boolean isAjaxRequest(HttpServletRequest req) {
		return req.getHeader(ajaxHaeder) != null && req.getHeader(ajaxHaeder).equals(Boolean.TRUE.toString());
	}
	
	/**
	 * Set AJAX Request Header (Default is AJAX)
	 * @param ajaxHeader
	 */

	public void setAjaxHaeder(String ajaxHeader) {
		this.ajaxHaeder = ajaxHeader;
	}
}
