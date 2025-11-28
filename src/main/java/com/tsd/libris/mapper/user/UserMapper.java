package com.tsd.libris.mapper.user;

import org.apache.ibatis.annotations.Mapper;

import com.tsd.libris.domain.entity.UsersEntity;

@Mapper
public interface UserMapper {
	
	UsersEntity findByLoginId(String loginId);
	boolean existsByLoginId(String loginId);
	UsersEntity findByUserId(Long userId);
	int insertUser(UsersEntity entity);
	int updateUser(UsersEntity entity);
	int updatePassword(UsersEntity entity);

}
