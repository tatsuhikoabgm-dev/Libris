package com.tsd.libris.service.User;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.tsd.libris.domain.dto.user.PasswordChangeForm;
import com.tsd.libris.domain.dto.user.mypage.MypageDto;
import com.tsd.libris.domain.dto.user.mypage.MypageEditConfirmDto;
import com.tsd.libris.domain.dto.user.mypage.MypageEditForm;
import com.tsd.libris.domain.dto.user.mypage.MypageRegisterForm;
import com.tsd.libris.domain.entity.UserProfilesEntity;
import com.tsd.libris.domain.entity.UserWithProfileEntity;
import com.tsd.libris.domain.entity.UsersEntity;
import com.tsd.libris.mapper.user.UserMapper;
import com.tsd.libris.mapper.user.UserProfilesMapper;
import com.tsd.libris.service.common.DbAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MypageService {

	private final UserProfilesMapper upm;
	private final UserMapper um;

	/*マイページ用のDTO作るよ
	 * 
	 */

	public MypageDto getUserInfo(Long userId) {

		UserWithProfileEntity e = upm.findUserProfileByUserId(userId);

		return new MypageDto(e.getLastName(),
				e.getFirstName(),
				e.getLastNameKana(),
				e.getFirstNameKana(),
				e.getPostalCode(),
				e.getPrefecture(),
				e.getCity(),
				e.getTown(),
				e.getAddressNumber(),
				e.getBuilding(),
				e.getBirthday(),
				e.getPhoneNumber(),
				e.getEmail(),
				e.getDisplayName(),
				e.getLoginId());
	}//getUserInfo
	
	
	
	

	//ユーザー情報編集画面のページDTO作成
	public MypageEditForm getMypageEditForm(Long userId) {

		UserWithProfileEntity e = upm.findUserProfileByUserId(userId);

		return new MypageEditForm(e.getLastName(),
				e.getFirstName(),
				e.getLastNameKana(),
				e.getFirstNameKana(),
				e.getPostalCode(),
				e.getPrefecture(),
				e.getCity(),
				e.getTown(),
				e.getAddressNumber(),
				e.getBuilding(),
				e.getBirthday(),
				e.getPhoneNumber(),
				e.getEmail(),
				e.getEmail(),
				e.getDisplayName());
	}

	//メアドの一致確認
	public void validateEdit(MypageEditForm form,
			BindingResult result) {

		if (!form.getEmail().equals(form.getEmailConfirm()))
			result.rejectValue("emailConfirm", null, "メールアドレスが一致しません");

	}//validateEdit
	
	
	
	/*入力確認画面
	 *初回入力フォームを入力確認用フォームに変換 
	 */
	public MypageEditConfirmDto toConfirmDto(MypageEditForm form) {
		
		
		 return new MypageEditConfirmDto(form.getLastName(),
				 						form.getFirstName(),
				 						form.getLastNameKana(),
				 						form.getFirstNameKana(),
				 						form.getPostalCode(),
				 						form.getPrefecture(),
				 						form.getCity(),
				 						form.getTown(),
				 						form.getAddressNumber(),
				 						form.getBuilding(),
				 						form.getBirthday(),
				 						form.getPhoneNumber(),
				 						form.getEmail(),
				 						form.getDisplayName());
	}//UserRegisterConfirmDto
	
	
	@Transactional
	public void updateProfile(MypageEditForm form,Long userId) {
		
		
		/*変更対象しか値いれないよ！！
		 * 
		 */
		int arart = um.updateUser(new UsersEntity(userId,
																	null,
																	null,
																	null,
																	form.getDisplayName(),
																	null,
																	null,
																	null));
		
		DbAssert.assertSingleUpdate(arart, "updateProfile.updateUser");
		
		/*変更不可項目については
		 * Mapper.xmlで項目から外してるけど
		 * ここでもnullにしておこうかな！！
		 */
		arart =upm.updateProfile(new UserProfilesEntity(userId,
																								null,
																								null,
																								null,
																								null,
																								form.getPostalCode(),
																								form.getPrefecture(),
																								form.getCity(),
																								form.getTown(),
																								form.getAddressNumber(),
																								form.getBuilding(),
																								null,
																								form.getPhoneNumber(),
																								form.getEmail(),
																								null,null
																								));
		
		DbAssert.assertSingleUpdate(arart, "updateProfile.updateProfile");
		
		
	}//updateProfile
	
	
	//初回登録か登録済みか判定するよん
	public boolean existsProfileByUserId(Long userId) {
		
		if(upm.findUserProfileByUserId(userId).getLastName()!=null)
			return true;
		
		return false;

	}//existsProfileByUserId
	
	
	public MypageRegisterForm getMypageRegisterForm(Long userId) {
		

		MypageRegisterForm form = new MypageRegisterForm();
		UserWithProfileEntity upe = upm.findUserProfileByUserId(userId);
		
		//null項目多いからsetter
		form.setEmail(upe.getEmail());
		form.setEmailConfirm(upe.getEmail());
		form.setDisplayName(upe.getDisplayName());
		
		return form;
		
		
	}
	
	
	//メアドの一致確認
	public void validateRegister(MypageRegisterForm form,
																BindingResult result) {
		
		if (!form.getEmail().equals(form.getEmailConfirm()))
			result.rejectValue("emailConfirm", null, "メールアドレスが一致しません");

	}//validateRegister
	
	
	
	/*メアド、表示名の変更
	 * マイページ初回登録用
	 */
	@Transactional
	public void updateAccount(Long userId,MypageRegisterForm form) {
		
		int arart = um.updateUser(new UsersEntity(userId,
										null,null,null,form.getDisplayName(),null,null,null));
		
		
		DbAssert.assertSingleUpdate(arart, "updateAccount.updateUser");
		
		upm.updateEmail(userId,form.getEmail());
		
		DbAssert.assertSingleUpdate(arart, "updateAccount.updateEmail");
	}
	
	
	/*プロフィール登録
	 * マイページ初回登録用
	 */
	@Transactional
	public void registerProfile(Long userId,MypageRegisterForm form) {
		
		int arart = upm.updateProfileForRegister(new UserProfilesEntity(userId,
													form.getLastName(),
													form.getFirstName(),
													form.getLastNameKana(),
													form.getFirstNameKana(),
													form.getPostalCode(),
													form.getPrefecture(),
													form.getCity(),
													form.getTown(),
													form.getAddressNumber(),
													form.getBuilding(),
													form.getBirthday(),
													form.getPhoneNumber(),
													null,null,null));
		
		DbAssert.assertSingleUpdate(arart, "registerProfile.updateProfileForRegister");
		
	}
	
	//パスワードの確認欄の一致チェック
	public boolean validatePassword(PasswordChangeForm form) {
		
		return form.getPassword().equals(form.getPasswordConfirm());
		
	}
	
	//現在のパスワードチェック
	public boolean matchesPassword(Long userId,PasswordChangeForm form) {
		
		String ph = um.findByUserId(userId).getPasswordHash();
		return BCrypt.checkpw(form.getOldPassword(), ph);
		
	}
	
	//パスワードのUPDATE
	@Transactional
	public void changePassword(Long userId,PasswordChangeForm form) {
		
		String passwordHash = BCrypt.hashpw(form.getPassword(), BCrypt.gensalt());
		int arart = um.updatePassword(userId,passwordHash);
		
		DbAssert.assertSingleUpdate(arart, "changePassword");
	}
	

}
