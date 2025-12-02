package com.tsd.libris.domain.converter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.tsd.libris.domain.api.books.GoogleBooksApiDto;
import com.tsd.libris.domain.api.books.GoogleBooksItemDto;
import com.tsd.libris.domain.api.books.GoogleBooksVolumeInfoDto;
import com.tsd.libris.domain.dto.books.BookSearchResultDto;

/*外部APIのネストしたDTOをフラットな内部DTOに変換するぞっ！！
 * 書籍検索画面と書籍詳細画面で使うロジックなので
 * メソッドに分けることにする
 */

@Component
public class GoogleBooksConverter {

	
	/*書籍検索画面用
	 * 
	 */
	public List<BookSearchResultDto> toSearchResultDto(GoogleBooksApiDto results) {
		
		//itemsがnullだとNPEでびっくりしたｗ
		List<GoogleBooksItemDto> items = Optional.ofNullable(results.getItems()).orElse(List.of());
		
		//外部DTOを内部DTOに変換するよ
			return items.stream().map(item ->{
					
			GoogleBooksVolumeInfoDto v = item.getVolumeInfo();
			
			//著者のListを一つの文字列にするよっ
			String authors = Optional.ofNullable(v.getAuthors())
					.map(a-> String.join(",", a))
					.orElse("著者情報なし");
			
			/*サムネイルリンクの判定
			 * 大きいのがあれば大きいのがいいな
			 */
			
			String thumbnailLink = Optional.ofNullable(v.getImageLinks()).map(
					img ->{
						if(!img.getThumbnail().isEmpty()) return img.getThumbnail();
						if(!img.getSmallThumbnail().isEmpty()) return img.getSmallThumbnail();
						return "/img/noimage.png";
					}).orElse("/img/noimage.png");
			
			/*内部DTOに入れてくよ
			 * 
			 */
			return new BookSearchResultDto(
					item.getId(),
					item.getVolumeInfo().getTitle(),
					authors,
					item.getVolumeInfo().getPublisher(),
					thumbnailLink);
			
					
				}).toList();//.stream()
		
	}//toSearchResultDto
	
}
