package com.kos0514.oop_in_java_learn.factory;

import com.kos0514.oop_in_java_learn.entity.generated.Race;
import com.kos0514.oop_in_java_learn.entity.generated.RaceStatusModifier;
import com.kos0514.oop_in_java_learn.mapper.RaceStatusModifierMapper;
import com.kos0514.oop_in_java_learn.model.playable_status.PlayableStatuses;
import com.kos0514.oop_in_java_learn.model.value.Age;
import com.kos0514.oop_in_java_learn.model.value.SoulId;
import com.kos0514.oop_in_java_learn.model.value.SoulName;
import com.kos0514.oop_in_java_learn.model.world.World;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransmigratorFactoryTest {

    @Mock
    private RaceStatusModifierMapper raceStatusModifierMapper;

    @Mock
    private PlayableStatusesFactory playableStatusesFactory;

    @InjectMocks
    private TransmigratorFactory transmigratorFactory;

    @Nested
    class Create {

        @Test
        void validParameters_createsTransmigrator() {
            // Arrange
            var soulName = SoulName.of("TestSoul");
            var age = Age.of(25);
            var world = mock(World.class);
            var race = createRace("race1", "TestRace");
            var raceStatusModifier = mock(RaceStatusModifier.class);
            var playableStatuses = mock(PlayableStatuses.class);

            when(raceStatusModifierMapper.selectByPrimaryKey(race.getId())).thenReturn(Optional.of(raceStatusModifier));
            when(playableStatusesFactory.create(eq(age), any(SoulId.class), eq(raceStatusModifier))).thenReturn(playableStatuses);

            // Act
            var result = transmigratorFactory.create(soulName, age, world, race);

            // Assert
            assertThat(result).isNotNull();
            assertThat(result.getSoulName()).isEqualTo(soulName);
            assertThat(result.getAge()).isEqualTo(age);
            assertThat(result.getWorld()).isEqualTo(world);
            assertThat(result.getRace()).isEqualTo(race);
            assertThat(result.getPlayableStatuses()).isEqualTo(playableStatuses);
            assertThat(result.getSoulId()).isNotNull();
        }

        @Test
        void raceStatusModifierNotFound_createsTransmigratorWithNullModifier() {
            // Arrange
            var soulName = SoulName.of("TestSoul");
            var age = Age.of(25);
            var world = mock(World.class);
            var race = createRace("race2", "TestRace2");
            var playableStatuses = mock(PlayableStatuses.class);

            when(raceStatusModifierMapper.selectByPrimaryKey(race.getId())).thenReturn(Optional.empty());
            when(playableStatusesFactory.create(eq(age), any(SoulId.class), eq(null))).thenReturn(playableStatuses);

            // Act
            var result = transmigratorFactory.create(soulName, age, world, race);

            // Assert
            assertThat(result).isNotNull();
            assertThat(result.getSoulName()).isEqualTo(soulName);
            assertThat(result.getAge()).isEqualTo(age);
            assertThat(result.getWorld()).isEqualTo(world);
            assertThat(result.getRace()).isEqualTo(race);
            assertThat(result.getPlayableStatuses()).isEqualTo(playableStatuses);
            assertThat(result.getSoulId()).isNotNull();
        }

        @Test
        void differentParameters_createsDifferentTransmigrators() {
            // Arrange
            var soulName1 = SoulName.of("TestSoul1");
            var soulName2 = SoulName.of("TestSoul2");
            var age = Age.of(25);
            var world = mock(World.class);
            var race = createRace("race3", "TestRace3");
            var raceStatusModifier = mock(RaceStatusModifier.class);
            var playableStatuses = mock(PlayableStatuses.class);

            when(raceStatusModifierMapper.selectByPrimaryKey(race.getId())).thenReturn(Optional.of(raceStatusModifier));
            when(playableStatusesFactory.create(eq(age), any(SoulId.class), eq(raceStatusModifier))).thenReturn(playableStatuses);

            // Act
            var result1 = transmigratorFactory.create(soulName1, age, world, race);
            var result2 = transmigratorFactory.create(soulName2, age, world, race);

            // Assert
            assertThat(result1).isNotNull();
            assertThat(result2).isNotNull();
            assertThat(result1.getSoulId()).isNotEqualTo(result2.getSoulId());
            assertThat(result1.getSoulName()).isNotEqualTo(result2.getSoulName());
        }

        @Test
        void sameParameters_createsDifferentTransmigratorsWithDifferentSoulIds() {
            // Arrange
            var soulName = SoulName.of("TestSoul");
            var age = Age.of(25);
            var world = mock(World.class);
            var race = createRace("race4", "TestRace4");
            var raceStatusModifier = mock(RaceStatusModifier.class);
            var playableStatuses = mock(PlayableStatuses.class);

            when(raceStatusModifierMapper.selectByPrimaryKey(race.getId())).thenReturn(Optional.of(raceStatusModifier));
            when(playableStatusesFactory.create(eq(age), any(SoulId.class), eq(raceStatusModifier))).thenReturn(playableStatuses);

            // Act
            var result1 = transmigratorFactory.create(soulName, age, world, race);
            var result2 = transmigratorFactory.create(soulName, age, world, race);

            // Assert
            assertThat(result1.getSoulId()).isNotEqualTo(result2.getSoulId());
        }
    }

    private Race createRace(String id, String name) {
        return new Race(
                id,                // id
                name,              // japaneseName
                "English" + name,  // englishName
                "STANDARD",        // rarity
                new Date(),        // createdAt
                new Date(),        // updatedAt
                "Special ability", // specialAbility
                "Description"      // description
        );
    }
}
