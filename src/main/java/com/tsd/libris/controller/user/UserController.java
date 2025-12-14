package com.tsd.libris.controller.user;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tsd.libris.domain.dto.user.UserRegisterConfirmDto;
import com.tsd.libris.domain.dto.user.UserRegisterForm;
import com.tsd.libris.service.User.UserService;
import com.tsd.libris.service.auth.AuthService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
	private final UserService us;
	private final AuthService as;
	
	/*会員登録画面
	 * 登録フォームの表示
	 */
	@GetMapping("/register")
	public String showUserRegisterForm(Model model) {
		
//		if(model.getAttribute("userRegisterForm") == null)
		model.addAttribute("userRegisterForm",new UserRegisterForm());
		
		return "/user/register/register";
	}//showUserRegisterForm
	
	/*登録内容確認画面
	 * 
	 */
	@PostMapping("/register/confirm")
	public String showRegisterConfirm(@Valid @ModelAttribute UserRegisterForm form,
										BindingResult result,
										Model model,
										HttpSession session){
		
		us.validateRegister(form, result);
		us.existsByLoginId(form, result);
		
		if(result.hasErrors()) {
			model.addAttribute("userRegisterForm",form);
			return "/user/register/register";
		}
		
		model.addAttribute("confirm",us.toConfirmDto(form));
		session.setAttribute("SESSION_USER_REGISTER",form);
		
		return "/user/register/confirm";
	}//showRegisterConfirm
	
	
	/*入力を修正する場合
	 * フォームを復元してあげる
	 */
	@PostMapping("/register/back")
	public String backToRegister(@ModelAttribute UserRegisterConfirmDto form,
									Model model,
									HttpSession session,
									RedirectAttributes ra){
		
		//↓セッション切れ確認用↓
//		session.removeAttribute("SESSION_USER_REGISTER");
		
		if(session.getAttribute("SESSION_USER_REGISTER")==null) {
			ra.addFlashAttribute("msg","セッションが切れました。もう一度入力してください。");
			return "redirect:/user/register";
		}
			
		model.addAttribute("userRegisterForm",session.getAttribute("SESSION_USER_REGISTER"));
		session.removeAttribute("SESSION_USER_REGISTER");
		
		return "/user/register/register";
	}//backToRegister()
	
	
	/*ユーザー登録
	 * 
	 */
	@PostMapping("/register/complete")
	public String registerUser(HttpSession session,
								Model model) {
		
		Long userId =us.registerUser((UserRegisterForm)session.getAttribute("SESSION_USER_REGISTER"));
		session.removeAttribute("SESSION_USER_REGISTER");
		session.setAttribute("SESSION_USER", us.createSessionUser(userId));
		
		return "redirect:/user/welcome";
	}
	
	/*ウェルカムページの表示
	 * 
	 */
	@GetMapping("/welcome")
	public String showWelcome() {
		return "/user/register/welcome";
	}
	
	

}
