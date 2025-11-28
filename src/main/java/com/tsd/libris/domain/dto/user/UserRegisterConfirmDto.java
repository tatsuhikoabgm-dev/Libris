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
	
	private String lastMame;
	private String firstName;
	private String lastNameKana;
	private String firstNameKana;
	private String postalCode;
	private String prefecture;
	private String city;
	private String town;
	private String addressNumber;
	private String building;
	private String birthday;
	private String phoneNumber;
	private String email;
	private String displayName;
	private String loginId;

}
