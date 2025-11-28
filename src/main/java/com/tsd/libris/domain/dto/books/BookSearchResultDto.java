package com.tsd.libris.domain.dto.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookSearchResultDto {

	/*APIのJSONをこれに変換する
	 * 
	 */
	
	
	String googleVolumeId;
	String title;
	String authors;
	String publisher;
	String thumbnailLink;
	
}
