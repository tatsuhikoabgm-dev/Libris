package com.tsd.libris.domain.dto.auth;

import com.tsd.libris.domain.enums.UserAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionUser {

	/*session格納用のDTOのため
	 * バリデーションはかけません
	 */
	
	
	Long userId;
	UserAuthority authority;
	String displayName;
	
	
}
