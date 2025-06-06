package com.kos0514.oop_in_java_learn.model;

import com.kos0514.oop_in_java_learn.entity.generated.Race;
import com.kos0514.oop_in_java_learn.model.playable_status.PlayableStatuses;
import com.kos0514.oop_in_java_learn.model.value.Age;
import com.kos0514.oop_in_java_learn.model.value.SoulId;
import com.kos0514.oop_in_java_learn.model.value.SoulName;
import com.kos0514.oop_in_java_learn.model.world.World;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * 転生者エンティティを表すクラス。
 * <p>
 * この不変クラスは転生者の魂ID、名前、年齢、基礎ステータスを保持します。
 * Lombokの{@code @Value}アノテーションを使用しているため、
 * 全フィールドは暗黙的にprivate finalとなり、
 * getter、equals、hashCode、toStringメソッドが自動生成されます。
 * </p>
 */
@Value
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Transmigrator {
    /**
     * 魂ID
     */
    SoulId soulId;

    /**
     * 名前
     */
    SoulName soulName;

    /**
     * 年齢
     */
    Age age;

    /**
     * 転生先の世界
     */
    World world;

    /**
     * 種族
     */
    Race race;

    /**
     * 基礎ステータス
     */
    PlayableStatuses playableStatuses;
}
