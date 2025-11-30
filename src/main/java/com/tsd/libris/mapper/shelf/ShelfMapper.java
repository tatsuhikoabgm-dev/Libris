package com.tsd.libris.mapper.shelf;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tsd.libris.domain.entity.UserBooksWithBooksEntity;
import com.tsd.libris.domain.enums.UserBookReadingStatus;

@Mapper
public interface ShelfMapper {

	
	List<UserBooksWithBooksEntity> findShelfBooksByUserId(@Param("userId") Long userId);
	List<UserBooksWithBooksEntity> findShelfBooksByUserIdAndStatus(@Param("userId") Long userId,@Param("status") UserBookReadingStatus status);
	
	
	
}
