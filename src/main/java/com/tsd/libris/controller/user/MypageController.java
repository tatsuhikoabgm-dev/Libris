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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tsd.libris.domain.dto.auth.SessionUser;
import com.tsd.libris.domain.dto.user.PasswordChangeForm;
import com.tsd.libris.domain.dto.user.mypage.MypageEditForm;
import com.tsd.libris.domain.dto.user.mypage.MypageRegisterForm;
import com.tsd.libris.domain.dto.user.mypage.MypageRegisterForm.RegisterType;
import com.tsd.libris.domain.dto.validation.AccountUpdate;
import com.tsd.libris.domain.dto.validation.ProfileRegister;
import com.tsd.libris.domain.entity.UsersEntity;
import com.tsd.libris.mapper.user.UserMapper;
import com.tsd.libris.service.User.MypageService;

import lombok.RequiredArgsConstructor;




@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

	private final MypageService ms;
	private final SmartValidator sv; 
	private final UserMapper um;
	
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
		
//		if(session.getAttribute("SESSION_MYPAGE_EDIT") == null) {
		model.addAttribute("mypageEditForm",ms.getMypageEditForm(su.getUserId()));
//		}else {
//		model.addAttribute("mypageEditForm",session.getAttribute("SESSION_MYPAGE_EDIT"));
//		}
		
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
	
	
	/*マイページの編集
	 * 
	 */
	@PostMapping("/edit/complete")
	public String editMypage(HttpSession session,
														RedirectAttributes ra) {
		
		SessionUser su = (SessionUser)session.getAttribute("SESSION_USER");
		MypageEditForm form = (MypageEditForm)session.getAttribute("SESSION_MYPAGE_EDIT");
		
		
		if(form == null)
			return "redirect:/mypage";

		//UPDATE
		ms.updateProfile(form,su.getUserId());
		//sessionのフォーム削除
		session.removeAttribute("SESSION_MYPAGE_EDIT");
		//新しいユーザー情報の取得
		UsersEntity e = um.findByUserId(su.getUserId());
		//sessionに新しいユーザー情報をセット
		session.setAttribute("SESSION_USER", new SessionUser(e.getUserId(),e.getAuthority(),e.getDisplayName()));
		
		ra.addFlashAttribute("successMessage","変更が完了しました！");
		
		return "redirect:/mypage";
	}
	
	
	
	
	
	//*********************マイページ初回登録*********************
	
	
	
	
	
	/*マイページ初回登録フォーム
	 * 　　　　＆
	 * 登録済みのemail・表示名変更フォーム
	 */
	
	@GetMapping("/register")
	public String showMypageRegisterForm(@RequestParam(required = false) String fromConfirm,
																				Model model,
																				HttpSession session){
		
		SessionUser su = (SessionUser)session.getAttribute("SESSION_USER");
		
		if("true".equals(fromConfirm)) {
			MypageRegisterForm sessionForm = (MypageRegisterForm)session.getAttribute("SESSION_MYPAGE_REGISTER");
			if(sessionForm != null) {
				model.addAttribute("mypageRegisterForm",sessionForm);
				return "user/mypage/register";
			}else{
				return "redirect:/mypage";
			}
		}
		model.addAttribute("mypageRegisterForm",ms.getMypageRegisterForm(su.getUserId()));
		
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
		}else if(form.getRegisterType() == RegisterType.PROFILE) {
			sv.validate(form, result,ProfileRegister.class);
		}
		
		if(result.hasErrors()) {
			model.addAttribute("mypageRegisterForm",form);
			return "user/mypage/register";
		}
		
		session.setAttribute("SESSION_MYPAGE_REGISTER",form);
		model.addAttribute("form",form);
		
		return "/user/mypage/register-confirm";
	}
	
	//更新処理
	@PostMapping("/register/complete")
	public String registerMypage(HttpSession session,
								RedirectAttributes ra){
		
		MypageRegisterForm form = (MypageRegisterForm)session.getAttribute("SESSION_MYPAGE_REGISTER");
		SessionUser su = (SessionUser)session.getAttribute("SESSION_USER");
		
		if(form == null)
			return "redirect:/mypage";
		
		if(form.getRegisterType() == RegisterType.ACCOUNT) {
			
			ms.updateAccount(su.getUserId(), form);
			//新しいユーザー情報の取得
			UsersEntity e = um.findByUserId(su.getUserId());
			//sessionに新しいユーザー情報をセット
			session.setAttribute("SESSION_USER", new SessionUser(e.getUserId(),e.getAuthority(),e.getDisplayName()));
			session.removeAttribute("SESSION_MYPAGE_REGISTER");
			ra.addFlashAttribute("successMessage","変更が完了しました");
			return "redirect:/mypage/register";
			
		}else if(form.getRegisterType() == RegisterType.PROFILE) {
			
			ms.registerProfile(su.getUserId(), form);
		}
		
		//新しいユーザー情報の取得
			UsersEntity e = um.findByUserId(su.getUserId());
			//sessionに新しいユーザー情報をセット
			session.setAttribute("SESSION_USER", new SessionUser(e.getUserId(),e.getAuthority(),e.getDisplayName()));
			session.removeAttribute("SESSION_MYPAGE_REGISTER");
			ra.addFlashAttribute("successMessage","変更が完了しました");
			return "redirect:/mypage";
	}
	
	

	//*********************パスワード変更*********************
	
	@GetMapping("/password")
	public String showePasswordForm(@RequestParam String returnTo ,Model model) {

		model.addAttribute("passwordChangeForm",new PasswordChangeForm());
		model.addAttribute("returnTo",returnTo);
		return "/user/mypage/password";
	}
	
	
	@PostMapping("/password")
	public String changePassword(@RequestParam String returnTo,
																@Valid@ModelAttribute PasswordChangeForm form,
																BindingResult result,
																HttpSession session,
																Model model,
																RedirectAttributes ra){
		
		SessionUser su = (SessionUser)session.getAttribute("SESSION_USER");
		
		if(!ms.matchesPassword(su.getUserId(), form))
			result.rejectValue("oldPassword", null, "パスワードが誤っています");
		
		if(!ms.validatePassword(form))
			result.rejectValue("passwordConfirm", null, "パスワードが一致していません");

		if(result.hasErrors())
			return "/user/mypage/password";
		
		
		ms.changePassword(su.getUserId(), form);
		ra.addFlashAttribute("successMessage","パスワードを変更しました");
		
		/*/mypageで登録済み判定してリダイレクトしてるからFlashが渡せなくて・・・
		 * マジで超ドはまったんだけど
		 * 結局しょっぱなからhidden繋げてここまで至るｗ
		 * だせぇｗｗ
		 */
		return "mypage".equals(returnTo)
        														? "redirect:/mypage"
        														: "redirect:/mypage/register";
		
	}
	
	
	
}
