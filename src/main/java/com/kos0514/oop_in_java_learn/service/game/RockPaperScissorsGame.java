package com.kos0514.oop_in_java_learn.service.game;

import com.kos0514.oop_in_java_learn.enums.RockPaperScissors;
import com.kos0514.oop_in_java_learn.io.ComputerChoiceProvider;
import com.kos0514.oop_in_java_learn.io.UserInputProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.IntFunction;

import static com.kos0514.oop_in_java_learn.enums.RockPaperScissors.PAPER;
import static com.kos0514.oop_in_java_learn.enums.RockPaperScissors.ROCK;
import static com.kos0514.oop_in_java_learn.enums.RockPaperScissors.SCISSORS;
import static com.kos0514.oop_in_java_learn.util.log.LoggingUtils.info;
import static com.kos0514.oop_in_java_learn.util.log.LoggingUtils.printSeparator;
import static com.kos0514.oop_in_java_learn.util.log.LoggingUtils.warn;
import static com.kos0514.oop_in_java_learn.util.log.LoggingUtils.warnInputNumber;

/**
 * じゃんけんゲームを実装する汎用的なコンポーネント。
 * 様々なゲームシナリオで再利用可能なように設計されています。
 *
 * <p>このクラスは以下の責任を持ちます：</p>
 * <ul>
 *   <li>じゃんけんゲームの実行</li>
 *   <li>勝敗判定</li>
 *   <li>勝利回数のカウント</li>
 * </ul>
 */
@Component
@RequiredArgsConstructor
public class RockPaperScissorsGame {

    private final ComputerChoiceProvider computerChoiceProvider;

    /**
     * 特定の条件に基づいてじゃんけんゲームを実行し、結果を変換して返します。
     *
     * @param <T>               戻り値の型
     * @param inputProvider     入力を受け付けるUserInputProviderオブジェクト
     * @param maxRounds         最大ラウンド数
     * @param gameTitle         ゲームのタイトル（ログ表示用）
     * @param gameDescription   ゲームの説明（ログ表示用）
     * @param winCountConverter 勝利回数を結果の型に変換する関数
     * @return 変換された結果
     */
    public <T> T playGameAndConvertResult(
            UserInputProvider inputProvider,
            int maxRounds,
            String gameTitle,
            String gameDescription,
            IntFunction<T> winCountConverter) {

        var wins = play(inputProvider, maxRounds, gameTitle, gameDescription);
        return winCountConverter.apply(wins);
    }

    /**
     * じゃんけんゲームを実行し、勝利回数を返します。
     *
     * @param inputProvider   入力を受け付けるUserInputProviderオブジェクト
     * @param maxRounds       最大ラウンド数
     * @param gameTitle       ゲームのタイトル（ログ表示用）
     * @param gameDescription ゲームの説明（ログ表示用）
     * @return 勝利回数
     */
    private int play(UserInputProvider inputProvider, int maxRounds, String gameTitle, String gameDescription) {
        info("【{}】", gameTitle);
        info(gameDescription);

        var wins = 0;

        // maxRounds回勝つか、プレイヤーがやめる場合ループ終了
        do {
            printSeparator();
            info("【{}回目のじゃんけん】", wins + 1);
            printSeparator();

            if (!playOneRound(inputProvider)) {
                info("負けてしまいました...");
                return wins; // 負けた時点で早期リターン
            }

            wins++;
            info("勝利しました！ 現在{}回勝利", wins);

        } while (wins < maxRounds && askToContinue(inputProvider));

        return wins;
    }

    /**
     * プレイヤーにゲームを続けるかどうか尋ねます。
     *
     * @param inputProvider 入力を受け付けるUserInputProviderオブジェクト
     * @return 続ける場合はtrue、やめる場合はfalse
     */
    private boolean askToContinue(UserInputProvider inputProvider) {
        info("続けますか？ (1: はい, 2: いいえ)");
        while (true) {
            try {
                var choice = Integer.parseInt(inputProvider.readLine());
                switch (choice) {
                    case 1 -> {
                        return true;
                    }
                    case 2 -> {
                        return false;
                    }
                    default -> warn("1か2を入力してください。");
                }
            } catch (NumberFormatException e) {
                warnInputNumber();
            }
        }
    }

    /**
     * じゃんけんの1ラウンドを実行します。
     * プレイヤーとコンピュータの手を比較し、勝敗を判定します。
     * あいこの場合は再帰的に再プレイします。
     *
     * @param inputProvider 入力を受け付けるUserInputProviderオブジェクト
     * @return プレイヤーが勝った場合はtrue、負けた場合はfalse
     */
    private boolean playOneRound(UserInputProvider inputProvider) {
        info("じゃんけんの手を選んでください:");
        info("1: グー");
        info("2: チョキ");
        info("3: パー");

        var playerHand = getPlayerChoice(inputProvider);

        // コンピュータの手を選択
        var computerHand = computerChoiceProvider.chooseHand();

        info("あなた: {}", playerHand.getJapaneseName());
        info("相手: {}", computerHand.getJapaneseName());

        // 勝敗判定
        if (playerHand == computerHand) {
            info("あいこです。もう一度！");
            return playOneRound(inputProvider); // 再帰的に再プレイ
        } else return (playerHand == ROCK && computerHand == SCISSORS) ||
                (playerHand == PAPER && computerHand == ROCK) ||
                (playerHand == SCISSORS && computerHand == PAPER); // プレイヤーの勝ち
    }

    /**
     * プレイヤーの手の選択を取得します。
     * 有効な選択（1-3）が入力されるまで繰り返し尋ねます。
     *
     * @param inputProvider 入力を受け付けるUserInputProviderオブジェクト
     * @return プレイヤーの選択（ROCK, PAPER, SCISSORS）
     */
    private RockPaperScissors getPlayerChoice(UserInputProvider inputProvider) {
        while (true) {
            try {
                var input = Integer.parseInt(inputProvider.readLine());
                if (input >= 1 && input <= 3) {
                    return RockPaperScissors.fromValue(input);
                } else {
                    warn("1から3の数字を入力してください。");
                }
            } catch (NumberFormatException e) {
                warnInputNumber();
            }
        }
    }
}
