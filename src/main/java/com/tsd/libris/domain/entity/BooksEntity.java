package com.tsd.libris.domain.entity;

import java.time.LocalDateTime;

import com.tsd.libris.domain.enums.BookStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BooksEntity {

	private Long id;
	private String googleVolumeId;
	private String isbn;
	private String title;
	private String authors;
	private String publisher;
	private String publishedDate;
	private String description;
	private String thumbnailLink;
	private String previewLink;
	private BookStatus status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	
}
