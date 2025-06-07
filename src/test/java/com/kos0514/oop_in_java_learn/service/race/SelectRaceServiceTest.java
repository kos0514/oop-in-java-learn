package com.kos0514.oop_in_java_learn.service.race;

import com.kos0514.oop_in_java_learn.entity.generated.Race;
import com.kos0514.oop_in_java_learn.enums.RaceRarity;
import com.kos0514.oop_in_java_learn.io.UserInputProvider;
import com.kos0514.oop_in_java_learn.io.test.TestInputProvider;
import com.kos0514.oop_in_java_learn.mapper.RaceMapper;
import com.kos0514.oop_in_java_learn.service.game.RockPaperScissorsGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.function.IntFunction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SelectRaceServiceTest {

    @Mock
    private RaceMapper raceMapper;

    @Mock
    private RockPaperScissorsGame rockPaperScissorsGame;

    @InjectMocks
    private SelectRaceService selectRaceService;

    // テスト用のモックデータ
    private List<Race> standardRaces;
    private List<Race> uniqueRaces;
    private List<Race> legendaryRaces;
    private List<Race> secretRaces;
    private Race testRace;


    @BeforeEach
    void setUp() {
        // テスト用の種族データを作成
        testRace = mock(Race.class);
        when(testRace.getId()).thenReturn("test-race-id");
        when(testRace.getJapaneseName()).thenReturn("テスト種族");
        when(testRace.getSpecialAbility()).thenReturn("テスト特殊能力");
        when(testRace.getDescription()).thenReturn("テスト種族の説明");
        when(testRace.getRarity()).thenReturn("STANDARD");

        // 各レア度の種族リストを作成
        standardRaces = List.of(testRace);
        uniqueRaces = List.of(testRace, mock(Race.class));
        legendaryRaces = List.of(testRace, mock(Race.class), mock(Race.class));
        secretRaces = List.of(testRace, mock(Race.class), mock(Race.class), mock(Race.class));
    }

    @Nested
    @DisplayName("selectRace メソッドのテスト")
    class SelectRace {

        @Test
        @DisplayName("正常系: STANDARD種族が選択される場合")
        void standardRarity_returnsSelectedRace() {
            // Arrange
            // プレイヤーの入力をシミュレート（1番目の種族を選択）
            var inputProvider = new TestInputProvider()
                    .addInput("1");

            // じゃんけんゲームの結果をSTANDARDに設定
            when(rockPaperScissorsGame.playGameAndConvertResult(
                    any(UserInputProvider.class),
                    eq(3),
                    anyString(),
                    anyString(),
                    any(IntFunction.class)
            )).thenReturn(RaceRarity.STANDARD);

            // 利用可能な種族リストを設定
            when(raceMapper.selectUpToRarity(RaceRarity.STANDARD)).thenReturn(standardRaces);

            // Act
            var selectedRace = selectRaceService.selectRace(inputProvider);

            // Assert
            assertThat(selectedRace).isEqualTo(testRace);
            verify(raceMapper).selectUpToRarity(RaceRarity.STANDARD);
        }

        @Test
        @DisplayName("正常系: UNIQUE種族が選択される場合")
        void uniqueRarity_returnsSelectedRace() {
            // Arrange
            // プレイヤーの入力をシミュレート（2番目の種族を選択）
            var inputProvider = new TestInputProvider()
                    .addInput("2");

            // じゃんけんゲームの結果をUNIQUEに設定
            when(rockPaperScissorsGame.playGameAndConvertResult(
                    any(UserInputProvider.class),
                    eq(3),
                    anyString(),
                    anyString(),
                    any(IntFunction.class)
            )).thenReturn(RaceRarity.UNIQUE);

            // 利用可能な種族リストを設定
            when(raceMapper.selectUpToRarity(RaceRarity.UNIQUE)).thenReturn(uniqueRaces);

            // Act
            var selectedRace = selectRaceService.selectRace(inputProvider);

            // Assert
            assertThat(selectedRace).isNotEqualTo(testRace); // 2番目の種族が選択されるはず
            verify(raceMapper).selectUpToRarity(RaceRarity.UNIQUE);
        }

        @Test
        @DisplayName("正常系: LEGENDARY種族が選択される場合")
        void legendaryRarity_returnsSelectedRace() {
            // Arrange
            // プレイヤーの入力をシミュレート（3番目の種族を選択）
            var inputProvider = new TestInputProvider()
                    .addInput("3");

            // じゃんけんゲームの結果をLEGENDARYに設定
            when(rockPaperScissorsGame.playGameAndConvertResult(
                    any(UserInputProvider.class),
                    eq(3),
                    anyString(),
                    anyString(),
                    any(IntFunction.class)
            )).thenReturn(RaceRarity.LEGENDARY);

            // 利用可能な種族リストを設定
            when(raceMapper.selectUpToRarity(RaceRarity.LEGENDARY)).thenReturn(legendaryRaces);

            // Act
            var selectedRace = selectRaceService.selectRace(inputProvider);

            // Assert
            assertThat(selectedRace).isNotEqualTo(testRace); // 3番目の種族が選択されるはず
            verify(raceMapper).selectUpToRarity(RaceRarity.LEGENDARY);
        }

        @Test
        @DisplayName("正常系: SECRET種族が選択される場合")
        void secretRarity_returnsSelectedRace() {
            // Arrange
            // プレイヤーの入力をシミュレート（4番目の種族を選択）
            var inputProvider = new TestInputProvider()
                    .addInput("4");

            // じゃんけんゲームの結果をSECRETに設定
            when(rockPaperScissorsGame.playGameAndConvertResult(
                    any(UserInputProvider.class),
                    eq(3),
                    anyString(),
                    anyString(),
                    any(IntFunction.class)
            )).thenReturn(RaceRarity.SECRET);

            // 利用可能な種族リストを設定
            when(raceMapper.selectUpToRarity(RaceRarity.SECRET)).thenReturn(secretRaces);

            // Act
            var selectedRace = selectRaceService.selectRace(inputProvider);

            // Assert
            assertThat(selectedRace).isNotEqualTo(testRace); // 4番目の種族が選択されるはず
            verify(raceMapper).selectUpToRarity(RaceRarity.SECRET);
        }

        @Test
        @DisplayName("正常系: じゃんけんゲームの変換関数が正しく動作する")
        void raceRarityConverter_worksCorrectly() {
            // Arrange
            var inputProvider = new TestInputProvider()
                    .addInput("1");

            // じゃんけんゲームのplayGameAndConvertResultメソッドをキャプチャ
            ArgumentCaptor<IntFunction<RaceRarity>> converterCaptor = ArgumentCaptor.forClass(IntFunction.class);

            when(rockPaperScissorsGame.playGameAndConvertResult(
                    any(UserInputProvider.class),
                    anyInt(),
                    anyString(),
                    anyString(),
                    converterCaptor.capture()
            )).thenReturn(RaceRarity.STANDARD);

            when(raceMapper.selectUpToRarity(any(RaceRarity.class))).thenReturn(standardRaces);

            // Act
            selectRaceService.selectRace(inputProvider);
            var converter = converterCaptor.getValue();

            // Assert - 変換関数のテスト
            assertThat(converter.apply(0)).isEqualTo(RaceRarity.STANDARD);
            assertThat(converter.apply(1)).isEqualTo(RaceRarity.UNIQUE);
            assertThat(converter.apply(2)).isEqualTo(RaceRarity.LEGENDARY);
            assertThat(converter.apply(3)).isEqualTo(RaceRarity.SECRET);
        }

        @Test
        @DisplayName("異常系: 無効な選択の後に有効な選択がされる場合")
        void invalidThenValidSelection_eventuallySucceeds() {
            // Arrange
            // プレイヤーの入力をシミュレート（無効な選択→有効な選択）
            var inputProvider = new TestInputProvider()
                    .addInputs("10", "abc", "1");

            // じゃんけんゲームの結果をSTANDARDに設定
            when(rockPaperScissorsGame.playGameAndConvertResult(
                    any(UserInputProvider.class),
                    eq(3),
                    anyString(),
                    anyString(),
                    any(IntFunction.class)
            )).thenReturn(RaceRarity.STANDARD);

            // 利用可能な種族リストを設定
            when(raceMapper.selectUpToRarity(RaceRarity.STANDARD)).thenReturn(standardRaces);

            // Act
            var selectedRace = selectRaceService.selectRace(inputProvider);

            // Assert
            assertThat(selectedRace).isEqualTo(testRace);
            verify(raceMapper).selectUpToRarity(RaceRarity.STANDARD);
        }
    }
}
