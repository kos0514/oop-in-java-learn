package com.kos0514.oop_in_java_learn.repository;

import com.kos0514.oop_in_java_learn.model.world.CultivationWorld;
import com.kos0514.oop_in_java_learn.model.world.FantasyWorld;
import com.kos0514.oop_in_java_learn.model.world.FullDiveGameWorld;
import com.kos0514.oop_in_java_learn.model.world.MagicTechWorld;
import com.kos0514.oop_in_java_learn.model.world.World;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 利用可能な転生先世界のリポジトリクラス。
 * <p>
 * 現時点ではメモリ内で初期化されたデータを返しますが、将来的には
 * データベースや外部APIからデータを取得することを想定しています。
 */
@Repository
public class WorldRepository {

    /**
     * 利用可能な世界のリストを取得します。
     *
     * @return 利用可能な世界のリスト
     */
    public List<World> getAvailableWorlds() {
        // 現在は固定リストを返却する
        return List.of(
                new FantasyWorld(),
                new MagicTechWorld(),
                new CultivationWorld(),
                new FullDiveGameWorld()
        );
    }
}