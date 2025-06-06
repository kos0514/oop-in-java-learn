package com.kos0514.oop_in_java_learn.io;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * ランダムなコンピュータの手の選択を提供する実装
 */
@Component
public class RandomComputerChoiceProvider implements ComputerChoiceProvider {
    private final Random random = new Random();
    
    @Override
    public int chooseHand() {
        return random.nextInt(3) + 1;
    }
}