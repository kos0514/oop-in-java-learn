package com.kos0514.oop_in_java_learn.service.game;

import com.kos0514.oop_in_java_learn.enums.RaceRarity;
import com.kos0514.oop_in_java_learn.io.ComputerChoiceProvider;
import com.kos0514.oop_in_java_learn.io.test.TestComputerChoiceProvider;
import com.kos0514.oop_in_java_learn.io.test.TestInputProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.function.IntFunction;

import static com.kos0514.oop_in_java_learn.enums.RaceRarity.LEGENDARY;
import static com.kos0514.oop_in_java_learn.enums.RaceRarity.SECRET;
import static com.kos0514.oop_in_java_learn.enums.RaceRarity.STANDARD;
import static com.kos0514.oop_in_java_learn.enums.RaceRarity.UNIQUE;
import static com.kos0514.oop_in_java_learn.enums.RockPaperScissors.PAPER;
import static com.kos0514.oop_in_java_learn.enums.RockPaperScissors.ROCK;
import static com.kos0514.oop_in_java_learn.enums.RockPaperScissors.SCISSORS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("RockPaperScissorsGame クラスのテスト")
class RockPaperScissorsGameTest {

    @Mock
    private ComputerChoiceProvider computerChoiceProvider;

    @InjectMocks
    private RockPaperScissorsGame rockPaperScissorsGame;

    /**
     * 型安全なIntFunction<RaceRarity>モックを作成するヘルパーメソッド
     *
     * @param winCount 勝利回数
     * @param rarity   対応するレア度
     * @return 設定済みのモック
     */
    private IntFunction<RaceRarity> createRaceRarityConverter(int winCount, RaceRarity rarity) {
        return i -> i == winCount ? rarity : null;
    }

    @Nested
    @DisplayName("playGameAndConvertResult メソッドのテスト")
    class PlayGameAndConvertResult {

        @Test
        @DisplayName("正常系: プレイヤーが1回勝利して終了する場合")
        void playerWinsOnce_returnsConvertedResult() {
            // Arrange
            // プレイヤーの入力をシミュレート（グー選択→勝利→終了）
            var inputProvider = new TestInputProvider()
                    .addInput("1")  // 1: グー選択
                    .addInput("2"); // 2: 終了

            // コンピュータの手を設定（プレイヤーがグー(ROCK)を出すとき、コンピュータはハサミ(SCISSORS)を出して負ける）
            when(computerChoiceProvider.chooseHand()).thenReturn(SCISSORS);

            // 型安全なコンバーターの作成
            var mockConverter = createRaceRarityConverter(1, UNIQUE);

            // Act
            var result = rockPaperScissorsGame.playGameAndConvertResult(
                    inputProvider,
                    3,
                    "テストゲーム",
                    "テスト説明",
                    mockConverter
            );

            // Assert
            assertThat(result).isEqualTo(UNIQUE);
        }

        @Test
        @DisplayName("正常系: プレイヤーが複数回勝利する場合")
        void playerWinsMultipleTimes_returnsConvertedResult() {
            // Arrange
            // プレイヤーの入力をシミュレート（グー選択→勝利→続行→グー選択→勝利→終了）
            var inputProvider = new TestInputProvider()
                    .addInputs("1", "1", "1", "2"); // 1: グー選択, 1: 続行, 1: グー選択, 2: 終了

            // コンピュータの手を設定（プレイヤーがグー(ROCK)を出すとき、コンピュータはハサミ(SCISSORS)を出して負ける）
            when(computerChoiceProvider.chooseHand()).thenReturn(SCISSORS, SCISSORS);

            // 型安全なコンバーターの作成
            var mockConverter = createRaceRarityConverter(2, LEGENDARY);

            // Act
            var result = rockPaperScissorsGame.playGameAndConvertResult(
                    inputProvider,
                    3,
                    "テストゲーム",
                    "テスト説明",
                    mockConverter
            );

            // Assert
            assertThat(result).isEqualTo(LEGENDARY);
        }

        @Test
        @DisplayName("正常系: プレイヤーが最大ラウンド数まで勝利する場合")
        void playerWinsMaxRounds_returnsConvertedResult() {
            // Arrange
            // プレイヤーの入力をシミュレート（3回連続で勝利するパターン）
            var inputProvider = new TestInputProvider()
                    .addInputs("1", "1", "1", "1", "1"); // 1: グー選択, 1: 続行, 1: グー選択, 1: 続行, 1: グー選択

            // コンピュータの手を設定（プレイヤーがグー(ROCK)を出すとき、コンピュータはハサミ(SCISSORS)を出して負ける）
            when(computerChoiceProvider.chooseHand()).thenReturn(SCISSORS, SCISSORS, SCISSORS);

            // 型安全なコンバーターの作成
            var mockConverter = createRaceRarityConverter(3, SECRET);

            // Act
            var result = rockPaperScissorsGame.playGameAndConvertResult(
                    inputProvider,
                    3,
                    "テストゲーム",
                    "テスト説明",
                    mockConverter
            );

            // Assert
            assertThat(result).isEqualTo(SECRET);
        }

