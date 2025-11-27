<h1 align="center">
  ☆☆<b>Libris - 読書管理システム</b>☆☆
</h1>


訓練校で学習したこと、自分で調べて学んだことを総動員して開発する読書管理システム。  
Google Books API と連携し、書籍情報の検索、読書管理を行うアプリケーションです。  
設計〜実装までの記録を時系列で残し、「どのようにシステムを考えて作ったか」を記録に残すことを目的にしています。
GitHubでのプロジェクト管理を学びながら開発します。
アーキテクチャを勉強する機会にもなるので、層ごとの責務の違い、機能などを意識して開発をします。
ＵＸ，ＵＩについても日ごろいいなと思う仕様を取り入れてみたり、なんでこういう仕様なんだろうと学ぶきっかけにしています。

---
#  開発管理（GitHub Projects）

進捗管理・タスク分割は GitHub Projects で行っています。  

➡ [Issue → ToDo → InProgress → Doneで管理しています。](https://github.com/users/tatsuhikoabgm-dev/projects/1)

---

#  開発タスク一覧（Issues）

100以上のタスクを Entity/DTO/Service/Controller 単位で分割し、  
テンプレートに基づいて Issue 化しています。

➡ [レイヤーごとにタスクを分割しています。](https://github.com/tatsuhikoabgm-dev/Libris/issues)
---

## 1. プロジェクト概要

###  目的
- 自分の技術力を確認し、向上させるためのシステム開発
- システム開発の思考過程をREADMEに記録する
- 就活で提出できるポートフォリオを作る
- Entity,DTO,MApper,Service,Controllerのレイヤードアーキテクチャによる責務と機能を分けた設計を学ぶ
- プロジェクトをGitHubで管理する

###  主な機能（予定含む）
- 会員登録／ログイン
- 権限（ユーザー・管理者）管理
- 書籍検索（Google Books API）
- 書籍の登録／編集／削除
- 読書ログ管理
- レビュー投稿・閲覧機能
- フィルタリング

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

<img width="500" alt="image" src="https://github.com/user-attachments/assets/8bc842b2-6ba1-4d29-b3b5-14e9627821de" />



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



## 📱 画面設計（UI Screens）

### 🔍 書籍検索画面
[<img src="screens/thumb_search.png" width="300" alt="検索画面サムネ" />](screens/search.svg)

### 📖 書籍詳細画面
[<img src="screens/thumb_detail.png" width="300" alt="詳細画面サムネ" />](screens/detail.svg)

### 📚 本棚一覧画面
[<img src="screens/thumb_shelf.png" width="300" alt="本棚画面サムネ" />](screens/shelf.svg)

---

### 📄 PDFまとめ（UI設計全ページ）
[「Libris UI 全画面設計書（PDF）」をダウンロード](screens/Libris_UI.pdf)






