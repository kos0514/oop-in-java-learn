package com.kos0514.oop_in_java_learn.io;

import com.kos0514.oop_in_java_learn.enums.RockPaperScissors;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * ランダムなコンピュータの手の選択を提供する実装
 */
@Component
public class RandomComputerChoiceProvider implements ComputerChoiceProvider {
    private final Random random = new Random();

    @Override
    public RockPaperScissors chooseHand() {
        return RockPaperScissors.fromValue(random.nextInt(3) + 1);
    }
}
