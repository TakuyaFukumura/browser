# シンプルブラウザ仕様書

## 概要
Java 17とJavaFXを使用して開発されたWindows向けのシンプルなWebブラウザアプリケーションです。基本的なWebブラウジング機能を提供し、拡張性を重視した設計となっています。

## バージョン
- **現在のバージョン**: 0.1.0
- **リリース日**: 2024年9月

## 技術仕様

### 開発環境
- **Java**: JDK 17
- **UIフレームワーク**: JavaFX (OpenJFX 21)
- **ビルドツール**: Maven
- **対象OS**: Windows
- **推奨IDE**: IntelliJ IDEA

### 依存関係
- `javafx-controls`: 21 (Windows用)
- `javafx-fxml`: 21 (Windows用)
- `javafx-web`: 21 (Windows用)

## プロジェクト構成

```
browser/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── browser/
│       │           └── BrowserApplication.java    # メインアプリケーションクラス
│       └── resources/
│           └── style.css                          # UIスタイル定義
├── pom.xml                                        # Maven設定ファイル
├── SPEC.md                                        # 仕様書（このファイル）
└── .gitignore                                     # Git除外設定
```

## 機能仕様

### 基本機能
1. **URL入力とページ読み込み**
   - URL入力フィールドでWebページのアドレスを指定
   - プロトコル未指定時は自動的にHTTPSを付与
   - Enterキーまたは「移動」ボタンでページ読み込み

2. **ナビゲーション機能**
   - 戻るボタン (←): 前のページに移動
   - 進むボタン (→): 次のページに移動
   - 更新ボタン (⟲): 現在のページを再読み込み

3. **Webページ表示**
   - JavaFX WebViewコンポーネントによるHTML/CSS/JavaScriptの表示
   - ページ読み込み状態の監視と反映

### UI/UX仕様
- **ウィンドウサイズ**: 1200×800ピクセル（デフォルト）
- **レスポンシブレイアウト**: ウィンドウサイズに応じてURL入力フィールドが自動調整
- **モダンUI**: CSS3を使用したフラットデザイン
- **カラーテーマ**: Google Chrome風の清潔なデザイン

### スタイル詳細
- **フォント**: Segoe UI（Windows標準）
- **カラーパレット**:
  - プライマリブルー: #4285f4
  - ボーダーグレー: #cccccc
  - 背景色: #f5f5f5
- **エフェクト**: ドロップシャドウとホバーエフェクト

## ビルドと実行

### 必要な環境
- JDK 17以上
- Maven 3.6以上
- Windows OS

### ビルド方法
```bash
mvn clean compile
```

### 実行方法
```bash
mvn exec:java
```

または

```bash
mvn javafx:run
```

### IDE実行時のVMオプション
```
--module-path [JavaFX SDKのlibパス] --add-modules javafx.controls,javafx.fxml,javafx.web
```

## 拡張性

### 将来の拡張可能性
1. **タブブラウジング**: 複数ページの同時表示
2. **ブックマーク機能**: お気に入りサイトの管理
3. **履歴管理**: 閲覧履歴の保存と検索
4. **ダウンロード管理**: ファイルダウンロード機能
5. **設定画面**: ユーザー設定のカスタマイズ
6. **プラグインシステム**: 機能拡張プラグインの対応

### 技術的拡張
1. **マルチプラットフォーム対応**: macOS/Linux版の開発
2. **ネイティブインストーラー**: jpackageによる実行ファイル作成
3. **パフォーマンス最適化**: メモリ使用量とレンダリング速度の改善
4. **セキュリティ強化**: SSL証明書検証とセキュリティ警告

## 制限事項

### 現在の制限
- Windows専用（他OSでは動作しない）
- 単一タブのみ対応
- ブックマーク機能なし
- ダウンロード機能なし
- プライベートブラウジングモードなし

### 技術的制限
- JavaFX WebViewのWebKit制限に依存
- WebRTC、WebGL等の最新Web技術は一部未対応
- Flash等のプラグイン未対応

## ライセンス
このプロジェクトはオープンソースプロジェクトです。

## 開発者向け情報

### コードスタイル
- 日本語コメントを使用
- JavaDocスタイルのドキュメンテーション
- 命名規則: キャメルケース（Java標準）

### テスト
現在のバージョンではユニットテストは実装されていません。今後のバージョンで追加予定です。

### 貢献
- バグ報告やFeature Requestは GitHub Issues で受け付けています
- Pull Requestは歓迎します
- コードレビューは日本語で実施します

## 参考資料
- [OpenJFX公式サイト](https://openjfx.io/)
- [JavaFX Documentation](https://docs.oracle.com/javafx/)
- [Maven Central - OpenJFX](https://search.maven.org/search?q=g:org.openjfx)