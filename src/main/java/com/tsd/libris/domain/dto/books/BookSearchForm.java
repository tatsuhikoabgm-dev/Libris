package com.tsd.libris.domain.dto.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookSearchForm {

	/*書籍検索用のフォーム
	 * APIの仕様がわからないから
	 * バリデーションはかけませｎ
	 */
	
	private String keyword;
	private String title;
	private String authors;
	private String publisher;
	private String isbn;
	private String printType;
	private String orderBy;
	
	
}
