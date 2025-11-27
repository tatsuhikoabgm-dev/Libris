##  概要
設定クラス `{ConfigName}` を作成する。

##  方針
- MVC / Session / Security / Logging など非機能要件を構成する
- 設定は Controller/Service に書かない

##  要件
- `{ConfigName}` を作成
- 必要な Bean 定義、Interceptor 設定などを実装
- セッション（SESSION_USER）設定がある場合はここで行う
- 必要に応じて例外ハンドラやログ設定も追加

##  完了条件
- 設定が正しく動作している
- アプリ起動時に期待通り設定が読み込まれる
