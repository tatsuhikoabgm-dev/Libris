package com.tsd.libris.controller.books;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tsd.libris.domain.dto.books.BookSearchForm;
import com.tsd.libris.domain.dto.books.BookSearchPageDto;
import com.tsd.libris.service.books.BooksService;

import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BooksController {
	
	//DI
  private final BooksService bs;
	
	/*書籍検索画面
	 * 検索用フォームの表示
	 */
	@GetMapping("/search")
	public String showSearchForm(@RequestParam(defaultValue = "1")int page,
																Model model,
																HttpSession session) {
		
		if(session.getAttribute("SESSION_BOOK_SEARCH_FORM") ==null) {
			model.addAttribute("form",new BookSearchForm());
			return  "books/search";
		}
		
		BookSearchPageDto pageDto = (BookSearchPageDto)session.getAttribute("SESSION_BOOK_SEARCH_PAGING");
		BookSearchForm form = (BookSearchForm)session.getAttribute("SESSION_BOOK_SEARCH_FORM");
		
		if(pageDto.getPage() == page) {
		//ページの復元
			model.addAttribute("form",session.getAttribute("SESSION_BOOK_SEARCH_FORM"));
			model.addAttribute("books",session.getAttribute("SESSION_BOOK_SEARCH_RESULTS"));
			model.addAttribute("page",session.getAttribute("SESSION_BOOK_SEARCH_PAGING"));
			return  "books/search";
		}
		
		/*すべてを司る神
		 * 全部呼ぶ君
		 */
		System.out.println(page);
		bs.getTotalItems(session, form, page);
		model.addAttribute("form",session.getAttribute("SESSION_BOOK_SEARCH_FORM"));
		model.addAttribute("books",session.getAttribute("SESSION_BOOK_SEARCH_RESULTS"));
		model.addAttribute("page",session.getAttribute("SESSION_BOOK_SEARCH_PAGING"));
		
		return  "books/search";
	}
	
	
	/*書籍検索画面
	 * 検索結果の表示と検索条件の復元
	 */
	@PostMapping("/search")
	public String searchBooks(@Valid @ModelAttribute BookSearchForm form,
															BindingResult result,
															Model model,
															HttpSession session) {
		
			//検索条件が不足していた場合
			if(bs.hasSearchCondition(form)) {
				model.addAttribute("errorMessage","フリーワード・タイトル・著者・ISBNのいずれかを入力してください");
				model.addAttribute("form",form);
				return "books/search";
			}
			
		/*すべてを司る神
		 * 全部呼ぶ君
		 */	
		bs.getTotalItems(session, form, 1);
			
		return "redirect:/books/search";
	}
	
	
	
}
