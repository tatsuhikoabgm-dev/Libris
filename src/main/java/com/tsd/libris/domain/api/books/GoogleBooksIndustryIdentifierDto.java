package com.tsd.libris.domain.api.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoogleBooksIndustryIdentifierDto {

	/*外部API用
	 * バリデーションはかけません
	 */
	
	private String type;
	private String identifier;
	
	
}
