package com.tsd.libris.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginIntercepter implements HandlerInterceptor {

	
	@Override
	public boolean preHandle(HttpServletRequest request,
								HttpServletResponse response,
								Object handler) throws Exception{
		
		HttpSession session = request.getSession(false);
		String uri = request.getRequestURI();
		boolean login = session != null && session.getAttribute("SESSION_USER") != null;
		
		boolean publicpage = uri.startsWith("/user/register") ||
							uri.equals("/error") ||
							uri.equals("/auth/login");
		
		if(login && publicpage) {
			response.sendRedirect("/books/search");
			return false;
		}
		
		if(!login && !publicpage) {
			response.sendRedirect("/auth/login");
			System.out.println(publicpage);
			System.out.println(uri);
			return false;
		}
		
		return true;
		
	
	}
	
	
	
}
