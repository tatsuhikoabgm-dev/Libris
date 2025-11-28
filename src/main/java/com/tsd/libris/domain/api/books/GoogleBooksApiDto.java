package com.tsd.libris.domain.api.books;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoogleBooksApiDto {

	/*外部API用
	 * バリデーションはかけません
	 */
	
	private int totalItems;
	private List<GoogleBooksItemDto> items;
	
	
	
}
