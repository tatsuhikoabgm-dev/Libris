package com.tsd.libris.domain.dto.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailViewDto {

	/*書籍詳細画面
	 * 詳細APIからこれに変換
	 */
	
	private String googleVolumeId;
	private Long id;
	private String title;
	private String authors;
	private String publishedDate;
	private String publisher;
	private String isbn;
	private String description;
	private String thumbnailLink;
	private String previewLink;
	
	
}
