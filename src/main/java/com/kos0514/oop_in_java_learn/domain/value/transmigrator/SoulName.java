package com.kos0514.oop_in_java_learn.domain.value.transmigrator;

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
public class SoulName {
    /**
     * 名
     */
    String firstName;

    /**
     * 姓
     */
    String lastName;
}