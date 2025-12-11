package com.tsd.libris.domain.dto.shelf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEditForm {

	/*本棚詳細画面
	 * レビュー投稿用Form
	 * POST信用できない問題がありますが
	 * 改善案件にします
	 */
	
	private String uuid;
	private Integer rating;
	private String review;
	
}
