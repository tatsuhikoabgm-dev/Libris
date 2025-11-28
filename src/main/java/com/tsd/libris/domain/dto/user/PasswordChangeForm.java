package com.tsd.libris.domain.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeForm {

	/* 半角英数字
	 *小文字・大文字・数字含むBCryptは
	 *解析に７年かかるらしい
	 */
	
	@NotBlank
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{8,16}$",
	message = 
	"パスワードは大文字・小文字・数字を含む８～１６文字の半角英数字で入力してください")
	private String oldPassword;
	
	@NotBlank
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{8,16}$",
	message = 
	"パスワードは大文字・小文字・数字を含む８～１６文字の半角英数字で入力してください")
	private String password;
	
	@NotBlank
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{8,16}$",
	message = 
	"パスワードは大文字・小文字・数字を含む８～１６文字の半角英数字で入力してください")
	private String passwordConfirm;
	
}
