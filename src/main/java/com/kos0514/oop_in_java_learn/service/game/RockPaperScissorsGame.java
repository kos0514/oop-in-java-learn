package com.kos0514.oop_in_java_learn.service.game;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.Scanner;
import java.util.function.IntFunction;

import static com.kos0514.oop_in_java_learn.util.LoggingUtils.printSeparator;

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
@Slf4j
@Component
@RequiredArgsConstructor
public class RockPaperScissorsGame {

    private final Random random = new Random();

    /**
     * 特定の条件に基づいてじゃんけんゲームを実行し、結果を変換して返します。
     *
     * @param <T> 戻り値の型
     * @param scanner 入力を受け付けるScannerオブジェクト
     * @param maxRounds 最大ラウンド数
     * @param gameTitle ゲームのタイトル（ログ表示用）
     * @param gameDescription ゲームの説明（ログ表示用）
     * @param winCountConverter 勝利回数を結果の型に変換する関数
     * @return 変換された結果
     */
    public <T> T playGameAndConvertResult(
            Scanner scanner,
            int maxRounds,
            String gameTitle,
            String gameDescription,
            IntFunction<T> winCountConverter) {

        var wins = play(scanner, maxRounds, gameTitle, gameDescription);
        return winCountConverter.apply(wins);
    }

    /**
     * じゃんけんゲームを実行し、勝利回数を返します。
     * 
     * @param scanner 入力を受け付けるScannerオブジェクト
     * @param maxRounds 最大ラウンド数
     * @param gameTitle ゲームのタイトル（ログ表示用）
     * @param gameDescription ゲームの説明（ログ表示用）
     * @return 勝利回数
     */
    private int play(Scanner scanner, int maxRounds, String gameTitle, String gameDescription) {
        log.info("【{}】", gameTitle);
        log.info(gameDescription);

        var wins = 0;

        // maxRounds回勝つか、プレイヤーがやめる場合ループ終了
        do {
            printSeparator();
            log.info("【{}回目のじゃんけん】", wins + 1);
            printSeparator();

            if (!playOneRound(scanner)) {
                log.info("負けてしまいました...");
                return wins; // 負けた時点で早期リターン
            }

            wins++;
            log.info("勝利しました！ 現在{}回勝利", wins);

        } while (wins < maxRounds && askToContinue(scanner));

        return wins;
    }

    /**
     * プレイヤーにゲームを続けるかどうか尋ねます。
     *
     * @param scanner 入力を受け付けるScannerオブジェクト
     * @return 続ける場合はtrue、やめる場合はfalse
     */
    private boolean askToContinue(Scanner scanner) {
        log.info("続けますか？ (1: はい, 2: いいえ)");
        while (true) {
            try {
                var choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> {
                        return true;
                    }
                    case 2 -> {
                        return false;
                    }
                    default -> log.warn("1か2を入力してください。");
                }
            } catch (NumberFormatException e) {
                log.warn("数値を入力してください。");
            }
        }
    }

    /**
     * じゃんけんの1ラウンドを実行します。
     * プレイヤーとコンピュータの手を比較し、勝敗を判定します。
     * あいこの場合は再帰的に再プレイします。
     *
     * @param scanner 入力を受け付けるScannerオブジェクト
     * @return プレイヤーが勝った場合はtrue、負けた場合はfalse
     */
    private boolean playOneRound(Scanner scanner) {
        log.info("じゃんけんの手を選んでください:");
        log.info("1: グー");
        log.info("2: チョキ");
        log.info("3: パー");

        var playerChoice = getPlayerChoice(scanner);

        // コンピュータの手をランダムに選択 (1: グー, 2: チョキ, 3: パー)
        var computerChoice = random.nextInt(3) + 1;

        var hands = new String[]{"", "グー", "チョキ", "パー"};
        log.info("あなた: {}", hands[playerChoice]);
        log.info("相手: {}", hands[computerChoice]);

        // 勝敗判定
        if (playerChoice == computerChoice) {
            log.info("あいこです。もう一度！");
            return playOneRound(scanner); // 再帰的に再プレイ
        } else return (playerChoice == 1 && computerChoice == 2) ||
                (playerChoice == 2 && computerChoice == 3) ||
                (playerChoice == 3 && computerChoice == 1); // プレイヤーの勝ち
    }

    /**
     * プレイヤーの手の選択を取得します。
     * 有効な選択（1-3）が入力されるまで繰り返し尋ねます。
     *
     * @param scanner 入力を受け付けるScannerオブジェクト
     * @return プレイヤーの選択（1: グー, 2: チョキ, 3: パー）
     */
    private int getPlayerChoice(Scanner scanner) {
        var playerChoice = 0;
        while (playerChoice < 1 || playerChoice > 3) {
            try {
                playerChoice = Integer.parseInt(scanner.nextLine());
                if (playerChoice < 1 || playerChoice > 3) {
                    log.warn("1から3の数字を入力してください。");
                }
            } catch (NumberFormatException e) {
                log.warn("数値を入力してください。");
            }
        }
        return playerChoice;
    }
}
