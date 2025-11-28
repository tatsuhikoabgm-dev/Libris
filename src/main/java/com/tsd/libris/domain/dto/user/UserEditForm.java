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
public class UserEditForm {

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

	@NotBlank
	@Size(max=25)
	private String building;

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
	
}
