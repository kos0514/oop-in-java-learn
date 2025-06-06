package com.kos0514.oop_in_java_learn.enums;

import lombok.Getter;

/**
 * じゃんけんの手を表す列挙型。
 */
@Getter
public enum RockPaperScissors {
    /**
     * グー
     */
    ROCK(1, "グー"),

    /**
     * チョキ
     */
    SCISSORS(2, "チョキ"),

    /**
     * パー
     */
    PAPER(3, "パー");

    /**
     * じゃんけんの手の数値表現
     */
    private final int value;

    /**
     * じゃんけんの手の日本語表記
     */
    private final String japaneseName;

    RockPaperScissors(int value, String japaneseName) {
        this.value = value;
        this.japaneseName = japaneseName;
    }

    /**
     * 数値からじゃんけんの手を取得します
     *
     * @param value 数値 (1: グー, 2: チョキ, 3: パー)
     * @return 対応するじゃんけんの手
     * @throws IllegalArgumentException 無効な数値の場合
     */
    public static RockPaperScissors fromValue(int value) {
        return switch (value) {
            case 1 -> ROCK;
            case 2 -> SCISSORS;
            case 3 -> PAPER;
            default -> throw new IllegalArgumentException("じゃんけんの手は1～3の範囲で入力してください");
        };
    }
}
