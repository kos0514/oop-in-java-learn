package com.kos0514.oop_in_java_learn.model.world;

/**
 * 転生先の世界を表すインターフェース。
 * 各世界は名前と説明を持ちます。
 */
public interface World {
    /**
     * 世界の名前を取得します。
     * @return 世界の名前
     */
    String getName();

    /**
     * 世界の説明を取得します。
     * @return 世界の説明
     */
    String getDescription();
}