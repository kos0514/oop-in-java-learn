package com.kos0514.oop_in_java_learn.model.value;

import lombok.Value;

/**
 * 転生者の年齢を表す値オブジェクト。
 * <p>
 * この不変クラスは転生者の年齢（歳数）を整数値として保持します。
 * Lombokの{@code @Value}アノテーションを使用しているため、
 * 全フィールドは暗黙的にprivate finalとなり、
 * getter、equals、hashCode、toStringメソッドが自動生成されます。
 * </p>
 */
@Value
public class Age {
    /**
     * 年齢（歳）
     */
    int value;

    /**
     * 整数値から年齢の値オブジェクトを生成します
     *
     * @param value 年齢の整数値
     * @return 妥当性が確認された年齢の値オブジェクト
     * @throws IllegalArgumentException 妥当な範囲外の値が指定された場合
     */
    public static Age of(int value) throws IllegalArgumentException {
        validateRange(value);
        return new Age(value);
    }

    /**
     * 文字列から年齢の値オブジェクトを生成します
     *
     * @param ageInput 年齢を表す文字列
     * @return 妥当性が確認された年齢の値オブジェクト
     * @throws IllegalArgumentException 数値に変換できない、または妥当な範囲外の値が指定された場合
     */
    public static Age from(String ageInput) throws IllegalArgumentException {
        if (ageInput == null || ageInput.trim().isEmpty()) {
            throw new IllegalArgumentException("年齢を入力してください");
        }

        try {
            int value = Integer.parseInt(ageInput.trim());
            return of(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("年齢には数値を入力してください", e);
        }
    }

    /**
     * 年齢の範囲を検証します
     *
     * @param value 検証する年齢の値
     * @throws IllegalArgumentException 妥当な範囲外の値が指定された場合
     */
    private static void validateRange(int value) throws IllegalArgumentException {
        if (value < 1 || value > 120) {
            throw new IllegalArgumentException("年齢は1～120の範囲で入力してください");
        }
    }

}