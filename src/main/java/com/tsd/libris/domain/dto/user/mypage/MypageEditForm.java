package com.tsd.libris.domain.dto.user.mypage;

import java.time.LocalDate;

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
public class MypageEditForm {
	
	/*UPDATE時は変更不可箇所については
	 * バリデーションをかけていないため
	 * POSTで変更されている可能性があるので
	 * 変更可能箇所だけのUPDATEをすること！！
	 * 
	 */

	//変更不可のためバリデーション無し
	private String lastName;
	private String firstName;
	private String lastNameKana;
	private String firstNameKana;
	
	//ハイフンなし
	//サービスでハイフンとるなら桁数かえてもよし
	@NotBlank(message = "入力してください")
	@Pattern(regexp="[0-9]{7}",message = "ハイフン無し数字７文字で入力してください")
	private String postalCode;
	
	@NotBlank(message = "入力してください")
	@Size(max=50,message = "５０文字以内で入力してください")
	private String prefecture;
	
	@NotBlank(message = "入力してください")
	@Size(max=50,message = "５０文字以内で入力してください")
	private String city;
	
	@NotBlank(message = "入力してください")
	@Size(max=50,message = "５０文字以内で入力してください")
	private String town;
	
	@NotBlank(message = "入力してください")
	@Size(max=50,message = "５０文字以内で入力してください")
	private String addressNumber;

	@NotBlank(message = "入力してください")
	@Size(max=191,message = "１９１文字以内で入力してください")
	private String building;
	
	//表示だけのためバリデーション無し
	private LocalDate birthday;

	//ハイフンなし
	//サービスでハイフンとるなら桁数かえてもよし
	@NotBlank(message = "入力してください")
	@Pattern(regexp = "^[0-9]{10,11}$",message = "ハイフン無し数字１１文字以内で入力してください")
	private String phoneNumber;
	
	@NotBlank(message = "入力してください")
	@Email(message = "入力形式に誤りがあります")
	@Size(max=191,message = "１９１文字以内で入力してください")
	private String email;
	
	//フィールドマッチかけるからこれでOK
	@NotBlank(message = "入力してください")
	private String emailConfirm;
	
	@Size(max=50,message = "５０文字以内で入力してください")
	private String displayName;
	
}
