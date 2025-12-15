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

import com.tsd.libris.domain.dto.auth.SessionUser;
import com.tsd.libris.domain.dto.user.mypage.MypageEditConfirmDto;
import com.tsd.libris.domain.dto.user.mypage.MypageEditForm;
import com.tsd.libris.service.User.MypageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

	private final MypageService ms;
	
	/*マイページの表示
	 * 
	 */
	@GetMapping("/")
	public String showMyPage(Model model,
							HttpSession session) {
		
		SessionUser su =(SessionUser)session.getAttribute("SESSION_USER");
		
		model.addAttribute("mypage",ms.getUserInfo(su.getUserId()));
		
		return "/user/mypage/mypage";
	}
	
	
	/*マイページ編集画面
	 * 
	 */
	@GetMapping("/edit")
	public String showUserEditForm(Model model,
									HttpSession session) {
		
		SessionUser su = (SessionUser)session.getAttribute("SESSION_USER");
		
		if(model.getAttribute("mypageEditForm") == null)
		model.addAttribute("mypageEditForm",ms.getMypageEditForm(su.getUserId()));
		
		return "/user/mypage/edit";
	}
	
	/*入力確認画面
	 * 
	 */
	@PostMapping("/edit/confirm")
	public String showEditConfirm(@Valid @ModelAttribute MypageEditForm form,
									BindingResult result,
									HttpSession session,
									Model model) {
		
		ms.validateRegister(form, result);
		
		if(result.hasErrors()) {
			model.addAttribute("mypageEditForm",form);
			return "/user/mypage/edit";
		}
		
		model.addAttribute("confirm",ms.toConfirmDto(form));
		session.setAttribute("SESSION_MYPAGE_EDIT", form);
		
		return "/user/mypage/edit-confirm";
	}//showEditConfirm
	
	
	
	/*入力を修正する場合
	 * フォームを復元してあげる
	 */
	@PostMapping("/edit/back")
	public String backToEdit(@ModelAttribute MypageEditConfirmDto form,
									Model model,
									HttpSession session,
									RedirectAttributes ra){
		
		if(session.getAttribute("SESSION_MYPAGE_EDIT")==null) {
			return "redirect:user/mypage/edit";
		}
			
		model.addAttribute("mypageEditForm",session.getAttribute("SESSION_MYPAGE_EDIT"));
		session.removeAttribute("SESSION_MYPAGE_EDIT");
		
		return "user/mypage/edit";
	}//backToRegister()
	
	
	
}
