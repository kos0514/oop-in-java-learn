package com.kos0514.oop_in_java_learn.factory;

import com.kos0514.oop_in_java_learn.entity.generated.RaceStatusModifier;
import com.kos0514.oop_in_java_learn.model.value.Age;
import com.kos0514.oop_in_java_learn.model.value.SoulId;
import com.kos0514.oop_in_java_learn.util.RandomGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayableStatusesFactoryTest {

    @Mock
    private RandomGenerator mockRandomGenerator;

    private PlayableStatusesFactory playableStatusesFactory;

    @BeforeEach
    void setUp() {
        playableStatusesFactory = new PlayableStatusesFactory(mockRandomGenerator);

        // デフォルトのモック動作を設定
        // 常に0を返すようにすることで、テスト結果が予測可能になる
        when(mockRandomGenerator.nextInt(anyInt())).thenReturn(0);
    }

    @Nested
    class Create {

        @Test
        void validParameters_createsPlayableStatuses() {
            // Arrange
            var age = Age.of(25);
            var soulId = SoulId.of(UUID.randomUUID());
            var raceStatusModifier = createRaceStatusModifier(1, 1, 1, 1, 1, 1, 10, 5);

            // ランダム値を固定
            setupRandomValues(0, 0, 0, 0, 0, 0, 0, 0);

            // Act
            var result = playableStatusesFactory.create(age, soulId, raceStatusModifier);

            // Assert
            assertThat(result).isNotNull();
            // ランダム値が0で固定されているため、正確な値を検証できる
            assertThat(result.getStrength().getValue()).isEqualTo(9); // 10 + 0 - 2 + 1(modifier)
            assertThat(result.getVitality().getValue()).isEqualTo(9); // 10 + 0 - 2 + 1(modifier)
            assertThat(result.getIntelligence().getValue()).isEqualTo(9); // 10 + 0 - 2 + 1(modifier)
            assertThat(result.getAgility().getValue()).isEqualTo(9); // 10 + 0 - 2 + 1(modifier)
            assertThat(result.getDexterity().getValue()).isEqualTo(9); // 10 + 0 - 2 + 1(modifier)
            assertThat(result.getLuck().getValue()).isEqualTo(9); // 10 + 0 - 2 + 1(modifier)
            assertThat(result.getHealthPoints().getValue()).isEqualTo(100); // 100 + 0 - 10 + 10(modifier)
            assertThat(result.getMagicPoints().getValue()).isEqualTo(50); // 50 + 0 - 5 + 5(modifier)

            // シード値が設定されたことを検証
            verify(mockRandomGenerator).setSeed(soulId.getId().getLeastSignificantBits());
        }

        @Test
        void nullRaceStatusModifier_createsPlayableStatusesWithBaseValues() {
            // Arrange
            var age = Age.of(25);
            var soulId = SoulId.of(UUID.randomUUID());

            // ランダム値を固定
            setupRandomValues(0, 0, 0, 0, 0, 0, 0, 0);

            // Act
            var result = playableStatusesFactory.create(age, soulId, null);

            // Assert
            assertThat(result).isNotNull();
            // ランダム値が0で固定されているため、正確な値を検証できる
            assertThat(result.getStrength().getValue()).isEqualTo(8); // 10 + 0 - 2
            assertThat(result.getVitality().getValue()).isEqualTo(8); // 10 + 0 - 2
            assertThat(result.getIntelligence().getValue()).isEqualTo(8); // 10 + 0 - 2
            assertThat(result.getAgility().getValue()).isEqualTo(8); // 10 + 0 - 2
            assertThat(result.getDexterity().getValue()).isEqualTo(8); // 10 + 0 - 2
            assertThat(result.getLuck().getValue()).isEqualTo(8); // 10 + 0 - 2
            assertThat(result.getHealthPoints().getValue()).isEqualTo(90); // 100 + 0 - 10
            assertThat(result.getMagicPoints().getValue()).isEqualTo(45); // 50 + 0 - 5

            // シード値が設定されたことを検証
            verify(mockRandomGenerator).setSeed(soulId.getId().getLeastSignificantBits());
        }

        @Test
        void sameParameters_producesSameResults() {
            // Arrange
            var age = Age.of(25);
            var soulId = SoulId.of(UUID.fromString("00000000-0000-0000-0000-000000000001"));
            var raceStatusModifier = createRaceStatusModifier(1, 1, 1, 1, 1, 1, 10, 5);

            // ランダム値を固定 - 一度だけ設定すれば両方の呼び出しに適用される
            setupRandomValues(0, 0, 0, 0, 0, 0, 0, 0);

            // Act
            var result1 = playableStatusesFactory.create(age, soulId, raceStatusModifier);

            // 2回目の呼び出し - 同じモック設定を使用
            var result2 = playableStatusesFactory.create(age, soulId, raceStatusModifier);

            // Assert
            assertThat(result1).isEqualTo(result2);

            // シード値が設定されたことを検証（2回呼ばれるはず）
            verify(mockRandomGenerator, times(2)).setSeed(soulId.getId().getLeastSignificantBits());
        }

        @ParameterizedTest
        @ValueSource(ints = {15, 25, 60})
        void differentAges_produceDifferentStatuses(int ageValue) {
            // Arrange
            var age = Age.of(ageValue);
            var soulId = SoulId.of(UUID.fromString("00000000-0000-0000-0000-000000000001"));
            var raceStatusModifier = createRaceStatusModifier(0, 0, 0, 0, 0, 0, 0, 0);

            // ランダム値を固定
            setupRandomValues(0, 0, 0, 0, 0, 0, 0, 0);

            // Act
            var result = playableStatusesFactory.create(age, soulId, raceStatusModifier);

            // Assert
            if (ageValue < 20) {
                // Young age should have higher agility/dexterity and lower intelligence
                assertThat(result.getAgility().getValue()).isEqualTo(10); // 10 + 2 + 0 - 2
                assertThat(result.getDexterity().getValue()).isEqualTo(10); // 10 + 2 + 0 - 2
                assertThat(result.getIntelligence().getValue()).isEqualTo(7); // 10 - 1 + 0 - 2
            } else if (ageValue > 50) {
                // Older age should have higher intelligence and lower agility/dexterity
                assertThat(result.getIntelligence().getValue()).isEqualTo(10); // 10 + 2 + 0 - 2
                assertThat(result.getAgility().getValue()).isEqualTo(7); // 10 - 1 + 0 - 2
                assertThat(result.getDexterity().getValue()).isEqualTo(7); // 10 - 1 + 0 - 2
            } else {
                // Middle age should be balanced
                assertThat(result.getAgility().getValue()).isEqualTo(8); // 10 + 0 - 2
                assertThat(result.getDexterity().getValue()).isEqualTo(8); // 10 + 0 - 2
                assertThat(result.getIntelligence().getValue()).isEqualTo(8); // 10 + 0 - 2
            }

            // シード値が設定されたことを検証
            verify(mockRandomGenerator).setSeed(soulId.getId().getLeastSignificantBits());
        }

        @Test
        void negativeModifiers_resultInMinimumValueOfOne() {
            // Arrange
            var age = Age.of(25);
            var soulId = SoulId.of(UUID.randomUUID());
            var raceStatusModifier = createRaceStatusModifier(-100, -100, -100, -100, -100, -100, -1000, -1000);

            // ランダム値を固定
            setupRandomValues(0, 0, 0, 0, 0, 0, 0, 0);

            // Act
            var result = playableStatusesFactory.create(age, soulId, raceStatusModifier);

            // Assert
            assertThat(result.getStrength().getValue()).isEqualTo(1);
            assertThat(result.getVitality().getValue()).isEqualTo(1);
            assertThat(result.getIntelligence().getValue()).isEqualTo(1);
            assertThat(result.getAgility().getValue()).isEqualTo(1);
            assertThat(result.getDexterity().getValue()).isEqualTo(1);
            assertThat(result.getLuck().getValue()).isEqualTo(1);
            assertThat(result.getHealthPoints().getValue()).isEqualTo(1);
            assertThat(result.getMagicPoints().getValue()).isEqualTo(1);

            // シード値が設定されたことを検証
            verify(mockRandomGenerator).setSeed(soulId.getId().getLeastSignificantBits());
        }
    }

    private void setupRandomValues(int str, int vit, int intel, int agi, int dex, int luck, int hp, int mp) {
        // 各ステータスのランダム値を設定
        // 常に同じ値を返すように設定（何度呼ばれても同じ値を返す）
        when(mockRandomGenerator.nextInt(5)).thenReturn(str);
        when(mockRandomGenerator.nextInt(21)).thenReturn(hp);
        when(mockRandomGenerator.nextInt(11)).thenReturn(mp);
    }

    private RaceStatusModifier createRaceStatusModifier(
            int strengthMod, int vitalityMod, int intelligenceMod, int agilityMod,
            int dexterityMod, int luckMod, int healthPointsMod, int magicPointsMod) {

        var modifier = mock(RaceStatusModifier.class);
        when(modifier.getStrengthMod()).thenReturn(strengthMod);
        when(modifier.getVitalityMod()).thenReturn(vitalityMod);
        when(modifier.getIntelligenceMod()).thenReturn(intelligenceMod);
        when(modifier.getAgilityMod()).thenReturn(agilityMod);
        when(modifier.getDexterityMod()).thenReturn(dexterityMod);
        when(modifier.getLuckMod()).thenReturn(luckMod);
        when(modifier.getHealthPointsMod()).thenReturn(healthPointsMod);
        when(modifier.getMagicPointsMod()).thenReturn(magicPointsMod);

        return modifier;
    }
}
