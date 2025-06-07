package com.kos0514.oop_in_java_learn.util.random_generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * {@link DefaultRandomGenerator}のテストクラス
 */
class DefaultRandomGeneratorTest {

    private DefaultRandomGenerator randomGenerator;

    @BeforeEach
    void setUp() {
        randomGenerator = new DefaultRandomGenerator();
    }

    @Nested
    @DisplayName("nextInt メソッドのテスト")
    class NextInt {

        @Test
        @DisplayName("正常系: 0から指定された上限値未満のランダムな整数を返す")
        void returnsRandomIntegerWithinBounds() {
            // Arrange
            var bound = 10;

            // Act
            var result = randomGenerator.nextInt(bound);

            // Assert
            assertThat(result).isGreaterThanOrEqualTo(0).isLessThan(bound);
        }

        @Test
        @DisplayName("正常系: 同じシードを設定すると同じ結果を返す")
        void returnsSameResultWithSameSeed() {
            // Arrange
            var bound = 100;
            var seed = 12345L;

            // 1回目の乱数生成
            randomGenerator.setSeed(seed);
            var firstResult = randomGenerator.nextInt(bound);

            // 新しいインスタンスで同じシードを設定
            var anotherGenerator = new DefaultRandomGenerator();
            anotherGenerator.setSeed(seed);

            // Act - 2回目の乱数生成
            var secondResult = anotherGenerator.nextInt(bound);

            // Assert
            assertThat(secondResult).isEqualTo(firstResult);
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, -100})
        @DisplayName("異常系: 上限値が0以下の場合はIllegalArgumentExceptionをスロー")
        void throwsExceptionWhenBoundIsZeroOrNegative(int invalidBound) {
            // Act & Assert
            assertThatThrownBy(() -> randomGenerator.nextInt(invalidBound))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("setSeed メソッドのテスト")
    class SetSeed {

        @Test
        @DisplayName("正常系: シードを設定すると決定的な結果を生成する")
        void seedDeterminesRandomSequence() {
            // Arrange
            var bound = 1000;
            var seed = 54321L;

            // 同じシードで2つのジェネレーターを初期化
            randomGenerator.setSeed(seed);
            var sequence1 = new int[5];
            for (int i = 0; i < 5; i++) {
                sequence1[i] = randomGenerator.nextInt(bound);
            }

            var anotherGenerator = new DefaultRandomGenerator();
            anotherGenerator.setSeed(seed);
            var sequence2 = new int[5];
            for (int i = 0; i < 5; i++) {
                sequence2[i] = anotherGenerator.nextInt(bound);
            }

            // Assert - 両方のシーケンスが同じであることを確認
            assertThat(sequence2).isEqualTo(sequence1);
        }

        @Test
        @DisplayName("正常系: 異なるシードは異なる結果を生成する可能性が高い")
        void differentSeedsLikelyProduceDifferentResults() {
            // Arrange
            var bound = 1000;
            var seed1 = 12345L;
            var seed2 = 54321L;

            // 1つ目のシードでの結果
            randomGenerator.setSeed(seed1);
            var result1 = randomGenerator.nextInt(bound);

            // 2つ目のシードでの結果
            randomGenerator.setSeed(seed2);
            var result2 = randomGenerator.nextInt(bound);

            // Assert - 結果が異なる可能性が高い（確率的なテスト）
            // 注: 非常に稀に同じ値が生成される可能性があるため、このテストは確率的
            assertThat(result1).isNotEqualTo(result2);
        }
    }
}