package com.kos0514.oop_in_java_learn.model.parameter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * パラメータ値オブジェクトの基底クラス
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
abstract class Parameter {
    /**
     * パラメータ値
     */
    private final int value;
    
    /**
     * パラメータの日本語名
     */
    private final String japaneseName;
    
    /**
     * パラメータの最小値（デフォルトは1）
     */
    private static final int MIN_VALUE = 1;

    /**
     * パラメータ値の妥当性を検証する
     * 
     * @param value 検証する値
     * @param japaneseName パラメータの日本語名
     * @throws IllegalArgumentException 妥当な範囲外の値が指定された場合
     */
    protected static void validateValue(int value, String japaneseName) throws IllegalArgumentException {
        if (value <= MIN_VALUE) {
            throw new IllegalArgumentException(japaneseName + "は" + MIN_VALUE + "以上の値である必要があります");
        }
    }
}
