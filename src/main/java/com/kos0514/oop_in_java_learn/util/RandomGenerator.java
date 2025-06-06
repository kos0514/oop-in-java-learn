package com.kos0514.oop_in_java_learn.util;

/**
 * ランダム値生成のための抽象インターフェース
 */
public interface RandomGenerator {
    /**
     * 0から指定された上限値（排他的）までの範囲でランダムな整数を生成します
     *
     * @param bound 上限値（排他的）
     * @return 0（包括的）から指定された上限値（排他的）までのランダムな整数
     */
    int nextInt(int bound);
    
    /**
     * 特定のシードを使用してランダムジェネレーターを初期化します
     *
     * @param seed 初期化に使用するシード値
     */
    void setSeed(long seed);
}