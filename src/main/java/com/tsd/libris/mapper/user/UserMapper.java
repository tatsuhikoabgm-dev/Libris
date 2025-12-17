package com.tsd.libris.mapper.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tsd.libris.domain.entity.UsersEntity;

@Mapper
public interface UserMapper {
	
	UsersEntity findByLoginId(@Param("loginId") String loginId);
	String existsByLoginId(@Param("loginId") String loginId);
	UsersEntity findByUserId(@Param("userId") Long userId);
	Integer insertUser(UsersEntity entity);
	Integer updateUser(UsersEntity entity);
	Integer updatePassword(@Param("userId")Long UserId,@Param("passwordHash")String passwordHash);

}
