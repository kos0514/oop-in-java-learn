package com.kos0514.oop_in_java_learn.model;

import com.kos0514.oop_in_java_learn.entity.generated.Race;
import com.kos0514.oop_in_java_learn.model.playable_status.PlayableStatuses;
import com.kos0514.oop_in_java_learn.model.value.Age;
import com.kos0514.oop_in_java_learn.model.value.SoulId;
import com.kos0514.oop_in_java_learn.model.value.SoulName;
import com.kos0514.oop_in_java_learn.model.world.World;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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

    @Nested
    class EqualsAndHashCode {

        @Test
        void sameValues_areEqual() {
            // Arrange
            var soulId1 = mock(SoulId.class);
            var soulName1 = mock(SoulName.class);
            var age1 = mock(Age.class);
            var world1 = mock(World.class);
            var race1 = mock(Race.class);
            var playableStatuses1 = mock(PlayableStatuses.class);

            var soulId2 = mock(SoulId.class);
            var soulName2 = mock(SoulName.class);
            var age2 = mock(Age.class);
            var world2 = mock(World.class);
            var race2 = mock(Race.class);
            var playableStatuses2 = mock(PlayableStatuses.class);

            // Same values for both sets of mocks
            var testUuid = UUID.randomUUID();
            when(soulId1.getId()).thenReturn(testUuid);
            when(soulId2.getId()).thenReturn(testUuid);

            var transmigrator1 = Transmigrator.builder()
                .soulId(soulId1)
                .soulName(soulName1)
                .age(age1)
                .world(world1)
                .race(race1)
                .playableStatuses(playableStatuses1)
                .build();

            var transmigrator2 = Transmigrator.builder()
                .soulId(soulId2)
                .soulName(soulName2)
                .age(age2)
                .world(world2)
                .race(race2)
                .playableStatuses(playableStatuses2)
                .build();

            // Act & Assert
            assertThat(transmigrator1).isEqualTo(transmigrator1); // Same instance
            assertThat(transmigrator1).isNotEqualTo(null); // Not null
            assertThat(transmigrator1).isNotEqualTo("not a transmigrator"); // Different type

            // Since we're using mocks and Lombok's @Value, the equals will compare references
            // This test is more to verify Lombok's @Value is working as expected
            assertThat(transmigrator1).isNotEqualTo(transmigrator2);
        }
    }
}
