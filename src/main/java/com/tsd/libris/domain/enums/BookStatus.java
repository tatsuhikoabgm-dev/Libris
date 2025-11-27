package com.tsd.libris.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookStatus {

	ACTIVE("ACTIVE","有効"),
	INACTIVE("INACTIVE","無効");
	
	
	private final String code;
	private final String label;
	
}
