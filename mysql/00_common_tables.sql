-- Common table definitions for both main and test schemas

DROP TABLE IF EXISTS race_status_modifiers;
DROP TABLE IF EXISTS races;

-- 種族テーブル
CREATE TABLE races
(
    id              VARCHAR(36) PRIMARY KEY COMMENT '種族の一意識別子',
    japanese_name   VARCHAR(50)                                        NOT NULL COMMENT '種族の日本語名',
    english_name    VARCHAR(50)                                        NOT NULL COMMENT '種族の英語名',
    special_ability TEXT                                               NOT NULL COMMENT '種族固有の特殊能力',
    description     TEXT                                               NOT NULL COMMENT '種族の説明文',
    rarity          ENUM ('STANDARD', 'UNIQUE', 'LEGENDARY', 'SECRET') NOT NULL COMMENT '種族の希少度',
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時'
) COMMENT '転生可能な種族の基本情報を管理するテーブル';

-- 種族ステータス修正値テーブル
CREATE TABLE race_status_modifiers
(
    race_id           VARCHAR(36) PRIMARY KEY COMMENT '種族ID（racesテーブルの外部キー）',
    strength_mod      INT NOT NULL DEFAULT 0 COMMENT '筋力修正値',
    vitality_mod      INT NOT NULL DEFAULT 0 COMMENT '体力修正値',
    intelligence_mod  INT NOT NULL DEFAULT 0 COMMENT '知力修正値',
    agility_mod       INT NOT NULL DEFAULT 0 COMMENT '敏捷性修正値',
    dexterity_mod     INT NOT NULL DEFAULT 0 COMMENT '器用さ修正値',
    luck_mod          INT NOT NULL DEFAULT 0 COMMENT '運修正値',
    health_points_mod INT NOT NULL DEFAULT 0 COMMENT 'HP修正値',
    magic_points_mod  INT NOT NULL DEFAULT 0 COMMENT 'MP修正値',
    created_at        TIMESTAMP    DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
    updated_at        TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
    FOREIGN KEY (race_id) REFERENCES races (id) ON DELETE CASCADE
) COMMENT '種族ごとのステータス修正値を管理するテーブル';