package com.browser;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * シンプルなJavaFXブラウザアプリケーション
 * <p>
 * このアプリケーションは基本的なWebブラウザ機能を提供します：
 * - URL入力とページ読み込み
 * - 戻る/進む/更新ボタン
 * - WebViewによるWebページ表示
 */
public class BrowserApplication extends Application {

    private WebView webView;
    private WebEngine webEngine;
    private TextField urlField;
    private Button backButton;
    private Button forwardButton;
    private Button refreshButton;
    private Button goButton;

    /**
     * アプリケーションのメインエントリーポイント
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // UI要素の初期化
        initializeUI();

        // レイアウトの構築
        BorderPane root = createLayout();

        // シーンの作成とCSSの適用
        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        // ステージの設定
        primaryStage.setTitle("シンプルブラウザ v0.1.0");
        primaryStage.setScene(scene);
        primaryStage.show();

        // デフォルトページの読み込み
        loadDefaultPage();
    }

    /**
     * UI要素を初期化する
     */
    private void initializeUI() {
        // WebViewとWebEngineの初期化
        webView = new WebView();
        webEngine = webView.getEngine();

        // URL入力フィールド
        urlField = new TextField();
        urlField.setPromptText("URLを入力してください (例: https://www.google.com)");
        urlField.getStyleClass().add("url-field");

        // ナビゲーションボタン
        backButton = new Button("←");
        backButton.getStyleClass().add("nav-button");
        backButton.setDisable(true);

        forwardButton = new Button("→");
        forwardButton.getStyleClass().add("nav-button");
        forwardButton.setDisable(true);

        refreshButton = new Button("⟲");
        refreshButton.getStyleClass().add("nav-button");

        goButton = new Button("移動");
        goButton.getStyleClass().add("go-button");

        // イベントハンドラの設定
        setupEventHandlers();
    }

    /**
     * イベントハンドラを設定する
     */
    private void setupEventHandlers() {
        // Go ボタンとEnterキーでページ読み込み
        goButton.setOnAction(e -> loadPage());
        urlField.setOnAction(e -> loadPage());

        // ナビゲーションボタン
        backButton.setOnAction(e -> webEngine.getHistory().go(-1));
        forwardButton.setOnAction(e -> webEngine.getHistory().go(1));
        refreshButton.setOnAction(e -> webEngine.reload());

        // WebEngineの状態変化監視
        webEngine.getLoadWorker().stateProperty().addListener(
                (obs, oldState, newState) -> {
                    if (newState == Worker.State.SUCCEEDED) {
                        String currentUrl = webEngine.getLocation();
                        if (currentUrl != null && !currentUrl.isEmpty()) {
                            urlField.setText(currentUrl);
                        }
                        updateNavigationButtons();
                    }
                }
        );

        // 履歴変更の監視
        webEngine.getHistory().currentIndexProperty().addListener(
                (obs, oldVal, newVal) -> {
                    updateNavigationButtons();
                }
        );
    }

    /**
     * ナビゲーションボタンの有効/無効状態を更新する
     */
    private void updateNavigationButtons() {
        var history = webEngine.getHistory();
        backButton.setDisable(history.getCurrentIndex() <= 0);
        forwardButton.setDisable(history.getCurrentIndex() >= history.getEntries().size() - 1);
    }

    /**
     * メインレイアウトを作成する
     */
    private BorderPane createLayout() {
        BorderPane root = new BorderPane();

        // ツールバーの作成
        HBox toolbar = createToolbar();
        root.setTop(toolbar);

        // WebViewを中央に配置
        root.setCenter(webView);

        return root;
    }

    /**
     * ツールバーを作成する
     */
    private HBox createToolbar() {
        HBox toolbar = new HBox(10);
        toolbar.setPadding(new Insets(10));
        toolbar.setAlignment(Pos.CENTER_LEFT);
        toolbar.getStyleClass().add("toolbar");

        // URL入力フィールドを伸縮可能に設定
        HBox.setHgrow(urlField, Priority.ALWAYS);

        toolbar.getChildren().addAll(
                backButton,
                forwardButton,
                refreshButton,
                urlField,
                goButton
        );

        return toolbar;
    }

    /**
     * URLフィールドに入力されたページを読み込む
     */
    private void loadPage() {
        String url = urlField.getText().trim();
        if (!url.isEmpty()) {
            // プロトコルが指定されていない場合はhttpsを追加
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "https://" + url;
            }
            webEngine.load(url);
        }
    }

    /**
     * デフォルトページを読み込む
     */
    private void loadDefaultPage() {
        String defaultUrl = "https://www.google.com";
        urlField.setText(defaultUrl);
        webEngine.load(defaultUrl);
    }
}
