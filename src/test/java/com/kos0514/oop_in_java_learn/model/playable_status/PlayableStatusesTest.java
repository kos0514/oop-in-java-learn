package com.kos0514.oop_in_java_learn.model.playable_status;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.kos0514.oop_in_java_learn.util.LoggingUtils.info;
import static com.kos0514.oop_in_java_learn.util.LoggingUtils.printSeparator;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class PlayableStatusesTest {

    @Nested
    class Of {

        @Test
        void validValues_createsInstance() {
            // Arrange
            int strength = 10;
            int vitality = 8;
            int intelligence = 12;
            int agility = 9;
            int dexterity = 11;
            int luck = 7;
            int healthPoints = 100;
            int magicPoints = 50;

            // Act
            var statuses = PlayableStatuses.of(
                    strength, vitality, intelligence, agility,
                    dexterity, luck, healthPoints, magicPoints
            );

            // Assert
            assertThat(statuses).isNotNull();
            assertThat(statuses.getStrength().getValue()).isEqualTo(strength);
            assertThat(statuses.getVitality().getValue()).isEqualTo(vitality);
            assertThat(statuses.getIntelligence().getValue()).isEqualTo(intelligence);
            assertThat(statuses.getAgility().getValue()).isEqualTo(agility);
            assertThat(statuses.getDexterity().getValue()).isEqualTo(dexterity);
            assertThat(statuses.getLuck().getValue()).isEqualTo(luck);
            assertThat(statuses.getHealthPoints().getValue()).isEqualTo(healthPoints);
            assertThat(statuses.getMagicPoints().getValue()).isEqualTo(magicPoints);
        }

        @Test
        void invalidStrength_throwsException() {
            // Act & Assert
            assertThatThrownBy(() ->
                    PlayableStatuses.of(0, 8, 12, 9, 11, 7, 100, 50)
            )
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("筋力は1以上の値である必要があります");
        }

        @Test
        void invalidVitality_throwsException() {
            // Act & Assert
            assertThatThrownBy(() ->
                    PlayableStatuses.of(10, 0, 12, 9, 11, 7, 100, 50)
            )
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("体力は1以上の値である必要があります");
        }

        @Test
        void invalidIntelligence_throwsException() {
            // Act & Assert
            assertThatThrownBy(() ->
                    PlayableStatuses.of(10, 8, 0, 9, 11, 7, 100, 50)
            )
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("知力は1以上の値である必要があります");
        }
    }

    @Nested
    class ShowStatus {

        @Test
        void displaysAllStatusValues() {
            // Arrange
            // Create a PlayableStatuses instance using the factory method
            var statuses = PlayableStatuses.of(10, 8, 12, 9, 11, 7, 100, 50);

            // Act & Assert
            try (MockedStatic<com.kos0514.oop_in_java_learn.util.LoggingUtils> mockedLoggingUtils = mockStatic(com.kos0514.oop_in_java_learn.util.LoggingUtils.class)) {
                statuses.showStatus();

                // Verify printSeparator was called twice
                mockedLoggingUtils.verify(() -> printSeparator(), times(2));

                // Verify info calls with correct parameters
                mockedLoggingUtils.verify(() -> info("【基礎ステータス】"));
                mockedLoggingUtils.verify(() -> info("STR: {}", 10));
                mockedLoggingUtils.verify(() -> info("VIT: {}", 8));
                mockedLoggingUtils.verify(() -> info("INT: {}", 12));
                mockedLoggingUtils.verify(() -> info("AGI: {}", 9));
                mockedLoggingUtils.verify(() -> info("DEX: {}", 11));
                mockedLoggingUtils.verify(() -> info("LUC: {}", 7));
                mockedLoggingUtils.verify(() -> info("HP: {}", 100));
                mockedLoggingUtils.verify(() -> info("MP: {}", 50));
            }
        }
    }
}
