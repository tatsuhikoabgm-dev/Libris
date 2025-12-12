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
		
		if(form.getLoginId() == null || form.getLoginId().isBlank()) return null;
		if(form.getPassword() == null ||  form.getPassword().isBlank()) return null;
		
		UsersEntity e = um.findByLoginId(form.getLoginId());
		
		if(e == null) return null;
		
		if(BCrypt.checkpw(form.getPassword(),e.getPasswordHash())) {
			return new SessionUser(e.getUserId(),e.getAuthority(),e.getDisplayName());
		}

		return null;
			
	}//authenticate

}
