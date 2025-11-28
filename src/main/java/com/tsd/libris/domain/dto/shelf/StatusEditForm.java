package com.tsd.libris.domain.dto.shelf;

import com.tsd.libris.domain.enums.UserBookReadingStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusEditForm {

	/*本棚詳細画面
	 * 本棚ステータス変更用Form
	 * POST信用できない問題がありますが
	 * 必ずデフォルト値を入れて対応します
	 */
	
	private Long id;
	private UserBookReadingStatus status;
}
