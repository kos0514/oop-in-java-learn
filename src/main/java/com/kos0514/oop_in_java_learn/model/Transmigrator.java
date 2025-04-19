package com.kos0514.oop_in_java_learn.model;

import com.kos0514.oop_in_java_learn.model.value.Age;
import com.kos0514.oop_in_java_learn.model.value.SoulId;
import com.kos0514.oop_in_java_learn.model.value.SoulName;
import com.kos0514.oop_in_java_learn.model.world.World;
import lombok.Value;

/**
 * 転生者エンティティを表すクラス。
 * <p>
 * この不変クラスは転生者の魂ID、名前、および年齢を保持します。
 * Lombokの{@code @Value}アノテーションを使用しているため、
 * 全フィールドは暗黙的にprivate finalとなり、
 * getter、equals、hashCode、toStringメソッドが自動生成されます。
 * </p>
 */
@Value
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
}
