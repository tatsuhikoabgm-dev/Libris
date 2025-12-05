package com.tsd.libris.domain.dto.books;

import com.tsd.libris.domain.enums.UserBookReadingStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBookRegisterForm {
	
	/*書籍詳細画面
	 * 本棚に登録する時のフォーム
	 * POST信用できない問題がありますが
	 * 必ずデフォルト値を入れて対応します
	 */
	
	private String googleVolumeId;
	private UserBookReadingStatus readingStatus;

}
