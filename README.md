# シンプルブラウザ (Simple Browser)

JavaFX を使用したシンプルなWebブラウザアプリケーションです。Windows環境での動作を想定して開発されています。

## 機能

- ✅ **URL入力**: WebページのアドレスをURL入力フィールドに入力してアクセス
- ✅ **ナビゲーション**: 戻る・進む・更新ボタンによるページナビゲーション
- ✅ **Webページ表示**: JavaFX WebViewによるHTML/CSS/JavaScriptの表示
- ✅ **モダンUI**: CSS3を使用したGoogle Chrome風のクリーンなデザイン
- ✅ **履歴管理**: ページ履歴の自動管理とボタン状態の更新

## 必要な環境

- **Java**: JDK 17以上
- **OS**: Windows (推奨)
- **Maven**: 3.6以上

## インストールと実行

### 1. リポジトリのクローン
```bash
git clone https://github.com/TakuyaFukumura/browser.git
cd browser
```

### 2. 依存関係の解決とビルド
```bash
mvn clean compile
```

### 3. アプリケーションの実行
```bash
mvn exec:java
```

または

```bash
mvn javafx:run
```

### 4. テストの実行
```bash
mvn test
```

### 5. JARファイルの作成
```bash
mvn package
```

## 使用方法

1. **アプリケーション起動**: 上記のコマンドでアプリケーションを起動します
2. **URL入力**: 上部のURL入力フィールドにWebサイトのアドレスを入力
   - プロトコル（https://）は自動で補完されます
   - 例: `google.com` → `https://google.com`
3. **ページ移動**: 
   - 「移動」ボタンをクリック、またはEnterキーでページを読み込み
   - 戻る (←) / 進む (→) ボタンで履歴を移動
   - 更新 (⟲) ボタンでページを再読み込み

## プロジェクト構成

```
browser/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/browser/
│   │   │       └── BrowserApplication.java    # メインアプリケーション
│   │   └── resources/
│   │       └── style.css                      # UIスタイル定義
│   └── test/
│       └── java/
│           └── com/browser/
│               ├── BrowserApplicationTest.java # 基本テスト
│               └── JavaFXComponentTest.java    # JavaFXコンポーネントテスト
├── target/                                     # ビルド出力（自動生成）
├── pom.xml                                     # Maven設定
├── SPEC.md                                     # 技術仕様書
├── README.md                                   # このファイル
└── .gitignore                                  # Git除外設定
```

## 技術仕様

- **言語**: Java 17
- **UIフレームワーク**: JavaFX (OpenJFX 21)
- **ビルドシステム**: Maven
- **テストフレームワーク**: JUnit 5
- **バージョン**: 0.1.0

## トラブルシューティング

### JavaFXが見つからない場合

IDE（IntelliJ IDEA等）から実行する場合は、VMオプションに以下を追加してください：

```
--module-path [JavaFX SDKのlibパス] --add-modules javafx.controls,javafx.fxml,javafx.web
```

### ビルドエラーが発生する場合

1. Java 17がインストールされていることを確認
2. 環境変数`JAVA_HOME`が正しく設定されていることを確認
3. Maven依存関係を更新: `mvn dependency:resolve`

### 起動時にWebViewが表示されない場合

- JavaFX WebKitエンジンがサポートされていない環境の可能性があります
- Windows 10/11の最新版での動作を推奨します

## 今後の拡張予定

- [ ] タブブラウジング機能
- [ ] ブックマーク管理
- [ ] ダウンロード機能
- [ ] プライベートブラウジングモード
- [ ] 設定画面
- [ ] macOS/Linux対応

## ライセンス

このプロジェクトはオープンソースプロジェクトです。

## 貢献

バグ報告やFeature Requestは [GitHub Issues](../../issues) で受け付けています。  
Pull Requestも歓迎します。

---

**開発者**: TakuyaFukumura  
**バージョン**: 0.1.0  
**最終更新**: 2024年9月