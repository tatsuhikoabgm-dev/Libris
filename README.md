# 📚 Libris - 読書管理システム

訓練校で学習したこと、自分で調べて学んだことを総動員して開発する読書管理システム。  
Google Books API と連携し、書籍情報の検索、読書管理を行うアプリケーションです。  
設計〜実装までの記録を時系列で残し、「どのようにシステムを考えて作ったか」を明確に示すことを目的にしています。
あわせてGitHubでのプロジェクト管理を学びながら開発します。

---

## 🗓️ Development Timeline
- 2025-11-18:　画面の設計と一緒にクラス設計をすればDTO名などを連動させられることに気づく
- 途中でとりあえずレイアウトだけを作成し、明日から画面設計とクラス設計を平行して行う　[ここまででできたもの](#ここまでで完成した画面設計ととりあえずのレイアウト)
- 2025-11-18:　画面の設計に入る　画面のイメージを持つことでどんな処理が必要なのか洗い出そうと思った
- 2025-11-17:　ドメインの設計が完了する　このリポジトリを立ち上げる　[ドメイン設計](#ドメイン設計)
- 2025-11-16:　ドメインの設計に着手する　ドメインから設計することが自分の中でしっくりきていた
- 2025-11-15:　GoogleBooksAPIから返ってくるJSONをDTOに入れることができたためこのシステムの構想に入る


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

##  3. ディレクトリ構成（例）
src
└── main
├── java
│ └── com.example.libris
│ ├── controller
│ ├── service
│ ├── mapper
│ ├── domain
│ │ ├── entity
│ │ ├── dto
│ │ └── googlebooks ← APIレスポンスPOJO
│ └── config
├── resources
│ ├── templates
│ │ ├── user
│ │ └── book
│ ├── mapper

│ ├── static
│ │ ├── css
│ │ └── js（必要なら）
└── test

##  4. 設計資料

### ドメイン設計
<img width="1653" height="2339" alt="ドメイン" src="https://github.com/user-attachments/assets/b9cd809d-65fc-4817-90db-f83ff2764ee9" />

### 書籍検索画面設計  
<img width="2339" height="1653" alt="書籍検索画面" src="https://github.com/user-attachments/assets/ef0dc816-f68a-4bc0-ab3a-6a55582dbd52" />

### ここまでで完成した画面設計ととりあえずのレイアウト
[画面設計.pdf](https://github.com/user-attachments/files/23609082/default.pdf)








