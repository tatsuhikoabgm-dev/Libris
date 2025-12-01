package com.tsd.libris.domain.converter;

import java.util.List;
import java.util.Optional;

import com.tsd.libris.domain.api.books.GoogleBooksApiDto;
import com.tsd.libris.domain.api.books.GoogleBooksVolumeInfoDto;
import com.tsd.libris.domain.dto.books.BookSearchResultDto;

/*APIDTOを内部DTOに変換するぞっ！！
 * 書籍検索画面と書籍詳細画面で使うロジックなので
 * メソッドに分けることにする
 */

public class GoogleBooksConverter {

	public List<BookSearchResultDto> toSearchResultDto(GoogleBooksApiDto results) {
		
		
		results.getItems().stream().map(item ->{
					
			GoogleBooksVolumeInfoDto v = item.getVolumeInfo();
			
			//著者のListを一つの文字列にするよっ
			String authors = Optional.ofNullable(v.getAuthors())
					.map(a-> String.join(",", a))
					.orElse("著者情報なし");
					
			
			
					
				});//.stream()
		
		
		
		
		
		
	}//toSearchResultDto
	
}
