package com.kos0514.oop_in_java_learn.model.playable_status;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayableStatusTest {

    // Concrete implementation for testing the abstract class
    private static class TestPlayableStatus extends PlayableStatus {
        private TestPlayableStatus(int value, String japaneseName) {
            super(value, japaneseName);
        }
        
        public static TestPlayableStatus of(int value) {
            validateValue(value, "テストステータス");
            return new TestPlayableStatus(value, "テストステータス");
        }
    }
    
    @Nested
    class Constructor {
        
        @Test
        void validValues_createsInstance() {
            // Arrange
            int value = 10;
            String japaneseName = "テストステータス";
            
            // Act
            var status = new TestPlayableStatus(value, japaneseName);
            
            // Assert
            assertThat(status).isNotNull();
            assertThat(status.getValue()).isEqualTo(value);
            assertThat(status.getJapaneseName()).isEqualTo(japaneseName);
        }
    }
    
    @Nested
    class ValidateValue {
        
        @ParameterizedTest
        @ValueSource(ints = {1, 5, 10, 100})
        void validValues_doesNotThrowException(int value) {
            // Act & Assert - No exception should be thrown
            TestPlayableStatus.of(value);
        }
        
        @ParameterizedTest
        @ValueSource(ints = {0, -1, -10})
        void invalidValues_throwsException(int value) {
            // Act & Assert
            assertThatThrownBy(() -> TestPlayableStatus.of(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("テストステータスは1以上の値である必要があります");
        }
    }
}