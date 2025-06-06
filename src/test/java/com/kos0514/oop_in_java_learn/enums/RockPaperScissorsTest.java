package com.kos0514.oop_in_java_learn.enums;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RockPaperScissorsTest {

    @Nested
    class EnumValues {
        
        @Test
        void hasCorrectNumberOfValues() {
            // Arrange & Act
            var values = RockPaperScissors.values();
            
            // Assert
            assertThat(values).hasSize(3);
        }
        
        @Test
        void containsExpectedValues() {
            // Arrange & Act
            var values = RockPaperScissors.values();
            
            // Assert
            assertThat(values)
                .extracting(Enum::name)
                .containsExactly("ROCK", "SCISSORS", "PAPER");
        }
    }
    
    @Nested
    class NumericValues {
        
        @ParameterizedTest
        @CsvSource({
            "ROCK, 1",
            "SCISSORS, 2",
            "PAPER, 3"
        })
        void hasCorrectNumericValue(RockPaperScissors hand, int expectedValue) {
            // Act
            var actualValue = hand.getValue();
            
            // Assert
            assertThat(actualValue).isEqualTo(expectedValue);
        }
    }
    
    @Nested
    class JapaneseNames {
        
        @ParameterizedTest
        @CsvSource({
            "ROCK, グー",
            "SCISSORS, チョキ",
            "PAPER, パー"
        })
        void hasCorrectJapaneseName(RockPaperScissors hand, String expectedName) {
            // Act
            var actualName = hand.getJapaneseName();
            
            // Assert
            assertThat(actualName).isEqualTo(expectedName);
        }
        
        @Test
        void allValuesHaveNonEmptyJapaneseNames() {
            // Act & Assert
            assertThat(RockPaperScissors.values())
                .extracting(RockPaperScissors::getJapaneseName)
                .doesNotContainNull()
                .allSatisfy(name -> assertThat(name).isNotBlank());
        }
    }
    
    @Nested
    class FromValue {
        
        @ParameterizedTest
        @CsvSource({
            "1, ROCK",
            "2, SCISSORS",
            "3, PAPER"
        })
        void returnsCorrectEnumForValidValue(int value, RockPaperScissors expectedHand) {
            // Act
            var actualHand = RockPaperScissors.fromValue(value);
            
            // Assert
            assertThat(actualHand).isEqualTo(expectedHand);
        }
        
        @ParameterizedTest
        @ValueSource(ints = {-1, 0, 4, 100})
        void throwsExceptionForInvalidValue(int invalidValue) {
            // Act & Assert
            assertThatThrownBy(() -> RockPaperScissors.fromValue(invalidValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("じゃんけんの手は1～3の範囲で入力してください");
        }
    }
}