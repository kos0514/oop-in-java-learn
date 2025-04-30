package com.kos0514.oop_in_java_learn.factory;

import com.kos0514.oop_in_java_learn.entity.generated.RaceStatusModifier;
import com.kos0514.oop_in_java_learn.model.playable_status.PlayableStatuses;
import com.kos0514.oop_in_java_learn.model.value.Age;
import com.kos0514.oop_in_java_learn.model.value.SoulId;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * PlayableStatusesの生成を担当するFactoryクラス
 */
@Component
public class PlayableStatusesFactory {

    /**
     * 年齢、SoulId、および種族修正値に基づいてステータスを生成します。
     *
     * @param age                転生者の年齢
     * @param soulId             転生者の魂ID（ランダム要素の生成に使用）
     * @param raceStatusModifier 種族のステータス修正値
     * @return 年齢、SoulId、および種族に基づいて調整された基礎ステータスの値オブジェクト
     */
    public PlayableStatuses create(Age age, SoulId soulId, RaceStatusModifier raceStatusModifier) {
        var baseStatus = generateBaseStatusFromAgeAndSoulId(age, soulId);

        if (raceStatusModifier == null) {
            return baseStatus;
        }

        return PlayableStatuses.of(
                Math.max(1, baseStatus.getStrength().getValue() + raceStatusModifier.getStrengthMod()),
                Math.max(1, baseStatus.getVitality().getValue() + raceStatusModifier.getVitalityMod()),
                Math.max(1, baseStatus.getIntelligence().getValue() + raceStatusModifier.getIntelligenceMod()),
                Math.max(1, baseStatus.getAgility().getValue() + raceStatusModifier.getAgilityMod()),
                Math.max(1, baseStatus.getDexterity().getValue() + raceStatusModifier.getDexterityMod()),
                Math.max(1, baseStatus.getLuck().getValue() + raceStatusModifier.getLuckMod()),
                Math.max(1, baseStatus.getHealthPoints().getValue() + raceStatusModifier.getHealthPointsMod()),
                Math.max(1, baseStatus.getMagicPoints().getValue() + raceStatusModifier.getMagicPointsMod())
        );
    }

    /**
     * 年齢とSoulIdに基づいた基礎ステータスを生成します
     *
     * @param age    転生者の年齢
     * @param soulId 転生者の魂ID（ランダム要素の生成に使用）
     * @return 年齢とSoulIdに基づいて調整された基礎ステータスの値オブジェクト
     */
    private PlayableStatuses generateBaseStatusFromAgeAndSoulId(Age age, SoulId soulId) {
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
        // HP/MPも若干のランダム要素を持たせる
        return PlayableStatuses.of(
                Math.max(1, baseStrength + random.nextInt(5) - 2),
                Math.max(1, baseVitality + random.nextInt(5) - 2),
                Math.max(1, baseIntelligence + random.nextInt(5) - 2),
                Math.max(1, baseAgility + random.nextInt(5) - 2),
                Math.max(1, baseDexterity + random.nextInt(5) - 2),
                Math.max(1, baseLuck + random.nextInt(5) - 2),
                baseHealthPoints + (random.nextInt(21) - 10), // ±10
                baseMagicPoints + (random.nextInt(11) - 5)   // ±5
        );
    }
}
