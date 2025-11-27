##  概要
Controller メソッド `{methodName}` を実装する。

##  方針
- Controller は「フォーム受取 → Service 呼び出し → Model返却」
- ビジネスロジック禁止
- バリデーション NG は forward
- 成功は redirect + flash attribute

##  要件
- `{methodName}` を Controller に追加する
- 入力フォーム `{FormName}` を受け取る（必要に応じて）
- Service `{ServiceMethod}` を呼び出す
- Model には DTO のみ詰める
- セッションは SESSION_USER または検索セッションを使用

##  完了条件
- Controller が薄い構成で実装されている
- Service 主導で画面処理が成立
- 画面の動作が期待通りである
