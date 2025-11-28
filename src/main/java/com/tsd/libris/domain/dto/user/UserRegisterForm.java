package com.tsd.libris.domain.dto.user;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterForm {
	
	@NotBlank
	@Size(max=12)
	private String lastName;
	
	@NotBlank
	@Size(max=12)
	private String firstName;
	
	//文字化けの可能性を回避するため全角カタカナの仕様
	@NotBlank
	@Size(max=12)
	@Pattern(regexp = "^[\\u30A0-\\u30FFー]+$")
	private String lastNameKana;
	
	//文字化けの可能性を回避するため全角カタカナの仕様
	@NotBlank
	@Size(max=12)
	@Pattern(regexp = "^[\\u30A0-\\u30FFー]+$")
	private String firstNameKana;
	
	//ハイフンなし
	//サービスでハイフンとるなら桁数かえてもよし
	@NotBlank
	@Pattern(regexp="[0-9]{7}")
	private String postalCode;
	
	@NotBlank
	@Size(max=12)
	private String prefecture;
	
	@NotBlank
	@Size(max=12)
	private String city;
	
	@NotBlank
	@Size(max=12)
	private String town;
	
	@NotBlank
	@Size(max=12)
	private String addressNumber;
	
	@Size(max=25)
	private String building;
	
	@NotNull
	@Past
	private LocalDate birthday;
	
	//ハイフンなし
	//サービスでハイフンとるなら桁数かえてもよし
	@NotBlank
	@Pattern(regexp = "^[0-9]{10,11}$")
	private String phoneNumber;
	
	@NotBlank
	@Email
	@Size(max=191)
	private String email;
	
	//フィールドマッチかけるからこれでOK
	@NotBlank
	private String emailConfirm;
	
	@Size(max=12)
	private String displayName;
	
	//半角英数字のみ
	@NotBlank
	@Size(min = 4, max = 50)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String loginId;
	
	//解析に７年かかる
	@NotBlank
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{8,16}$",
			message = "パスワードは大文字・小文字・数字を含む８～１６文字の半角英数字で入力してください")
	private String password;
	
	//フィールドマッチかけるからこれでOK
	@NotBlank
	private String passwordConfirm;
	
}
