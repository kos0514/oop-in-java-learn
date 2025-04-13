package com.kos0514.oop_in_java_learn.factory;

import com.kos0514.oop_in_java_learn.model.Transmigrator;
import com.kos0514.oop_in_java_learn.model.value.SoulId;
import com.kos0514.oop_in_java_learn.model.value.SoulName;
import com.kos0514.oop_in_java_learn.model.value.Age;

import org.springframework.stereotype.Component;
import java.util.UUID;

/**
 * ビルダーパターンを利用した転生者ファクトリー
 */
@Component
public class TransmigratorFactory {

    /**
     * 新しい転生者ビルダーを作成します。
     *
     * @return TransmigratorBuilder インスタンス
     */
    public TransmigratorBuilder builder() {
        return new TransmigratorBuilder();
    }

    /**
     * 転生者を生成します。
     * @param firstName 名
     * @param lastName  姓
     * @param age 年齢
     * @return Transmigrator インスタンス
     */
    public Transmigrator createTransmigrator(String firstName, String lastName, int age) {
        return builder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withAge(age)
                .build();
    }

    /**
     * 転生者オブジェクトを構築するためのビルダークラス
     */
    public static class TransmigratorBuilder {
        private String firstName;
        private String lastName;
        private int age;

        public TransmigratorBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public TransmigratorBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public TransmigratorBuilder withAge(int age) {
            this.age = age;
            return this;
        }

        public Transmigrator build() {
            var soulId = new SoulId(UUID.randomUUID());
            var soulName = new SoulName(firstName, lastName);
            var ageValue = new Age(age);

            return new Transmigrator(soulId, soulName, ageValue);
        }
    }
}