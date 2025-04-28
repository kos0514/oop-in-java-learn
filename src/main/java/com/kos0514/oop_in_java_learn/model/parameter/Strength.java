package com.kos0514.oop_in_java_learn.model.parameter;

/**
 * 筋力パラメータを表す値オブジェクト
 */
public class Strength extends Parameter {
    
    private static final String JAPANESE_NAME = "筋力";
    
    private Strength(int value) {
        super(value, JAPANESE_NAME);
    }
    
    /**
     * 整数値から筋力の値オブジェクトを生成します
     *
     * @param value 筋力の整数値
     * @return 妥当性が確認された筋力の値オブジェクト
     * @throws IllegalArgumentException 妥当な範囲外の値が指定された場合
     */
    static Strength of(int value) throws IllegalArgumentException {
        validateValue(value, JAPANESE_NAME);
        return new Strength(value);
    }
}
