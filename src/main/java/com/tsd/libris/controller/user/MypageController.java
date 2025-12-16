package com.tsd.libris.controller.user;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tsd.libris.domain.dto.auth.SessionUser;
import com.tsd.libris.domain.dto.user.mypage.MypageEditForm;
import com.tsd.libris.domain.dto.user.mypage.MypageRegisterForm;
import com.tsd.libris.domain.dto.user.mypage.MypageRegisterForm.RegisterType;
import com.tsd.libris.domain.dto.validation.AccountUpdate;
import com.tsd.libris.domain.dto.validation.ProfileRegister;
import com.tsd.libris.service.User.MypageService;

import lombok.RequiredArgsConstructor;




@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

	private final MypageService ms;
	private final SmartValidator sv; 
	
	/*マイページの表示
	 * 
	 */
	@GetMapping("")
	public String showMyPage(Model model,
							HttpSession session) {
		
		SessionUser su =(SessionUser)session.getAttribute("SESSION_USER");
		
		if(!ms.existsProfileByUserId(su.getUserId()))
			return "redirect:/mypage/register";
		
		model.addAttribute("mypage",ms.getUserInfo(su.getUserId()));
		
		return "/user/mypage/mypage";
	}
	
	
	/*マイページ編集画面
	 * 
	 */
	@GetMapping("/edit")
	public String showMypageEditForm(Model model,
									HttpSession session) {
		
		SessionUser su = (SessionUser)session.getAttribute("SESSION_USER");
		
		if(session.getAttribute("SESSION_MYPAGE_EDIT") == null) {
		model.addAttribute("mypageEditForm",ms.getMypageEditForm(su.getUserId()));
		}else {
		model.addAttribute("mypageEditForm",session.getAttribute("SESSION_MYPAGE_EDIT"));
		}
		
		return "/user/mypage/edit";
	}
	
	/*入力確認画面
	 * マイページ登録済み
	 */
	@PostMapping("/edit/confirm")
	public String showEditConfirm(@Valid @ModelAttribute MypageEditForm form,
									BindingResult result,
									HttpSession session,
									Model model) {
		
		ms.validateEdit(form, result);
		
		if(result.hasErrors()) {
			model.addAttribute("mypageEditForm",form);
			return "/user/mypage/edit";
		}
		
		model.addAttribute("confirm",ms.toConfirmDto(form));
		session.setAttribute("SESSION_MYPAGE_EDIT", form);
		
		return "/user/mypage/edit-confirm";
	}//showEditConfirm
	
	
	/*マイページの更新
	 * 
	 */
	@PostMapping("/edit/complete")
	public String editMypage(HttpSession session,
														RedirectAttributes ra) {
		
		SessionUser su = (SessionUser)session.getAttribute("SESSION_USER");
		MypageEditForm form = (MypageEditForm)session.getAttribute("SESSION_MYPAGE_EDIT");

		
		ms.updateProfile(form,su.getUserId());
		session.removeAttribute("SESSION_MYPAGE_EDIT");
		ra.addFlashAttribute("successMessage","変更が完了しました！");
		
		return "redirect:/mypage";
	}
	
	
	
	
	
	//*********************マイページ初回登録*********************
	
	
	
	
	
	/*マイページ初回登録フォーム
	 * 　　　　＆
	 * 登録済みのemail・表示名変更フォーム
	 */
	
	@GetMapping("/register")
	public String showMypageRegisterForm(Model model,
																				HttpSession session){
		
		SessionUser su = (SessionUser)session.getAttribute("SESSION_USER");
		
		if(session.getAttribute("SESSION_MYPAGE_REGISTER") == null) {
		model.addAttribute("mypageRegisterForm",ms.getMypageRegisterForm(su.getUserId()));
		}else {
		model.addAttribute("mypageRegisterForm",(MypageRegisterForm)session.getAttribute("SESSION_MYPAGE_REGISTER"));
		}
		
		return "user/mypage/register";
	}
	
	/*入力確認画面
	 * 
	 */
	@PostMapping("/register/confirm")
	public String showRegisterConfirm(@ModelAttribute MypageRegisterForm form,
																	BindingResult result,
																	HttpSession session,
																	Model model) {
		
		if(form.getRegisterType() == RegisterType.ACCOUNT) {
			sv.validate(form,result,AccountUpdate.class);
			ms.validateRegister(form, result);
			System.out.println(form);
		}else if(form.getRegisterType() == RegisterType.PROFILE) {
			sv.validate(form, result,ProfileRegister.class);
			System.out.println(form);
		}
		
		if(result.hasErrors()) {
			model.addAttribute("mypageRegisterForm",form);
			return "user/mypage/register";
		}
		
		session.setAttribute("SESSION_MYPAGE_REGISTER",form);
		model.addAttribute("form",form);
		
		return "/user/mypage/register-confirm";
	}
	
	
	@PostMapping("/register/complete")
	public String registerMypage(HttpSession session,
																RedirectAttributes ra){
		
		
		
		return "redirect:/mypage";
	}
	
	
	
	
	
	
	
	
	
}
