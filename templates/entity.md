##  概要
`{EntityName}` エンティティを作成する。

##  目的
- DB テーブル構造を反映した Domain Model を確立する
- Mapper 層で利用する純粋なデータ構造を提供する
- DTO と Entity の完全分離方針に従う

##  要件
- Java クラスとして `{EntityName}` を作成する
- フィールド名は DB の snake_case に対応させる（camelCase 化）
- Lombok（@Data/@NoArgsConstructor/@AllArgsConstructor）を使用
- null を許可しないポリシーに従う
- 意味のあるフィールドにはコメントを付ける

##  完了条件
- クラスが正しく作成済み
- 型・責務が他レイヤーと整合している
- PRがレビュー & マージ済み
