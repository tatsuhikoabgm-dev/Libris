package com.tsd.libris.mapper.shelf;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tsd.libris.domain.entity.UserBooksWithBooksEntity;

@Mapper
public interface ShelfMapper {

	
	List<UserBooksWithBooksEntity> findShelfBooksByUserId(Long userId);
	List<UserBooksWithBooksEntity> findShelfBooksByUserIdAndStatus(Long userId,String status);
	
	
	
}
