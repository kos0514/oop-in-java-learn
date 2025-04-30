package com.kos0514.oop_in_java_learn.model.playable_status;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * プレイヤーステータスの値オブジェクトの基底クラス
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
abstract class PlayableStatus {
    /**
     * プレイヤーステータス値
     */
    private final int value;

    /**
     * プレイヤーステータスの日本語名
     */
    private final String japaneseName;

    /**
     * プレイヤーステータスの最小値（デフォルトは1）
     */
    private static final int MIN_VALUE = 1;

    /**
     * プレイヤーステータス値の妥当性を検証する
     *
     * @param value        検証する値
     * @param japaneseName プレイヤーステータスの日本語名
     * @throws IllegalArgumentException 妥当な範囲外の値が指定された場合
     */
    protected static void validateValue(int value, String japaneseName) throws IllegalArgumentException {
        if (value <= MIN_VALUE) {
            throw new IllegalArgumentException(japaneseName + "は" + MIN_VALUE + "以上の値である必要があります");
        }
    }
}
