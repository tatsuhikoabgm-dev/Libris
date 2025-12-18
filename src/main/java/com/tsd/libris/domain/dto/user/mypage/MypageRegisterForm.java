package com.tsd.libris.domain.dto.user.mypage;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import com.tsd.libris.domain.dto.validation.AccountUpdate;
import com.tsd.libris.domain.dto.validation.ProfileRegister;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MypageRegisterForm {

	private RegisterType registerType;
	
	public enum RegisterType{
		PROFILE,
		ACCOUNT
	}
	
	//カード①
	
	@NotBlank(message = "入力してください",groups = ProfileRegister.class)
	@Size(max = 50,groups = ProfileRegister.class)
	private String lastName;

	@NotBlank(message = "入力してください",groups = ProfileRegister.class)
	@Size(max = 50,groups = ProfileRegister.class)
	private String firstName;

	//文字化けの可能性を回避するため全角カタカナの仕様
	@NotBlank(message = "入力してください",groups = ProfileRegister.class)
	@Size(max = 50,groups = ProfileRegister.class)
	@Pattern(regexp = "^[\\u30A0-\\u30FFー]+$")
	private String lastNameKana;

	//文字化けの可能性を回避するため全角カタカナの仕様
	@NotBlank(message = "入力してください",groups = ProfileRegister.class)
	@Size(max = 50,groups = ProfileRegister.class)
	@Pattern(regexp = "^[\\u30A0-\\u30FFー]+$")
	private String firstNameKana;

	//ハイフンなし
	//サービスでハイフンとるなら桁数かえてもよし
	@NotBlank(groups = ProfileRegister.class)
	@Pattern(regexp = "[0-9]{7}",groups = ProfileRegister.class,message = "ハイフン無し７文字で入力してください")
	private String postalCode;

	@NotBlank(message = "入力してください",groups = ProfileRegister.class)
	@Size(max = 50,groups = ProfileRegister.class)
	private String prefecture;

	@NotBlank(message = "入力してください",groups = ProfileRegister.class)
	@Size(max = 50,groups = ProfileRegister.class)
	private String city;

	@NotBlank(message = "入力してください",groups = ProfileRegister.class)
	@Size(max = 50,groups = ProfileRegister.class)
	private String town;

	@NotBlank(message = "入力してください",groups = ProfileRegister.class)
	@Size(max = 50,groups = ProfileRegister.class)
	private String addressNumber;

	@Size(max = 100,groups = ProfileRegister.class)
	private String building;

	@NotNull(message = "入力してください",groups = ProfileRegister.class)
	@Past
	private LocalDate birthday;

	//ハイフンなし
	//サービスでハイフンとるなら桁数かえてもよし
	@NotBlank
	@Pattern(message = "ハイフン無し１０～１１文字入力してください",regexp = "^[0-9]{10,11}$",groups = ProfileRegister.class)
	private String phoneNumber;
	
	
	//カード②
	
	@NotBlank(message = "入力してください" ,groups = AccountUpdate.class)
	@Email(message = "入力形式に誤りがあります",groups = AccountUpdate.class)
	@Size(max=191, message = "１９１文字以内で入力してください",groups = AccountUpdate.class)
	private String email;
	
	//フィールドマッチかけるからこれでOK
	@NotBlank(message = "入力してください",groups = AccountUpdate.class)
	private String emailConfirm;
	
	@Size(max=50 , message ="５０文字以内で入力してください",groups = AccountUpdate.class)
	private String displayName;
	

}
