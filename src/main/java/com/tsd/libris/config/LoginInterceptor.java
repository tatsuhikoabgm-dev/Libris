package com.tsd.libris.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	
	@Override
	public boolean preHandle(HttpServletRequest request,
								HttpServletResponse response,
								Object handler) throws Exception{
		
		HttpSession session = request.getSession(false);
		String uri = request.getRequestURI();
		boolean login = session != null && session.getAttribute("SESSION_USER") != null;
		
		boolean publicPage =
		        uri.startsWith("/auth/login") ||
		        uri.startsWith("/user");

		boolean staticResource =
	            uri.startsWith("/css/") ||
	            uri.startsWith("/js/") ||
	            uri.startsWith("/images/") ||
	            uri.equals("/favicon.ico");
		
		boolean errorPage =
		        uri.startsWith("/error");
		
		
	    if (staticResource) return true;
	    if(errorPage) return true;
	    
	    
		
		if(login && publicPage) {
			response.sendRedirect("/books/search");
			return false;
		}
		
		if(!login && !publicPage) {
			response.sendRedirect("/auth/login");
			System.out.println(publicPage);
			System.out.println(uri);
			return false;
		}
		
		return true;
		
	
	}
	
	
	
}
