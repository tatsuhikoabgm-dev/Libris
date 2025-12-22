package com.tsd.libris.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tsd.libris.exception.ApplicationException;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class test {

	@GetMapping("/test")
	public String getMethodName() {
		return "test";
	}
	
	
	
	@GetMapping("/test/error")
	public String testerror() {
	    throw new ApplicationException(
	        ApplicationException.Type.INVALID_REQUEST,
	        "テスト例外"
	    );
	}
	
}
