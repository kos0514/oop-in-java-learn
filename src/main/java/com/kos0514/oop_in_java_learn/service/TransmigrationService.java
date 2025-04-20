package com.kos0514.oop_in_java_learn.service;

import com.kos0514.oop_in_java_learn.model.world.World;
import com.kos0514.oop_in_java_learn.model.value.Age;
import com.kos0514.oop_in_java_learn.model.value.SoulName;
import com.kos0514.oop_in_java_learn.repository.WorldRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.kos0514.oop_in_java_learn.model.Transmigrator;
import com.kos0514.oop_in_java_learn.factory.TransmigratorFactory;

import lombok.RequiredArgsConstructor;

import java.util.Scanner;

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
@Slf4j
@Service
@RequiredArgsConstructor
public class TransmigrationService {

    private final TransmigratorFactory transmigratorFactory;

    private final WorldRepository worldRepository;

    /**
     * 転生プロセスを開始します。
     * ユーザーからの入力を受け付け、転生者の情報、転生先の世界、
     * 獲得するスキルなどを設定し、転生処理を完了します。
     */
    public void startTransmigrationProcess() {
        log.info("転生プロセスを開始します...");
        printSeparator();
        log.info("    異世界転生トランスミッションサービス");
        printSeparator();

        try (Scanner scanner = new Scanner(System.in)) {
            // 転生者の基本情報を入力
            var soulName = collectSoulName(scanner);
            var age = collectAge(scanner);

            // 世界選択
            var selectedWorld = selectWorld(scanner);

            // ファクトリーメソッドで転生者を作成
            var transmigrator = transmigratorFactory.createTransmigrator(soulName, age, selectedWorld);

            // 転生の実行
            executeTransmigration(transmigrator);

            log.info("転生が完了しました！");
            log.info("名前: {}", transmigrator.getSoulName().getName());
            log.info("転生先: {}", transmigrator.getWorld().getName());
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
                log.info("転生者の名前を入力してください:");
                var name = scanner.nextLine();
                soulName = SoulName.of(name);
            } catch (IllegalArgumentException e) {
                log.warn(e.getMessage());
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
                log.info("転生者の年齢を入力してください (1～120の整数):");
                age = Age.fromString(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                log.warn(e.getMessage());
            }
        }
        return age;
    }

    /**
     * 転生先の世界を選択します。
     *
     * @param scanner 入力を受け付けるScannerオブジェクト
     * @return 選択された世界
     */
    private World selectWorld(Scanner scanner) {
        var availableWorlds = worldRepository.getAvailableWorlds();

        log.info("【転生先世界の選択】");

        // 利用可能な世界の一覧を表示
        for (int i = 0; i < availableWorlds.size(); i++) {
            World world = availableWorlds.get(i);
            log.info("{}. {}", i + 1, world.getName());
            log.info("   {}", world.getDescription());
            log.info("");
        }

        World selectedWorld = null;
        while (selectedWorld == null) {
            try {
                log.info("番号を入力してください (1-{}):", availableWorlds.size());
                var selection = Integer.parseInt(scanner.nextLine());

                if (selection >= 1 && selection <= availableWorlds.size()) {
                    selectedWorld = availableWorlds.get(selection - 1);
                    log.info("");
                    printSeparator();
                    log.info("{}に転生が決定しました！", selectedWorld.getName());
                    log.info("【世界の説明】");
                    log.info("{}", selectedWorld.getDescription());
                    printSeparator();
                    log.info("");
                } else {
                    log.warn("有効な番号を入力してください (1-{})。", availableWorlds.size());
                }
            } catch (NumberFormatException e) {
                log.warn("数値を入力してください。");
            }
        }

        return selectedWorld;
    }

    /**
     * 転生処理を実行します。
     *
     * @param transmigrator 転生者オブジェクト
     */
    private void executeTransmigration(Transmigrator transmigrator) {
        var name = transmigrator.getSoulName().getName();
        var worldName = transmigrator.getWorld().getName();

        log.info("{}さんの転生を実行しています...", name);
        log.info("転生完了: {}さんは、{}に転生しました！", name, worldName);
    }

    /**
     * 転生プロセスのセパレーターを表示します。
     */
    private void printSeparator() {
        log.info("======================================");
    }

}
