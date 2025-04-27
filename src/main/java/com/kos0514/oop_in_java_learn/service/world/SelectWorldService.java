package com.kos0514.oop_in_java_learn.service.world;

import com.kos0514.oop_in_java_learn.model.world.World;
import com.kos0514.oop_in_java_learn.repository.WorldRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Scanner;

/**
 * 転生先の世界選択を管理するサービスクラス。
 * 利用可能な世界の一覧を表示し、ユーザーに選択させます。
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SelectWorldService {

    private final WorldRepository worldRepository;

    /**
     * 転生先の世界を選択します。
     *
     * @param scanner 入力を受け付けるScannerオブジェクト
     * @return 選択された世界
     */
    public World selectWorld(Scanner scanner) {
        var availableWorlds = worldRepository.getAvailableWorlds();

        log.info("【転生先世界の選択】");

        // 利用可能な世界の一覧を表示
        for (var i = 0; i < availableWorlds.size(); i++) {
            var world = availableWorlds.get(i);
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
     * 転生プロセスのセパレーターを表示します。
     */
    private void printSeparator() {
        log.info("======================================");
    }
}