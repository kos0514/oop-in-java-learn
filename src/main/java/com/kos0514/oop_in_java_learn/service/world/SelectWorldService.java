package com.kos0514.oop_in_java_learn.service.world;

import com.kos0514.oop_in_java_learn.io.UserInputProvider;
import com.kos0514.oop_in_java_learn.model.world.World;
import com.kos0514.oop_in_java_learn.repository.WorldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.kos0514.oop_in_java_learn.util.LoggingUtils.endPrintSeparator;
import static com.kos0514.oop_in_java_learn.util.LoggingUtils.info;
import static com.kos0514.oop_in_java_learn.util.LoggingUtils.startPrintSeparator;
import static com.kos0514.oop_in_java_learn.util.LoggingUtils.warn;
import static com.kos0514.oop_in_java_learn.util.LoggingUtils.warnInputNumber;

/**
 * 転生先の世界選択を管理するサービスクラス。
 * 利用可能な世界の一覧を表示し、ユーザーに選択させます。
 */
@Service
@RequiredArgsConstructor
public class SelectWorldService {

    private final WorldRepository worldRepository;

    /**
     * 転生先の世界を選択します。
     *
     * @param inputProvider 入力を受け付けるUserInputProviderオブジェクト
     * @return 選択された世界
     */
    public World selectWorld(UserInputProvider inputProvider) {
        var availableWorlds = worldRepository.getAvailableWorlds();

        startPrintSeparator();
        info("【転生先世界の選択】");

        // 利用可能な世界の一覧を表示
        for (var i = 0; i < availableWorlds.size(); i++) {
            var world = availableWorlds.get(i);
            info("{}. {}", i + 1, world.getName());
            info("   {}", world.getDescription());
        }
        endPrintSeparator();

        World selectedWorld = null;
        while (selectedWorld == null) {
            try {
                info("番号を入力してください (1-{}):", availableWorlds.size());
                var selection = Integer.parseInt(inputProvider.readLine());

                if (selection < 1 || selection > availableWorlds.size()) {
                    warn("有効な番号を入力してください (1-{})。", availableWorlds.size());
                    continue; // 条件に合わない場合は次のループへ
                }

                selectedWorld = availableWorlds.get(selection - 1);
                startPrintSeparator();
                info("{}に転生が決定しました！", selectedWorld.getName());
                info("【世界の説明】");
                info("{}", selectedWorld.getDescription());
                endPrintSeparator();
            } catch (NumberFormatException e) {
                warnInputNumber();
            }
        }

        return selectedWorld;
    }

}
