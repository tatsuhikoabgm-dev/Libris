package com.tsd.libris.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserAuthority {

	USER("USER", "一般ユーザー"),
	ADMIN("ADMIN", "管理者");

	private final String code;
	private final String label;

}
