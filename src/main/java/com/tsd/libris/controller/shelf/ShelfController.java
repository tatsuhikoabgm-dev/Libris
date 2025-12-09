package com.tsd.libris.controller.shelf;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tsd.libris.domain.dto.shelf.ShelfListPageDto;
import com.tsd.libris.service.shelf.ShelfService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/shelf")
public class ShelfController {
	
	private final ShelfService ss;
	
	
	/*本棚の表示
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

	
	
}
