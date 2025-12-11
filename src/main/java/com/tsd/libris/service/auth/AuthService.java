package com.tsd.libris.service.auth;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.tsd.libris.domain.dto.auth.SessionUser;
import com.tsd.libris.domain.dto.user.LoginForm;
import com.tsd.libris.domain.entity.UsersEntity;
import com.tsd.libris.mapper.user.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UserMapper um;
	
	public SessionUser authenticate(LoginForm form) {
		
		UsersEntity e = um.findByLoginId(form.getLoginId());
		
//		System.out.println(e);
		if(e == null) return null;
		
		if(BCrypt.checkpw(form.getPassword(),e.getPasswordHash())) {
			return new SessionUser(e.getUserId(),e.getAuthority(),e.getDisplayName());
		}
		System.out.println("111");
		return null;
			
	}//authenticate

}
