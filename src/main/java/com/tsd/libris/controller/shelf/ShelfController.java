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

import com.tsd.libris.domain.dto.shelf.ShelfListPageDto;
import com.tsd.libris.domain.dto.shelf.StatusEditForm;
import com.tsd.libris.service.shelf.ShelfService;

import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
@RequestMapping("/shelf")
public class ShelfController {
	
	private final ShelfService ss;
	
	
	/*本棚表示画面
	 * 
	 */

	@GetMapping("/list")
	public String shelf(@RequestParam(defaultValue = "ALL") String status,
	                    Model model,
	                    HttpSession session) {
		
		ShelfListPageDto dto = ss.getShelfPage(1L,"test",status);
		model.addAttribute("page",dto);
		System.out.println(dto);
		
	    return "shelf/list";
	}

	
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Long id,
															Model model,
															HttpSession session) {
		
		model.addAttribute("page",ss.getEditShelfPage(id));
		model.addAttribute("formStatus",ss.getStatusEditForm(id));
		model.addAttribute("formReview",ss.getReviewEditForm(id));
		
		
		return "shelf/edit";
	}
	
	
	@PostMapping("/edit/status")
	public String editUserBookStatus(@Valid @ModelAttribute StatusEditForm form,
																		BindingResult result) {
		
		System.out.println(form.getId());
		ss.editUserBookStatus(form);
//		ss.editUserReview(form);

		return "redirect:/shelf/edit/" + form.getId();	
	}
	
	
	
	
}
