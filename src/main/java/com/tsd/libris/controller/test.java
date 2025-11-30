package com.tsd.libris.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tsd.libris.domain.entity.BooksEntity;
import com.tsd.libris.domain.entity.UserBooksEntity;
import com.tsd.libris.domain.entity.UserProfilesEntity;
import com.tsd.libris.domain.entity.UsersEntity;
import com.tsd.libris.domain.enums.UserBookReadingStatus;
import com.tsd.libris.mapper.book.BooksMapper;
import com.tsd.libris.mapper.shelf.ShelfMapper;
import com.tsd.libris.mapper.user.UserMapper;
import com.tsd.libris.mapper.user.UserProfilesMapper;
import com.tsd.libris.mapper.userbooks.UserBookMapper;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class test {

	private final UserMapper um;
	private final UserProfilesMapper upm;
	private final BooksMapper bm;
	private final UserBookMapper ubm;
	private final ShelfMapper sm;
	
	
	@GetMapping("/test")
	public String testMeth() {
		
		//UserMapperのテスト
		System.out.println("findByLoginId : " + um.findByLoginId("miyu08"));
		System.out.println("existsByLoginId : " + um.existsByLoginId("seiya48"));
		System.out.println("findByUserId : " + um.findByUserId(42l));
		UsersEntity test = new UsersEntity();
//		test.setLoginId("test03");
//		test.setPasswordHash("test02");
//		test.setDisplayName("テスト");
//		System.out.println("insertUser : " + um.insertUser(test));
		test.setDisplayName("てってって");
		test.setUserId(52L);
		System.out.println("updateUser : " + um.updateUser(test));
		test.setUserId(54L);
		test.setPasswordHash("あああああああ");
		System.out.println("updatePassword : " + um.updatePassword(test));
		
		//UserprofilesMapperのテスト
		System.out.println("findUserProfileByUserId : " + upm.findUserProfileByUserId(51L));
		UserProfilesEntity upentity = new UserProfilesEntity();
		upentity.setUserId(51L);
		upentity.setLastName("陳");
		upentity.setFirstName("やんちょる");
		upentity.setLastNameKana("チン");
		upentity.setFirstNameKana("ヤンチョル");
		upentity.setPostalCode("1234567");
		upentity.setPrefecture("テスト");
		upentity.setCity("テスト");
		upentity.setTown("テスト");
		upentity.setAddressNumber("1-1-1");
		upentity.setBuilding("テスト2501");
		upentity.setBirthday(LocalDate.of(2026,11,30));
		upentity.setPhoneNumber("07012345678");
		upentity.setEmail("chin@yancyor.com");
//		System.out.println(upm.insertProfile(upentity));
		System.out.println(upm.updateProfile(upentity));
		
		
		//BooksMapperのテスト
		System.out.println(bm.findByGoogleVolumeId("vol1069"));
		System.out.println(bm.findBookDetailByBookId(99L));
		
		BooksEntity bentity = new BooksEntity();
		bentity.setGoogleVolumeId("test0001");
		bentity.setIsbn("999999999");
		bentity.setTitle("テストテスト");
		bentity.setAuthors("テスト出版");
		bentity.setPublisher("テスト");
		bentity.setPublishedDate("2026-11-28");
		bentity.setDescription("超楽しいマジびっくり");
		bentity.setThumbnailLink("http://www.pogikapedfn");
		bentity.setPreviewLink("http://www.miofgjaewoige");
		
//		System.out.println(bm.insertBook(bentity));
		
		
		//UserBookMapperのテスト
		System.out.println("findByUserIdAndBookId : " + ubm.findByUserIdAndBookId(15L, 24L));
		System.out.println("findReviewsByBookId : " + ubm.findReviewsByBookId(21L));
		System.out.println("findUserBookStatus : " + ubm.findUserBookStatus(24L, 21L));
		
		UserBooksEntity ubentity = new UserBooksEntity();
		
		ubentity.setUserId(33L);
		ubentity.setBookId(33L);
		ubentity.setStatus(UserBookReadingStatus.WANT);
		ubentity.setRating(4);
		ubentity.setReview("超おもろーーー！！");
		
//		System.out.println(ubm.insertUserBook(ubentity));
		
		ubentity.setUserId(null);
		ubentity.setBookId(null);
		ubentity.setRating(null);
		ubentity.setReview(null);
		ubentity.setId(203L);
		ubentity.setStatus(UserBookReadingStatus.PENDING);
		
		System.out.println("updateUserBookStatus : " + ubm.updateUserBookStatus(ubentity));
		
		ubentity.setReview("うひゃああああああああ");
		
		System.out.println("updateUserBookReview : " + ubm.updateUserBookReview(ubentity));
		
		System.err.println("findShelfBooksByUserId : " + sm.findShelfBooksByUserId(22L));
		System.err.println("findShelfBooksByUserId : " + sm.findShelfBooksByUserIdAndStatus(22L, UserBookReadingStatus.COMPLETED));
		
		
		
		return "test"; 
	}
	
	
}
