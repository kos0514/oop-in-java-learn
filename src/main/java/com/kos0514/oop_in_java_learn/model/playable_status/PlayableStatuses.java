package com.kos0514.oop_in_java_learn.model.playable_status;

import com.kos0514.oop_in_java_learn.entity.generated.RaceStatusModifier;
import com.kos0514.oop_in_java_learn.model.value.Age;
import com.kos0514.oop_in_java_learn.model.value.SoulId;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Random;

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
     * @param strength 筋力の整数値
     * @param vitality 体力の整数値
     * @param intelligence 知力の整数値
     * @param agility 敏捷性の整数値
     * @param dexterity 器用さの整数値
     * @param luck 運の整数値
     * @param healthPoints 体力値の整数値
     * @param magicPoints 魔力値の整数値
     * @return 妥当性が確認された基礎ステータスの値オブジェクト
     * @throws IllegalArgumentException 妥当な範囲外の値が指定された場合
     */
    private static PlayableStatuses of(int strength, int vitality, int intelligence, int agility, int dexterity, int luck, int healthPoints, int magicPoints) {
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
     * 年齢、SoulId、および種族に基づいた基礎ステータスを生成します
     *
     * @param age 転生者の年齢
     * @param soulId 転生者の魂ID（ランダム要素の生成に使用）
     * @param raceParameterModifier 種族のステータス修正値
     * @return 年齢、SoulId、および種族に基づいて調整された基礎ステータスの値オブジェクト
     */
    public static PlayableStatuses generateFrom(Age age, SoulId soulId, RaceStatusModifier raceParameterModifier) {
        // 既存のgenerateFromメソッドを呼び出して基本ステータスを取得
        var baseParams = generateFrom(age, soulId);

        // 種族の修正値がnullの場合は基本ステータスをそのまま返す
        if (raceParameterModifier == null) {
            return baseParams;
        }

        // 種族の修正値を適用
        var adjustedStrength = Math.max(1, baseParams.getStrength().getValue() + raceParameterModifier.getStrengthMod());
        var adjustedVitality = Math.max(1, baseParams.getVitality().getValue() + raceParameterModifier.getVitalityMod());
        var adjustedIntelligence = Math.max(1, baseParams.getIntelligence().getValue() + raceParameterModifier.getIntelligenceMod());
        var adjustedAgility = Math.max(1, baseParams.getAgility().getValue() + raceParameterModifier.getAgilityMod());
        var adjustedDexterity = Math.max(1, baseParams.getDexterity().getValue() + raceParameterModifier.getDexterityMod());
        var adjustedLuck = Math.max(1, baseParams.getLuck().getValue() + raceParameterModifier.getLuckMod());
        var adjustedHealthPoints = Math.max(1, baseParams.getHealthPoints().getValue() + raceParameterModifier.getHealthPointsMod());
        var adjustedMagicPoints = Math.max(1, baseParams.getMagicPoints().getValue() + raceParameterModifier.getMagicPointsMod());

        return of(adjustedStrength, adjustedVitality, adjustedIntelligence, adjustedAgility,
                adjustedDexterity, adjustedLuck, adjustedHealthPoints, adjustedMagicPoints);
    }

    /**
     * 年齢とSoulIdに基づいた基礎ステータスを生成します
     * 
     * @param age 転生者の年齢
     * @param soulId 転生者の魂ID（ランダム要素の生成に使用）
     * @return 年齢とSoulIdに基づいて調整された基礎ステータスの値オブジェクト
     */
    private static PlayableStatuses generateFrom(Age age, SoulId soulId) {
        // 年齢に基づいた基本ステータスの生成ロジック
        var ageValue = age.getValue();

        // 基本値
        var baseStrength = 10;
        var baseVitality = 10;
        var baseIntelligence = 10;
        var baseAgility = 10;
        var baseDexterity = 10;
        var baseLuck = 10;
        var baseHealthPoints = 100;
        var baseMagicPoints = 50;

        // 年齢による調整（例）
        // 若年層: agility/dexterity高め、intelligence低め
        // 中年層: バランス型
        // 高年層: intelligence高め、agility/dexterity低め
        if (ageValue < 20) {
            baseAgility += 2;
            baseDexterity += 2;
            baseIntelligence -= 1;
        } else if (ageValue > 50) {
            baseIntelligence += 2;
            baseAgility -= 1;
            baseDexterity -= 1;
        }

        // SoulIdからランダム要素を生成
        var random = new Random(soulId.getId().getLeastSignificantBits());

        // ±2の範囲でランダムに変動させ、かつ最小値を確保（1未満にならないようにする）
        var randomizedStrength = Math.max(1, baseStrength + random.nextInt(5) - 2);
        var randomizedVitality = Math.max(1, baseVitality + random.nextInt(5) - 2);
        var randomizedIntelligence = Math.max(1, baseIntelligence + random.nextInt(5) - 2);
        var randomizedAgility = Math.max(1, baseAgility + random.nextInt(5) - 2);
        var randomizedDexterity = Math.max(1, baseDexterity + random.nextInt(5) - 2);
        var randomizedLuck = Math.max(1, baseLuck + random.nextInt(5) - 2);

        // HP/MPも若干のランダム要素を持たせる
        var randomizedHealthPoints = baseHealthPoints + (random.nextInt(21) - 10); // ±10
        var randomizedMagicPoints = baseMagicPoints + (random.nextInt(11) - 5);   // ±5

        return of(randomizedStrength, randomizedVitality, randomizedIntelligence, randomizedAgility, 
                 randomizedDexterity, randomizedLuck, randomizedHealthPoints, randomizedMagicPoints);
    }
}
