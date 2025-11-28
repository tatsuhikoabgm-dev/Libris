package com.tsd.libris.mapper.user;

import org.apache.ibatis.annotations.Mapper;

import com.tsd.libris.domain.entity.UserProfilesEntity;

@Mapper
public interface UserProfilesMapper {

	
	UserProfilesEntity findUserProfileByUserId(Long userId);
	int insertProfile(UserProfilesEntity entity);
	int updateProfile(UserProfilesEntity entity);
	
	
}
