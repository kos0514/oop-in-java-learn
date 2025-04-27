package com.kos0514.oop_in_java_learn.model.value;

import com.kos0514.oop_in_java_learn.entity.generated.RaceParameterModifier;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Random;

/**
 * 転生者の基礎パラメータを表す値オブジェクト。
 * <p>
 * この不変クラスは転生者の基礎的な能力値（strength, vitality, intelligence, agility, dexterity, luck, healthPoints, magicPoints）を保持します。
 * Lombokの{@code @Value}アノテーションを使用しているため、
 * 全フィールドは暗黙的にprivate finalとなり、
 * getter、equals、hashCode、toStringメソッドが自動生成されます。
 * </p>
 */
@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseParameters {
    /**
     * 筋力 - 物理的な力を示す数値
     */
    int strength;

    /**
     * 体力 - 耐久性や最大HPへの影響
     */
    int vitality;

    /**
     * 知力 - 高度な思考力や最大MPへ影響
     */
    int intelligence;

    /**
     * 敏捷性 - 素早さや回避率
     */
    int agility;

    /**
     * 器用さ - 精密な操作のスキルに影響
     */
    int dexterity;

    /**
     * 運 - アイテムドロップ率やイベント発生確率
     */
    int luck;

    /**
     * 体力値 - 総耐久値
     */
    int healthPoints;

    /**
     * 魔力値 - 魔法を扱うエネルギー
     */
    int magicPoints;

    /**
     * 基礎パラメータの値オブジェクトを生成します
     * 内部使用のみを想定しているため、privateアクセス修飾子を使用
     *
     * @param strength 筋力
     * @param vitality 体力
     * @param intelligence 知力
     * @param agility 敏捷性
     * @param dexterity 器用さ
     * @param luck 運
     * @param healthPoints 体力値
     * @param magicPoints 魔力値
     * @return 妥当性が確認された基礎パラメータの値オブジェクト
     * @throws IllegalArgumentException 妥当な範囲外の値が指定された場合
     */
    private static BaseParameters of(int strength, int vitality, int intelligence, int agility, int dexterity, int luck, int healthPoints, int magicPoints) {
        validateParameters(strength, vitality, intelligence, agility, dexterity, luck, healthPoints, magicPoints);
        return new BaseParameters(strength, vitality, intelligence, agility, dexterity, luck, healthPoints, magicPoints);
    }

    /**
     * 年齢、SoulId、および種族に基づいた基礎パラメータを生成します
     *
     * @param age 転生者の年齢
     * @param soulId 転生者の魂ID（ランダム要素の生成に使用）
     * @param raceParameterModifier 種族のパラメータ修正値
     * @return 年齢、SoulId、および種族に基づいて調整された基礎パラメータの値オブジェクト
     */
    public static BaseParameters generateFrom(Age age, SoulId soulId, RaceParameterModifier raceParameterModifier) {
        // 既存のgenerateFromメソッドを呼び出して基本パラメータを取得
        var baseParams = generateFrom(age, soulId);

        // 種族の修正値がnullの場合は基本パラメータをそのまま返す
        if (raceParameterModifier == null) {
            return baseParams;
        }

        // 種族の修正値を適用
        var adjustedStrength = Math.max(1, baseParams.getStrength() + raceParameterModifier.getStrengthMod());
        var adjustedVitality = Math.max(1, baseParams.getVitality() + raceParameterModifier.getVitalityMod());
        var adjustedIntelligence = Math.max(1, baseParams.getIntelligence() + raceParameterModifier.getIntelligenceMod());
        var adjustedAgility = Math.max(1, baseParams.getAgility() + raceParameterModifier.getAgilityMod());
        var adjustedDexterity = Math.max(1, baseParams.getDexterity() + raceParameterModifier.getDexterityMod());
        var adjustedLuck = Math.max(1, baseParams.getLuck() + raceParameterModifier.getLuckMod());
        var adjustedHealthPoints = Math.max(1, baseParams.getHealthPoints() + raceParameterModifier.getHealthPointsMod());
        var adjustedMagicPoints = Math.max(1, baseParams.getMagicPoints() + raceParameterModifier.getMagicPointsMod());

        return of(adjustedStrength, adjustedVitality, adjustedIntelligence, adjustedAgility,
                adjustedDexterity, adjustedLuck, adjustedHealthPoints, adjustedMagicPoints);
    }

    /**
     * 年齢とSoulIdに基づいた基礎パラメータを生成します
     * 
     * @param age 転生者の年齢
     * @param soulId 転生者の魂ID（ランダム要素の生成に使用）
     * @return 年齢とSoulIdに基づいて調整された基礎パラメータの値オブジェクト
     */
    private static BaseParameters generateFrom(Age age, SoulId soulId) {
        // 年齢に基づいた基本パラメータの生成ロジック
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

    /**
     * パラメータ値の妥当性を検証します
     *
     * @param strength 筋力
     * @param vitality 体力
     * @param intelligence 知力
     * @param agility 敏捷性
     * @param dexterity 器用さ
     * @param luck 運
     * @param healthPoints 体力値
     * @param magicPoints 魔力値
     * @throws IllegalArgumentException 妥当な範囲外の値が指定された場合
     */
    private static void validateParameters(int strength, int vitality, int intelligence, int agility, int dexterity, int luck, int healthPoints, int magicPoints) {
        validateStatParameter("strength", strength);
        validateStatParameter("vitality", vitality);
        validateStatParameter("intelligence", intelligence);
        validateStatParameter("agility", agility);
        validateStatParameter("dexterity", dexterity);
        validateStatParameter("luck", luck);
        validateHpMpParameter("healthPoints", healthPoints);
        validateHpMpParameter("magicPoints", magicPoints);
    }

    /**
     * 基本ステータスパラメータの妥当性を検証します
     *
     * @param paramName パラメータ名
     * @param value パラメータ値
     * @throws IllegalArgumentException 妥当な範囲外の値が指定された場合
     */
    private static void validateStatParameter(String paramName, int value) {
        if (value < 1) {
            throw new IllegalArgumentException(paramName + "は1以上の値である必要があります");
        }
    }

    /**
     * HP/MPパラメータの妥当性を検証します
     *
     * @param paramName パラメータ名
     * @param value パラメータ値
     * @throws IllegalArgumentException 妥当な範囲外の値が指定された場合
     */
    private static void validateHpMpParameter(String paramName, int value) {
        if (value < 1) {
            throw new IllegalArgumentException(paramName + "は1以上の値である必要があります");
        }
    }
}
