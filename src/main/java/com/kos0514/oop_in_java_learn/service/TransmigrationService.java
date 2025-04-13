package com.kos0514.oop_in_java_learn.service;

import com.kos0514.oop_in_java_learn.model.value.Age;
import com.kos0514.oop_in_java_learn.model.value.SoulName;
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

    /**
     * 転生プロセスを開始します。
     * ユーザーからの入力を受け付け、転生者の情報、転生先の世界、
     * 獲得するスキルなどを設定し、転生処理を完了します。
     */
    public void startTransmigrationProcess() {
        log.info("転生プロセスを開始します...");

        try (Scanner scanner = new Scanner(System.in)) {
            // 転生者の基本情報を入力
            var soulName = collectSoulName(scanner);
            var age = collectAge(scanner);

            // ファクトリーメソッドで転生者を作成
            var transmigrator = transmigratorFactory.createTransmigrator(soulName, age);

            // 転生の実行
            executeTransmigration(transmigrator);

            log.info("転生が完了しました！");
            log.info("名前: {}", transmigrator.getSoulName().getFullName());
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
                log.info("転生者の名前（名）を入力してください:");
                var firstName = scanner.nextLine();

                log.info("転生者の名前（姓）を入力してください:");
                var lastName = scanner.nextLine();

                soulName = SoulName.of(firstName, lastName);
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
     * 転生処理を実行します。
     *
     * @param transmigrator 転生者オブジェクト
     */
    private void executeTransmigration(Transmigrator transmigrator) {
        log.info(transmigrator.getSoulName().getFullName() + "さんの転生を実行しています...");
        log.info("転生完了: " + transmigrator.getSoulName().getFullName() + "さんは転生しました！");
    }
}