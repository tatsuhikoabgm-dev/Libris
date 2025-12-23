package com.tsd.libris.controller.advice;

import java.net.BindException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.tsd.libris.exception.ApplicationException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class,
			BindException.class,
			IllegalArgumentException.class })
	public String handleRequestBindException(Model model) {

		model.addAttribute("errorMessage", "不正な値です！！変なことしないでください！ｗ");
		return "error/common";
	}

	@ExceptionHandler(ApplicationException.class)
	public String handleApplicationException(ApplicationException e,
			Model model) {

		if (e.getType() == ApplicationException.Type.INVALID_REQUEST) {
			model.addAttribute("errorMessage", "不正な値です！！変なことしないでください！ｗ");
		} else {
			model.addAttribute("errorMessage", "システムエラーです。もう一度試してみてください！");
		}

		return "error/common";
	}

	@ExceptionHandler(Exception.class)
	public String handleOtherException(Model model) {

		model.addAttribute("errorMessage", "不正な値です！！変なことしないでください！ｗ");
		return "error/common";
	}

}
