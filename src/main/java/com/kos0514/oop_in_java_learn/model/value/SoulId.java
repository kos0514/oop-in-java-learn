package com.kos0514.oop_in_java_learn.model.value;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.UUID;

/**
 * 転生者の魂IDを表す値オブジェクト。
 * <p>
 * この不変クラスは転生者の魂IDをUUIDとして保持します。
 * Lombokの{@code @Value}アノテーションを使用しているため、
 * 全フィールドは暗黙的にprivate finalとなり、
 * getter、equals、hashCode、toStringメソッドが自動生成されます。
 * </p>
 */
@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SoulId {
    /**
     * 魂ID
     */
    UUID id;

    /**
     * 新しいランダムなSoulIdを生成するファクトリーメソッド
     *
     * @return 新しい魂ID
     */
    public static SoulId newId() {
        return new SoulId(UUID.randomUUID());
    }
}