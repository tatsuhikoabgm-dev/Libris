package com.tsd.libris.service.shelf;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tsd.libris.domain.dto.shelf.ReviewEditForm;
import com.tsd.libris.domain.dto.shelf.ReviewEditPageDto;
import com.tsd.libris.domain.dto.shelf.ShelfBookDetailDto;
import com.tsd.libris.domain.dto.shelf.ShelfBookSummaryDto;
import com.tsd.libris.domain.dto.shelf.ShelfListPageDto;
import com.tsd.libris.domain.dto.shelf.StatusEditForm;
import com.tsd.libris.domain.entity.BooksEntity;
import com.tsd.libris.domain.entity.UserBooksEntity;
import com.tsd.libris.domain.enums.UserBookReadingStatus;
import com.tsd.libris.mapper.book.BooksMapper;
import com.tsd.libris.mapper.shelf.ShelfMapper;
import com.tsd.libris.mapper.userbooks.UserBookMapper;

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
		
//		System.out.println("Entity : " + sm.findShelfBooksByUserId(userId));
		
		if(status.equals("ALL")) {
			System.out.println(sm.findShelfBooksByUserId(userId));
			return sm.findShelfBooksByUserId(userId).stream()
													.map( list -> new ShelfBookSummaryDto(list.getUbId(),list.getThumbnailLink())
															).toList();
		}
		return sm.findShelfBooksByUserIdAndStatus(userId,UserBookReadingStatus.valueOf(status)).stream()
													.map( list -> new ShelfBookSummaryDto(list.getUbId(),list.getThumbnailLink())
															).toList();
	
	}
	
	public ShelfListPageDto getShelfPage(Long userId,String displayName,String status) {
		
		return new ShelfListPageDto(displayName,getShelfBooks(userId,status));
		
	}
	
	//****************本棚編集画面****************
	
	
	
	/*本棚編集画面
	 * 
	 */
	public ReviewEditPageDto getEditShelfPage(Long id) {
		
		Long bookId = ubm.findById(id).getBookId();
		BooksEntity e = bm.findBookDetailByBookId(bookId);
		boolean b = false;
		
		if(ubm.findReviewById(id).getReview() != null) b =true ;
		
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
	 public StatusEditForm getStatusEditForm(Long id) {
		 
		 
		 return new StatusEditForm(id,
				 UserBookReadingStatus.valueOf(ubm.findUserBookStatus(id)));
		 }
	 
	
	/*本棚編集画面
	 * レビュー投稿用フォーム
	 */
	 public ReviewEditForm getReviewEditForm(Long id) {
		 
		 UserBooksEntity e = ubm.findReviewById(id);
		 
		 return new ReviewEditForm(id,
				 											e.getRating(),
				 											e.getReview());
	 }
	
	 
	 
	 
	 /*ステータス更新
	  * 
	  */
	 public void editUserBookStatus(StatusEditForm form) {
		
		 ubm.updateUserBookStatus(form.getId(),form.getStatus());
		 
	 }
	 
	 
	 /*レビューの更新
	  * 
	  */
	 public void editUserReview(ReviewEditForm form) {
		 
		 ubm.updateUserBookReview(form.getReview(),form.getId());
		 
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
