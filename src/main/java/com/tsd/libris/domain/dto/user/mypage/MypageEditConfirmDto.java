package com.tsd.libris.domain.dto.user.mypage;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MypageEditConfirmDto {

	/*UPDATE時は変更不可箇所については
	 * バリデーションをかけていないため
	 * POSTで変更されている可能性があるので
	 * 変更可能箇所だけのUPDATEをすること！！
	 * 
	 * session無いのデータでUPDATEするように！！！
	 * 
	 */
	
	private String lastName;
	private String firstName;
	private String lastNameKana;
	private String firstNameKana;
	private String postalCode;
	private String prefecture;
	private String city;
	private String town;
	private String addressNumber;
	private String building;
	private LocalDate birthday;
	private String phoneNumber;
	private String email;
	private String displayName;
	
}
