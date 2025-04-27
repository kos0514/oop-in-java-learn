package com.kos0514.oop_in_java_learn.service.race;

import com.kos0514.oop_in_java_learn.entity.generated.Race;
import com.kos0514.oop_in_java_learn.enums.RaceRarity;
import com.kos0514.oop_in_java_learn.mapper.RaceMapper;
import com.kos0514.oop_in_java_learn.service.game.RockPaperScissorsGame;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;
import java.util.function.IntFunction;

import static com.kos0514.oop_in_java_learn.enums.RaceRarity.STANDARD;
import static com.kos0514.oop_in_java_learn.enums.RaceRarity.UNIQUE;
import static com.kos0514.oop_in_java_learn.enums.RaceRarity.LEGENDARY;
import static com.kos0514.oop_in_java_learn.enums.RaceRarity.SECRET;

/**
 * 種族選択とじゃんけんゲームを管理するサービスクラス。
 * 種族の希少度に応じたじゃんけんゲームを実施し、
 * プレイヤーが選択可能な種族を決定します。
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SelectRaceService {

    private final RaceMapper raceMapper;
    private final RockPaperScissorsGame rockPaperScissorsGame;

    /**
     * じゃんけんゲームを実行して種族を選択します。
     *
     * @param scanner 入力を受け付けるScannerオブジェクト
     * @return 選択された種族
     */
    public Race selectRace(Scanner scanner) {
        log.info("【種族選択】");
        log.info("あなたの種族を決定します。");
        log.info("希少な種族を選ぶには、じゃんけんに勝つ必要があります。");

        // じゃんけんの結果に基づいて利用可能な最大レア度を決定
        var maxRarity = playRockPaperScissorsForRace(scanner);

        // 利用可能な種族のリストを取得
        var availableRaces = raceMapper.selectUpToRarity(maxRarity);

        // 選択可能な種族を表示
        displayAvailableRaces(availableRaces);

        // プレイヤーに種族を選択させる
        return promptRaceSelection(scanner, availableRaces);
    }

    /**
     * 種族選択のためのじゃんけんゲームを実行します。
     * 勝利回数に応じて選択可能な種族の希少度を決定します。
     *
     * @param scanner 入力を受け付けるScannerオブジェクト
     * @return 選択可能な最大レア度
     */
    private RaceRarity playRockPaperScissorsForRace(Scanner scanner) {
        // ゲームの説明文を作成
        var gameTitle = "じゃんけんゲーム";
        var description = String.format(
                """
                        レア種族を選ぶには、じゃんけんに勝つ必要があります。
                        %s: 通常選択可能
                        %s: 1回勝利で選択可能
                        %s: 2回連続勝利で選択可能
                        %s: 3回連続勝利で選択可能""",
                STANDARD.name(),
                UNIQUE.name(),
                LEGENDARY.name(),
                SECRET.name()
        );

        // 勝利回数からRaceRarityへの変換関数
        IntFunction<RaceRarity> winsToRarity = this::getRaceRarityByWins;

        // じゃんけんゲームを実行し、結果をRaceRarityに変換
        return rockPaperScissorsGame.playGameAndConvertResult(
                scanner, 
                3, // 最大3回まで
                gameTitle,
                description,
                winsToRarity
        );
    }

    /**
     * 勝利回数に基づいて選択可能な種族の希少度を決定します。
     *
     * @param wins 勝利回数
     * @return 対応する種族の希少度
     */
    private RaceRarity getRaceRarityByWins(int wins) {
        return switch (wins) {
            case 1 -> logAndYield(UNIQUE);
            case 2 -> logAndYield(LEGENDARY);
            case 3 -> logAndYield(SECRET);
            default -> logAndYield(STANDARD);
        };
    }

    /**
     * ログを出力し、指定された RaceRarity を返すメソッド。
     *
     * @param rarity 出力したい RaceRarity
     * @return rarity
     */
    private RaceRarity logAndYield(RaceRarity rarity) {
        if (rarity == STANDARD) {
            log.info("{}の種族のみ選択可能です。", rarity.name());
        } else {
            log.info("{}までの種族が選択可能になりました！", rarity.name());
        }
        return rarity;
    }

    /**
     * 選択可能な種族リストを表示します。
     *
     * @param races 表示する種族リスト
     */
    private void displayAvailableRaces(List<Race> races) {
        log.info("【選択可能な種族】");
        for (var i = 0; i < races.size(); i++) {
            var race = races.get(i);
            log.info("{}. {} ({})", i + 1, race.getJapaneseName(), race.getRarity());
            log.info("   特殊能力: {}", race.getSpecialAbility());
            log.info("   {}", race.getDescription());
            log.info("");
        }
    }

    /**
     * プレイヤーに種族を選択させるプロンプトを表示し、選択を処理します。
     * 有効な選択が行われるまで繰り返し尋ねます。
     *
     * @param scanner 入力を受け付けるScannerオブジェクト
     * @param availableRaces 選択可能な種族リスト
     * @return 選択された種族
     */
    private Race promptRaceSelection(Scanner scanner, List<Race> availableRaces) {
        Race selectedRace = null;
        while (selectedRace == null) {
            try {
                log.info("番号を入力してください (1-{}):", availableRaces.size());
                var selection = Integer.parseInt(scanner.nextLine());

                if (selection >= 1 && selection <= availableRaces.size()) {
                    selectedRace = availableRaces.get(selection - 1);
                    displaySelectedRace(selectedRace);
                } else {
                    log.warn("有効な番号を入力してください (1-{})。", availableRaces.size());
                }
            } catch (NumberFormatException e) {
                log.warn("数値を入力してください。");
            }
        }

        return selectedRace;
    }

    /**
     * 選択された種族の情報を表示します。
     *
     * @param race 表示する種族
     */
    private void displaySelectedRace(Race race) {
        log.info("");
        printSeparator();
        log.info("{}種族に転生が決定しました！", race.getJapaneseName());
        log.info("【種族の説明】");
        log.info("{}", race.getDescription());
        log.info("【特殊能力】");
        log.info("{}", race.getSpecialAbility());
        printSeparator();
        log.info("");
    }

    /**
     * 転生プロセスのセパレーターを表示します。
     */
    private void printSeparator() {
        log.info("======================================");
    }
}
