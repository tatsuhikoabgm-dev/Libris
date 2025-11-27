package com.tsd.libris.domain.entity;

import java.time.LocalDateTime;

import com.tsd.libris.domain.enums.UserBookReadingStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBooksEntity {

	private Long id;
	private Long userId;
	private Long bookId;
	private UserBookReadingStatus status;
	private Integer rating;
	private String review;
	private LocalDateTime finishedAt;
	private LocalDateTime reviewUpdatedAt;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
}
