package com.tsd.libris.domain.entity;

import java.time.LocalDateTime;

import com.tsd.libris.domain.enums.UserAuthority;
import com.tsd.libris.domain.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersEntity {
	
	private Long userId;
	private String loginId;
	private String passwordHash;
	private UserAuthority authority;
	private String displayName;
	private UserStatus status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	

}
