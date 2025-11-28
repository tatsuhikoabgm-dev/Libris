package com.tsd.libris.domain.dto.books;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookListPageDto {

	
	/*書籍検索画面のページDTO
	 * 
	 */
	
	private List<BookSearchResultDto> results;
	private int currentPage;
	private int totalPages;
	private int pageSize;
	private boolean hasNext;
	private boolean hasPrevious;
	private String message;
	
}
