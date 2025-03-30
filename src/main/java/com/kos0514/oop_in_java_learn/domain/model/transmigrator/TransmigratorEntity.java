package com.kos0514.oop_in_java_learn.domain.model.transmigrator;

    import com.kos0514.oop_in_java_learn.domain.value.transmigrator.Age;
    import com.kos0514.oop_in_java_learn.domain.value.transmigrator.SoulId;
    import com.kos0514.oop_in_java_learn.domain.value.transmigrator.SoulName;
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
    public class TransmigratorEntity {
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
    }