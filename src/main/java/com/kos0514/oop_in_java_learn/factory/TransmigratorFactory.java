package com.kos0514.oop_in_java_learn.factory;

import com.kos0514.oop_in_java_learn.model.Transmigrator;
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
     * @return Transmigrator インスタンス
     */
    public Transmigrator createTransmigrator(SoulName soulName, Age age) {
        return builder()
                .withSoulName(soulName)
                .withAge(age)
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
        private SoulName soulName;
        private Age age;

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
         * ビルダーの設定値から転生者オブジェクトを構築します。
         *
         * @return 構築された転生者オブジェクト
         */
        public Transmigrator build() {
            return new Transmigrator(SoulId.newId(), soulName, age);
        }
    }
}