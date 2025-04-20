package com.kos0514.oop_in_java_learn.model.value;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * 転生者の名前を表す値オブジェクト。
 * <p>
 * この不変クラスは転生者の名前を文字列として保持します。(姓・名は分割されません)
 * Lombokの{@code @Value}アノテーションを使用しているため、
 * 全フィールドは暗黙的にprivate finalとなり、
 * getter、equals、hashCode、toStringメソッドが自動生成されます。
 * </p>
 */
@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SoulName {
    /**
     * 転生者の名前
     */
    String name;

    /**
     * 名と姓から転生者の名前を表す値オブジェクトを生成します
     *
     * @param name 転生者の名前
     * @throws IllegalArgumentException 転生者の名前
     */
    public static SoulName of(String name) throws IllegalArgumentException {
        validate(name);
        return new SoulName(name);
    }

    /**
     * 転生者の名前の妥当性を検証します
     *
     * @param name 転生者の名前
     * @throws IllegalArgumentException 転生者の名前が空の場合
     */
    private static void validate(String name) throws IllegalArgumentException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("名前は空にできません");
        }
    }
}