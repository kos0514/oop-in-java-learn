package com.kos0514.oop_in_java_learn.util;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * java.util.Randomを使用したRandomGeneratorの標準実装
 */
@Component
public class DefaultRandomGenerator implements RandomGenerator {
    private final Random random;

    public DefaultRandomGenerator() {
        this.random = new Random();
    }

    @Override
    public int nextInt(int bound) {
        return random.nextInt(bound);
    }

    @Override
    public void setSeed(long seed) {
        random.setSeed(seed);
    }
}