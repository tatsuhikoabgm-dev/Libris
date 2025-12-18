package com.tsd.libris.controller.advice;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalViewControllerAdvice {

	
	@ModelAttribute("currentPath")
	public String addCurrentPath(HttpServletRequest request) {
		return request.getServletPath();
	}
	
	
}
