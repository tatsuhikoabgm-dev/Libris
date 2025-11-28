package com.tsd.libris.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBooksWithBooksEntity {

	private UserBooksEntity userBooks;
	private BooksEntity books;
	
}
