package com.kos0514.oop_in_java_learn.model.value;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SoulNameTest {

    @Nested
    class Of {

        @ParameterizedTest
        @ValueSource(strings = {"テスト", "Test Name", "田中太郎", "A"})
        void validNames_createsInstance(String name) {
            // Act
            var soulName = SoulName.of(name);

            // Assert
            assertThat(soulName).isNotNull();
            assertThat(soulName.getName()).isEqualTo(name);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "  "})
        void emptyOrBlankName_throwsException(String name) {
            // Act & Assert
            assertThatThrownBy(() -> SoulName.of(name))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("名前は空にできません");
        }

        @Test
        void nullName_throwsException() {
            // Act & Assert
            assertThatThrownBy(() -> SoulName.of(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("名前は空にできません");
        }
    }

    @Nested
    class EqualsAndHashCode {

        @Test
        void sameName_areEqual() {
            // Arrange
            var name1 = SoulName.of("テスト");
            var name2 = SoulName.of("テスト");

            // Act & Assert
            assertThat(name1).isEqualTo(name2);
            assertThat(name1.hashCode()).isEqualTo(name2.hashCode());
        }

        @Test
        void differentNames_areNotEqual() {
            // Arrange
            var name1 = SoulName.of("テスト1");
            var name2 = SoulName.of("テスト2");

            // Act & Assert
            assertThat(name1).isNotEqualTo(name2);
        }
    }
}