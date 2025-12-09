package com.tsd.libris.service.shelf;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tsd.libris.domain.dto.shelf.ShelfBookSummaryDto;
import com.tsd.libris.domain.dto.shelf.ShelfListPageDto;
import com.tsd.libris.domain.enums.UserBookReadingStatus;
import com.tsd.libris.mapper.shelf.ShelfMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShelfService {

	private final ShelfMapper sm;
	
	
	/*ユーザーの本棚情報を取得するよ
	 * 
	 */
	public List<ShelfBookSummaryDto> getShelfBooks(Long userId,String status){
		
		System.out.println("Entity : " + sm.findShelfBooksByUserId(userId));
		
		if(status.equals("ALL")) {
			return sm.findShelfBooksByUserId(userId).stream()
													.map( list -> new ShelfBookSummaryDto(list.getBId(),list.getThumbnailLink())
															).toList();
		}
		return sm.findShelfBooksByUserIdAndStatus(userId,UserBookReadingStatus.valueOf(status)).stream()
													.map( list -> new ShelfBookSummaryDto(list.getBId(),list.getThumbnailLink())
															).toList();
	
	}
	
	public ShelfListPageDto getShelfPage(Long userId,String displayName,String status) {
		
		return new ShelfListPageDto(displayName,getShelfBooks(userId,status));
		
	}
	
	
}
