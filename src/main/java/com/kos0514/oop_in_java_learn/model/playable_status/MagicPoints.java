package com.kos0514.oop_in_java_learn.model.playable_status;

/**
 * 魔力値ステータスを表す値オブジェクト
 */
public class MagicPoints extends PlayableStatus {

    private static final String JAPANESE_NAME = "魔力値";

    private MagicPoints(int value) {
        super(value, JAPANESE_NAME);
    }

    /**
     * 整数値から魔力値の値オブジェクトを生成します
     *
     * @param value 魔力値の整数値
     * @return 妥当性が確認された魔力値の値オブジェクト
     * @throws IllegalArgumentException 妥当な範囲外の値が指定された場合
     */
    static MagicPoints of(int value) throws IllegalArgumentException {
        validateValue(value, JAPANESE_NAME);
        return new MagicPoints(value);
    }
}
