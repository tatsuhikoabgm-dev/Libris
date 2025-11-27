##  概要
Service メソッド `{methodName}` を実装する。

##  方針
- Controller を薄く保つためビジネスロジックは Service に集約
- DTO/Entity の変換は Service が担当
- Optional を利用して null 安全化
- 必要なら複数 Mapper を連携させる

##  要件
- `{methodName}` を Service に追加
- Mapper を組み合わせて必要な処理を構築
- DTO → Entity / Entity → DTO を Service で実装する
- null 禁止 DTO のポリシーに従う

##  完了条件
- メソッドが期待通り動作する
- Controller 側にロジックが漏れていない
- 画面単位のユースケースが成立する
