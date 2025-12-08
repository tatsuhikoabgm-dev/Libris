package com.tsd.libris.domain.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBooksWithUserEntity {
	
	private String displayName;
	private Integer rating;
	private String review;
	private LocalDateTime reviewUpdatedAt;
}
