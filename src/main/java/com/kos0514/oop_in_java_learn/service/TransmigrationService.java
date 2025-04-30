package com.kos0514.oop_in_java_learn.service;

import com.kos0514.oop_in_java_learn.model.value.Age;
import com.kos0514.oop_in_java_learn.model.value.SoulName;
import com.kos0514.oop_in_java_learn.service.race.SelectRaceService;
import com.kos0514.oop_in_java_learn.service.world.SelectWorldService;
import org.springframework.stereotype.Service;
import com.kos0514.oop_in_java_learn.model.Transmigrator;
import com.kos0514.oop_in_java_learn.factory.TransmigratorFactory;

import lombok.RequiredArgsConstructor;

import java.util.Scanner;

import static com.kos0514.oop_in_java_learn.util.LoggingUtils.*;

/**
 * 異世界転生プロセスを管理するサービスクラス。
 * ユーザーの入力を受け付け、転生先の世界選択、スキル選択などの
 * 転生に関する一連の処理を実行します。
 *
 * <p>このサービスはSpringのDIコンテナによって管理され、
 * 必要に応じて他のサービスやリポジトリとの連携を行います。</p>
 *
 * @author kos0514
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class TransmigrationService {

    private final TransmigratorFactory transmigratorFactory;
    private final SelectWorldService selectWorldService;
    private final SelectRaceService selectRaceService;

    /**
     * 転生プロセスを開始します。
     * ユーザーからの入力を受け付け、転生者の情報、転生先の世界、
     * 獲得するスキルなどを設定し、転生処理を完了します。
     */
    public void startTransmigrationProcess() {
        info("転生プロセスを開始します...");
        printSeparator();
        info("    異世界転生トランスミッションサービス");
        printSeparator();

        try (Scanner scanner = new Scanner(System.in)) {
            // 転生者の基本情報を入力
            var soulName = collectSoulName(scanner);
            var age = collectAge(scanner);

            // 世界選択
            var selectedWorld = selectWorldService.selectWorld(scanner);

            // 種族選択
            var selectedRace = selectRaceService.selectRace(scanner);

            // ファクトリーメソッドで転生者を作成
            var transmigrator = transmigratorFactory.create(soulName, age, selectedWorld, selectedRace);

            // 転生の実行
            executeTransmigration(transmigrator);

            // 基礎ステータスの表示
            transmigrator.getPlayableStatuses().showStatus();
        }
    }

    /**
     * 転生者の名前を収集します。
     *
     * @param scanner 入力を受け付けるScannerオブジェクト
     * @return 名前の値オブジェクト
     */
    private SoulName collectSoulName(Scanner scanner) {
        SoulName soulName = null;
        while (soulName == null) {
            try {
                info("転生者の名前を入力してください:");
                var name = scanner.nextLine();
                soulName = SoulName.of(name);
            } catch (IllegalArgumentException e) {
                warn(e.getMessage());
            }
        }
        return soulName;
    }

    /**
     * 転生者の年齢を収集します。
     *
     * @param scanner 入力を受け付けるScannerオブジェクト
     * @return 年齢の値オブジェクト
     */
    private Age collectAge(Scanner scanner) {
        Age age = null;
        while (age == null) {
            try {
                info("転生者の年齢を入力してください (1～120の整数):");
                age = Age.fromString(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                warn(e.getMessage());
            }
        }
        return age;
    }


    /**
     * 転生処理を実行します。
     *
     * @param transmigrator 転生者オブジェクト
     */
    private void executeTransmigration(Transmigrator transmigrator) {
        var name = transmigrator.getSoulName().getName();
        var worldName = transmigrator.getWorld().getName();
        var raceName = transmigrator.getRace().getJapaneseName();

        info("{}さんの転生を実行しています...", name);
        info("転生完了: 「{}」さんは、「{}」の「{}」種族に転生しました！", name, worldName, raceName);
    }
}
