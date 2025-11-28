package com.tsd.libris.domain.dto.shelf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEditPageDto {

	/*本棚詳細画面ページDTO
	 *
	 */
	
	private ShelfBookDetailDto book;
	private ReviewEditForm form;
	private boolean hasReview;
	
	
}
