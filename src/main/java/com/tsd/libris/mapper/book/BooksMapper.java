package com.tsd.libris.mapper.book;

import org.apache.ibatis.annotations.Mapper;

import com.tsd.libris.domain.entity.BooksEntity;

@Mapper
public interface BooksMapper {

	BooksEntity findByGoogleVolumeId(String volumeId);
	BooksEntity findBookDetailByBookId(Long bookId);
	int insertBook(BooksEntity entity);
	
}