        @Test
        @DisplayName("正常系: プレイヤーが負ける場合")
        void playerLoses_returnsZeroWins() {
            // Arrange
            // プレイヤーの入力をシミュレート（チョキ選択→負け）
            var inputProvider = new TestInputProvider()
                    .addInput("2"); // 2: チョキ選択

            // コンピュータの手を設定（プレイヤーがチョキ(PAPER)を出すとき、コンピュータはグー(ROCK)を出して勝つ）
            when(computerChoiceProvider.chooseHand()).thenReturn(ROCK);

            // 型安全なコンバーターの作成
            var mockConverter = createRaceRarityConverter(0, STANDARD);

            // Act
            var result = rockPaperScissorsGame.playGameAndConvertResult(
                    inputProvider,
                    3,
                    "テストゲーム",
                    "テスト説明",
                    mockConverter
            );

            // Assert
            assertThat(result).isEqualTo(STANDARD);
        }

        @Test
        @DisplayName("異常系: 無効な入力の後に有効な入力がされる場合")
        void invalidThenValidInput_eventuallySucceeds() {
            // Arrange
            // プレイヤーの入力をシミュレート（無効な入力→グー選択→勝利→終了）
            var inputProvider = new TestInputProvider()
                    .addInputs("5", "abc", "1", "2"); // 5: 無効な選択, abc: 無効な選択, 1: グー選択, 2: 終了

            // コンピュータの手を設定（プレイヤーがグー(ROCK)を出すとき、コンピュータはハサミ(SCISSORS)を出して負ける）
            when(computerChoiceProvider.chooseHand()).thenReturn(SCISSORS);

            // 型安全なコンバーターの作成
            var mockConverter = createRaceRarityConverter(1, UNIQUE);

            // Act
            var result = rockPaperScissorsGame.playGameAndConvertResult(
                    inputProvider,
                    3,
                    "テストゲーム",
                    "テスト説明",
                    mockConverter
            );

            // Assert
            assertThat(result).isEqualTo(UNIQUE);
        }

        @Test
        @DisplayName("異常系: askToContinueで無効な入力の後に有効な入力がされる場合")
        void askToContinue_invalidThenValidInput_eventuallySucceeds() {
            // Arrange
            // プレイヤーの入力をシミュレート
            // 1: グー選択→勝利→無効な入力(3)→無効な入力(abc)→続行(1)→グー選択→勝利→終了(2)
            var inputProvider = new TestInputProvider()
                    .addInputs("1", "3", "abc", "1", "1", "2");

            // コンピュータの手を設定（プレイヤーがグー(ROCK)を出すとき、コンピュータはハサミ(SCISSORS)を出して負ける）
            when(computerChoiceProvider.chooseHand()).thenReturn(SCISSORS, SCISSORS);

            // 型安全なコンバーターの作成
            var mockConverter = createRaceRarityConverter(2, LEGENDARY);

            // Act
            var result = rockPaperScissorsGame.playGameAndConvertResult(
                    inputProvider,
                    3,
                    "テストゲーム",
                    "テスト説明",
                    mockConverter
            );

            // Assert
            assertThat(result).isEqualTo(LEGENDARY);
        }
    }

    @Nested
    @DisplayName("あいこのテスト")
    class TieTests {

        @Test
        @DisplayName("あいこの後に勝利するケース")
        void tieFollowedByWin_returnsConvertedResult() {
            // Arrange
            // TestComputerChoiceProviderを直接使用
            var testProvider = new TestComputerChoiceProvider()
                    .addChoices(ROCK, SCISSORS); // 最初はグー(ROCK)でプレイヤーとあいこ、次はハサミ(SCISSORS)でプレイヤーの勝ち

            var gameWithProvider = new RockPaperScissorsGame(testProvider);

            // プレイヤーの入力をシミュレート（グー選択→あいこ→グー選択→勝利→終了）
            var inputProvider = new TestInputProvider()
                    .addInputs("1", "1", "2"); // 1: グー選択, 1: グー選択, 2: 終了

            // 型安全なコンバーターの作成
            var mockConverter = createRaceRarityConverter(1, UNIQUE);

            // Act
            var result = gameWithProvider.playGameAndConvertResult(
                    inputProvider,
                    3,
                    "あいこテスト",
                    "あいこの後に勝利するケース",
                    mockConverter
            );

            // Assert
            assertThat(result).isEqualTo(UNIQUE);
        }

        @Test
        @DisplayName("複数回のあいこの後に勝利するケース")
        void multipleTiesFollowedByWin_returnsConvertedResult() {
            // Arrange
            // TestComputerChoiceProviderを直接使用
            var testProvider = new TestComputerChoiceProvider()
                    .addChoices(ROCK, SCISSORS, PAPER, SCISSORS); // グー(ROCK)→あいこ, チョキ(SCISSORS)→あいこ, パー(PAPER)→あいこ, チョキ(SCISSORS)→プレイヤーの勝ち

            var gameWithProvider = new RockPaperScissorsGame(testProvider);

            // プレイヤーの入力をシミュレート（グー→あいこ→チョキ→あいこ→パー→あいこ→グー→勝利→終了）
            var inputProvider = new TestInputProvider()
                    .addInputs("1", "2", "3", "1", "2"); // グー, チョキ, パー, グー, 終了

            // 型安全なコンバーターの作成
            var mockConverter = createRaceRarityConverter(1, UNIQUE);

            // Act
            var result = gameWithProvider.playGameAndConvertResult(
                    inputProvider,
                    3,
                    "複数あいこテスト",
                    "複数回のあいこの後に勝利するケース",
                    mockConverter
            );

            // Assert
            assertThat(result).isEqualTo(UNIQUE);
        }
    }
}
