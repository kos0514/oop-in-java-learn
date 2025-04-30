package com.kos0514.oop_in_java_learn.model.playable_status;

/**
 * 知力ステータスを表す値オブジェクト
 */
public class Intelligence extends PlayableStatus {

    private static final String JAPANESE_NAME = "知力";

    private Intelligence(int value) {
        super(value, JAPANESE_NAME);
    }

    /**
     * 整数値から知力の値オブジェクトを生成します
     *
     * @param value 知力の整数値
     * @return 妥当性が確認された知力の値オブジェクト
     * @throws IllegalArgumentException 妥当な範囲外の値が指定された場合
     */
    static Intelligence of(int value) throws IllegalArgumentException {
        validateValue(value, JAPANESE_NAME);
        return new Intelligence(value);
    }
}
