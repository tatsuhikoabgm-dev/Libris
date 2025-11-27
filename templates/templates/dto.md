##  概要
DTO `{DtoName}` を作成する。

##  目的
- Controller/View 間の受け渡し専用データ構造を提供する
- null 禁止 / emptyList() ポリシーに従う

##  要件
- DTO クラス `{DtoName}` を作成
- 全フィールド null 禁止
- リストは emptyList() で初期化する
- Entity → DTO の変換ロジックは Service に寄せる
- DTO にロジックは持たせない

##  完了条件
- DTO クラス作成済み
- null 禁止ポリシーが遵守されている
- Controller に安全に渡せる状態である
