package com.kos0514.oop_in_java_learn.model.playable_status;

/**
 * 体力ステータスを表す値オブジェクト
 */
public class Vitality extends PlayableStatus {

    private static final String JAPANESE_NAME = "体力";

    private Vitality(int value) {
        super(value, JAPANESE_NAME);
    }

    /**
     * 整数値から体力の値オブジェクトを生成します
     *
     * @param value 体力の整数値
     * @return 妥当性が確認された体力の値オブジェクト
     * @throws IllegalArgumentException 妥当な範囲外の値が指定された場合
     */
    static Vitality of(int value) throws IllegalArgumentException {
        validateValue(value, JAPANESE_NAME);
        return new Vitality(value);
    }
}
