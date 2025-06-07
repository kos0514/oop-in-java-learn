package com.kos0514.oop_in_java_learn.model.playable_status;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import static com.kos0514.oop_in_java_learn.util.log.LoggingUtils.info;
import static com.kos0514.oop_in_java_learn.util.log.LoggingUtils.printSeparator;

/**
 * 転生者の基礎ステータスを表す値オブジェクト。
 * <p>
 * この不変クラスは転生者の基礎的な能力値（strength, vitality, intelligence, agility, dexterity, luck, healthPoints, magicPoints）を保持します。
 * Lombokの{@code @Value}アノテーションを使用しているため、
 * 全フィールドは暗黙的にprivate finalとなり、
 * getter、equals、hashCode、toStringメソッドが自動生成されます。
 * </p>
 */
@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayableStatuses {
    /**
     * 筋力 - 物理的な力を示す数値
     */
    Strength strength;

    /**
     * 体力 - 耐久性や最大HPへの影響
     */
    Vitality vitality;

    /**
     * 知力 - 高度な思考力や最大MPへ影響
     */
    Intelligence intelligence;

    /**
     * 敏捷性 - 素早さや回避率
     */
    Agility agility;

    /**
     * 器用さ - 精密な操作のスキルに影響
     */
    Dexterity dexterity;

    /**
     * 運 - アイテムドロップ率やイベント発生確率
     */
    Luck luck;

    /**
     * 体力値 - 総耐久値
     */
    HealthPoints healthPoints;

    /**
     * 魔力値 - 魔法を扱うエネルギー
     */
    MagicPoints magicPoints;

    /**
     * 整数値から基礎ステータスの値オブジェクトを生成します
     *
     * @param strength     筋力の整数値
     * @param vitality     体力の整数値
     * @param intelligence 知力の整数値
     * @param agility      敏捷性の整数値
     * @param dexterity    器用さの整数値
     * @param luck         運の整数値
     * @param healthPoints 体力値の整数値
     * @param magicPoints  魔力値の整数値
     * @return 妥当性が確認された基礎ステータスの値オブジェクト
     * @throws IllegalArgumentException 妥当な範囲外の値が指定された場合
     */
    public static PlayableStatuses of(int strength, int vitality, int intelligence, int agility, int dexterity, int luck, int healthPoints, int magicPoints) {
        return new PlayableStatuses(
                Strength.of(strength),
                Vitality.of(vitality),
                Intelligence.of(intelligence),
                Agility.of(agility),
                Dexterity.of(dexterity),
                Luck.of(luck),
                HealthPoints.of(healthPoints),
                MagicPoints.of(magicPoints)
        );
    }

    /**
     * 転生者の基礎ステータスを表示します。
     */
    public void showStatus() {
        printSeparator();
        info("【基礎ステータス】");
        info("STR: {}", getStrength().getValue());
        info("VIT: {}", getVitality().getValue());
        info("INT: {}", getIntelligence().getValue());
        info("AGI: {}", getAgility().getValue());
        info("DEX: {}", getDexterity().getValue());
        info("LUC: {}", getLuck().getValue());
        info("HP: {}", getHealthPoints().getValue());
        info("MP: {}", getMagicPoints().getValue());
        printSeparator();
    }
}
