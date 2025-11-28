package com.tsd.libris.mapper.userbooks;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tsd.libris.domain.entity.UserBooksEntity;
import com.tsd.libris.domain.enums.UserBookReadingStatus;

@Mapper
public interface UserBookMapper {

	UserBooksEntity findByUserIdAndBookId(Long userId,Long bookId);
	List<UserBooksEntity> findReviewsByBookId(Long bookId);
	UserBookReadingStatus findUserBookStatus(Long userId,Long bookId);
	int insertUserBook(UserBooksEntity entity);
	int updateUserBookStatus(UserBooksEntity entity);
	int updateUserBookReview(UserBooksEntity entity);
//	UserBooksEntity findReviewByBookIdAndUserId(Long bookId,Long userId);
	
}
