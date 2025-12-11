package com.tsd.libris.controller.auth;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tsd.libris.domain.dto.user.LoginForm;
import com.tsd.libris.service.auth.AuthService;

import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthService as;
	
	@GetMapping("/login")
	public String showLoginForm(Model model){
		
		model.addAttribute("loginForm",new LoginForm());
		
		return "/auth/login";
	}
	
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute LoginForm form,
											BindingResult result,
											Model model,
											HttpSession session) {
		
		if(result.hasErrors()) {
			model.addAttribute("loginForm",form);
			return "/auth/login";
		}
		
		if(as.authenticate(form)==null) {
			model.addAttribute("loginForm",form);
			return "/auth/login";
		}
		
		session.setAttribute("SESSION_USER",as.authenticate(form));
		
		return "redirect:/books/search";
	}
	

}
