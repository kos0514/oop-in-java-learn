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
    int years;
}