package com.tsd.libris.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class test {

//	private final UserMapper um;
	
	
	@GetMapping("/test")
	public String testMeth(@RequestParam String loginId ) {
		
//		System.out.println(um.findByLoginId(loginId));
		
		return "test";
	}
	
	
}
