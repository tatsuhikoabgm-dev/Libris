package com.tsd.libris.domain.dto.shelf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShelfBookDetailDto {

	/*本棚詳細画面用ページDTO
	 * 
	 */
	
	private String bookId;
	private String title;
	private String authors;
	private String publishedDate;
	private String publisher;
	private String isbn;
	private String description;
	private String thumbnailLink;
	private String previewLink;
}
