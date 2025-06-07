package com.kos0514.oop_in_java_learn.service;

import com.kos0514.oop_in_java_learn.factory.TransmigratorFactory;
import com.kos0514.oop_in_java_learn.io.SystemInputProvider;
import com.kos0514.oop_in_java_learn.io.UserInputProvider;
import com.kos0514.oop_in_java_learn.model.Transmigrator;
import com.kos0514.oop_in_java_learn.model.value.Age;
import com.kos0514.oop_in_java_learn.model.value.SoulName;
import com.kos0514.oop_in_java_learn.service.race.SelectRaceService;
import com.kos0514.oop_in_java_learn.service.world.SelectWorldService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.kos0514.oop_in_java_learn.util.log.LoggingUtils.info;
import static com.kos0514.oop_in_java_learn.util.log.LoggingUtils.printSeparator;
import static com.kos0514.oop_in_java_learn.util.log.LoggingUtils.warn;

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
        try (UserInputProvider inputProvider = new SystemInputProvider()) {
            startTransmigrationProcess(inputProvider);
        } catch (Exception e) {
            warn("転生プロセス中にエラーが発生しました: " + e.getMessage());
            throw new RuntimeException("転生プロセスが失敗しました", e);
        }
    }

    /**
     * 転生プロセスを開始します。
     * ユーザーからの入力を受け付け、転生者の情報、転生先の世界、
     * 獲得するスキルなどを設定し、転生処理を完了します。
     *
     * @param inputProvider 入力を受け付けるUserInputProviderオブジェクト
     */
    void startTransmigrationProcess(UserInputProvider inputProvider) {
        info("転生プロセスを開始します...");
        printSeparator();
        info("    異世界転生トランスミッションサービス");
        printSeparator();

        // 転生者の基本情報を入力
        var soulName = collectSoulName(inputProvider);
        var age = collectAge(inputProvider);

        // 世界選択
        var selectedWorld = selectWorldService.selectWorld(inputProvider);

        // 種族選択
        var selectedRace = selectRaceService.selectRace(inputProvider);

        // ファクトリーメソッドで転生者を作成
        var transmigrator = transmigratorFactory.create(soulName, age, selectedWorld, selectedRace);

        // 転生の実行
        executeTransmigration(transmigrator);

        // 基礎ステータスの表示
        transmigrator.getPlayableStatuses().showStatus();
    }

    /**
     * 転生者の名前を収集します。
     *
     * @param inputProvider 入力を受け付けるUserInputProviderオブジェクト
     * @return 名前の値オブジェクト
     */
    private SoulName collectSoulName(UserInputProvider inputProvider) {
        SoulName soulName = null;
        while (soulName == null) {
            try {
                info("転生者の名前を入力してください:");
                var name = inputProvider.readLine();
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
     * @param inputProvider 入力を受け付けるUserInputProviderオブジェクト
     * @return 年齢の値オブジェクト
     */
    private Age collectAge(UserInputProvider inputProvider) {
        Age age = null;
        while (age == null) {
            try {
                info("転生者の年齢を入力してください (1～120の整数):");
                age = Age.fromString(inputProvider.readLine());
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
