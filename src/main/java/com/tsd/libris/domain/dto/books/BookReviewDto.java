package com.tsd.libris.domain.dto.books;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookReviewDto {

	/*書籍詳細画面
	 * 本棚のレビューを入れるDTO
	 */
	
	private String displayName;
	private int rating;
	private String review;
	private LocalDateTime reviewedAt;
	
}
