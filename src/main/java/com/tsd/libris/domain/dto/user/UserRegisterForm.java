package com.tsd.libris.domain.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterForm {
	
	//UX向上のためとりあえず機能を体験させられるだけの最低限に変更
//	@NotBlank
//	@Size(max=50)
//	private String lastName;
//	
//	@NotBlank
//	@Size(max=50)
//	private String firstName;
//	
//	//文字化けの可能性を回避するため全角カタカナの仕様
//	@NotBlank
//	@Size(max=50)
//	@Pattern(regexp = "^[\\u30A0-\\u30FFー]+$")
//	private String lastNameKana;
//	
//	//文字化けの可能性を回避するため全角カタカナの仕様
//	@NotBlank
//	@Size(max=50)
//	@Pattern(regexp = "^[\\u30A0-\\u30FFー]+$")
//	private String firstNameKana;
//	
//	//ハイフンなし
//	//サービスでハイフンとるなら桁数かえてもよし
//	@NotBlank
//	@Pattern(regexp="[0-9]{7}")
//	private String postalCode;
//	
//	@NotBlank
//	@Size(max=50)
//	private String prefecture;
//	
//	@NotBlank
//	@Size(max=50)
//	private String city;
//	
//	@NotBlank
//	@Size(max=50)
//	private String town;
//	
//	@NotBlank
//	@Size(max=50)
//	private String addressNumber;
//	
//	@Size(max=100)
//	private String building;
//	
//	@NotNull
//	@Past
//	private LocalDate birthday;
//	
//	//ハイフンなし
//	//サービスでハイフンとるなら桁数かえてもよし
//	@NotBlank
//	@Pattern(regexp = "^[0-9]{10,11}$")
//	private String phoneNumber;
//	
	@NotBlank(message = "入力してください")
	@Email(message = "入力形式に誤りがあります")
	@Size(max=191, message = "１９１文字以内で入力してください")
	private String email;
	
	//フィールドマッチかけるからこれでOK
	@NotBlank(message = "入力してください")
	private String emailConfirm;
	
	@Size(max=50 , message ="５０文字以内で入力してください")
	private String displayName;
	
	//半角英数字のみ
	@NotBlank(message ="入力してください")
	@Pattern(regexp = "^[a-zA-Z0-9]{4,50}$",
			message="４～５０文字の半角英数字で入力してください")
	private String loginId;
	
	//解析に７年かかる
	@NotBlank(message = "入力してください")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{8,16}$",
			message = "パスワードは大文字・小文字・数字を含む８～１６文字の半角英数字で入力してください")
	private String password;
	
	//フィールドマッチかけるからこれでOK
	@NotBlank(message = "入力してください")
	private String passwordConfirm;
	
}
