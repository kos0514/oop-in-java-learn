-- テスト用スキーマの作成
CREATE DATABASE IF NOT EXISTS transmigration_test
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;
GRANT ALL ON `transmigration_test`.* TO 'user'@'%';


USE transmigration_test;

-- 共通テーブル定義を読み込む
SOURCE /db-init/00_common_tables.sql;
