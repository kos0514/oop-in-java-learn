package com.kos0514.oop_in_java_learn.enums;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

class RaceRarityTest {

    @Nested
    class EnumValues {
        
        @Test
        void hasCorrectNumberOfValues() {
            // Arrange & Act
            var values = RaceRarity.values();
            
            // Assert
            assertThat(values).hasSize(4);
        }
        
        @Test
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
    class RequiredWins {
        
        @ParameterizedTest
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
        void standardRarityRequiresZeroWins() {
            // Arrange
            var standard = RaceRarity.STANDARD;
            
            // Act
            var requiredWins = standard.getRequiredWins();
            
            // Assert
            assertThat(requiredWins).isZero();
        }
        
        @Test
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
    class JapaneseDescription {
        
        @ParameterizedTest
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
        void allValuesHaveNonEmptyDescription() {
            // Act & Assert
            assertThat(RaceRarity.values())
                .extracting(RaceRarity::getJapaneseDescription)
                .doesNotContainNull()
                .allSatisfy(description -> assertThat(description).isNotBlank());
        }
    }
}