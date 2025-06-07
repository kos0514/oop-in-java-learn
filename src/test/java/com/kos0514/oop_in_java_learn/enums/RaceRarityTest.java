package com.kos0514.oop_in_java_learn.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("RaceRarity 列挙型のテスト")
class RaceRarityTest {

    @Nested
    @DisplayName("列挙型の値のテスト")
    class EnumValues {

        @Test
        @DisplayName("正しい数の値を持つこと")
        void hasCorrectNumberOfValues() {
            // Arrange & Act
            var values = RaceRarity.values();

            // Assert
            assertThat(values).hasSize(4);
        }

        @Test
        @DisplayName("期待される値を含むこと")
        void containsExpectedValues() {
            // Arrange & Act
            var values = RaceRarity.values();

            // Assert
            assertThat(values)
                    .extracting(Enum::name)
                    .containsExactly("STANDARD", "UNIQUE", "LEGENDARY", "SECRET");
        }
    }

    @Nested
    @DisplayName("必要な勝利回数のテスト")
    class RequiredWins {

        @ParameterizedTest
        @DisplayName("各レア度に対して正しい必要勝利回数を持つこと")
        @CsvSource({
                "STANDARD, 0",
                "UNIQUE, 1",
                "LEGENDARY, 2",
                "SECRET, 3"
        })
        void hasCorrectRequiredWins(RaceRarity rarity, int expectedWins) {
            // Act
            var actualWins = rarity.getRequiredWins();

            // Assert
            assertThat(actualWins).isEqualTo(expectedWins);
        }

        @Test
        @DisplayName("STANDARD レア度は0回の勝利が必要")
        void standardRarityRequiresZeroWins() {
            // Arrange
            var standard = RaceRarity.STANDARD;

            // Act
            var requiredWins = standard.getRequiredWins();

            // Assert
            assertThat(requiredWins).isZero();
        }

        @Test
        @DisplayName("SECRET レア度は3回の勝利が必要")
        void secretRarityRequiresThreeWins() {
            // Arrange
            var secret = RaceRarity.SECRET;

            // Act
            var requiredWins = secret.getRequiredWins();

            // Assert
            assertThat(requiredWins).isEqualTo(3);
        }
    }

    @Nested
    @DisplayName("日本語の説明のテスト")
    class JapaneseDescription {

        @ParameterizedTest
        @DisplayName("各レア度に対して正しい日本語の説明を持つこと")
        @CsvSource({
                "STANDARD, 一般",
                "UNIQUE, レア",
                "LEGENDARY, スーパーレア",
                "SECRET, ウルトラレア"
        })
        void hasCorrectJapaneseDescription(RaceRarity rarity, String expectedDescription) {
            // Act
            var actualDescription = rarity.getJapaneseDescription();

            // Assert
            assertThat(actualDescription).isEqualTo(expectedDescription);
        }

        @Test
        @DisplayName("すべての値が空でない説明を持つこと")
        void allValuesHaveNonEmptyDescription() {
            // Act & Assert
            assertThat(RaceRarity.values())
                    .extracting(RaceRarity::getJapaneseDescription)
                    .doesNotContainNull()
                    .allSatisfy(description -> assertThat(description).isNotBlank());
        }
    }
}
