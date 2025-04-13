package com.kos0514.oop_in_java_learn.service;

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
        System.out.println("転生プロセスを開始します...");

        try (Scanner scanner = new Scanner(System.in)) {
            // 転生者の基本情報を入力
            var transmigrator = collectTransmigratorInfo(scanner);

            // 転生先の世界を選択

            // スキルを選択

            // 転生の実行
            executeTransmigration(transmigrator);

            System.out.println("転生が完了しました！");
            System.out.println("名前: " + transmigrator.getSoulName());
        }
    }

    /**
     * 転生者の基本情報を収集します。
     *
     * @param scanner ユーザー入力を読み取るスキャナー
     * @return 作成された転生者オブジェクト
     */
    private Transmigrator collectTransmigratorInfo(Scanner scanner) {
        System.out.println("転生者の名前（名）を入力してください:");
        String firstName = scanner.nextLine();

        System.out.println("転生者の名前（姓）を入力してください:");
        String lastName = scanner.nextLine();

        System.out.println("転生者の年齢を入力してください:");
        int age = Integer.parseInt(scanner.nextLine());

        return transmigratorFactory.createTransmigrator(firstName, lastName, age);
    }

    /**
     * 転生処理を実行します。
     *
     * @param transmigrator 転生者オブジェクト
     */
    private void executeTransmigration(Transmigrator transmigrator) {
        System.out.println(transmigrator.getSoulName() + "さんの転生を実行しています...");

        // ここに転生処理の実装を追加
    }
}