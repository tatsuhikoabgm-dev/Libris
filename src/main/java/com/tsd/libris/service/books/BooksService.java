package com.tsd.libris.service.books;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tsd.libris.domain.api.books.GoogleBooksApiDto;
import com.tsd.libris.domain.converter.GoogleBooksConverter;
import com.tsd.libris.domain.dto.books.BookListPageDto;
import com.tsd.libris.domain.dto.books.BookSearchForm;
import com.tsd.libris.domain.dto.books.BookSearchResultDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BooksService {
	
//	private final BooksService bs;
	private final GoogleBooksConverter converter;
	
	
	/*BookSearchFormのチェック
	 * 検索条件の不足をチェック
	 */
	public boolean hasSearchCondition(BookSearchForm form) {
		
		if(form.getKeyword().isBlank() && form.getTitle().isBlank() && form.getAuthors().isBlank() && form.getIsbn().isBlank()) {
			return true;
		}return false;
		
	}
	
	
	/*検索条件からエンドポイントを作成し
	 * 返ってきたJSONをAPI用DTOに詰めて返す
	 */
	public GoogleBooksApiDto searchBooks(BookSearchForm form,int page){
	
		//エンドポイントの生成
		String url = "https://www.googleapis.com/books/v1/volumes?projection=lite&maxResults=20&q=";
		if(form.getIsbn().isBlank()) {
		if(!form.getKeyword().isBlank()) url += form.getKeyword().replaceAll("\\s+|\u3000+","+");
		if(!form.getTitle().isBlank()) url += "+intitle:" + form.getTitle();
		if(!form.getAuthors().isBlank()) url += "+inauthor:" + form.getAuthors();
		if(!form.getPublisher().isBlank()) url += "+inpublisher:" + form.getAuthors();
		url += "&orderBy=" + form.getOrderBy();
		url += "&printType=" + form.getPrintType();
//		url += "&startIndex=" + (page-1)*20;
		}else {
		//ISBN入力自は単体検索-
			url = "https://www.googleapis.com/books/v1/volumes?projection=lite&q=isbn:" + form.getIsbn();
		}
		
		//テスト出力
		System.out.println(url);	
		System.out.println("form : " + form);
		
		//API叩くぞ！！おりゃ！！
		RestTemplate rest = new RestTemplate();
		ResponseEntity<GoogleBooksApiDto> results = rest.getForEntity(url, GoogleBooksApiDto.class);
		
		return results.getBody();
		
	}//searchBooks
	
	/*復元のため検索条件と検索結果をSessionに入れるよ
	 * 
	 */
	public void saveSearchSession(HttpSession session,BookSearchForm form,List<BookSearchResultDto> books ) {
		
		session.setAttribute("SESSION_BOOK_SEARCH_FORM", form);
		session.setAttribute("SESSION_BOOK_SEARCH_RESULTS", books);
		
	}
	
	
	/*ページDTOを作るよ
	 * 
	 */
	public GoogleBooksApiDto  createBookSearchPage(HttpSession session,BookSearchForm form,int page) {
		
		BookListPageDto pageDto = new BookListPageDto();
		GoogleBooksApiDto results = searchBooks(form, page);
		
//		if(results.getTotalItems()>=100) pageDto.setTotalItems(100);
//		else pageDto.setTotalItems(results.getTotalItems());
//		pageDto.setPage(page);
//		pageDto.setMaxPages((int)Math.ceil((double)pageDto.getTotalItems()/20));
//		pageDto.setBooks(converter.toSearchResultDto(results));
//		pageDto.setForm(form);
		
		/*とりあえずページDTOじゃなくて
		 * GoogleBooksApiDtoをテンプレートで表示させるだけにするわ！
		 */
		saveSearchSession(session,form,converter.toSearchResultDto(results));
		
		return results;
		
			
		
	}

}
