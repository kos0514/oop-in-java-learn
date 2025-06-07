package com.kos0514.oop_in_java_learn.io;

import com.kos0514.oop_in_java_learn.enums.RockPaperScissors;
import com.kos0514.oop_in_java_learn.io.test.TestComputerChoiceProvider;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RandomComputerChoiceProviderTest {

    @Nested
    class ChooseHand {

        @Test
        void returnsValidRockPaperScissorsValue() {
            // Arrange
            var provider = new RandomComputerChoiceProvider();

            // Act
            var result = provider.chooseHand();

            // Assert
            assertThat(result).isNotNull();
            assertThat(result).isInstanceOf(RockPaperScissors.class);
        }

        @Test
        void returnsAllPossibleValuesOverMultipleCalls() {
            // Arrange
            var provider = new RandomComputerChoiceProvider();
            var results = new HashSet<RockPaperScissors>();
            int attempts = 100; // High number to ensure all values are likely to be chosen

            // Act
            for (int i = 0; i < attempts; i++) {
                results.add(provider.chooseHand());
            }

            // Assert
            assertThat(results).hasSize(3); // Should contain all three possible values
            assertThat(results).contains(RockPaperScissors.ROCK, RockPaperScissors.PAPER, RockPaperScissors.SCISSORS);
        }
    }

    @Nested
    class ChooseHandWithPredefinedValues {

        @Test
        void returnsRockWhenConfigured() {
            // Arrange
            var provider = new TestComputerChoiceProvider()
                    .addChoice(RockPaperScissors.ROCK);

            // Act
            var result = provider.chooseHand();

            // Assert
            assertThat(result).isEqualTo(RockPaperScissors.ROCK);
        }

        @Test
        void returnsScissorsWhenConfigured() {
            // Arrange
            var provider = new TestComputerChoiceProvider()
                    .addChoice(RockPaperScissors.SCISSORS);

            // Act
            var result = provider.chooseHand();

            // Assert
            assertThat(result).isEqualTo(RockPaperScissors.SCISSORS);
        }

        @Test
        void returnsPaperWhenConfigured() {
            // Arrange
            var provider = new TestComputerChoiceProvider()
                    .addChoice(RockPaperScissors.PAPER);

            // Act
            var result = provider.chooseHand();

            // Assert
            assertThat(result).isEqualTo(RockPaperScissors.PAPER);
        }
    }
}
