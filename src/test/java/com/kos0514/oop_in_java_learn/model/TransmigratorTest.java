package com.kos0514.oop_in_java_learn.model;

import com.kos0514.oop_in_java_learn.entity.generated.Race;
import com.kos0514.oop_in_java_learn.model.playable_status.PlayableStatuses;
import com.kos0514.oop_in_java_learn.model.value.Age;
import com.kos0514.oop_in_java_learn.model.value.SoulId;
import com.kos0514.oop_in_java_learn.model.value.SoulName;
import com.kos0514.oop_in_java_learn.model.world.World;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TransmigratorTest {

    @Nested
    class Builder {

        @Test
        void buildsTransmigratorWithAllProperties() {
            // Arrange
            var soulId = mock(SoulId.class);
            var soulName = mock(SoulName.class);
            var age = mock(Age.class);
            var world = mock(World.class);
            var race = mock(Race.class);
            var playableStatuses = mock(PlayableStatuses.class);

            // Act
            var transmigrator = Transmigrator.builder()
                    .soulId(soulId)
                    .soulName(soulName)
                    .age(age)
                    .world(world)
                    .race(race)
                    .playableStatuses(playableStatuses)
                    .build();

            // Assert
            assertThat(transmigrator).isNotNull();
            assertThat(transmigrator.getSoulId()).isSameAs(soulId);
            assertThat(transmigrator.getSoulName()).isSameAs(soulName);
            assertThat(transmigrator.getAge()).isSameAs(age);
            assertThat(transmigrator.getWorld()).isSameAs(world);
            assertThat(transmigrator.getRace()).isSameAs(race);
            assertThat(transmigrator.getPlayableStatuses()).isSameAs(playableStatuses);
        }
    }

    @Nested
    class Getters {

        @Test
        void returnsCorrectValues() {
            // Arrange
            var soulId = mock(SoulId.class);
            var soulName = mock(SoulName.class);
            var age = mock(Age.class);
            var world = mock(World.class);
            var race = mock(Race.class);
            var playableStatuses = mock(PlayableStatuses.class);

            var testUuid = UUID.randomUUID();
            when(soulId.getId()).thenReturn(testUuid);
            when(soulName.getName()).thenReturn("テスト魂");
            when(age.getValue()).thenReturn(25);
            when(world.getName()).thenReturn("テスト世界");
            when(race.getJapaneseName()).thenReturn("テスト種族");

            var transmigrator = Transmigrator.builder()
                    .soulId(soulId)
                    .soulName(soulName)
                    .age(age)
                    .world(world)
                    .race(race)
                    .playableStatuses(playableStatuses)
                    .build();

            // Act & Assert
            assertThat(transmigrator.getSoulId().getId()).isEqualTo(testUuid);
            assertThat(transmigrator.getSoulName().getName()).isEqualTo("テスト魂");
            assertThat(transmigrator.getAge().getValue()).isEqualTo(25);
            assertThat(transmigrator.getWorld().getName()).isEqualTo("テスト世界");
            assertThat(transmigrator.getRace().getJapaneseName()).isEqualTo("テスト種族");
        }
    }

}
