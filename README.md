# 📚 Libris - 読書管理システム

訓練校で学習したこと、自分で調べて学んだことを総動員して開発する読書管理システム。  
Google Books API と連携し、書籍情報の検索、読書管理を行うアプリケーションです。  
設計〜実装までの記録を時系列で残し、「どのようにシステムを考えて作ったか」を明確に示すことを目的にしています。
あわせてGitHubでのプロジェクト管理を学びながら開発します。
アーキテクチャを勉強する機会にもなるので、層ごとの責務の違い、機能などを意識して開発をします。

---

## 🗓️ Development Timeline
- 2025-11-21: 書籍検索画面、書籍詳細画面、本棚表示画面、本棚編集画面の画面設計とクラス設計が終わる。これでメインとなる機能の設計が完了。明日からユーザー登録、ログイン、ログアウトの設計に入る。
- 2025-11-21: クラス設計をしていてUIの変更を思いついた。これはPGに入る前で本当によかったな＆レイヤーと機能をしっかり分けているから修正箇所が明白だなってとてもうれしい気持ちになる。
  DTOとEntity間のデータの流れ、複合Entity、ページDTO、FormDTOなどそれぞれの役割について少しずつ理解が追い付いてきたので設計がちょっとずつ楽しくなってきている。
  Controllerの設計で引数を書いているときはちゃんとアノテーションも書いてコピペすればそのまま使えるようにしたり頭の中でＰＧしているみたいで楽しい。
- 2025-11-20: 設計の順番は画面→DTO→Mapper→Service→Controllerがしっくりきそうな予感。。。。やっと命名にも慣れてきた気が。。。。する。。ｗ
- 2025-11-19: [Entity](#Entity) , [enum](#enum) , [書籍検索画面](#書籍検索画面) と [書籍詳細画面](#書籍詳細画面) の [DTO](#DTO) , [Service](#Service) , [Controller](#Controller) , [Mapper](#Mapper) のクラス設計が終わる
- 2025-11-18:　画面の設計と一緒にクラス設計を考えることにする。
- 2025-11-18:　画面の設計に入る　画面のイメージを持つことでどんな処理が必要なのか洗い出そうと思った。
- 2025-11-17:　ドメインの設計が完了する　このリポジトリを立ち上げる　[ドメイン設計](#ドメイン設計)
- 2025-11-16:　ドメインの設計に着手する　ドメインから設計することが自分の中でしっくりきていた。
- 2025-11-15:　GoogleBooksAPIから返ってくるJSONをDTOに入れることができたためこのシステムの構想に入る。


---

## 1. プロジェクト概要

###  目的
- 自分の技術力を確認し、向上させるためのシステム開発
- システム開発の思考過程をREADMEに時系列で記録する
- 就活で提出できる「時間・思考が見えるポートフォリオ」を作る

###  主な機能（予定含む）
- 会員登録／ログイン
- 権限（ユーザー・管理者）管理
- 書籍検索（Google Books API）
- 書籍の登録／編集／削除
- 読書ログ管理
- レビュー投稿・閲覧機能
- 各種ソート・フィルタリング

---

##  2. 技術スタック
| 分類 | 使用技術 |
|------|-----------|
| バックエンド | Java / Spring Boot / MyBatis |
| DB | MySQL |
| ビュー | Thymeleaf / HTML / CSS |
| API | Google Books API |
| その他 | Lombok / Git / GitHub / Postman |

---

##  3. ディレクトリ構成
<img width="808" height="553" alt="image" src="https://github.com/user-attachments/assets/8bc842b2-6ba1-4d29-b3b5-14e9627821de" />



#  4. 設計資料

## クラス設計

### ドメイン設計
<img width="1653" height="2339" alt="ドメイン" src="https://github.com/user-attachments/assets/b9cd809d-65fc-4817-90db-f83ff2764ee9" />
---

### Entity
<img width="2339" height="1653" alt="クラス設計書 - Entity" src="https://github.com/user-attachments/assets/84059852-96bf-4f66-a783-7c40de4fc12d" />


---

### enum
<img width="2339" height="1653" alt="enum" src="https://github.com/user-attachments/assets/9a0f37f6-cf5c-4d83-8c9b-0b83d7ca9f43" />
---

## 書籍検索画面、書籍詳細画面、本棚表示画面、本棚編集画面
### DTO
<img width="2339" height="1653" alt="クラス設計書 - DTO" src="https://github.com/user-attachments/assets/afa61240-226d-48cf-802c-35e6c1574db9" />


### Mapper
<img width="2339" height="1653" alt="クラス設計書 - Mapper" src="https://github.com/user-attachments/assets/9233ce05-4f0e-4850-a3d2-2267925c83ea" />


### Service
<img width="2339" height="1653" alt="クラス設計書 - Service" src="https://github.com/user-attachments/assets/46c58d99-422c-4fc6-9eac-9476d3abcb2c" />



### Controller
<img width="2339" height="1653" alt="クラス設計書 - Controller" src="https://github.com/user-attachments/assets/b0705785-c77e-4b94-9bd5-19aeddaaffdd" />



## 画面設計
### 書籍検索画面
<img width="2339" height="1653" alt="書籍検索画面" src="https://github.com/user-attachments/assets/ef0dc816-f68a-4bc0-ab3a-6a55582dbd52" />

### 書籍詳細画面


### 本棚表示画面
<img width="2339" height="1653" alt="画面設計 - 本棚表示画面" src="https://github.com/user-attachments/assets/482dfc66-0db0-4098-afc9-eaaf04089b19" />


### 本棚編集画面
<img width="2339" height="1653" alt="画面設計 - 本棚編集画面" src="https://github.com/user-attachments/assets/07da84d7-b2fa-4ff3-8604-afdf178f9ba7" />















