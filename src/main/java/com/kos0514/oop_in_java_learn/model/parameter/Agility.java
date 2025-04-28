package com.kos0514.oop_in_java_learn.model.parameter;

/**
 * 敏捷性パラメータを表す値オブジェクト
 */
public class Agility extends Parameter {
    
    private static final String JAPANESE_NAME = "敏捷性";
    
    private Agility(int value) {
        super(value, JAPANESE_NAME);
    }
    
    /**
     * 整数値から敏捷性の値オブジェクトを生成します
     *
     * @param value 敏捷性の整数値
     * @return 妥当性が確認された敏捷性の値オブジェクト
     * @throws IllegalArgumentException 妥当な範囲外の値が指定された場合
     */
    static Agility of(int value) throws IllegalArgumentException {
        validateValue(value, JAPANESE_NAME);
        return new Agility(value);
    }
}
