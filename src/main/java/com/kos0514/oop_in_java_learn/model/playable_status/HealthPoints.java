package com.kos0514.oop_in_java_learn.model.playable_status;

/**
 * 体力値ステータスを表す値オブジェクト
 */
public class HealthPoints extends PlayableStatus {

    private static final String JAPANESE_NAME = "体力値";

    private HealthPoints(int value) {
        super(value, JAPANESE_NAME);
    }

    /**
     * 整数値から体力値の値オブジェクトを生成します
     *
     * @param value 体力値の整数値
     * @return 妥当性が確認された体力値の値オブジェクト
     * @throws IllegalArgumentException 妥当な範囲外の値が指定された場合
     */
    static HealthPoints of(int value) throws IllegalArgumentException {
        validateValue(value, JAPANESE_NAME);
        return new HealthPoints(value);
    }
}
