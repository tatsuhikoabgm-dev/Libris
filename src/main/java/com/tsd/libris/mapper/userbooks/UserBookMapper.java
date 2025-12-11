package com.tsd.libris.mapper.userbooks;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tsd.libris.domain.entity.UserBooksEntity;
import com.tsd.libris.domain.entity.UserBooksWithUserEntity;
import com.tsd.libris.domain.enums.UserBookReadingStatus;

@Mapper
public interface UserBookMapper {

	UserBooksEntity findById(@Param("uuid")String uuid);
	UserBooksEntity findByUserIdAndBookId(@Param("userId") Long userId,@Param("bookId") Long bookId);
	List<UserBooksWithUserEntity> findReviewsByBookId(@Param("bookId")Long bookId);
	UserBooksEntity findReviewById(@Param("uuid")String uuid);
	String findUserBookStatus(@Param("uuid")String uuid);
	Integer insertUserBook(UserBooksEntity entity);
	Integer updateUserBookStatus(String uuid,UserBookReadingStatus status);
	Integer updateUserBookReview(UserBooksEntity entity);
	
	Integer uuid(@Param("id")Long id,@Param("uuid") String uuid);
	
}
