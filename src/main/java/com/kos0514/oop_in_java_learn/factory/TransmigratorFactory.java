package com.kos0514.oop_in_java_learn.factory;

import com.kos0514.oop_in_java_learn.entity.generated.Race;
import com.kos0514.oop_in_java_learn.model.Transmigrator;
import com.kos0514.oop_in_java_learn.model.world.World;
import com.kos0514.oop_in_java_learn.model.value.BaseParameters;
import com.kos0514.oop_in_java_learn.model.value.SoulId;
import com.kos0514.oop_in_java_learn.model.value.SoulName;
import com.kos0514.oop_in_java_learn.model.value.Age;

import org.springframework.stereotype.Component;

/**
 * ビルダーパターンを利用した転生者ファクトリー
 */
@Component
public class TransmigratorFactory {

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
        // 魂IDを先に生成して、基礎パラメータ生成に使用する
        var soulId = SoulId.newId();

        return builder()
                .withSoulId(soulId)
                .withSoulName(soulName)
                .withAge(age)
                .withWorld(world)
                .withRace(race)
                .withBaseParameters(BaseParameters.generateFrom(age, soulId))
                .build();
    }

    /**
     * 新しい転生者ビルダーを作成します。
     *
     * @return TransmigratorBuilder インスタンス
     */
    private TransmigratorBuilder builder() {
        return new TransmigratorBuilder();
    }

    /**
     * 転生者オブジェクトを構築するためのビルダークラス
     */
    private static class TransmigratorBuilder {
        private SoulId soulId;
        private SoulName soulName;
        private Age age;
        private World world;
        private Race race;
        private BaseParameters parameters;

        /**
         * 転生者の魂IDを設定します。
         *
         * @param soulId 転生者の魂IDを表す値オブジェクト
         * @return このビルダーインスタンス
         */
        public TransmigratorBuilder withSoulId(SoulId soulId) {
            this.soulId = soulId;
            return this;
        }

        /**
         * 転生者の名前を設定します。
         *
         * @param soulName 転生者の名前を表す値オブジェクト
         * @return このビルダーインスタンス
         */
        public TransmigratorBuilder withSoulName(SoulName soulName) {
            this.soulName = soulName;
            return this;
        }

        /**
         * 転生者の年齢を設定します。
         *
         * @param age 転生者の年齢を表す値オブジェクト
         * @return このビルダーインスタンス
         */
        public TransmigratorBuilder withAge(Age age) {
            this.age = age;
            return this;
        }

        /**
         * 転生先の世界を設定します。
         *
         * @param world 転生先の世界
         * @return このビルダーインスタンス
         */
        public TransmigratorBuilder withWorld(World world) {
            this.world = world;
            return this;
        }

        /**
         * 転生する種族を設定します。
         *
         * @param race 転生する種族
         * @return このビルダーインスタンス
         */
        public TransmigratorBuilder withRace(Race race) {
            this.race = race;
            return this;
        }

        /**
         * 基礎パラメータを設定します。
         *
         * @param parameters 基礎パラメータを表す値オブジェクト
         * @return このビルダーインスタンス
         */
        public TransmigratorBuilder withBaseParameters(BaseParameters parameters) {
            this.parameters = parameters;
            return this;
        }

        /**
         * ビルダーの設定値から転生者オブジェクトを構築します。
         *
         * @return 構築された転生者オブジェクト
         */
        public Transmigrator build() {
            return new Transmigrator(soulId, soulName, age, world, race, parameters);
        }
    }
}
