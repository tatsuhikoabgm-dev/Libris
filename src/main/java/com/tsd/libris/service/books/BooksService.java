package com.tsd.libris.service.books;

import java.util.List;
import java.util.UUID;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tsd.libris.domain.api.books.GoogleBooksApiDto;
import com.tsd.libris.domain.api.books.GoogleBooksItemDto;
import com.tsd.libris.domain.converter.GoogleBooksConverter;
import com.tsd.libris.domain.dto.books.BookDetailPageDto;
import com.tsd.libris.domain.dto.books.BookDetailViewDto;
import com.tsd.libris.domain.dto.books.BookReviewDto;
import com.tsd.libris.domain.dto.books.BookSearchForm;
import com.tsd.libris.domain.dto.books.BookSearchPageDto;
import com.tsd.libris.domain.dto.books.BookSearchResultDto;
import com.tsd.libris.domain.dto.books.UserBookRegisterForm;
import com.tsd.libris.domain.entity.BooksEntity;
import com.tsd.libris.domain.entity.UserBooksEntity;
import com.tsd.libris.domain.entity.UserBooksWithUserEntity;
import com.tsd.libris.domain.enums.UserBookReadingStatus;
import com.tsd.libris.mapper.book.BooksMapper;
import com.tsd.libris.mapper.userbooks.UserBookMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BooksService {
	
	private final GoogleBooksConverter converter;
	private final BooksMapper bm;
	private final UserBookMapper ubm;
	
	
//******************書籍検索画面*****************	
	
	/*BookSearchFormのチェック
	 * 検索条件の不足をチェック
	 */
	public boolean hasSearchCondition(BookSearchForm form) {
		
		if(form.getKeyword().isBlank() && form.getTitle().isBlank() && form.getAuthors().isBlank() && form.getIsbn().isBlank()) {
			return true;
		}return false;
		
	}
	
	
	/*検索条件からエンドポイントを作成し
	 * 返ってきたJSONをAPI用DTOに詰めて返す
	 * ページも対応してるよ！
	 */
	public GoogleBooksApiDto searchBooks(BookSearchForm form,int page){
	
		//エンドポイントの生成
		String url = "https://www.googleapis.com/books/v1/volumes?projection=lite&maxResults=20&langRestrict=ja&q=";
		if(form.getIsbn().isBlank()) {
		if(!form.getKeyword().isBlank()) url += form.getKeyword().replaceAll("\\s+|\u3000+","+");
		if(!form.getTitle().isBlank()) url += "+intitle:" + form.getTitle();
		if(!form.getAuthors().isBlank()) url += "+inauthor:" + form.getAuthors();
		if(!form.getPublisher().isBlank()) url += "+inpublisher:" + form.getAuthors();
		url += "&orderBy=" + form.getOrderBy();
		url += "&printType=" + form.getPrintType();
		url += "&startIndex=" + (page-1)*20;
		}else {
		//ISBN入力自は単体検索-
			url = "https://www.googleapis.com/books/v1/volumes?projection=lite&q=isbn:" + form.getIsbn();
		}
		
		
		//API叩くぞ！！おりゃ！！
		ResponseEntity<GoogleBooksApiDto> results = new RestTemplate().getForEntity(url, GoogleBooksApiDto.class);
		
		return results.getBody();
		
	}//searchBooks
	
	
	/*復元のため検索条件と検索結果をSessionに入れるよ
	 * 
	 */
	public void saveSearchSession(HttpSession session,BookSearchForm form,List<BookSearchResultDto> books,BookSearchPageDto page ) {
		
		session.setAttribute("SESSION_BOOK_SEARCH_FORM", form);
		session.setAttribute("SESSION_BOOK_SEARCH_RESULTS", books);
		session.setAttribute("SESSION_BOOK_SEARCH_PAGING", page);
		
	}
	
	
	/*ページDTOを作るよ
	 * 
	 */
	public BookSearchPageDto  createBookSearchPage(GoogleBooksApiDto results, int page) {
		
		BookSearchPageDto dto = new BookSearchPageDto();
		
		int totalItems;
				
		//ページネイション用のデータをセットする
		if(results.getTotalItems()>=80) totalItems = 80;
		else totalItems = results.getTotalItems();
		dto.setTotalItems(totalItems);
		dto.setPage(page);
		dto.setMaxPages((totalItems +19)/20);
		dto.setStartIndex((page-1)*20+1);
		dto.setEndIndex((page)*20);
		
		return dto;
		
	}//createBookSearchPage
	
	
	public void getTotalItems(HttpSession session,BookSearchForm form,int page) {
		
		GoogleBooksApiDto results = searchBooks(form, page);
		BookSearchPageDto pageDto = createBookSearchPage(results, page);
		saveSearchSession(session, form, converter.toSearchResultDto(results), pageDto);
		
	}
	
	
	
	
	
	
	//******************書籍詳細画面*****************
	
	
	/*API叩くよ！！
	 * volumeId用のエンドポイント
	 */
	public GoogleBooksItemDto getBookInfo(String googleVolumeId) {
		
		String url = "https://www.googleapis.com/books/v1/volumes/" + googleVolumeId;
		
		ResponseEntity<GoogleBooksItemDto> rest= new RestTemplate().getForEntity(url,GoogleBooksItemDto.class);
		
		
		return rest.getBody();
		
	}
	
	/*booksテーブルに登録済を確認
	 * なければ空を返す
	 */
	public BookDetailViewDto findBookByVolumeId(String googleVolumeId) {
		
		BooksEntity entity = bm.findByGoogleVolumeId(googleVolumeId);
		
		if(entity != null) {
			
		
		return new BookDetailViewDto(entity.getGoogleVolumeId()
									,entity.getId()
									,entity.getTitle()
									,entity.getAuthors()
									,entity.getPublishedDate()
									,entity.getPublisher()
									,entity.getIsbn()
									,entity.getDescription()
									,entity.getThumbnailLink()
									,entity.getPreviewLink()
									);
		}
		return null;
		
		
		}//findBookByVolumeId
	
	
	public UserBooksEntity getUserBook(Long userId,Long bookId ) {
		
		return ubm.findByUserIdAndBookId(userId, bookId);
	}
	
	public List<BookReviewDto> getReviews(Long bookId){
		
		List<UserBooksWithUserEntity> entity = ubm.findReviewsByBookId(bookId);
		
		
		return entity.stream()
					.map(e -> new BookReviewDto(
												e.getDisplayName()
												,e.getRating()
//												,Optional.ofNullable(e.getRating()).orElse(null) 
												,e.getReview()
												,e.getReviewUpdatedAt())
						)
					.toList();
				
	}//getReviews
	
	
	
	/*全知全能の神
	 * 書籍詳細画面
	 */
	
	public BookDetailPageDto getBookDetailPage(Long userId,String googleVolumeId) {
		
		BookDetailPageDto dto = new BookDetailPageDto();
		/*先にネガティブのフラグ立てるよん
		 *空のフォームもいれとくしん 
		 */
		dto.setMyBookExists(false);
		dto.setMyBookStatus(null);
//		dto.setForm(new UserBookRegisterForm(googleVolumeId,null));
		dto.setReviews(List.of());
		
		//Booksに登録無し＝ド新規
		if(findBookByVolumeId(googleVolumeId) == null) {
			dto.setBook(converter.toDetailDto(getBookInfo(googleVolumeId)));
			
			return dto;
		}
		
		//Booksに登録されている
		dto.setBook(findBookByVolumeId(googleVolumeId));
		
		//レビューがあればセット
		if(getReviews(dto.getBook().getId()) != null)
		dto.setReviews(getReviews(dto.getBook().getId()));

		
		//ユーザーの本棚に登録されている
		if(getUserBook(userId, dto.getBook().getId()) != null) {
			dto.setMyBookExists(true);
			dto.setMyBookStatus(getUserBook(userId, dto.getBook().getId())
								.getStatus().getLabel());
		}
		return dto;
	}
	
	
//	******************本棚登録処理*****************
	
	/*INSERTの神
	 * 本棚登録之命
	 */
	public void saveUserBook(Long userId,UserBookRegisterForm form) {
		Long bookId;
		
		bookId = getOrCreateBookId(form.getGoogleVolumeId());
		saveUserBookToShelf(userId,bookId,form.getReadingStatus().getCode());
		
	}
	
	
	
	/*booksテーブルにあればidを返して
	 * なければＩＮＳＥＲＴするよ！
	 */
 public Long getOrCreateBookId(String googleVolumeId) {
	 
	 //API叩いてEntityを生成するよ
	 if(findBookByVolumeId(googleVolumeId) == null) {
		 BookDetailViewDto dto = converter.toDetailDto(getBookInfo(googleVolumeId));
		 BooksEntity entity = new BooksEntity(null,
				 																		dto.getGoogleVolumeId(),
				 																		dto.getIsbn(),
				 																		dto.getTitle(),
				 																		dto.getAuthors(),
				 																		dto.getPublisher(),
				 																		dto.getPublishedDate(),
				 																		dto.getDescription(),
				 																		dto.getThumbnailLink(),
				 																		dto.getPreviewLink(),
				 																		null,
				 																		null,
				 																		null
				 																		);
		 Integer arart = bm.insertBook(entity);
		 return entity.getId();
	 }
		 
	 return bm.findByGoogleVolumeId(googleVolumeId).getId();
	 
	 
 }//getOrCreateBookId
 
 
 public void saveUserBookToShelf(Long userId,Long bookId,String status) {
	
	 Integer arart = ubm.insertUserBook(new UserBooksEntity(null,
			 																		UUID.randomUUID().toString(),
			 																		userId,
			 																		bookId,
			 																		UserBookReadingStatus.valueOf(status),
			 																		null,
			 																		null,
			 																		null,
			 																		null,
			 																		null,
			 																		null));
 }
	
	
	
}
