package com.tsd.libris.domain.dto.user.mypage;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MypageDto {
	
	//表示用のためバリデーションはかけません
	
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
	private String loginId;

}
