package com.kos0514.oop_in_java_learn.model.value;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AgeTest {

    @Nested
    class Of {
        
        @ParameterizedTest
        @ValueSource(ints = {1, 25, 50, 120})
        void validValues_createsInstance(int value) {
            // Act
            var age = Age.of(value);
            
            // Assert
            assertThat(age).isNotNull();
            assertThat(age.getValue()).isEqualTo(value);
        }
        
        @ParameterizedTest
        @ValueSource(ints = {0, -1, 121, 200})
        void invalidValues_throwsException(int value) {
            // Act & Assert
            assertThatThrownBy(() -> Age.of(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("年齢は1～120の範囲で入力してください");
        }
    }
    
    @Nested
    class FromString {
        
        @ParameterizedTest
        @CsvSource({
            "1, 1",
            "25, 25",
            "120, 120",
            "'  50  ', 50" // Trimming test
        })
        void validStrings_createsInstance(String input, int expected) {
            // Act
            var age = Age.fromString(input);
            
            // Assert
            assertThat(age).isNotNull();
            assertThat(age.getValue()).isEqualTo(expected);
        }
        
        @ParameterizedTest
        @ValueSource(strings = {"0", "-1", "121", "200"})
        void outOfRangeValues_throwsException(String input) {
            // Act & Assert
            assertThatThrownBy(() -> Age.fromString(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("年齢は1～120の範囲で入力してください");
        }
        
        @ParameterizedTest
        @ValueSource(strings = {"", "  ", "abc", "25.5", "25a"})
        void invalidFormatStrings_throwsException(String input) {
            // Act & Assert
            assertThatThrownBy(() -> Age.fromString(input))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        void nullInput_throwsException() {
            // Act & Assert
            assertThatThrownBy(() -> Age.fromString(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("年齢を入力してください");
        }
    }
    
    @Nested
    class EqualsAndHashCode {
        
        @Test
        void sameValue_areEqual() {
            // Arrange
            var age1 = Age.of(25);
            var age2 = Age.of(25);
            
            // Act & Assert
            assertThat(age1).isEqualTo(age2);
            assertThat(age1.hashCode()).isEqualTo(age2.hashCode());
        }
        
        @Test
        void differentValues_areNotEqual() {
            // Arrange
            var age1 = Age.of(25);
            var age2 = Age.of(30);
            
            // Act & Assert
            assertThat(age1).isNotEqualTo(age2);
        }
    }
}