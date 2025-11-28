package com.tsd.libris.domain.api.books;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoogleBooksVolumeInfoDto {

	/*外部API用
	 * バリデーションはかけません
	 */
	
	private String title;
	private List<String> authors;
	private List<GoogleBooksIndustryIdentifierDto> DtoindustryIdentifiers;
	private String publishedDate;
	private String description;
	private GoogleBooksImageLinksDto imageLinks;
	private String previewLink;


}
