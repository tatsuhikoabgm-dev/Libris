package com.tsd.libris.service.shelf;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsd.libris.domain.dto.shelf.ReviewEditForm;
import com.tsd.libris.domain.dto.shelf.ReviewEditPageDto;
import com.tsd.libris.domain.dto.shelf.ShelfBookDetailDto;
import com.tsd.libris.domain.dto.shelf.ShelfBookSummaryDto;
import com.tsd.libris.domain.dto.shelf.ShelfListPageDto;
import com.tsd.libris.domain.dto.shelf.StatusEditForm;
import com.tsd.libris.domain.entity.BooksEntity;
import com.tsd.libris.domain.entity.UserBooksEntity;
import com.tsd.libris.domain.enums.UserBookReadingStatus;
import com.tsd.libris.exception.ApplicationException;
import com.tsd.libris.mapper.book.BooksMapper;
import com.tsd.libris.mapper.shelf.ShelfMapper;
import com.tsd.libris.mapper.userbooks.UserBookMapper;
import com.tsd.libris.service.common.DbAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShelfService {

	private final ShelfMapper sm;
	private final BooksMapper bm;
	private final UserBookMapper ubm;
	
	
	/*ユーザーの本棚情報を取得するよ
	 * 
	 */
	public List<ShelfBookSummaryDto> getShelfBooks(Long userId,String status){
		
		if(!"ALL".equals(status)) {
		if(!UserBookReadingStatus.isValid(status))
			throw new ApplicationException(ApplicationException.Type.INVALID_REQUEST,
											"getShelfBooks : " + status);
		}
		
		if("ALL".equals(status)) {
			return sm.findShelfBooksByUserId(userId).stream()
													.map( list -> new ShelfBookSummaryDto(list.getUuid(),list.getThumbnailLink())
															).toList();
		}
		return sm.findShelfBooksByUserIdAndStatus(userId,UserBookReadingStatus.valueOf(status)).stream()
													.map( list -> new ShelfBookSummaryDto(list.getUuid(),list.getThumbnailLink())
															).toList();
	
	}
	
	public ShelfListPageDto getShelfPage(Long userId,String displayName,String status) {
		
		return new ShelfListPageDto(displayName,getShelfBooks(userId,status));
		
	}
	
	//****************本棚編集画面****************
	
	
	
	/*本棚編集画面
	 * 
	 */
	public ReviewEditPageDto getEditShelfPage(String uuid) {
		
		Long bookId = ubm.findById(uuid).getBookId();
		BooksEntity e = bm.findBookDetailByBookId(bookId);
		boolean b = false;
		
		if(ubm.findReviewById(uuid).getReview() != null) b =true ;
		
		ShelfBookDetailDto dto = new ShelfBookDetailDto(e.getId(),
																	e.getTitle(),
																	e.getAuthors(),
																	e.getPublishedDate(),
																	e.getPublisher(),
																	e.getIsbn(),
																	e.getDescription(),
																	e.getThumbnailLink(),
																	e.getPreviewLink());
		
		return new ReviewEditPageDto(dto,b);
		
	}
	
	
	/*本棚編集画面
	 * ステータス変更用フォーム
	 */
	 public StatusEditForm getStatusEditForm(String uuid) {
		 
		 
		 return new StatusEditForm(uuid,
				 UserBookReadingStatus.valueOf(ubm.findUserBookStatus(uuid)));
		 }
	 
	
	/*本棚編集画面
	 * レビュー投稿用フォーム
	 */
	 public ReviewEditForm getReviewEditForm(String uuid) {
		 
		 UserBooksEntity e = ubm.findReviewById(uuid);
		 
		 return new ReviewEditForm(uuid,
				 											e.getRating(),
				 											e.getReview());
	 }
	
	 
	 
	 
	 /*ステータス更新
	  * 
	  */
	 @Transactional
	 public void editUserBookStatus(StatusEditForm form) {
		 
		 Integer arart = ubm.updateUserBookStatus(form.getUuid(),form.getStatus());
		 
		 DbAssert.assertSingleUpdate(arart,"editUserBookStatus");
	 }
	 
	 
	 /*レビューの更新
	  * 
	  */
	 @Transactional
	 public void editUserReview(ReviewEditForm form) {
		 
		 if(form.getRating() < 0 || form.getRating() > 5  )
			 throw new ApplicationException(ApplicationException.Type.INVALID_REQUEST, "editUserReview : " + form.getRating());
			 
		 UserBooksEntity e = new UserBooksEntity();
		 e.setUuid(form.getUuid());
		 e.setRating(form.getRating());
		 e.setReview(form.getReview());
		 Integer arart = ubm.updateUserBookReview(e);

		 DbAssert.assertSingleUpdate(arart, "editUserReview");
		 
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
