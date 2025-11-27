package com.tsd.libris.domain.dto.user;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {

	@NotBlank
	String loginId;
	@NotBlank
	String password;
	
}
