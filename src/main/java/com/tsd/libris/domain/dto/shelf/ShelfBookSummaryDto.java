package com.tsd.libris.domain.dto.shelf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShelfBookSummaryDto {

	/*本棚表示画面
	 * 一冊分のDTO
	 */
	
	
	private Long id;
	private String thumbnailLink;
	
	
}
