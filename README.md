# OOP-in-Java-Learn プロジェクト

このプロジェクトは Spring Boot を使用したコンソールベースの「異世界転生」（Isekai Transmigration）ゲームです。

## プロジェクトのセットアップ方法

### 1. Docker を使用したデータベースのセットアップ

このプロジェクトは MySQL データベースを使用しています。Docker Compose を使用して簡単にセットアップできます。

```bash
docker compose up -d
```

これにより、以下の設定で MySQL データベースが起動します：

#### MySQL 接続設定

- **データベース名**: transmigration
- **ユーザー名**: user
- **パスワード**: password
- **ポート**: 3306
- **接続 URL**: jdbc:mysql://localhost:3306/transmigration
- **タイムゾーン**: Asia/Tokyo
- **文字コード**: utf8mb4
- **照合順序**: utf8mb4_unicode_ci

### 2. MyBatis Generator の実行

データベースのテーブルからエンティティクラスとマッパーを自動生成するには、以下のコマンドを実行します：

```bash
./gradlew mbGenerator
```

これにより、以下のファイルが生成されます：

- エンティティクラス: `src/main/java/com/kos0514/oop_in_java_learn/entity/generated/`
- マッパークラス: `src/main/java/com/kos0514/oop_in_java_learn/mapper/generated/`

## プロジェクト情報

- **Java バージョン**: 21
- **Spring Boot バージョン**: 3.4.4
- **MyBatis バージョン**: 3.0.3