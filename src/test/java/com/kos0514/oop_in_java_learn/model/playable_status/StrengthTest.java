package com.kos0514.oop_in_java_learn.model.playable_status;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StrengthTest {

    @Nested
    class Of {

        @ParameterizedTest
        @ValueSource(ints = {1, 5, 10, 100})
        void validValues_createsInstance(int value) {
            // Act
            var strength = Strength.of(value);

            // Assert
            assertThat(strength).isNotNull();
            assertThat(strength.getValue()).isEqualTo(value);
            assertThat(strength.getJapaneseName()).isEqualTo("筋力");
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, -10})
        void invalidValues_throwsException(int value) {
            // Act & Assert
            assertThatThrownBy(() -> Strength.of(value))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("筋力は1以上の値である必要があります");
        }
    }
}