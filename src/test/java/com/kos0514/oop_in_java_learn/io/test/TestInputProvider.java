package com.kos0514.oop_in_java_learn.io.test;

import com.kos0514.oop_in_java_learn.io.UserInputProvider;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * テスト用の入力プロバイダー
 * 事前に設定した入力値を順番に返します
 */
public class TestInputProvider implements UserInputProvider {
    private final Queue<String> inputLines = new LinkedList<>();

    /**
     * テスト用の入力値を追加します
     *
     * @param line 入力値
     * @return this (メソッドチェーン用)
     */
    public TestInputProvider addInput(String line) {
        inputLines.add(line);
        return this;
    }

    /**
     * 複数の入力値を一度に追加します
     *
     * @param lines 入力値の配列
     * @return this (メソッドチェーン用)
     */
    public TestInputProvider addInputs(String... lines) {
        Collections.addAll(inputLines, lines);
        return this;
    }

    @Override
    public String readLine() {
        if (inputLines.isEmpty()) {
            throw new IllegalStateException("テスト用の入力値が不足しています");
        }
        return inputLines.poll();
    }

    @Override
    public void close() {
        // 特に何もしない
    }
}