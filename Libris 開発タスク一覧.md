#  Libris 開発タスク一覧


---

# 0. 共通 / プロジェクトセットアップ
- [ ] #0-1 初期 README を作成する（アーキテクチャ方針まとめ）
- [ ] #0-2 Projects（ToDo / InProgress / Done）を初期設定する

---

# 1. Domain Layer（Entity / DTO / Enum）

## 1-1. Entity（クラス単位）
- [ ] UsersEntity を作成する
- [ ] UserProfilesEntity を作成する
- [ ] UserBooksEntity を作成する
- [ ] BooksEntity を作成する
- [ ] UserBooksWithBooksEntity を作成する

## 1-2. DTO（クラス単位）

### 認証 / セッション
- [ ] LoginForm を作成する
- [ ] SessionUser を作成する

### Google Books API
- [ ] GoogleBooksApiDto を作成する
- [ ] GoogleBooksItemDto を作成する
- [ ] GoogleBooksVolumeInfoDto を作成する
- [ ] GoogleBooksIndustryIdentifierDto を作成する
- [ ] GoogleBooksImageLinksDto を作成する

### 書籍検索
- [ ] BookSearchForm を作成する
- [ ] BookSearchResultDto を作成する
- [ ] BookListPageDto を作成する
- [ ] BookDetailViewDto を作成する
- [ ] BookReviewDto を作成する
- [ ] BookDetailPageDto を作成する
- [ ] UserBookRegisterForm を作成する

### 本棚
- [ ] ShelfBookSummaryDto を作成する
- [ ] ShelfListPageDto を作成する
- [ ] ShelfBookDetailDto を作成する
- [ ] StatusEditForm を作成する
- [ ] ReviewEditForm を作成する
- [ ] ReviewEditPageDto を作成する

### ユーザー
- [ ] UserRegisterForm を作成する
- [ ] UserRegisterConfirmDto を作成する
- [ ] UserMypageDto を作成する
- [ ] UserEditForm を作成する
- [ ] UserEditConfirmDto を作成する
- [ ] PasswordChangeForm を作成する

## 1-3. Enum
- [ ] UserAuthority を作成する
- [ ] UserStatus を作成する
- [ ] BookStatus を作成する
- [ ] UserBookReadingStatus を作成する

---

# 2. Mapper Layer（メソッド単位）
- [ ] findByLoginId を実装する
- [ ] findByGoogleVolumeId を実装する
- [ ] findByUserIdAndBookId を実装する
- [ ] findReviewsByBookId を実装する
- [ ] insertBook を実装する
- [ ] insertUserBook を実装する
- [ ] findShelfBooksByUserId を実装する
- [ ] findShelfBooksByUserIdAndStatus を実装する
- [ ] findBookDetailByBookId を実装する
- [ ] findUserBookStatus を実装する
- [ ] findReviewByBookIdAndUserId を実装する
- [ ] updateUserBookStatus を実装する
- [ ] updateUserBookReview を実装する
- [ ] existsByLoginId を実装する
- [ ] insertUser を実装する
- [ ] insertUserProfile を実装する
- [ ] findUserProfileByUserId を実装する
- [ ] findByUserId を実装する
- [ ] updateUser を実装する
- [ ] upateUserProfile を実装する
- [ ] updatePassword を実装する

---

# 3. Service Layer（メソッド単位）

## 認証 / セッション
- [ ] authenticate を実装する
- [ ] loginSession を実装する
- [ ] logout を実装する
- [ ] clearSearchSession を実装する
- [ ] hasSearchCondition を実装する

## 書籍検索
- [ ] searchBooks を実装する
- [ ] buildPage を実装する
- [ ] saveSearchSession を実装する

## 書籍詳細
- [ ] getBookDetailPage を実装する
- [ ] findBookByVolumeId を実装する
- [ ] getBookInfo を実装する
- [ ] getUserBook を実装する
- [ ] getReviews を実装する
- [ ] saveUserBook を実装する
- [ ] getOrCreateBookId を実装する
- [ ] saveUserBookToShelf を実装する

## 本棚
- [ ] getShelfBooks を実装する
- [ ] getShelfPage を実装する
- [ ] getEditShelfPage を実装する
- [ ] getStatusEditForm を実装する
- [ ] updateUserBookStatus を実装する
- [ ] saveUserReview を実装する

## ユーザー登録
- [ ] existsByLoginId を実装する
- [ ] toConfirmDto を実装する
- [ ] toForm を実装する
- [ ] registerUser を実装する
- [ ] hashPassword を実装する

## ユーザー情報
- [ ] getUserInfo を実装する
- [ ] getUserEditForm を実装する
- [ ] toUserConfirm を実装する
- [ ] toUserEditform を実装する
- [ ] updateUser を実装する
- [ ] changePassword を実装する

---

# 4. Controller Layer（メソッド単位）

## 認証
- [ ] showLoginForm を実装する
- [ ] login を実装する
- [ ] LogoutController を実装する

## 書籍検索
- [ ] showSearchForm を実装する
- [ ] searchBooks を実装する

## 詳細
- [ ] showDetail を実装する
- [ ] saveUserBook を実装する

## 本棚
- [ ] listShelf を実装する
- [ ] showEditForm を実装する
- [ ] editUserReview を実装する
- [ ] editUserBookStatus を実装する

## 会員登録
- [ ] showUserRegisterForm を実装する
- [ ] showRegisterConfirm を実装する
- [ ] backToRegister を実装する
- [ ] registerUser を実装する

## マイページ
- [ ] showMyPage を実装する

## ユーザー編集
- [ ] showUserEditForm を実装する
- [ ] showUserEditConfirm を実装する
- [ ] backToUserEdit を実装する
- [ ] editUser を実装する

## パスワード変更
- [ ] showPasswordForm を実装する
- [ ] changePassword を実装する

---

# 5. Config Layer（クラス単位）
- [ ] WebMvcConfig を作成する
- [ ] SessionConfig を作成する
- [ ] SecurityConfig を作成する
- [ ] GlobalExceptionHandler を作成する
- [ ] LoggingConfig を作成する

