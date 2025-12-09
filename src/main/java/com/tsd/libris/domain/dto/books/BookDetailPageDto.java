package com.tsd.libris.domain.dto.books;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailPageDto {

	/*書籍詳細画面のページDTO
	 * 
	 */
	
	private BookDetailViewDto book;
	private List<BookReviewDto> reviews;
//	private UserBookRegisterForm form;
	private boolean myBookExists;
	private String myBookStatus;
	
}
