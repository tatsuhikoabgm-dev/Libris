package com.tsd.libris.service.books;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tsd.libris.domain.api.books.GoogleBooksApiDto;
import com.tsd.libris.domain.api.books.GoogleBooksItemDto;
import com.tsd.libris.domain.converter.GoogleBooksConverter;
import com.tsd.libris.domain.dto.books.BookSearchForm;
import com.tsd.libris.domain.dto.books.BookSearchPageDto;
import com.tsd.libris.domain.dto.books.BookSearchResultDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BooksService {
	
	private final GoogleBooksConverter converter;
	
	
//******************書籍検索画面*****************	
	
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
	 * ページも対応してるよ！
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
		url += "&startIndex=" + (page-1)*20;
		}else {
		//ISBN入力自は単体検索-
			url = "https://www.googleapis.com/books/v1/volumes?projection=lite&q=isbn:" + form.getIsbn();
		}
		
		//テスト出力
		System.out.println(url);	
		System.out.println("form : " + form);
		
		//API叩くぞ！！おりゃ！！
		ResponseEntity<GoogleBooksApiDto> results = new RestTemplate().getForEntity(url, GoogleBooksApiDto.class);
		System.out.println("API叩いた！！");
		
		return results.getBody();
		
	}//searchBooks
	
	
	/*復元のため検索条件と検索結果をSessionに入れるよ
	 * 
	 */
	public void saveSearchSession(HttpSession session,BookSearchForm form,List<BookSearchResultDto> books,BookSearchPageDto page ) {
		
		session.setAttribute("SESSION_BOOK_SEARCH_FORM", form);
		session.setAttribute("SESSION_BOOK_SEARCH_RESULTS", books);
		session.setAttribute("SESSION_BOOK_SEARCH_PAGING", page);
		
	}
	
	
	/*ページDTOを作るよ
	 * 
	 */
	public BookSearchPageDto  createBookSearchPage(GoogleBooksApiDto results, int page) {
		
		BookSearchPageDto dto = new BookSearchPageDto();
		
		int totalItems;
				
		//ページネイション用のデータをセットする
		if(results.getTotalItems()>=80) totalItems = 80;
		else totalItems = results.getTotalItems();
		dto.setTotalItems(totalItems);
		dto.setPage(page);
		dto.setMaxPages((totalItems +19)/20);
		dto.setStartIndex((page-1)*20+1);
		dto.setEndIndex((page)*20);
		
		return dto;
		
	}//createBookSearchPage
	
	
	public void getTotalItems(HttpSession session,BookSearchForm form,int page) {
		
		GoogleBooksApiDto results = searchBooks(form, page);
		BookSearchPageDto pageDto = createBookSearchPage(results, page);
		saveSearchSession(session, form, converter.toSearchResultDto(results), pageDto);
		System.out.println(pageDto);
		
	}
	
	
	
	
	
	
	//******************書籍詳細画面*****************
	
	
	public void getBookDetailPage(String volumeId) {
		
		String url = "https://www.googleapis.com/books/v1/volumes/" + volumeId;
		
		ResponseEntity<GoogleBooksItemDto> rest= new RestTemplate().getForEntity(url,GoogleBooksItemDto.class);
		
		System.out.println(url);
		System.out.println(volumeId);
		System.out.println(rest.getBody());
		
		
	}
	
	
	
	
	
	
	
	
	
}
