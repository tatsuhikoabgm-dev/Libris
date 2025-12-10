package com.tsd.libris.mapper.userbooks;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tsd.libris.domain.entity.UserBooksEntity;
import com.tsd.libris.domain.entity.UserBooksWithUserEntity;
import com.tsd.libris.domain.enums.UserBookReadingStatus;

@Mapper
public interface UserBookMapper {

	UserBooksEntity findById(@Param("id")Long id);
	UserBooksEntity findByUserIdAndBookId(@Param("userId") Long userId,@Param("bookId") Long bookId);
	List<UserBooksWithUserEntity> findReviewsByBookId(@Param("bookId")Long bookId);
	UserBooksEntity findReviewById(@Param("id")Long id);
	String findUserBookStatus(@Param("id")Long id);
	Integer insertUserBook(UserBooksEntity entity);
	Integer updateUserBookStatus(Long id,UserBookReadingStatus status);
	Integer updateUserBookReview(String review,Long id);
	
}
