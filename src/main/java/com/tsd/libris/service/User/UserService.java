package com.tsd.libris.service.User;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.tsd.libris.domain.dto.auth.SessionUser;
import com.tsd.libris.domain.dto.user.UserRegisterConfirmDto;
import com.tsd.libris.domain.dto.user.UserRegisterForm;
import com.tsd.libris.domain.entity.UserProfilesEntity;
import com.tsd.libris.domain.entity.UsersEntity;
import com.tsd.libris.mapper.user.UserMapper;
import com.tsd.libris.mapper.user.UserProfilesMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper um;
	private final UserProfilesMapper upm;
	
	/*初回入力での確認欄一致チェック
	 * 
	 */
	public void validateRegister(UserRegisterForm form,
												BindingResult result) {
		
		if(!form.getEmail().equals(form.getEmailConfirm()))
			result.rejectValue("emailConfirm",null,"メールアドレスが一致しません");
		
		if(!form.getPassword().equals(form.getPasswordConfirm()))
			result.rejectValue("passwordConfirm",null,"パスワードが一致しません");
		
	}//validateRegister
	
	
	
	/*ユーザーIDの存在チェック
	 * 
	 */
	public void existsByLoginId(UserRegisterForm form,
									BindingResult result) {
		
		if(um.existsByLoginId(form.getLoginId()) != null)
			result.rejectValue("loginId",null,"このユーザーＩＤは既に使われています");
		
	}//existsByLoginId
	
	
	
	/*入力確認画面
	 *初回入力フォームを入力確認用フォームに変換 
	 */
	public UserRegisterConfirmDto toConfirmDto(UserRegisterForm form) {
		
		//パスワードは伏字にする
		String password = "";
		 for(int i =1 ; i <= form.getPassword().length();i++) {
			 password += "●";
		 }
		
		 return new UserRegisterConfirmDto(form.getEmail(),
											form.getDisplayName(),
											form.getLoginId(),
											password);
	}//UserRegisterConfirmDto
	
	
	//BCryptでハッシュ化するよん
	public String hashPassword(String password){
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	/*ユーザー情報をテーブルにINSERT
	 * usersとuser_profileにINSERTだからね！！
	 */
	@Transactional
	public Long registerUser(UserRegisterForm form) {
		
		UsersEntity ue = new UsersEntity(null,
											form.getLoginId(),
											hashPassword(form.getPassword()),
											null,
											form.getDisplayName(),
											null,
											null,
											null);
		
		um.insertUser(ue);
		
		//項目多すぎてしかたなくsetterにしたｗ
		UserProfilesEntity upe = new UserProfilesEntity();
		upe.setUserId(ue.getUserId());
		upe.setEmail(form.getEmail());
		
		upm.insertProfile(upe);
		
		return ue.getUserId();
		
	}//registerUser
	
	
	
	/*会員登録後にsessionを入れるよ
	 * 
	 */
	
	
	public SessionUser createSessionUser(Long userId) {
		
		UsersEntity e = um.findByUserId(userId);
		
		return new SessionUser(e.getUserId(),
								e.getAuthority(),
								e.getDisplayName());
	}//createSessionUser
	
	
	
	
}
