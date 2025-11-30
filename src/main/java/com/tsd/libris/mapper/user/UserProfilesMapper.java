package com.tsd.libris.mapper.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tsd.libris.domain.entity.UserProfilesEntity;

@Mapper
public interface UserProfilesMapper {

	
	UserProfilesEntity findUserProfileByUserId(@Param("userId") Long userId);
	Integer insertProfile(UserProfilesEntity entity);
	Integer updateProfile(UserProfilesEntity entity);
	
	
}
