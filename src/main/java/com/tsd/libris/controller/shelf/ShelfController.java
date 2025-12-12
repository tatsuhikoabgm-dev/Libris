package com.tsd.libris.controller.shelf;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tsd.libris.domain.dto.auth.SessionUser;
import com.tsd.libris.domain.dto.shelf.ReviewEditForm;
import com.tsd.libris.domain.dto.shelf.ShelfListPageDto;
import com.tsd.libris.domain.dto.shelf.StatusEditForm;
import com.tsd.libris.service.shelf.ShelfService;

import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
@RequestMapping("/shelf")
public class ShelfController {
	
	private final ShelfService ss;
//	private final UserBookMapper ubm;
	
	/*本棚表示画面
	 * 
	 */
	
	@GetMapping("/list")
	public String shelf(@RequestParam(defaultValue = "ALL") String status,
	                    Model model,
	                    HttpSession session) {
		
		SessionUser sessionUser = (SessionUser)session.getAttribute("SESSION_USER");
		
		ShelfListPageDto dto = ss.getShelfPage(sessionUser.getUserId(),sessionUser.getDisplayName(),status);
		model.addAttribute("page",dto);
		System.out.println(dto);
		model.addAttribute("status",status);
		
	    return "shelf/list";
	}

	
	@GetMapping("/edit/{uuid}")
	public String showEditForm(@PathVariable String uuid,
															Model model) {

		model.addAttribute("page",ss.getEditShelfPage(uuid));
		model.addAttribute("formStatus",ss.getStatusEditForm(uuid));
		model.addAttribute("formReview",ss.getReviewEditForm(uuid));
		
		
		return "shelf/edit";
	}
	
	
	@PostMapping("/edit/status")
	public String editUserBookStatus(@Valid @ModelAttribute StatusEditForm form,
																		BindingResult result) {
		
		ss.editUserBookStatus(form);

		return "redirect:/shelf/edit/" + form.getUuid();	
	}
	
	@PostMapping("/edit/review")
	public String editUserReview(@Valid @ModelAttribute ReviewEditForm form,
																	BindingResult result) {
		
		ss.editUserReview(form);
			
		return "redirect:/shelf/edit/" + form.getUuid();
	}
	
	
	
}
