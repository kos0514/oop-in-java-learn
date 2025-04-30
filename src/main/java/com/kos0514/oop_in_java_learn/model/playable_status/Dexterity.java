package com.kos0514.oop_in_java_learn.model.playable_status;

/**
 * 器用さのステータスを表す値オブジェクト
 */
public class Dexterity extends PlayableStatus {

    private static final String JAPANESE_NAME = "器用さ";

    private Dexterity(int value) {
        super(value, JAPANESE_NAME);
    }

    /**
     * 整数値から器用さの値オブジェクトを生成します
     *
     * @param value 器用さの整数値
     * @return 妥当性が確認された器用さの値オブジェクト
     * @throws IllegalArgumentException 妥当な範囲外の値が指定された場合
     */
    static Dexterity of(int value) throws IllegalArgumentException {
        validateValue(value, JAPANESE_NAME);
        return new Dexterity(value);
    }
}
