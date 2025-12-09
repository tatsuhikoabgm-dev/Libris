package com.tsd.libris.mapper.userbooks;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tsd.libris.domain.entity.UserBooksEntity;
import com.tsd.libris.domain.entity.UserBooksWithUserEntity;

@Mapper
public interface UserBookMapper {

	UserBooksEntity findByUserIdAndBookId(@Param("userId") Long userId,@Param("bookId") Long bookId);
	List<UserBooksWithUserEntity> findReviewsByBookId(@Param("bookId")Long bookId);
	String findUserBookStatus(@Param("userId")Long userId,@Param("bookId")Long bookId);
	Integer insertUserBook(UserBooksEntity entity);
	Integer updateUserBookStatus(UserBooksEntity entity);
	Integer updateUserBookReview(UserBooksEntity entity);
//	UserBooksEntity findReviewByBookIdAndUserId(Long bookId,Long userId);
	
}
