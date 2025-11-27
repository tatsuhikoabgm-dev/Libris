package com.tsd.libris.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserBookReadingStatus {

	WANT("WANT","欲しい"),
	READING("READING","読んでる"),
	COMPLETED("COMPLETED","読了"),
	PENDING("PENDING","積読");
	
	private final String code;
	private final String label;
	
	
}
