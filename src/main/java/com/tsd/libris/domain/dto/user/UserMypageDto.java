package com.tsd.libris.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMypageDto {
	
	//表示用のためバリデーションはかけません
	
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
