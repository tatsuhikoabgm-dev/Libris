# 📚 Libris - 読書管理システム

訓練校で学習したこと、自分で調べて学んだことを総動員して開発する読書管理システム。  
Google Books API と連携し、書籍情報の検索、読書管理を行うアプリケーションです。  
設計〜実装までの記録を時系列で残し、「どのようにシステムを考えて作ったか」を明確に示すことを目的にしています。
あわせてGitHubでのプロジェクト管理を学びながら開発します。
アーキテクチャを勉強する機会にもなるので、層ごとの責務の違い、機能などを意識して開発をします。
ＵＸ，ＵＩについても日ごろいいなと思う仕様を取り入れてみたり、なんでこういう仕様なんだろうと学ぶきっかけにしています。

---

## 🗓️ Development Timeline
- 2025-11-26: クラス設計が終了した。DBを作成しダミーデータを流し込む作業までは完了。いよいよPGに入る！！
- 2025-11-25: 更新が滞ってしまった。[画面設計](#画面設計)がすべて終わりクラス設計をしている。最初のころに設計していた書籍検索画面、書籍詳細画面の設計が甘かったのでやり直して[メモ](#メモ)を追加した。
進捗が遅れそうで怖かったが、レイヤードの理解が深まったと前向きにとらえ納得がいくように設計をし直している。Mapperから返ってくるnullをどうしようかと調べているとoptionalにたどりついた。
最近、今まで使ったことのないメソッドやクラスが出てきて知恵熱がでそう。optional系,objectクラス.stream()など、ぜひこの開発中にものにしたいと思っている。
- 2025-11-21: 書籍検索画面、書籍詳細画面、本棚表示画面、本棚編集画面の画面設計とクラス設計が終わる。これでメインとなる機能の設計が完了。明日からユーザー登録、ログイン、ログアウトの設計に入る。
- 2025-11-21: クラス設計をしていて書籍詳細画面のUI変更を思いついた。これはPGに入る前で本当によかったな＆レイヤーと機能をしっかり分けているから修正箇所が明白だなってとてもうれしい気持ちになる。
  DTOとEntity間のデータの流れ、複合Entity、ページDTO、FormDTOなどそれぞれの役割について少しずつ理解が追い付いてきたので設計がちょっとずつ楽しくなってきている。
  Controllerの設計で引数を書いているときはちゃんとアノテーションも書いてコピペすればそのまま使えるようにしたり頭の中でＰＧしているみたいで楽しい。
- 2025-11-20: 設計の順番は画面→DTO→Mapper→Service→Controllerがしっくりきそうな予感。。。。やっと命名にも慣れてきた気が。。。。する。。ｗ
- 2025-11-19: [Entity](#Entity) , [enum](#enum) , [書籍検索画面](#書籍検索画面) と [書籍詳細画面](#書籍詳細画面) の [DTO](#DTO) , [Mapper](#Mapper) , [Service](#Service) , [Controller](#Controller) のクラス設計が終わる
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
- Entity,DTO,MApper,Service,Controllerのレイヤードアーキテクチャによる責務と機能を分けた設計を学ぶ

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
| その他 | Lombok / Git / GitHub |

---

##  3. ディレクトリ構成
[クラス設計書 - 構成とURLマッピング.pdf](https://github.com/user-attachments/files/23751249/-.URL.pdf)

<img width="808" height="553" alt="image" src="https://github.com/user-attachments/assets/8bc842b2-6ba1-4d29-b3b5-14e9627821de" />



#  4. 設計資料

## クラス設計

### ドメイン設計
[【決定】ドメイン設計 - ドメイン設計.pdf](https://github.com/user-attachments/files/23751220/-.pdf)

<img width="1653" height="2339" alt="ドメイン" src="https://github.com/user-attachments/assets/b9cd809d-65fc-4817-90db-f83ff2764ee9" />


---

### Entity
[クラス設計書 - Entity.pdf](https://github.com/user-attachments/files/23751224/-.Entity.pdf)

<img width="2339" height="1653" alt="クラス設計書 - Entity" src="https://github.com/user-attachments/assets/84059852-96bf-4f66-a783-7c40de4fc12d" />


---

### enum
[クラス設計書 - enum.pdf](https://github.com/user-attachments/files/23751227/-.enum.pdf)

<img width="2339" height="1653" alt="enum" src="https://github.com/user-attachments/assets/9a0f37f6-cf5c-4d83-8c9b-0b83d7ca9f43" />


---

### DTO
[クラス設計書 - DTO.pdf](https://github.com/user-attachments/files/23751230/-.DTO.pdf)

<img width="595" height="842" alt="クラス設計書 - DTO pdf_page1" src="https://github.com/user-attachments/assets/4e305fa3-b1a6-49a2-84e1-d0b05416bf86" />


---

### SESSION
[クラス設計書 - SessionKeys.pdf](https://github.com/user-attachments/files/23751232/-.SessionKeys.pdf)

<img width="842" height="595" alt="クラス設計書 - SessionKeys pdf_page1" src="https://github.com/user-attachments/assets/b042363e-24bb-49b7-bdb3-2b49d818f1bd" />


---

### Mapper
[クラス設計書 - Mapper.pdf](https://github.com/user-attachments/files/23751236/-.Mapper.pdf)

<img width="2339" height="1653" alt="クラス設計書 - Mapper" src="https://github.com/user-attachments/assets/9233ce05-4f0e-4850-a3d2-2267925c83ea" />


---

### Service
[クラス設計書 - Service.pdf](https://github.com/user-attachments/files/23751239/-.Service.pdf)

<img width="842" height="595" alt="クラス設計書 - Service pdf_page1" src="https://github.com/user-attachments/assets/ed5c0437-a3d8-4aa0-8534-c4ef35b8762c" />



---

### Controller
[クラス設計書 - Controller.pdf](https://github.com/user-attachments/files/23751240/-.Controller.pdf)

<img width="842" height="595" alt="クラス設計書 - Controller pdf_page1" src="https://github.com/user-attachments/assets/1b950a3d-b06d-4b1c-85d1-059029237ca7" />



---

## 画面設計

[【決定】画面設計.pdf](https://github.com/user-attachments/files/23750894/default.pdf)


## メモ
[クラス設計書 - 書籍検索画面のロジック.pdf](https://github.com/user-attachments/files/23751256/-.pdf)

<img width="842" height="595" alt="クラス設計書 - 書籍検索画面のロジック pdf_page1" src="https://github.com/user-attachments/assets/22f14523-fc28-4685-8291-88f59c656626" />

[クラス設計書 - 書籍詳細画面のロジック.pdf](https://github.com/user-attachments/files/23751257/-.pdf)

<img width="842" height="595" alt="クラス設計書 - 書籍詳細画面のロジック pdf_page1" src="https://github.com/user-attachments/assets/44ca824d-08b2-4fee-9f7f-eba44d74a035" />










### 　



