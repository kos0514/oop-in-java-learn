package com.kos0514.oop_in_java_learn.model.playable_status;

/**
 * 運ステータスを表す値オブジェクト
 */
public class Luck extends PlayableStatus {
    
    private static final String JAPANESE_NAME = "運";
    
    private Luck(int value) {
        super(value, JAPANESE_NAME);
    }
    
    /**
     * 整数値から運の値オブジェクトを生成します
     *
     * @param value 運の整数値
     * @return 妥当性が確認された運の値オブジェクト
     * @throws IllegalArgumentException 妥当な範囲外の値が指定された場合
     */
    static Luck of(int value) throws IllegalArgumentException {
        validateValue(value, JAPANESE_NAME);
        return new Luck(value);
    }
}
