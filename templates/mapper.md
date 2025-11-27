##  概要
Mapper メソッド `{methodName}` を実装する。

##  方針
- Mapper は Entity / 複合Entity のみ返す（DTO 禁止）
- SQL は XML / アノテーションどちらでも可
- N+1 防止のため JOIN を活用

##  要件
- `{methodName}` を Mapper に追加する
- 適切な引数/返却型を定義する
- SQL 実装（XML or annotation）を追加
- Service 層から利用可能であることを確認する

##  完了条件
- Mapper メソッドが正常に動作する
- 戻り値が正しく Entity と一致する
- 必要に応じて JOIN が正しく機能する
