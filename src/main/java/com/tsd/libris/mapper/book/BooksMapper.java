package com.tsd.libris.mapper.book;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tsd.libris.domain.entity.BooksEntity;

@Mapper
public interface BooksMapper {

	BooksEntity findByGoogleVolumeId(@Param("googleVolumeId") String googleVolumeId);
	BooksEntity findBookDetailByBookId(@Param("bookId") Long bookId);
	Integer insertBook(BooksEntity entity);
	
}
