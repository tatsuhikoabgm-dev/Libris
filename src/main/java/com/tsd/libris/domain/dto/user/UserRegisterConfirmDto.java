package com.tsd.libris.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterConfirmDto {
	
	/*変更内容確認画面用
	 * session内の初回入力内容と照らすため
	 * バリデーションはかけません。
	 */
	
	private String email;
	private String displayName;
	private String loginId;
	private String password;

}
