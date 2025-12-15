package com.tsd.libris.service.User;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.tsd.libris.domain.dto.user.mypage.MypageDto;
import com.tsd.libris.domain.dto.user.mypage.MypageEditConfirmDto;
import com.tsd.libris.domain.dto.user.mypage.MypageEditForm;
import com.tsd.libris.domain.entity.UserWithProfileEntity;
import com.tsd.libris.mapper.user.UserProfilesMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MypageService {

	private final UserProfilesMapper upm;

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
	public void validateRegister(MypageEditForm form,
			BindingResult result) {

		if (!form.getEmail().equals(form.getEmailConfirm()))
			result.rejectValue("emailConfirm", null, "メールアドレスが一致しません");

	}//validateRegister
	
	
	
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
	
	
	
	public void updateProfile(MypageEditForm form) {
		
		
		
	}
	
	
	
	
	
	
	
	

}
