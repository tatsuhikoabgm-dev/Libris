package com.tsd.libris.service.books;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tsd.libris.domain.api.books.GoogleBooksApiDto;
import com.tsd.libris.domain.dto.books.BookSearchForm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BooksService {
	
//	private final GoogleBooksConverter converter;
	
	/*BookSearchFormのチェック
	 * 検索条件の不足をチェック
	 */
	public boolean hasSearchCondition(BookSearchForm form) {
		
		if(form.getKeyword().isBlank() && form.getTitle().isBlank() && form.getAuthors().isBlank() && form.getIsbn().isBlank()) {
			return true;
		}return false;
		
	}
	
	/*検索条件からエンドポイントを作成し
	 * 返ってきたJSOをDTOに詰める
	 */
	public void searchBooks(BookSearchForm form){
	
		//エンドポイントの生成
		String url = "https://www.googleapis.com/books/v1/volumes?projection=lite&maxResults=40&q=";
		if(form.getIsbn().isBlank()) {
		if(!form.getKeyword().isBlank()) url += form.getKeyword();
		if(!form.getTitle().isBlank()) url += "+intitle:" + form.getTitle();
		if(!form.getAuthors().isBlank()) url += "+inauthor:" + form.getAuthors();
		if(!form.getPublisher().isBlank()) url += "+inpublisher:" + form.getAuthors();
		url += "&orderBy=" + form.getOrderBy();
		url += "&printType=" + form.getPrintType();
		}else {
		//ISBN入力自は単体検索-
			url = "https://www.googleapis.com/books/v1/volumes?projection=lite&q=isbn:" + form.getIsbn();
		}
		
		//テスト出力
		System.out.println(url);	
		System.out.println(form);
		
		RestTemplate rest = new RestTemplate();
		ResponseEntity<GoogleBooksApiDto> results = rest.getForEntity(url, GoogleBooksApiDto.class);
		
		//テスト出力
		System.out.println(results.getBody());
		
//		converter.toSearchResultDto(results.getBody().getClass());
		
		
		
	}//searchBooks
	
	

}
