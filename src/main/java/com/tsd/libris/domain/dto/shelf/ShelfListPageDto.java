package com.tsd.libris.domain.dto.shelf;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShelfListPageDto {
	
	/*本棚表示画面のページDTO
	 * 
	 */
	
	private String displayName;
	private List<ShelfBookSummaryDto> books;

}
