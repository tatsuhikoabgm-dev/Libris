package com.tsd.libris.controller.books;

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
import com.tsd.libris.domain.dto.books.BookSearchForm;
import com.tsd.libris.domain.dto.books.BookSearchPageDto;
import com.tsd.libris.domain.dto.books.UserBookRegisterForm;
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
	
	
	
	/*書籍詳細画面
	 * 
	 */
	@GetMapping("/detail/{googleVolumeId}")
	public String getMethodName(@PathVariable("googleVolumeId") String googleVolumeId,
													Model model,
													HttpSession session) {
		
		SessionUser sessionUser = (SessionUser)session.getAttribute("SESSION_USER");
		
		model.addAttribute("page",bs.getBookDetailPage(sessionUser.getUserId(), googleVolumeId)); 
		model.addAttribute("form",new UserBookRegisterForm(googleVolumeId,null));
		
		return "/books/detail";
	}
	
	
	/*本棚に登録
	 * 
	 */
	@PostMapping("/register")
	public String saveUserBook(@ModelAttribute("form") UserBookRegisterForm form,
								BindingResult result,
								Model model,
								HttpSession session
								) {
		SessionUser sessionUser = (SessionUser)session.getAttribute("SESSION_USER");
		bs.saveUserBook(sessionUser.getUserId(), form);
		model.addAttribute("msg","☆" + form.getReadingStatus().getLabel() + "で登録しました☆");		
		
		return "redirect:/books/detail/" + form.getGoogleVolumeId();
	}
	
	
	
	
	
	
}
