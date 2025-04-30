package com.kos0514.oop_in_java_learn.factory;

import com.kos0514.oop_in_java_learn.entity.generated.Race;
import com.kos0514.oop_in_java_learn.mapper.RaceStatusModifierMapper;
import com.kos0514.oop_in_java_learn.model.Transmigrator;
import com.kos0514.oop_in_java_learn.model.world.World;
import com.kos0514.oop_in_java_learn.model.playable_status.PlayableStatuses;
import com.kos0514.oop_in_java_learn.model.value.SoulId;
import com.kos0514.oop_in_java_learn.model.value.SoulName;
import com.kos0514.oop_in_java_learn.model.value.Age;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * ビルダーパターンを利用した転生者ファクトリー
 */
@Component
@RequiredArgsConstructor
public class TransmigratorFactory {

    private final RaceStatusModifierMapper raceStatusModifierMapper;

    /**
     * 転生者を生成します。
     *
     * @param soulName 転生者の名前を表す値オブジェクト
     * @param age 転生者の年齢を表す値オブジェクト
     * @param world 転生先の世界
     * @param race 転生する種族
     * @return Transmigrator インスタンス
     */
    public Transmigrator createTransmigrator(SoulName soulName, Age age, World world, Race race) {
        // 魂IDを先に生成して、基礎ステータス生成に使用する
        var soulId = SoulId.newId();

        // 種族のステータス修正値を取得
        var raceParameterModifier = raceStatusModifierMapper.selectByPrimaryKey(race.getId()).orElse(null);

        // 転生者のステータス値を生成
        var playableStatuses = PlayableStatuses.generateFrom(age, soulId, raceParameterModifier);

        return Transmigrator.builder()
                .soulId(soulId)
                .soulName(soulName)
                .age(age)
                .world(world)
                .race(race)
                .playableStatuses(playableStatuses)
                .build();
    }
}
