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
														HttpServletResponse responce,
														Object handler) throws Exception{
		
		HttpSession session = request.getSession(false);
		String uri = request.getRequestURI();
		boolean login = session != null && session.getAttribute("SESSION_USER") != null;
		
		if(login == true && )
		
	
	}
	
	
	
	
	
	
	
}
