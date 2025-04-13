package com.kos0514.oop_in_java_learn.model.value;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * 転生者の名前を表す値オブジェクト。
 * <p>
 * この不変クラスは転生者の名前（名と姓）を文字列として保持します。
 * Lombokの{@code @Value}アノテーションを使用しているため、
 * 全フィールドは暗黙的にprivate finalとなり、
 * getter、equals、hashCode、toStringメソッドが自動生成されます。
 * </p>
 */
@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SoulName {
    /**
     * 名
     */
    String firstName;

    /**
     * 姓
     */
    String lastName;

    /**
     * 名と姓から転生者の名前を表す値オブジェクトを生成します
     *
     * @param firstName 名
     * @param lastName 姓
     * @return 妥当性が確認された名前の値オブジェクト
     * @throws IllegalArgumentException 名または姓が空の場合
     */
    public static SoulName of(String firstName, String lastName) {
        validate(firstName, lastName);
        return new SoulName(firstName, lastName);
    }

    /**
     * 名と姓の妥当性を検証します
     *
     * @param firstName 検証する名
     * @param lastName 検証する姓
     * @throws IllegalArgumentException 名または姓が空の場合
     */
    private static void validate(String firstName, String lastName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("名前（名）は空にできません");
        }

        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("名前（姓）は空にできません");
        }
    }

    /**
     * 「姓 名」形式のフルネームを取得します
     *
     * @return 「姓 名」形式のフルネーム
     */
    public String getFullName() {
        return lastName + " " + firstName;
    }
}