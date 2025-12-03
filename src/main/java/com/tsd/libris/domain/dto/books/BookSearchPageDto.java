package com.tsd.libris.domain.dto.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookSearchPageDto {

	
	/*書籍検索画面のページDTO
	 * 
	 */
	
	private int totalItems; 
	private int page;
	private int maxPages; 
	private int startIndex; 
	private int endIndex;
	
}
