package com.kos0514.oop_in_java_learn.enums;

import lombok.Getter;

/**
 * 種族の希少度を表す列挙型。
 * 希少度に応じて、じゃんけんでの勝利回数が必要になります。
 */
@Getter
public enum RaceRarity {
    /**
     * 一般的な種族（通常選択可能）
     */
    STANDARD(0, "一般"),

    /**
     * レア種族（じゃんけん1回勝利で選択可能）
     */
    UNIQUE(1, "レア"),

    /**
     * スーパーレア種族（じゃんけん2回勝利で選択可能）
     */
    LEGENDARY(2, "スーパーレア"),

    /**
     * ウルトラレア種族（じゃんけん3回勝利で選択可能）
     */
    SECRET(3, "ウルトラレア");

    /**
     *  希少度の種族を選択するために必要なじゃんけんの勝利回数
     */
    private final int requiredWins;
    /**
     *  希少度の日本語表記
     */
    private final String japaneseDescription;

    RaceRarity(int requiredWins, String japaneseDescription) {
        this.requiredWins = requiredWins;
        this.japaneseDescription = japaneseDescription;
    }
}
