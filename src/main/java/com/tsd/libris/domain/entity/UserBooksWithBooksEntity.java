package com.tsd.libris.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBooksWithBooksEntity {

	private String uuid;
	private Long bId;
	private String thumbnailLink;
	
}
