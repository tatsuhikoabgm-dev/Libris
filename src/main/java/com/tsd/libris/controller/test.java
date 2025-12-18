package com.tsd.libris.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class test {

	@GetMapping("/test")
	public String getMethodName() {
		return "test";
	}
	
	
	
	
	
}
